package com.graduation.project.healthsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.project.healthsys.bean.Picture;
import com.graduation.project.healthsys.mapper.PictureDao;
import com.graduation.project.healthsys.service.IPictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl extends ServiceImpl<PictureDao, Picture> implements IPictureService {

}

