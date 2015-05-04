package me.veryyoung.movie.controller;

import me.veryyoung.movie.security.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by veryyoung on 2015/5/4.
 */
@Controller
@RequestMapping("/account")
@LoginRequired
public class AccountController extends BaseController {


    @RequestMapping({"/index", ""})
    public String getAccount() {
        return "/account";
    }
}
