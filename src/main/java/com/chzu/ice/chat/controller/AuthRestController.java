package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author mason
 */
@RestController
@RequestMapping(value = "/api/userAccount")
public class AuthRestController {
    private AuthService authService;

    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "register")
    public BaseResponse register(@RequestBody RegisterReq registerReq) {
        System.out.println("注册...");
        System.out.println("用户名: " + registerReq.getUsername());
        System.out.println("密码：" + registerReq.getPassword());
        return authService.register(registerReq);
    }

    @PostMapping(value = "login")
    public BaseResponse login(@RequestBody @Validated LoginReq loginReq) {
        System.out.println("开始验证...");
        System.out.println("用户名: " + loginReq.getUsername());
        System.out.println("密码: " + loginReq.getPassword());
        System.out.println("公钥: " + loginReq.getPublicKey());
        return authService.login(loginReq);
    }

    @PostMapping(value = "getAccessToken")
    public BaseResponse getAccessToken() {
        return authService.getAccessToken();
    }
}
