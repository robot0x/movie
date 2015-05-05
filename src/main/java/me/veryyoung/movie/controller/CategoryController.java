package me.veryyoung.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by veryyoung on 2015/5/5.
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @RequestMapping({"/index", ""})
    public String list() {
        return "/category/list";
    }
}
