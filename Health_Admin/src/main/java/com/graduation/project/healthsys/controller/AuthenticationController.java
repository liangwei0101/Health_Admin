package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.project.healthsys.bean.User;
import com.graduation.project.healthsys.enums.Resultenum;
import com.graduation.project.healthsys.exception.HtException;
import com.graduation.project.healthsys.service.IUserService;
import com.graduation.project.healthsys.util.Md5Util;
import com.graduation.project.healthsys.util.ParamsUtils;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private IUserService userService;
    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public Object login(@RequestBody Map<String,Object> reqMap) {

        List<User> userList = userService.list();
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();

        String username = reqMap.get("username").toString();
        String password = reqMap.get("password").toString();

        List<User> userListTemp = userList.stream().filter((User u) -> u.getPhone().equals(username)).collect(Collectors.toList());

        if(!userListTemp.isEmpty() && userListTemp.get(0).getPwd().equals(password)){
            resultMap.put("code",200);
            data.put("token",username);
            resultMap.put("data",data);
        }else{
            resultMap.put("code",200);
            data.put("error","账户密码有误");
            resultMap.put("data",data);
        }
        return resultMap;
    }

    @RequestMapping(value ="/userInfo", method = RequestMethod.GET)
    public Object token(HttpServletRequest request) {

        Cookie[] cookies =  request.getCookies();
        List<User> userList = userService.list();
        List<User> userListTemp = new ArrayList<>();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("Admin-Token")){
                    userListTemp = userList.stream().filter((User u) -> u.getPhone().equals(cookie.getValue())).collect(Collectors.toList());
                }
            }
        }

        List<String> list = new LinkedList<>();
//        list.add("admin");
        list.add(userListTemp.get(0).getUserType());
        Map<String, Object> temp = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("code",200);
        temp.put("roles",list);
        temp.put("introduction","I am a " + userListTemp.get(0).getUserType());
        temp.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        temp.put("name",userListTemp.get(0).getRealName());
        data.put("data",temp);

        return data;
    }

    @RequestMapping(value ="/logout", method = RequestMethod.POST)
    public Object logout() {
        return ResultUtil.success();
    }
}
