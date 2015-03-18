package me.veryyoung.movie.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by veryyoung on 2015/3/4.
 */
public abstract class BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;


    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    private Class entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public List<T> findAll() throws DataAccessException {
        return getCurrentSession().createCriteria(entityClass).list();
    }

    public T find(String id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    public void create(T t) {
        getCurrentSession().save(t);
    }

    public void update(T t) {
        getCurrentSession().update(t);
    }

    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    public void flush() {
        getCurrentSession().flush();
    }

}
