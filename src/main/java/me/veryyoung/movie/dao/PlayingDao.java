package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Playing;
import org.springframework.stereotype.Repository;

/**
 * Created by veryyoung on 2015/5/7.
 */
@Repository
public class PlayingDao extends BaseDao<Playing> {
    public PlayingDao() {
        super(Playing.class);
    }
}
