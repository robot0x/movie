package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Playing;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.utils.OrderBySqlFormulaUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

    public List<Subject> listBySearch(int start, int end, String year, String place, String type, String sort) {
        Criteria criteria = getCurrentSession().createCriteria(Subject.class);
        if (!year.contains("不限")) {
            criteria.add(Restrictions.eq("year", new Short(year)));
        }
        if (!place.contains("不限")) {
            criteria.add(Restrictions.like("countries", "%".concat(place).concat("%")));
        }
        if (!type.contains("不限")) {
            criteria.add(Restrictions.like("genres", "%".concat(type).concat("%")));
        }
        if (sort.equals("rating")) {
            criteria.addOrder(OrderBySqlFormulaUtils.sqlFormula("totalRating / ratingCount desc"));
        } else if (sort.equals("date")) {
            criteria.addOrder(Order.desc("pubDate"));
        }
        criteria.setFirstResult(start);
        criteria.setMaxResults(end);
        return criteria.list();
    }

    public int countBySearch(String year, String place, String type, String sort) {
        Criteria criteria = getCurrentSession().createCriteria(Subject.class).setProjection(Projections.rowCount());
        if (!year.contains("不限")) {
            criteria.add(Restrictions.eq("year", new Short(year)));
        }
        if (!place.contains("不限")) {
            criteria.add(Restrictions.like("countries", "%".concat(place).concat("%")));
        }
        if (!type.contains("不限")) {
            criteria.add(Restrictions.like("genres", "%".concat(type).concat("%")));
        }
        if (sort.equals("rating")) {
            criteria.addOrder(OrderBySqlFormulaUtils.sqlFormula("totalRating / ratingCount desc"));
        } else if (sort.equals("date")) {
            criteria.addOrder(Order.desc("pubDate"));
        }
        Long count = (Long) (criteria.uniqueResult());
        return count.intValue();
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
