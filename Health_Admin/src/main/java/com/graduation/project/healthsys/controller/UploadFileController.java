package com.graduation.project.healthsys.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.Picture;
import com.graduation.project.healthsys.bean.Project;
import com.graduation.project.healthsys.bean.User;
import com.graduation.project.healthsys.service.IPictureService;
import com.graduation.project.healthsys.service.IUserService;
import com.graduation.project.healthsys.service.impl.QiniuService;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class UploadFileController {

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private IPictureService iPictureService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/imgUpdate" ,method = RequestMethod.GET)
    public Object GetFileUrl() {

        List<Map> mapList = new ArrayList<>();

        List<Picture> pictureList = iPictureService.list();
        List<User> userList = userService.list();

        for (Picture item: pictureList) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("id", item.getId());
            map.put("userId",item.getUserId());
            map.put("url",item.getUrl());
            String name = userList.stream().filter((User u) -> u.getIdCard().equals(item.getUserId())).collect(Collectors.toList()).get(0).getRealName();
            map.put("userName",name);

            mapList.add(map);
        }

        return ResultUtil.success(mapList);
    }

    @RequestMapping(value = "/imgUpdate")
    public Object handleFileUpload(@RequestParam("userId")String userId, @RequestParam("file") MultipartFile multipartFile[]) {

        qiniuService.Upload(userId,multipartFile);

        return ResultUtil.success();
    }
}
