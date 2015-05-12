package me.veryyoung.movie.utils;

import me.veryyoung.movie.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * for jstl
 * <p/>
 * Created by veryyoung on 2015/5/12.
 */
@Service
public class ApplicationUtils {

    @Autowired
    private UserDao userDao;

    public String findUserNameById(String id) {
        return userDao.find(id).getUserName();
    }

}
