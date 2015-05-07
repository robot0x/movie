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
        Playing playing = new Playing("25745752");
        playingDao.create(playing);
    }
}
