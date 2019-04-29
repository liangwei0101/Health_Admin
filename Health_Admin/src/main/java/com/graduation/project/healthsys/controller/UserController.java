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
import com.graduation.project.healthsys.mapper.UserDao;
import com.graduation.project.healthsys.service.IUserService;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 功能说明: 前端控制器
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserDao userDao;

  @Autowired
  private IUserService userService;

  @RequestMapping(value ="/user", method = RequestMethod.GET)
  public Object getUser(){
    return ResultUtil.success(userService.list());
  }

  @RequestMapping(value ="/user/{userType}", method = RequestMethod.GET)
  public Object getUserByUserType(@PathVariable("userType") String userType){
    return ResultUtil.success(userDao.getUserByUserType(userType));
  }

  @RequestMapping(value ="/user", method = RequestMethod.POST)
  public Object add(User user){
    userService.save(user);
    return ResultUtil.success();
  }

  @RequestMapping(value ="/user", method = RequestMethod.PUT)
  public Object update(User user){
    userService.updateById(user);
    return ResultUtil.success();
  }

  @RequestMapping(value ="/user", method = RequestMethod.DELETE)
  public Object delete(User user){
    userService.removeById(user);
    return ResultUtil.success();
  }

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
