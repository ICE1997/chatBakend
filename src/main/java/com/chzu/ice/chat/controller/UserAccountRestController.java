package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;
import com.chzu.ice.chat.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/userAccount")
public class UserAccountRestController {
    private UserAccountService userAccountService;

    @Autowired
    public UserAccountRestController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResponse register(@RequestBody RegisterReq registerReq) {
        System.out.println("注册...");
        return userAccountService.register(registerReq);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(@RequestBody LoginReq loginReq) {
        System.out.println("开始验证...");
        return userAccountService.login(loginReq);
    }

}
