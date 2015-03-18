package me.veryyoung.movie.controller;

import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.service.DoubanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by veryyoung on 2015/3/18.
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {

    @Autowired
    private DoubanService doubanService;

    @RequestMapping("/{id}")
    @ResponseBody
    public Subject getSubject(@PathVariable(value = "id") String id) {
        return doubanService.find(id);
    }

}
