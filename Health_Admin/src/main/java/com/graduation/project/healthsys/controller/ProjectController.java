package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.Branch;
import com.graduation.project.healthsys.bean.Project;
import com.graduation.project.healthsys.service.IProjectService;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @RequestMapping(value ="/project", method = RequestMethod.GET)
    public Object get() {
        return ResultUtil.success(projectService.list());
    }

    @RequestMapping(value ="/project/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable("id") String id) {
        return ResultUtil.success(projectService.getById(id));
    }

    @RequestMapping(value ="/project", method = RequestMethod.POST)
    public Object add(Project project) {
        project.setId(IdWorker.getIdStr());
        projectService.save(project);
        return ResultUtil.success();
    }

    @RequestMapping(value ="/project", method = RequestMethod.PUT)
    public Object update(Project project) {
        projectService.updateById(project);
        return ResultUtil.success();
    }

    @RequestMapping(value ="/project", method = RequestMethod.DELETE)
    public Object delete(Project project) {
        projectService.removeById(project);
        return ResultUtil.success();
    }

}
