package me.veryyoung.movie.controller;

import me.veryyoung.movie.dao.CommentDao;
import me.veryyoung.movie.security.LoginRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by veryyoung on 2015/5/4.
 */
@Controller
@RequestMapping("/user")
@LoginRequired
public class UserController extends BaseController {

    @Autowired
    private CommentDao commentDao;

    @RequestMapping("/{id}")
    public String getAccount(@PathVariable(value = "id") String id) {
        return "/user";
    }
}
