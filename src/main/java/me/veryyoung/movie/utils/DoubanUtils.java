package me.veryyoung.movie.utils;

import me.veryyoung.movie.entity.Subject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by veryyoung on 2015/3/18.
 */
public class DoubanUtils {

    private static Logger logger = LoggerFactory.getLogger(DoubanUtils.class);


    public static Subject getSubject(String id) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://api.douban.com/v2/movie/subject/" + id + "?apikey=0df993c66c0c636e29ecbb5344252a4a";
        HttpGet httpGet = new HttpGet(url);
        //创建响应处理器处理服务器响应内容
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        JSONObject jsonObject;
        Subject subject = null;
        try {
            String responseBody = httpClient.execute(httpGet, responseHandler);
            jsonObject = new JSONObject(responseBody);
            subject = new Subject();
            subject.setId(jsonObject.getString("id"));
            subject.setTitle(jsonObject.getString("title"));
            subject.setOriginalTitle(jsonObject.getString("original_title"));


            JSONArray jsonArray = jsonObject.getJSONArray("aka");
            int length = jsonArray.length();
            StringBuilder sb = new StringBuilder(jsonArray.get(0).toString());
            for (int i = 1; i < length; i++) {
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setAka(sb.toString());

            subject.setRatingCount(jsonObject.getInt("ratings_count"));
            subject.setTotalRating(subject.getRatingCount() * jsonObject.getJSONObject("rating").getDouble("average"));
            subject.setImage(jsonObject.getJSONObject("images").getString("large"));

            jsonArray = jsonObject.getJSONArray("directors");
            length = jsonArray.length();
            sb = new StringBuilder(jsonArray.getJSONObject(0).getString("name"));
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
            subject.setGenres(sb.toString());

            subject.setSummary(jsonObject.getString("summary"));



            logger.info("responseBody:{}", responseBody);
            logger.info("subject:{}", subject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }
}
