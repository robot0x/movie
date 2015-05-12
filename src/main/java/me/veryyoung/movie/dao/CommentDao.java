package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Comment;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by veryyoung on 2015/5/12.
 */
@Repository
public class CommentDao extends BaseDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }

    public int countBySubjectId(String id) {
        Criteria criteria = getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    public List<Comment> list(int start, int end) {
        Criteria criteria = getCurrentSession().createCriteria(Comment.class);
        criteria.setFirstResult(start);
        criteria.setMaxResults(end);
        return criteria.list();
    }

}
