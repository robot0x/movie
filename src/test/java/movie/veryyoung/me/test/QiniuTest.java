package java.movie.veryyoung.me.test;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by veryyoung on 2015/3/26.
 */
public class QiniuTest {

    @Test
    public void uploadStream() throws IOException {

        String imageUrl = "http://img3.douban.com/view/movie_poster_cover/lpst/public/p2231066272.jpg";
        String imageName = "6875263";

        URL url = new URL(imageUrl);
        // 得到URLConnection对象
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
//        QiniuUtils.uploadStream(new FileInputStream(is), imageName);


    }

}
