package me.veryyoung.movie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by veryyoung on 2015/3/18.
 */
public abstract class BaseService {

    protected Logger logger = null;

    public BaseService() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }
}
