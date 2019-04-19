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
import com.graduation.project.healthsys.bean.Charge;
import com.graduation.project.healthsys.service.IChargeService;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明: 前端控制器
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ChargeController {

    @Autowired
    private IChargeService chargeService;

    @RequestMapping(value ="/charge", method = RequestMethod.GET)
    public Object get() {
        Object aa = chargeService.list();
        return ResultUtil.success(chargeService.list());
    }

    @RequestMapping(value ="/charge", method = RequestMethod.POST)
    public Object add(Charge charge) {
        charge.setId(IdWorker.getIdStr());
        chargeService.save(charge);
        return ResultUtil.success();
    }

    @RequestMapping(value ="/charge", method = RequestMethod.PUT)
    public Object update(Charge charge) {
        chargeService.updateById(charge);
        return ResultUtil.success();
    }

    @RequestMapping(value ="/charge", method = RequestMethod.DELETE)
    public Object delete(Charge charge) {
        chargeService.removeById(charge);
        return ResultUtil.success();
    }
	
}
