package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.MealProject;
import com.graduation.project.healthsys.bean.Project;
import com.graduation.project.healthsys.bean.User;
import com.graduation.project.healthsys.mapper.MealProjectDao;
import com.graduation.project.healthsys.mapper.ProjectDao;
import com.graduation.project.healthsys.service.IMealProjectService;
import com.graduation.project.healthsys.service.IProjectService;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class MealProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private MealProjectDao mealProjectDao;

    @Autowired
    private IMealProjectService mealProjectService;

    @RequestMapping(value ="/mealProject", method = RequestMethod.GET)
    public Object getUser(){
        return ResultUtil.success(mealProjectService.list());
    }

    @RequestMapping(value ="/mealProject/{meal_id}", method = RequestMethod.GET)
    public Object getUser(@PathVariable("meal_id") String meal_id){
        List<Project> ReturnProjectList = new ArrayList<Project>();
        List<MealProject> mealProjectList = mealProjectService.list(new QueryWrapper<MealProject>().eq("meal_id", meal_id));
        List<Project> projectList = projectService.list();

        for (MealProject m:mealProjectList) {
            List<Project>  tempList =  projectList.stream().filter((Project p) -> p.getId().equals(m.getProjectId())).collect(Collectors.toList());
            ReturnProjectList.add(tempList.get(0));
        }
        return ResultUtil.success(ReturnProjectList);
    }

    @RequestMapping(value ="/mealProject", method = RequestMethod.POST)
    public Object add(@RequestBody List<MealProject> mealProjectList){
        for (MealProject item: mealProjectList) {
            item.setId(IdWorker.getIdStr());
        }
        mealProjectService.saveBatch(mealProjectList);
        return ResultUtil.success();
    }

    @RequestMapping(value ="/mealProject", method = RequestMethod.DELETE)
    public Object delete(MealProject mealProject){
        mealProjectDao.DeleteByMealId(mealProject.getMealId());
        return ResultUtil.success();
    }
}
