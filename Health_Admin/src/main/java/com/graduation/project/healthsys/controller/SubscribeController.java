/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-13    xiemd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.MealProject;
import com.graduation.project.healthsys.bean.Project;
import com.graduation.project.healthsys.bean.Subscribe;
import com.graduation.project.healthsys.mapper.MealProjectDao;
import com.graduation.project.healthsys.mapper.ProjectDao;
import com.graduation.project.healthsys.mapper.SubscribeDao;
import com.graduation.project.healthsys.service.IMealProjectService;
import com.graduation.project.healthsys.service.IMealService;
import com.graduation.project.healthsys.service.IProjectService;
import com.graduation.project.healthsys.service.ISubscribeService;
import com.graduation.project.healthsys.util.DateUtils;
import com.graduation.project.healthsys.util.ParamsUtils;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能说明: 前端控制器
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class SubscribeController {

    @Autowired
    private ISubscribeService subscribeService;

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private IMealProjectService mealProjectService;

    @Autowired
    private IProjectService projectService;

    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public Object getSubscribe() {
        List<Subscribe> subscribeList = subscribeService.list();
        return ResultUtil.success(subscribeList);
    }

    @RequestMapping(value = "/subscribe/{idCard}", method = RequestMethod.GET)
    public Object getSubscribeDetail(@PathVariable("idCard") String idCard) {
        List<Project> returnList =new ArrayList<>();

        List<Subscribe> subscribe = subscribeDao.selectList(new QueryWrapper<Subscribe>().eq("id_card",idCard));
        List<MealProject> mealProjectList = mealProjectService.list(new QueryWrapper<MealProject>().eq("meal_id", subscribe.get(0).getMealId()));
        List<Project> projectList = projectService.list();

        for (MealProject m:mealProjectList) {
            List<Project>  tempList =  projectList.stream().filter((Project p) -> p.getId().equals(m.getProjectId())).collect(Collectors.toList());
            if(!tempList.isEmpty()){
                returnList.add(tempList.get(0));
            }
        }

        return ResultUtil.success(returnList);
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public Object subscribe(@RequestBody Subscribe subscribe) throws ParseException {
        subscribe.setId(IdWorker.getIdStr());
        subscribeService.save(subscribe);
        return ResultUtil.success(subscribe);
    }
}
