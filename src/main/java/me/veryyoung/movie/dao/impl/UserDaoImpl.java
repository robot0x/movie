package me.veryyoung.movie.dao.impl;

import me.veryyoung.movie.dao.UserDao;
import me.veryyoung.movie.entity.User;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Created by veryyoung on 2015/3/3.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Override
    public void create(User user) {
        getHibernateTemplate().save(user);
    }

}
