package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Subject;
import org.springframework.stereotype.Repository;

/**
 * Created by veryyoung on 2015/3/18.
 */
@Repository
public class SubjectDao extends BaseDao<Subject> {

    public SubjectDao() {
        super(Subject.class);
    }
}
