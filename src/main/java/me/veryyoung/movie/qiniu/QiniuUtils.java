package me.veryyoung.movie.qiniu;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.resumableio.ResumeableIoApi;
import com.qiniu.api.rs.PutPolicy;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by veryyoung on 2015/3/26.
 */
public class QiniuUtils {


    private static Logger logger = LoggerFactory.getLogger(QiniuUtils.class);

    public static final String ACCESS_KEY = "1OcsILqPu9A_YrO7bgAEBowPWwmjTfzt_zUoINRC";

    public static final String SECRET_KEY = "BW1s2xfqoty1RRzNI4xhVMs6dt6i7zjf3FdbX9Ty";

    private static final String BUCKET_NAME = "movie";

    public static void uploadStream(FileInputStream fis, String fileName) throws AuthException, JSONException, FileNotFoundException {
        Mac mac = new Mac(SECRET_KEY, SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
        String upToken = putPolicy.token(mac);
        PutRet ret = ResumeableIoApi.put(fis, upToken, fileName, null);
        logger.info("ret:{}", ret);
    }
}




