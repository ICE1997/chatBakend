package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.UserAccount;
import com.chzu.ice.chat.dao.UserAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public boolean register(UserAccount userAccount) {
        boolean flag = false;
        try {
            userAccountDao.register(userAccount);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public UserAccount login(UserAccount userAccount) {
         return userAccountDao.login(userAccount);
    }

    @Override
    public UserAccount findUserByUserName(String username) {
        return userAccountDao.findUserByUserName(username);
    }
}
