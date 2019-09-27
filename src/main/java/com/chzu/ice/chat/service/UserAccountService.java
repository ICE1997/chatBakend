package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.UserAccount;

public interface UserAccountService {
    boolean register(UserAccount userAccount);
    UserAccount login(UserAccount userAccount);
    UserAccount findUserByUserName(String username);
}
