package me.veryyoung.movie.service;

import me.veryyoung.movie.entity.User;

import java.util.List;

/**
 * Created by veryyoung on 2015/3/3.
 */
public interface UserService {

    public void create(User user);

    public List<User> findAll();
}
