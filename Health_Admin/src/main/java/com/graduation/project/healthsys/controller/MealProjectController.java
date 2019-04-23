package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.MealProject;
import com.graduation.project.healthsys.bean.User;
import com.graduation.project.healthsys.mapper.MealProjectDao;
import com.graduation.project.healthsys.service.IMealProjectService;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class MealProjectController {

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
        List<MealProject> mealProjectList = mealProjectService.list();
        List<MealProject> projectList = mealProjectList.stream().filter((MealProject mealProject) -> mealProject.getMealId() == meal_id).collect(Collectors.toList());
        return ResultUtil.success(projectList);
    }

    @RequestMapping(value ="/mealProject", method = RequestMethod.POST)
    public Object add(@RequestBody List<MealProject> mealProjectList){
        for (MealProject item: mealProjectList) {
            item.setId(IdWorker.getIdStr());
        }
        mealProjectService.saveBatch(mealProjectList);
        return ResultUtil.success();
    }

    @RequestMapping(value ="/mealProject", method = RequestMethod.PUT)
    public Object update(List<MealProject> mealProjectList){
        MealProject mealProject = mealProjectList.get(0);
        mealProjectDao.DeleteByMealId(mealProject.getMealId());
        mealProjectService.saveBatch(mealProjectList);
        return ResultUtil.success();
    }

    @RequestMapping(value ="/mealProject", method = RequestMethod.DELETE)
    public Object delete(MealProject mealProject){
        mealProjectDao.DeleteByMealId(mealProject.getMealId());
        return ResultUtil.success();
    }
}
