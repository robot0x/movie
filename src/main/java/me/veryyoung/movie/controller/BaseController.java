package me.veryyoung.movie.controller;

import me.veryyoung.movie.validator.ValidatorWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by veryyoung on 2015/3/2.
 */
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    protected Validator validator = factory.getValidator();
    private ValidatorWrapper validatorWrapper;


    protected Logger logger = null;

    public BaseController() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }

    protected ValidatorWrapper getValidatorWrapper() {
        if (validatorWrapper == null) {
            validatorWrapper = new ValidatorWrapper(validator);
        }
        return validatorWrapper;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
