package me.veryyoung.movie.service.impl;

import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.rest.PageInfo;
import me.veryyoung.movie.service.BaseService;
import me.veryyoung.movie.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by veryyoung on 2015/5/6.
 */
@Service
public class SubjectServiceImpl extends BaseService implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public PageInfo<Subject> listBySearch(int pageNo, int pageSize, String year, String place, String type, String sort) {
        PageInfo<Subject> pageInfo = new PageInfo<>(pageNo, pageSize);
        pageInfo.setResultList(subjectDao.listBySearch(pageInfo.getStartRow(), pageSize, year, place, type, sort));
        pageInfo.setTotalRows(subjectDao.countBySearch(year, place, type, sort));
        return pageInfo;
    }

}
