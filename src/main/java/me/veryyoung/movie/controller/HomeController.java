package me.veryyoung.movie.controller;

import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.User;
import me.veryyoung.movie.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by veryyoung on 2015/3/2.
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectDao subjectDao;


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("subjects", subjectDao.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("/login");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(User user) {
        ModelAndView modelAndView = new ModelAndView("/register");

        logger.info("user:{}", user);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userService.create(user);

        return modelAndView;
    }

}
