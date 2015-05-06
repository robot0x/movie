package me.veryyoung.movie.service;

import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.rest.PageInfo;

/**
 * Created by veryyoung on 2015/5/6.
 */
public interface SubjectService {

    public PageInfo<Subject> listBySearch(int pageNo, int pageSize);

}
