package me.veryyoung.movie.test;

import me.veryyoung.movie.qiniu.QiniuUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by veryyoung on 2015/3/26.
 */
public class QiniuTest {

    @Test
    public void testUploadToQiniu() throws IOException {
        QiniuUtils.uploadToQiniu("http://img5.douban.com/view/movie_poster_cover/spst/public/p2239530046.jpg","25718082");
    }

}
