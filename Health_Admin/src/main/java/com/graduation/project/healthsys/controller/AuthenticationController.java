package com.graduation.project.healthsys.controller;

import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public Object login() {
        Map<String, Object> temp = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("code",200);
        temp.put("token","admin-token");
        data.put("data",temp);
        return data;
    }

    @RequestMapping(value ="/userInfo", method = RequestMethod.GET)
    public Object token() {
//        String[] aa= new String[1];
        List<String> aa = new LinkedList<>();
        aa.add("admin");
//        aa[0] = "admin";
        Map<String, Object> temp = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("code",200);
        temp.put("roles",aa);
        temp.put("introduction","I am a super administrator");
        temp.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        temp.put("name","Super Admin");
        data.put("data",temp);

        return data;
    }

    @RequestMapping(value ="/logout", method = RequestMethod.POST)
    public Object logout() {
        return ResultUtil.success();
    }
}
