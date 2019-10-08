package com.chzu.ice.chat.service;

import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;

public interface AuthService {
    BaseResponse register(RegisterReq registerReq);

    BaseResponse login(LoginReq loginReq);

    UserAccount findUserByUserName(String username);
}
