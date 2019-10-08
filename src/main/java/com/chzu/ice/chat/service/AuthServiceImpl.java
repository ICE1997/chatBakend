package com.chzu.ice.chat.service;

import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.dao.UserAccountDao;
import com.chzu.ice.chat.pojo.gson.LoginResp;
import com.chzu.ice.chat.pojo.gson.BaseResponse;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public BaseResponse register(UserAccount userAccount) {
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

    public BaseResponse login(UserAccount userAccount) {
        UserAccount t = findUserByUserName(userAccount.getUsername());
        if (t != null) {
            UserAccount account = userAccountDao.login(userAccount);
            if (account != null) {
                LoginResp loginResp = new LoginResp();
                loginResp.topic = account.getTopic();
                return ResultUtil.loginSuccess(loginResp);
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
