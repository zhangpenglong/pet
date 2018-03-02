package com.pipichongwu.modules.user.controller;

import com.pipichongwu.modules.user.model.User;
import com.pipichongwu.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dongao on 2018/1/27.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(){
        User user = userService.getUser(1);
        return user.getUserName();
    }

}
