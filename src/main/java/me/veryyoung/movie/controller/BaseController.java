package me.veryyoung.movie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by veryyoung on 2015/3/2.
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected Logger logger = null;

    public BaseController() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }
}
