package me.veryyoung.movie.service;

import me.veryyoung.movie.entity.Subject;

import java.util.List;

/**
 * Created by veryyoung on 2015/3/18.
 */
public interface DoubanService {

    //存在直接返回，不存在从豆瓣抓取后返回
    public Subject find(String id);

    public List<Subject> findPlaying();

    public void saveBySearch(String q);

}
