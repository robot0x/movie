package me.veryyoung.movie.utils;

import me.veryyoung.movie.dao.PlayingDao;
import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.Playing;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.service.DoubanService;
import me.veryyoung.movie.service.impl.DoubanServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veryyoung on 2015/5/14.
 */
public class ScheduleUtils {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private PlayingDao playingDao;

    @Autowired
    private DoubanService doubanService;

    @Scheduled(cron = "0 0 0 * * ?")   //每天零点执行一次
    public void renewPlaying() {
        List<Subject> result = subjectDao.getPlaying();
        if (result.isEmpty()) {
            String id;
            List<Playing> playingList;
            String responseBody = HttpClientUtils.get(DoubanServiceImpl.DOUBAN_PLAYING_URL);
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray jsonArray = jsonObject.getJSONArray("entries");
            int length = jsonArray.length();
            logger.debug("{} movies are playing", length);
            playingList = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                id = jsonArray.getJSONObject(i).getString("id");
                doubanService.find(id);
                playingList.add(new Playing(id));
            }
            playingDao.renew(playingList);
        }
    }
}
