package me.veryyoung.movie.qiniu;

import com.qiniu.api.auth.DigestAuthClient;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.net.Client;
import com.qiniu.api.net.EncodeUtils;

/**
 * Created by veryyoung on 2015/3/26.
 */
public class QiniuUtils {


    public static final String ACCESS_KEY = "1OcsILqPu9A_YrO7bgAEBowPWwmjTfzt_zUoINRC";

    public static final String SECRET_KEY = "BW1s2xfqoty1RRzNI4xhVMs6dt6i7zjf3FdbX9Ty";

    private static final String BUCKET_NAME = "movie";

    public static final String QINIU_URL = "http://7xia3v.com1.z0.glb.clouddn.com/";

    /**
     * 上传对应主题的图片到七牛云
     */
    public static void uploadToQiniu(String from, String subjectId) {
        String to = BUCKET_NAME.concat(":").concat(subjectId);
        String encodeFrom = EncodeUtils.urlsafeEncode(from);
        String encodeTo = EncodeUtils.urlsafeEncode(to);
        String url = "http://iovip.qbox.me/fetch/" + encodeFrom + "/to/" + encodeTo;
        Mac mac = new com.qiniu.api.auth.digest.Mac(QiniuUtils.ACCESS_KEY, QiniuUtils.SECRET_KEY);
        Client client = new DigestAuthClient(mac);
        CallRet ret = client.call(url);
    }
}




