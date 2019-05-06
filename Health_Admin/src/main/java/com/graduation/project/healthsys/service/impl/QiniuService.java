package com.graduation.project.healthsys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.gson.Gson;
import com.graduation.project.healthsys.bean.Picture;
import com.graduation.project.healthsys.service.IPictureService;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.sun.imageio.plugins.common.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class QiniuService {

    @Autowired
    private IPictureService iPictureService;

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

    public void Upload(String userId, MultipartFile images[]) {

        for (MultipartFile file: images) {
            String path = "";
            Picture picture = new Picture();
            picture.setId(IdWorker.getIdStr());
            picture.setUserId(userId);


            DefaultPutRet putRet =new DefaultPutRet();
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            String imageExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();

            String imageName = UUID.randomUUID().toString().replace("-", "") + "." + imageExt;
            try {
                Response response = uploadManager.put(file.getBytes(), imageName, getUpToken());
                if (response.isOK() && response.isJson()) {
                    //解析上传成功的结果
                    putRet  = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                    path = QINIU_IMAGE_DOMAIN + putRet.key;
                    picture.setUrl(path);
                    iPictureService.save(picture);
                }
            } catch (IOException e) {

            }
        }
    }
}
