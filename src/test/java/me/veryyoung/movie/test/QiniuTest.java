package me.veryyoung.movie.test;

import com.qiniu.api.auth.DigestAuthClient;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.net.Client;
import com.qiniu.api.net.EncodeUtils;
import me.veryyoung.movie.qiniu.QiniuUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by veryyoung on 2015/3/26.
 */
public class QiniuTest {

    @Test
    public void uploadStream() throws IOException {

        String from = "http://i2.sinaimg.cn/IT/cr/2014/0209/1645509745.jpg";
        String to = "movie:test.jpg";
        String encodeFrom = EncodeUtils.urlsafeEncode(from);
        String encodeTo = EncodeUtils.urlsafeEncode(to);
        System.out.println(encodeTo);
        String url = "http://iovip.qbox.me/fetch/" + encodeFrom + "/to/" + encodeTo;
        System.out.println(url);
        Mac mac = new com.qiniu.api.auth.digest.Mac(QiniuUtils.ACCESS_KEY, QiniuUtils.SECRET_KEY);
        Client client = new DigestAuthClient(mac);
        CallRet ret = client.call(url);
        System.out.println(ret.response);
        System.out.println(ret.statusCode);


    }

}
