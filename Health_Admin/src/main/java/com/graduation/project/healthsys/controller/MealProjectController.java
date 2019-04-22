package com.graduation.project.healthsys.controller;

import com.graduation.project.healthsys.bean.User;
import com.graduation.project.healthsys.service.IUserService;
import com.graduation.project.healthsys.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MealProjectController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value ="/user", method = RequestMethod.GET)
    public Object getUser(){
        return ResultUtil.success(userService.list());
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
}
