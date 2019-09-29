package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.UserAccount;
import com.chzu.ice.chat.pojo.ResponseJ;

public interface UserAccountService {
    ResponseJ register(UserAccount userAccount);
    ResponseJ login(UserAccount userAccount);
    UserAccount findUserByUserName(String username);
}
