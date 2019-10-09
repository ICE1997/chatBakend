package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/userAccount")
public class AuthRestController {
    private AuthService authService;

    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResponse register(@RequestBody RegisterReq registerReq) {
        System.out.println("注册...");
        return authService.register(registerReq);

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(@RequestBody LoginReq loginReq) {
        System.out.println("开始验证...");
        return authService.login(loginReq);
    }

}
