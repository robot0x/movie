package me.veryyoung.movie.utils;

import me.veryyoung.movie.entity.Subject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
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
        String url = "https://api.douban.com/v2/movie/subject/" + 6875263;
        HttpGet httpGet = new HttpGet(url);
        //创建响应处理器处理服务器响应内容
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        //执行请求并获取结果
        try {
            String responseBody = httpClient.execute(httpGet, responseHandler);
            logger.info("responseBody:{}", responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Subject();
    }
}
