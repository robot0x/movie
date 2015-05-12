package me.veryyoung.movie.controller;

import me.veryyoung.movie.service.DoubanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by veryyoung on 2015/3/18.
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {

    @Autowired
    private DoubanService doubanService;

    @RequestMapping("/{id}")
    public ModelAndView getSubject(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/subject/details");
        modelAndView.addObject("subject", doubanService.find(id));
        return modelAndView;
    }

    @RequestMapping("/{id}/comments")
    public ModelAndView getComments(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/subject/comments");
        modelAndView.addObject("subject", doubanService.find(id));
        return modelAndView;
    }

    @RequestMapping("/comment")
    public String getComment() {
        return "/subject/comment";
    }

}
