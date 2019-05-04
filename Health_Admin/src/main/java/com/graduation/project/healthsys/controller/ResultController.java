/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-13    xiemd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.*;
import com.graduation.project.healthsys.mapper.SubscribeDao;
import com.graduation.project.healthsys.service.*;
import com.graduation.project.healthsys.service.impl.MailServiceImpl;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collector;
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
public class ResultController {

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private IResultService iResultService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMealService mealService;

    @Autowired
    private MailServiceImpl mailService;

    @RequestMapping(value ="/result", method = RequestMethod.GET)
    public Object getByIdCard(){

        List<Map> returnList = new ArrayList<>();

        List<User> userList = userService.list();
        List<Result> resultList = iResultService.list();

        for (Result item : resultList) {
            Map<String,String> map = new HashMap<>();
            map.put("idCard",item.getIdCard());
            map.put("Name",userList.stream().filter((User u) -> u.getIdCard().equals(item.getIdCard())).collect(Collectors.toList()).get(0).getRealName());

            returnList.add(map);
        }

        HashSet h = new HashSet(returnList);
        returnList.clear();
        returnList.addAll(h);

        return ResultUtil.success(returnList);
    }

    @RequestMapping(value ="/result/{idCard}", method = RequestMethod.GET)
    public Object get(@PathVariable("idCard") String idCard){
        List<Map> returnList = new ArrayList<>();
        Map<String,Object> returnMap = new HashMap<>();

        List<Project> projectList = projectService.list();
        List<User> userList = userService.list();
        List<Meal> mealList = mealService.list();
        List<Result> resultList = iResultService.list(new QueryWrapper<Result>().eq("id_card",idCard));

        String realName =  userList.stream().filter((User u) -> u.getIdCard().equals(idCard)).collect(Collectors.toList()).get(0).getRealName();
        String mealName = mealList.stream().filter((Meal m) -> m.getId().equals(resultList.get(0).getMealId())).collect(Collectors.toList()).get(0).getName();


        List<String> projectIdList = new ArrayList<>();
        List<String> projectNameList = new ArrayList<>();
        List<String> checkValList = new ArrayList<>();
        List<String> resultListShow = new ArrayList<>();
        List<String> remarkList = new ArrayList<>();
        List<String> resultShowList = new ArrayList<>();

        for (Result item: resultList) {
            projectIdList.add(item.getProjectId());

            returnMap.put("id", resultList.get(0).getId());
            returnMap.put("idCard", resultList.get(0).getIdCard());
            returnMap.put("subscribeId",resultList.get(0).getSubscribeId());
            returnMap.put("mealId",resultList.get(0).getMealId());
            returnMap.put("realName", realName);
            returnMap.put("mealName", mealName);


           String nameTemp = projectList.stream().filter((Project p) -> p.getId().equals(item.getProjectId())).collect(Collectors.toList()).get(0).getName();
            nameTemp += "：" + item.getCheckVal() + "。"  + "备注：" + item.getRemark();
            returnMap.put("resultShow",nameTemp);

            resultShowList.add(nameTemp);
            projectNameList.add(nameTemp);
            checkValList.add(item.getCheckVal());
            resultListShow.add(item.getResult());
            remarkList.add(item.getRemark());
        }

        returnMap.put("projectIdList",projectIdList);
        returnMap.put("projectNameList",projectNameList);
        returnMap.put("checkValList",checkValList);
        returnMap.put("remarkList",remarkList);
        returnMap.put("resultShowList",resultShowList);

        if( resultListShow.indexOf("可以更上一步") < 0){
            returnMap.put("result","完美");
        }else{
            returnMap.put("result","可以更上一步");
        }



        return ResultUtil.success(returnMap);
    }

    @RequestMapping(value ="/result", method = RequestMethod.POST)
    public Object add(@RequestBody List<Result> result){

        Result result1 = result.get(0);
        Subscribe subscribe = subscribeDao.selectList(new QueryWrapper<Subscribe>().eq("id_card",result1.getIdCard())).get(0);

        for (Result item : result) {
            if(!item.getCheckVal().equals("正常")){
                item.setResult("可以更上一步");
            }else{
                item.setResult("完美");
            }
            item.setId(IdWorker.getIdStr());
            item.setSubscribeId(subscribe.getId());
            item.setIdCard(result1.getIdCard());
            item.setMealId(subscribe.getMealId());
        }

        List<Result> aa = result;

        iResultService.saveBatch(result);

        // 发送邮件
        String emailStr = "";
        List<Project> projectList = projectService.list();
        List<Result> resultList = iResultService.list(new QueryWrapper<Result>().eq("id_card",result1.getIdCard()));
        for (Result item: resultList) {
            String nameTemp = projectList.stream().filter((Project p) -> p.getId().equals(item.getProjectId())).collect(Collectors.toList()).get(0).getName();
            nameTemp += "：" + item.getCheckVal() + "。"  + "备注：" + item.getRemark();
            emailStr +=nameTemp;
        }

        List<User> userList = userService.list();
        String email = userList.stream().filter((User u) -> u.getIdCard().equals(result1.getIdCard())).collect(Collectors.toList()).get(0).getEmail();
        mailService.SendEmail(email,emailStr);

        return ResultUtil.success();
    }
}
