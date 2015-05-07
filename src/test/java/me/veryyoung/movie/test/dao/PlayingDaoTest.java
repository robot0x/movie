package me.veryyoung.movie.test.dao;

import me.veryyoung.movie.dao.PlayingDao;
import me.veryyoung.movie.entity.Playing;
import me.veryyoung.movie.test.AbstractSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by veryyoung on 2015/5/7.
 */

public class PlayingDaoTest extends AbstractSpringTest {

    @Autowired
    private PlayingDao playingDao;

    @Test
    public void test() {
        String[] idList = {"111", "222", "333", "444"};
        for (String id : idList) {
            Playing playing = new Playing(id);
            playingDao.create(playing);
        }
    }

    @Test
    public void testCeil() {
        System.out.println(1 / 6);
        System.out.println(Math.ceil(((float)1 / 6)));
    }
}
