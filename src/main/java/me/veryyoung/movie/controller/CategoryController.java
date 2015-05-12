package me.veryyoung.movie.controller;

import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.rest.PageInfo;
import me.veryyoung.movie.service.DoubanService;
import me.veryyoung.movie.service.SubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by veryyoung on 2015/5/5.
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DoubanService doubanService;

    @RequestMapping({"/index", ""})
    public ModelAndView index(String key) {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        key = key.trim();
        if (StringUtils.isNotEmpty(key)) {
            modelAndView.addObject("key", key);
        }
        doubanService.saveBySearch(key);
        return modelAndView;
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Subject> list(int pageNo, String year, String place, String type, String sort, String key) {
        return subjectService.listBySearch(pageNo, 6, year, place, type, sort, key);
    }

}
