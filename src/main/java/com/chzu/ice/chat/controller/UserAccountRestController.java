package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.BaseResponse;
import com.chzu.ice.chat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/userAccount")
public class UserAccountRestController {
    private AuthService authService;

    @Autowired
    public UserAccountRestController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResponse register(@RequestBody UserAccount account) {
        System.out.println("注册...");
        return authService.register(account);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(@RequestBody UserAccount userAccount) {
        System.out.println("开始验证...");
        return authService.login(userAccount);
    }

}
