package me.veryyoung.movie.service.impl;

import me.veryyoung.movie.dao.SubjectDao;
import me.veryyoung.movie.entity.Subject;
import me.veryyoung.movie.service.BaseService;
import me.veryyoung.movie.service.DoubanService;
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

/**
 * Created by veryyoung on 2015/3/18.
 */
@Service
public class DoubanServiceImpl extends BaseService implements DoubanService {

    @Autowired
    private SubjectDao subjectDao;

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
            subject.setImage(jsonObject.getJSONObject("images").getString("large"));

            JSONArray jsonArray = jsonObject.getJSONArray("directors");
            int length = jsonArray.length();
            StringBuilder sb = new StringBuilder(jsonArray.getJSONObject(0).getString("name"));
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.getJSONObject(i).getString("name"));
            }
            subject.setDirectors(sb.toString());

            jsonArray = jsonObject.getJSONArray("casts");
            sb = new StringBuilder(jsonArray.getJSONObject(0).getString("name"));
            length = jsonArray.length();
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.getJSONObject(i).getString("name"));
            }
            subject.setCasts(sb.toString());

            jsonArray = jsonObject.getJSONArray("writers");
            length = jsonArray.length();
            sb = new StringBuilder(jsonArray.getJSONObject(0).getString("name"));
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.getJSONObject(i).getString("name"));
            }
            subject.setWriters(sb.toString());

            subject.setPubDate(jsonObject.getString("mainland_pubdate"));
            subject.setYear((short) jsonObject.getInt("year"));

            jsonArray = jsonObject.getJSONArray("languages");
            length = jsonArray.length();
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setLanguages(sb.toString());

            jsonArray = jsonObject.getJSONArray("durations");
            length = jsonArray.length();
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setDurations(sb.toString());

            jsonArray = jsonObject.getJSONArray("genres");
            length = jsonArray.length();
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setGenres(sb.toString());

            jsonArray = jsonObject.getJSONArray("countries");
            length = jsonArray.length();
            sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setCountries(sb.toString());

            subject.setSummary(jsonObject.getString("summary"));

            subjectDao.create(subject);

            logger.debug("add subject [{}] from douban", subject);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }
}
