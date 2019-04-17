package com.graduation.project.healthsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.project.healthsys.bean.Project;
import com.graduation.project.healthsys.mapper.ProjectDao;
import com.graduation.project.healthsys.service.IProjectService;
import org.springframework.stereotype.Service;


@Service("ProjectServiceImpl")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements IProjectService {

}
