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
import com.graduation.project.healthsys.bean.User;
import com.graduation.project.healthsys.exception.HtException;
import com.graduation.project.healthsys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 功能说明: 前端控制器
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private IUserService userService;

  @GetMapping("/login")
  public Object login(@RequestParam("phone") String phone, @RequestParam(name="password", required = false) String password) {

//    String pwd = Md5Util.create32Md5(password);

    User user = userService.getOne(new QueryWrapper<User>().eq("phone", phone));
    if (Objects.isNull(user)) {
      throw new HtException(102, "用户信息不存在");
    }

    return null;
  }
	
}
