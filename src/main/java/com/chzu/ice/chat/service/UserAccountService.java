package com.chzu.ice.chat.service;

import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.BaseResponse;

public interface UserAccountService {
    BaseResponse register(UserAccount userAccount);
    BaseResponse login(UserAccount userAccount);
    UserAccount findUserByUserName(String username);
}
