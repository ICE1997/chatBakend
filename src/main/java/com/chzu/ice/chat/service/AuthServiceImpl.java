package com.chzu.ice.chat.service;

import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.dao.UserAccountDao;
import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.resp.data.LoginData;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public BaseResponse register(RegisterReq registerReq) {
        try {
            if (findUserByUserName(registerReq.getUsername()) != null) {
                return ResultUtil.registerFailedForUserExist(null);
            } else {
                userAccountDao.register(registerReq.getUsername(), registerReq.getPassword());
                return ResultUtil.registerSucceed(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.registerFailedForSystemError(null);
    }

    public BaseResponse login(LoginReq loginReq) {
        if (findUserByUserName(loginReq.getUsername()) != null) {
            UserAccount account = userAccountDao.login(loginReq.getUsername(), loginReq.getPassword());
            if (account != null) {
                LoginData loginData = new LoginData();
                return ResultUtil.loginSucceed(loginData);
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
