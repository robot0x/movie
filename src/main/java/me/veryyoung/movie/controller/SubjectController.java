package me.veryyoung.movie.controller;

import me.veryyoung.movie.dao.CommentDao;
import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.Comment;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.service.DoubanService;
import me.veryyoung.movie.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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
    public ModelAndView getSubject(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/subject/details");
        modelAndView.addObject("count", commentDao.countBySubjectId(id));
        modelAndView.addObject("subject", doubanService.find(id));
        return modelAndView;
    }

    @RequestMapping("/{id}/comments")
    public ModelAndView getComments(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/subject/comments");
        modelAndView.addObject("subject", doubanService.find(id));
        modelAndView.addObject("count", commentDao.countBySubjectId(id));
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    public String getComments(@PathVariable(value = "id") String id, Comment comment) {
        comment.setSubjectId(id);
        comment.setUserId(ContextUtils.getUserId(request));
        comment.setSubmitDate(new Date());
        commentDao.create(comment);
        Subject subject = doubanService.find(id);
        subject.setTotalRating(subject.getRatingCount() + comment.getRating());
        subject.setRatingCount(subject.getRatingCount() + 1);
        subjectDao.update(subject);
        return "redirect:/subject/" + id;
    }


}
