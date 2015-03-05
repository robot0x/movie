package me.veryyoung.movie.dao;


import me.veryyoung.movie.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by veryyoung on 2015/3/3.
 */

@Repository
@Transactional
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

}
