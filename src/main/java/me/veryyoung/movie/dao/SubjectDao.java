package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Playing;
import me.veryyoung.movie.entity.Subject;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by veryyoung on 2015/3/18.
 */
@Repository
public class SubjectDao extends BaseDao<Subject> {

    @Autowired
    private PlayingDao playingDao;

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
        int count = ((Long) getCurrentSession().createQuery("select count(*) from Subject").uniqueResult()).intValue();
        return count;
    }

    public List<Subject> getPlaying() {
        List<Playing> playingList = playingDao.findAll();
        if (playingList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Subject> playingSubjects = new ArrayList<>(playingList.size());
        Subject subject;
        for (Playing playing : playingList) {
            subject = find(playing.getId());
            playingSubjects.add(subject);
        }
        return playingSubjects;
    }

}
