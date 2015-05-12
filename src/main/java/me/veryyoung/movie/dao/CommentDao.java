package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Comment;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by veryyoung on 2015/5/12.
 */
@Repository
public class CommentDao extends BaseDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }

    public int countBySubjectId(String id) {
        Query query = getCurrentSession().createQuery("select count(*) from Subject as subject where subject.id = :id");
        query.setString("id", id);
        return ((Long) query.uniqueResult()).intValue();
    }

}
