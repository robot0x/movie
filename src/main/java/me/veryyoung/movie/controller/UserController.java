package me.veryyoung.movie.controller;

import me.veryyoung.movie.dao.CommentDao;
import me.veryyoung.movie.dao.UserDao;
import me.veryyoung.movie.entity.Comment;
import me.veryyoung.movie.entity.User;
import me.veryyoung.movie.rest.PageInfo;
import me.veryyoung.movie.security.LoginRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by veryyoung on 2015/5/4.
 */
@Controller
@RequestMapping("/user")
@LoginRequired
public class UserController extends BaseController {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/{id}")
    public ModelAndView getAccount(@PathVariable(value = "id") String id, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        ModelAndView modelAndView = new ModelAndView("/user");
        User user = userDao.find(id);
        PageInfo<Comment> pageInfo = new PageInfo<>(pageNo, 5);
        modelAndView.addObject("user", user);
        int commentCount = commentDao.countByUserId(id);
        modelAndView.addObject("commentCount", commentCount);
        if (commentCount > 0) {
            pageInfo.setResultList(commentDao.listByUserId(id, pageInfo.getStartRow(), 5));
            pageInfo.setTotalRows(commentDao.countByUserId(id));
        }
        modelAndView.addObject("pageInfo", pageInfo);

        return modelAndView;
    }
}
