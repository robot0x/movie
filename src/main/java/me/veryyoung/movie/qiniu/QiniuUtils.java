package me.veryyoung.movie.qiniu;

import com.qiniu.api.auth.DigestAuthClient;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.net.Client;
import com.qiniu.api.net.EncodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by veryyoung on 2015/3/26.
 */
public class QiniuUtils {


    public static final String ACCESS_KEY = "";

    public static final String SECRET_KEY = "";

    private static final String BUCKET_NAME = "movie";

    public static final String QINIU_URL = "http://7xia3v.com1.z0.glb.clouddn.com/";

    private static Logger logger = LoggerFactory.getLogger(QiniuUtils.class);

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
        logger.info("upload image :{} to :{},response is:{}", from, subjectId, ret);
    }

    public static void deleteFromQiniu(String subjectId) {
        String encodeTo = EncodeUtils.urlsafeEncode(BUCKET_NAME.concat(":").concat(subjectId));
        String url = "http://rs.qiniu.com/delete/" + encodeTo;
        Mac mac = new com.qiniu.api.auth.digest.Mac(QiniuUtils.ACCESS_KEY, QiniuUtils.SECRET_KEY);
        Client client = new DigestAuthClient(mac);
        CallRet ret = client.call(url);
        logger.info("delete image:{},response is:{}", subjectId, ret);

    }
}




