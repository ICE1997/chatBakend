package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.UserAccount;
import com.chzu.ice.chat.dao.UserAccountDao;
import com.chzu.ice.chat.pojo.LoginResponse;
import com.chzu.ice.chat.pojo.ResponseJ;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public ResponseJ register(UserAccount userAccount) {
        try {
            if (findUserByUserName(userAccount.getUsername()) != null) {
                return ResultUtil.registerFailedForUserExist(null);
            } else {
                userAccountDao.register(userAccount);
                return ResultUtil.registerSucceed(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.registerFailedForSystemError(null);
    }

    public ResponseJ login(UserAccount userAccount) {
        UserAccount t = findUserByUserName(userAccount.getUsername());
        if (t != null) {
            UserAccount account = userAccountDao.login(userAccount);
            if (account != null) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.topic = account.getTopic();
                return ResultUtil.loginSuccess(loginResponse);
            } else {
                return ResultUtil.loginFailedForWrongPassword(null);
            }
        } else {
            return ResultUtil.loginFailedForUserNotExist(null);
        }
    }

    @Override
    public UserAccount findUserByUserName(String username) {
        return userAccountDao.findUserByUserName(username);
    }
}
