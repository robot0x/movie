package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * Created by veryyoung on 2015/5/12.
 */
@Repository
public class CommentDao extends BaseDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }

}
