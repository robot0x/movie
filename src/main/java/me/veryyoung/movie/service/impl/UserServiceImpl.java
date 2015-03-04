package me.veryyoung.movie.service.impl;

import me.veryyoung.movie.dao.UserDao;
import me.veryyoung.movie.entity.User;
import me.veryyoung.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by veryyoung on 2015/3/3.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User user) {
        userDao.create(user);
    }
}
