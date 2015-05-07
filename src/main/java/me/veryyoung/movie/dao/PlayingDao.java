package me.veryyoung.movie.dao;

import me.veryyoung.movie.entity.Playing;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by veryyoung on 2015/5/7.
 */
@Repository
public class PlayingDao extends BaseDao<Playing> {

    public PlayingDao() {
        super(Playing.class);
    }

    //更新local热映列表
    public void renew(List<Playing> playingList) {
        deleteAll();
        for (Playing playing : playingList) {
            create(playing);
        }
    }

}
