package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Comment;
import org.hibernate.Query;
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

    public int countBySubjectId(String subjectId) {
        Query query = getCurrentSession().createQuery("select count(*) from Comment as comment where comment.subjectId = :subjectId");
        query.setString("subjectId", subjectId);
        return ((Long) query.uniqueResult()).intValue();
    }

    public List<Comment> listBySubjectId(String subjectId, int start, int end) {
        Query query = getCurrentSession().createQuery("from Comment as comment where comment.subjectId = :subjectId order by comment.submitDate desc");
        query.setString("subjectId", subjectId);
        query.setFirstResult(start);
        query.setMaxResults(end);
        return query.list();
    }

}
