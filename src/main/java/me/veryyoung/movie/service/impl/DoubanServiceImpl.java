package me.veryyoung.movie.service.impl;

import me.veryyoung.movie.dao.PlayingDao;
import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.Playing;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.qiniu.QiniuUtils;
import me.veryyoung.movie.service.BaseService;
import me.veryyoung.movie.service.DoubanService;
import me.veryyoung.movie.utils.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by veryyoung on 2015/3/18.
 */
@Service
public class DoubanServiceImpl extends BaseService implements DoubanService {


    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private PlayingDao playingDao;

    private static final String DOUBAN_API_URL = "https://api.douban.com/v2/movie";

    private static final String DOUBAN_APIKEY = "apikey=0df993c66c0c636e29ecbb5344252a4a";

    private static final String DOUBAN_SUBJECT_URL = DOUBAN_API_URL + "/subject/%s?" + DOUBAN_APIKEY;

    public static final String DOUBAN_PLAYING_URL = DOUBAN_API_URL + "/nowplaying?" + DOUBAN_APIKEY;

    private static final String DOUBAN_SEARCH_URL = DOUBAN_API_URL + "/search?q=%s";


    private JSONObject jsonObject;

    private JSONArray jsonArray;

    private int length;


    @Override
    public Subject find(String id) {
        Subject subject = subjectDao.find(id);

        if (null != subject) {
            return subject;
        }

        String responseBody = HttpClientUtils.get(String.format(DOUBAN_SUBJECT_URL, id));
        jsonObject = new JSONObject(responseBody);
        subject = new Subject();
        subject.setId(jsonObject.getString("id"));
        subject.setTitle(jsonObject.getString("title"));
        subject.setOriginalTitle(jsonObject.getString("original_title"));

        subject.setRatingCount(jsonObject.getInt("ratings_count"));
        subject.setTotalRating(subject.getRatingCount() * jsonObject.getJSONObject("rating").getDouble("average"));
        QiniuUtils.uploadToQiniu(jsonObject.getJSONObject("images").getString("large"), id);

        jsonArray = jsonObject.getJSONArray("directors");
        length = jsonArray.length();
        StringBuilder sb;
        if (length > 0) {
            sb = new StringBuilder(jsonArray.getJSONObject(0).getString("name"));
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.getJSONObject(i).getString("name"));
            }
            subject.setDirectors(sb.toString());
        }

        jsonArray = jsonObject.getJSONArray("casts");
        length = jsonArray.length();
        if (length > 0) {
            sb = new StringBuilder(jsonArray.getJSONObject(0).getString("name"));
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.getJSONObject(i).getString("name"));
            }
            subject.setCasts(sb.toString());
        }


        jsonArray = jsonObject.getJSONArray("writers");
        length = jsonArray.length();
        if (length > 0) {
            sb = new StringBuilder(jsonArray.getJSONObject(0).getString("name"));
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.getJSONObject(i).getString("name"));
            }
            subject.setWriters(sb.toString());
        }

        String mainland_pubdate = jsonObject.getString("mainland_pubdate");
        if (StringUtils.isNotEmpty(mainland_pubdate)) {
            try {
                subject.setPubDate(DateUtils.parseDate(mainland_pubdate, "yyyy-mm-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotEmpty(jsonObject.getString("year"))) {
            subject.setYear((short) jsonObject.getInt("year"));
        }

        jsonArray = jsonObject.getJSONArray("languages");
        length = jsonArray.length();
        if (length > 0) {
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setLanguages(sb.toString());
        }

        jsonArray = jsonObject.getJSONArray("durations");
        length = jsonArray.length();
        if (length > 0) {
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setDurations(sb.toString());
        }

        jsonArray = jsonObject.getJSONArray("genres");
        length = jsonArray.length();
        if (length > 0) {
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setGenres(sb.toString());
        }

        jsonArray = jsonObject.getJSONArray("countries");
        length = jsonArray.length();
        if (length > 0) {
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setCountries(sb.toString());
        }

        subject.setSummary(jsonObject.getString("summary"));

        subjectDao.create(subject);

        logger.debug("add subject [{}] from douban", subject);

        return subject;
    }

    @Override
    public List<Subject> findPlaying() {
        List<Subject> result = subjectDao.getPlaying();
        if (result.isEmpty()) {
            Subject subject;
            result = new ArrayList<>();
            String id;
            List<Playing> playingList;
            String responseBody = HttpClientUtils.get(DOUBAN_PLAYING_URL);
            jsonObject = new JSONObject(responseBody);
            jsonArray = jsonObject.getJSONArray("entries");
            length = jsonArray.length();
            logger.debug("{} movies are playing", length);
            playingList = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                id = jsonArray.getJSONObject(i).getString("id");
                subject = find(id);
                result.add(subject);
                playingList.add(new Playing(id));
            }
            playingDao.renew(playingList);
        }
        return result;
    }

    @Override
    public void saveBySearch(String q) {
        String responseBody = HttpClientUtils.get(String.format(DOUBAN_SEARCH_URL, q));
        jsonObject = new JSONObject(responseBody);
        jsonArray = jsonObject.getJSONArray("subjects");
        length = jsonArray.length();
        JSONObject jo;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                jo = jsonArray.optJSONObject(i);
                if (null != jo) {
                    find(jo.getString("id"));
                }
            }
        }
    }
}
