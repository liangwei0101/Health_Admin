package com.graduation.project.healthsys.controller;


import com.graduation.project.healthsys.service.impl.QiniuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api")
public class UploadFileController {

    @Autowired
    private QiniuService qiniuService;

    @RequestMapping(value = "/imgUpdate", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file")MultipartFile files) {

        String path = qiniuService.Upload(files);
        return path;
    }
}
