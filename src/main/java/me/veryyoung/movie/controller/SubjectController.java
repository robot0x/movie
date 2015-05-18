package me.veryyoung.movie.controller;

import me.veryyoung.movie.dao.CommentDao;
import me.veryyoung.movie.entity.Comment;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.rest.PageInfo;
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
        PageInfo<Comment> pageInfo = new PageInfo<>(pageNo, 5);
        pageInfo.setTotalRows(subject.getCommentCount());
        if (subject.getCommentCount() > 0) {
            pageInfo.setResultList(commentDao.listBySubjectId(id, pageInfo.getStartRow(), 5));
        }
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }


}
