package me.veryyoung.movie.service.impl;

import me.veryyoung.movie.dao.PlayingDao;
import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.Playing;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.qiniu.QiniuUtils;
import me.veryyoung.movie.service.BaseService;
import me.veryyoung.movie.service.DoubanService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Override
    public Subject find(String id) {
        Subject subject = subjectDao.find(id);

        if (null != subject) {
            return subject;
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://api.douban.com/v2/movie/subject/" + id + "?apikey=0df993c66c0c636e29ecbb5344252a4a";
        HttpGet httpGet = new HttpGet(url);
        //创建响应处理器处理服务器响应内容
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        JSONObject jsonObject;
        subject = null;
        try {
            String responseBody = httpClient.execute(httpGet, responseHandler);
            jsonObject = new JSONObject(responseBody);
            subject = new Subject();
            subject.setId(jsonObject.getString("id"));
            subject.setTitle(jsonObject.getString("title"));
            subject.setOriginalTitle(jsonObject.getString("original_title"));

            subject.setRatingCount(jsonObject.getInt("ratings_count"));
            subject.setTotalRating(subject.getRatingCount() * jsonObject.getJSONObject("rating").getDouble("average"));
            QiniuUtils.uploadToQiniu(jsonObject.getJSONObject("images").getString("large"), id);

            JSONArray jsonArray = jsonObject.getJSONArray("directors");
            int length = jsonArray.length();
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
                subject.setPubDate(DateUtils.parseDate(mainland_pubdate, "yyyy-mm-dd"));
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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public List<Subject> findPlaying() {
        List<Subject> result = subjectDao.getPlaying();
        if (result.isEmpty()) {
            HttpClient httpClient = HttpClientBuilder.create().build();
            String url = "http://api.douban.com/v2/movie/nowplaying?apikey=0df993c66c0c636e29ecbb5344252a4a";
            HttpGet httpGet = new HttpGet(url);
            //创建响应处理器处理服务器响应内容
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            JSONObject jsonObject;
            Subject subject;
            JSONArray jsonArray;
            result = new ArrayList<>();
            int length;
            String id;
            List<Playing> playingList;
            try {
                String responseBody = httpClient.execute(httpGet, responseHandler);
                jsonObject = new JSONObject(responseBody);
                jsonArray = jsonObject.getJSONArray("entries");
                length = jsonArray.length();
                playingList = new ArrayList<>(length);
                for (int i = 0; i < length; i++) {
                    id = jsonArray.getJSONObject(i).getString("id");
                    subject = find(id);
                    result.add(subject);
                    playingList.add(new Playing(id));
                }
                playingDao.renew(playingList);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @Override
    public void saveBysearch(String q) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://api.douban.com/v2/movie/search?q=" + q;
        HttpGet httpGet = new HttpGet(url);
        //创建响应处理器处理服务器响应内容
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        JSONObject jsonObject;
        JSONArray jsonArray;
        int length;
        try {
            String responseBody = httpClient.execute(httpGet, responseHandler);
            jsonObject = new JSONObject(responseBody);
            jsonArray = jsonObject.getJSONArray("subjects");
            length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                find(jsonArray.getJSONObject(i).getString("id"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
