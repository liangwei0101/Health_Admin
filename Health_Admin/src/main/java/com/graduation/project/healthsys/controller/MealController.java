/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/17      xmd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.Meal;
import com.graduation.project.healthsys.bean.MealProject;
import com.graduation.project.healthsys.bean.Project;
import com.graduation.project.healthsys.enums.Resultenum;
import com.graduation.project.healthsys.exception.HtException;
import com.graduation.project.healthsys.service.IMealProjectService;
import com.graduation.project.healthsys.service.IMealService;
import com.graduation.project.healthsys.service.IProjectService;
import com.graduation.project.healthsys.util.ParamsUtils;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 功能说明:
 * 开发人员: xmd    xmd@hzregtech.com <br>
 * 开发时间: 2019/4/17 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class MealController {

    @Autowired
    private IMealService mealService;

    @RequestMapping(value = "/meal", method = RequestMethod.GET)
    public Object list() {
        return ResultUtil.success(mealService.list());
    }

    @RequestMapping(value = "/meal", method = RequestMethod.POST)
    public Object add(Meal meal) {
        meal.setId(IdWorker.getIdStr());
        mealService.save(meal);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/meal", method = RequestMethod.PUT)
    public Object update(Meal meal) {
        mealService.updateById(meal);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/meal", method = RequestMethod.DELETE)
    public Object delete(Meal meal) {
        mealService.removeById(meal);
        return ResultUtil.success();
    }

//  @Autowired
//  private IMealProjectService mealProjectService;
//  @Autowired
//  private IProjectService projectService;
//
//  @RequestMapping(value = "/add")
//  public Object add(Map<String, Object> param) {
//    String name = ParamsUtils.stringParam(param, "name");
//
//    double money = Double.parseDouble(ParamsUtils.stringParam(param,"money"));
//
//    List<String> projectIds = (List<String>) param.get("projectIds");
//
//    Meal meal = Meal.builder()
//        .id(IdWorker.getIdStr())
//        .name(name)
//        .money(money)
//        .build();
//
//    mealService.save(meal);
//
//    if (Objects.isNull(projectIds) || projectIds.isEmpty()) {
//      throw new HtException(Resultenum.MISSING_PARAM, "请选择项目");
//    }
//    List<MealProject> mealProjects = new LinkedList<>();
//    projectIds.stream().forEach(e -> mealProjects.add(MealProject.builder()
//        .id(IdWorker.getIdStr())
//        .mealId(meal.getId())
//        .projectId(e)
//        .build()));
//
//    if (!mealProjects.isEmpty()) {
//      mealProjectService.saveBatch(mealProjects);
//    }
//    return ResultUtil.success();
//  }
//
//
//  @RequestMapping(value = "/update")
//  public Object update(Map<String, Object> param) {
//
//    String id =  ParamsUtils.stringParam(param, "id");
//
//    String name = ParamsUtils.stringParam(param, "name");
//
//    double money = Double.parseDouble(ParamsUtils.stringParam(param,"money"));
//
//    List<String> projectIds = (List<String>) param.get("projectIds");
//
//    Meal meal = Meal.builder()
//        .id(id)
//        .name(name)
//        .money(money)
//        .build();
//
//    if (!mealService.updateById(meal)) {
//      throw new HtException("修改失败");
//    }
//
//    if (Objects.isNull(projectIds) || projectIds.isEmpty()) {
//      throw new HtException(Resultenum.MISSING_PARAM, "请选择项目");
//    }
//
//    mealProjectService.remove(new QueryWrapper<MealProject>().eq("meal_id", id));
//
//    List<MealProject> mealProjects = new LinkedList<>();
//    projectIds.stream().forEach(e -> mealProjects.add(MealProject.builder()
//        .id(IdWorker.getIdStr())
//        .mealId(meal.getId())
//        .projectId(e)
//        .build()));
//
//    if (!mealProjects.isEmpty()) {
//      mealProjectService.saveBatch(mealProjects);
//    }
//    return ResultUtil.success();
//  }
//
//  @RequestMapping(value = "/meal",method = RequestMethod.GET)
//  public Object list() {
//    Object aa = mealProjectService.list();
//    return ResultUtil.success(mealProjectService.list());
//  }
//
//  @RequestMapping(value = "detail")
//  public Object detail(Map<String, Object> param) {
//    String id = ParamsUtils.stringParam(param, "id");
//
//    Meal meal = mealService.getById(id);
//
//    List<MealProject> mealProjects = mealProjectService.list(new QueryWrapper<MealProject>().eq("meal_id", id));
//
//    Set<String> set = mealProjects.stream().map(e->e.getProjectId()).collect(Collectors.toSet());
//
//    Collection<Project> projects = projectService.listByIds(set);
//
//    Map<String, Object> result = new HashMap<>();
//
//    result.put("projects", projects);
//
//    result.put("meal", meal);
//
//    return ResultUtil.success(result);
//  }

}