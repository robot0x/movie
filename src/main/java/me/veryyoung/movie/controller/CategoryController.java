package me.veryyoung.movie.controller;

import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.rest.PageInfo;
import me.veryyoung.movie.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by veryyoung on 2015/5/5.
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping({"/index", ""})
    public String index() {
        return "/category/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Subject> list(int pageNo, String year, String place, String type, String sort) {
        return subjectService.listBySearch(pageNo, 6, year, place, type, sort);
    }

}
