package com.datao.util;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.io.InputStream;

/**
 * Created by 海涛 on 2016/5/5.
 */
@Named
public class QiniuUtil {

    @Value("${qiniu.ak}")
    private String  AK;

    @Value("${qiniu.sk}")
    private String SK;

    @Value("${qiniu.domain}")
    private String URL;

    @Value("${qiniu.bucket}")
    private String BUCKET;

    public String uploadFile(InputStream inputStream) {
        try {
            Auth auth = Auth.create(AK, SK);
            String uploadToken = auth.uploadToken(BUCKET);
            UploadManager uploadManager = new UploadManager();
            Response resp = uploadManager.put(IOUtils.toByteArray(inputStream), null, uploadToken);
            String fileKey = (String) resp.jsonToMap().get("key");
            return BUCKET+"/"+fileKey;
        } catch (Exception ex) {
            throw new RuntimeException("上传到七牛异常");
        }
    }

}
