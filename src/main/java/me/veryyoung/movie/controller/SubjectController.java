package me.veryyoung.movie.controller;

import me.veryyoung.movie.dao.CommentDao;
import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.Comment;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.qiniu.QiniuUtils;
import me.veryyoung.movie.rest.PageInfo;
import me.veryyoung.movie.security.AdminRequired;
import me.veryyoung.movie.service.DoubanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by veryyoung on 2015/3/18.
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {

    @Autowired
    private DoubanService doubanService;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private SubjectDao subjectDao;

    @RequestMapping("/{id}")
    public ModelAndView getSubject(@PathVariable(value = "id") String id, String error) {
        ModelAndView modelAndView = new ModelAndView("/subject/details");
        Subject subject = doubanService.find(id);
        if (null == subject) {
            return new ModelAndView("/misc/404");
        }
        modelAndView.addObject("subject", subject);
        if (subject.getCommentCount() > 0) {
            modelAndView.addObject("comments", commentDao.listBySubjectId(id, 0, 5));
        }
        if (StringUtils.isNotEmpty(error)) {
            modelAndView.addObject("error", error);
        }
        return modelAndView;
    }

    @RequestMapping("/{id}/comments")
    public ModelAndView getComments(@PathVariable(value = "id") String id, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        ModelAndView modelAndView = new ModelAndView("/subject/comments");
        Subject subject = doubanService.find(id);
        modelAndView.addObject("subject", subject);
        if (null == subject) {
            return new ModelAndView("/misc/404");
        }
        PageInfo<Comment> pageInfo = new PageInfo<>(pageNo, 5);
        pageInfo.setTotalRows(subject.getCommentCount());
        if (subject.getCommentCount() > 0) {
            pageInfo.setResultList(commentDao.listBySubjectId(id, pageInfo.getStartRow(), 5));
        }
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }


    @RequestMapping("/{id}/edit")
    @AdminRequired
    public ModelAndView getEdit(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/subject/edit");
        Subject subject = doubanService.find(id);
        modelAndView.addObject("subject", subject);
        if (null == subject) {
            return new ModelAndView("/misc/404");
        }
        return modelAndView;
    }

    @RequestMapping("/{id}/update")
    @AdminRequired
    public String getUpdate(@PathVariable(value = "id") String id, Subject subject) {
        Subject storedSubject = doubanService.find(id);
        if (null == storedSubject) {
            return "/misc/404";
        }
        storedSubject.setDirectors(subject.getDirectors());
        storedSubject.setCasts(subject.getCasts());
        storedSubject.setWriters(subject.getWriters());
        storedSubject.setGenres(subject.getGenres());
        storedSubject.setCountries(subject.getCountries());
        storedSubject.setLanguages(subject.getLanguages());
        storedSubject.setDurations(subject.getDurations());
        storedSubject.setOriginalTitle(subject.getOriginalTitle());
        storedSubject.setSummary(subject.getSummary());
        subjectDao.update(storedSubject);
        return "redirect:/subject/" + id;
    }

    @RequestMapping("/{id}/delete")
    @AdminRequired
    public String getDelete(@PathVariable(value = "id") String id) {
        Subject subject = doubanService.find(id);
        if (null == subject) {
            return "/misc/404";
        } else {
            subjectDao.delete(subject);
            QiniuUtils.deleteFromQiniu(id);
        }
        return "redirect:/";
    }

}
