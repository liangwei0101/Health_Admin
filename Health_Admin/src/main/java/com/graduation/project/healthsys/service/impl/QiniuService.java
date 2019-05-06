package com.graduation.project.healthsys.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.sun.imageio.plugins.common.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class QiniuService {

    // 账号密钥，可在个人中心-密钥管理中查看
    private static final String ACCESS_KEY = "oxIuhvkHgIyhLGExyhouLfUrleJDcZtrOnNDmIl4";

    private static final String SECRET_KEY = "5R1L7eQkddxgF20qshUAtGnPat5yDEFbpFq1COMo";

    // 要上传的空间
    private static final String BUCKET_NAME = "liangwei1994";

    // 七牛默认外链域名
    private static final String QINIU_IMAGE_DOMAIN = "http://pqzg0sf82.bkt.clouddn.com/";

    // 密钥配置
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    // 创建配置对象
    private Configuration cfg = new Configuration(Zone.huadong());

    // 创建上传对象
    private UploadManager uploadManager = new UploadManager(cfg);

    // 获得简单上传的凭证
    private String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    public String Upload(MultipartFile image) {
        DefaultPutRet putRet =new DefaultPutRet();

        int dotPos = image.getOriginalFilename().lastIndexOf(".");
        String imageExt = image.getOriginalFilename().substring(dotPos + 1).toLowerCase();

        String imageName = UUID.randomUUID().toString().replace("-", "") + "." + imageExt;
        try {
            Response response = uploadManager.put(image.getBytes(), imageName, getUpToken());
            if (response.isOK() && response.isJson()) {
                //解析上传成功的结果
                putRet  = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return QINIU_IMAGE_DOMAIN + putRet.key;
            }
            log.error("七牛云上传图片失败：" + response.bodyString());
        } catch (QiniuException e) {
            log.error("七牛异常：" + e.getMessage());
        } catch (IOException e) {
            log.error("IO异常：" + e.getMessage());
        }
        return QINIU_IMAGE_DOMAIN + putRet.key;
    }
}
