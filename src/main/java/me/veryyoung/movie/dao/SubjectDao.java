package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by veryyoung on 2015/3/18.
 */
@Repository
public class SubjectDao extends BaseDao<Subject> {

    public SubjectDao() {
        super(Subject.class);
    }

    public List<Subject> listBySearch(int start, int end) {
        Query query = getCurrentSession().createQuery("from Subject as subject");
        query.setFirstResult(start);
        query.setMaxResults(end);
        return query.list();
    }

    public int countBySearch() {
        int count = ((Long)getCurrentSession().createQuery("select count(*) from Subject").uniqueResult()).intValue();
        return count;
    }

}
