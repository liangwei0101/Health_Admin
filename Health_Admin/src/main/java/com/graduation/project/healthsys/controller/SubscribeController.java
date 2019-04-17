/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-13    xiemd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.graduation.project.healthsys.bean.Subscribe;
import com.graduation.project.healthsys.service.ISubscribeService;
import com.graduation.project.healthsys.util.DateUtils;
import com.graduation.project.healthsys.util.ParamsUtils;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
  @RequestMapping(value = "/subscribe")
  public Object subscribe(Map<String, Object> param) {

    String idCard = ParamsUtils.stringParam(param, "idCard");

    String mealId = ParamsUtils.stringParam(param, "mealId");

    String subscribeType = ParamsUtils.stringParam(param, "subscribeType");

    String subscribeTimeStr = ParamsUtils.stringParam(param, "subscribeTime");

    String branchNo = ParamsUtils.stringParam(param, "branchNo");

    Date subscribeTime = DateUtils.stringToDate(subscribeTimeStr, "yyyy-MM-dd");

    String serialNo = DateUtils.getNowDateTimeStr();


    Subscribe subscribe = Subscribe.builder()
        .id(IdWorker.getIdStr())
        .idCard(idCard)
        .mealId(mealId)
        .subscribeType(subscribeType)
        .branchNo(branchNo)
        .subscribeTime(subscribeTime)
        .serialNo(serialNo)
        .build();

    subscribeService.save(subscribe);

    Map<String, Object> result = new HashMap<>();
    result.put("subscribe", subscribe);
    return ResultUtil.success(result);
  }
}
