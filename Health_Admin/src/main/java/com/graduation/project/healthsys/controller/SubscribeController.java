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
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

  @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
  public Object getSubscribe()
  {
     List<Subscribe> subscribeList = subscribeService.list();
     return ResultUtil.success(subscribeList);
  }

  @RequestMapping(value = "/subscribe/{idCard}", method = RequestMethod.GET)
  public Object getSubscribeDetail(@PathVariable("idCard") String idCard)
  {
    return null;
  }

  @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
  public Object subscribe(@RequestBody Subscribe subscribe) throws ParseException {
    subscribe.setId(IdWorker.getIdStr());
    subscribeService.save(subscribe);
    return ResultUtil.success(subscribe);
  }


}
