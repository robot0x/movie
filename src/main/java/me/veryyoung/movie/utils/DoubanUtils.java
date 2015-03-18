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
        String url = "https://api.douban.com/v2/movie/subject/" + id;
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
                logger.info("value:{}", jsonArray.get(i));
                sb.append("/").append(jsonArray.get(i));
            }
            subject.setAka(sb.toString());
            logger.info("rating:{}", jsonObject.getJSONObject("rating").getDouble("average"));
//            subject.setRating(jsonObject.getJSONArray("rating").getDouble("average"));
            logger.info("responseBody:{}", responseBody);
            logger.info("subject:{}", subject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }
}
