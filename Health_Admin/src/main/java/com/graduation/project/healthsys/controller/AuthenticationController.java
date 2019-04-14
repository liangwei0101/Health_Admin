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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private IUserService userService;
    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public Object login(Map<String, Object> param) {
        String phone = ParamsUtils.stringParam(param, "phone");
        String password = ParamsUtils.stringParam(param, "password");
        if (Objects.isNull(phone) || Objects.equals("", phone)) {
            throw new HtException(Resultenum.MISSING_PARAM, "请输入登录名");
        }

        if (Objects.isNull(password) || Objects.equals("", phone)) {
            throw new HtException(Resultenum.MISSING_PARAM, "请输入密码");
        }
        String pwd = Md5Util.create32Md5(password);
        User user = userService.getOne(new QueryWrapper<User>().eq("phone", phone));
        if (Objects.isNull(user)) {
            throw new HtException(Resultenum.NO_USER);
        }
        if (!Objects.equals(pwd, user.getPwd())) {
            throw new HtException(Resultenum.PWD_ERROR);
        }
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();
        resultMap.put("code",200);
        data.put("token","admin-token");
        data.put("user", user);
        resultMap.put("data",data);
        return resultMap;
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
