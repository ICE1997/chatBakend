package com.chzu.ice.chat.service.impl;

import com.chzu.ice.chat.dao.UserAccountDao;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.resp.data.LoginData;
import com.chzu.ice.chat.service.AuthService;
import com.chzu.ice.chat.util.JavaTokenUtil;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author mason
 * AuthService的实现，用于注册、登录
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JavaTokenUtil tokenUtil;

    @Override
    public BaseResponse register(RegisterReq registerReq) {
        try {
            if (getUserByUserName(registerReq.getUsername()) != null) {
                return ResultUtil.registerFailedForUserExist(null);
            } else {
                userAccountDao.register(registerReq.getUsername(), new BCryptPasswordEncoder().encode(registerReq.getPassword()));
                return ResultUtil.registerSucceed(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.registerFailedForSystemError(null);
    }

    @Override
    public BaseResponse login(LoginReq loginReq) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getUsername());
        String accessToken = tokenUtil.generateAccessToken(userDetails);
        String refreshToken = tokenUtil.generateRefreshToken(userDetails);
        LoginData loginData = new LoginData();
        loginData.refresh_token = refreshToken;
        loginData.access_token = accessToken;
        return ResultUtil.loginSucceed(loginData);
    }

    @Override
    public BaseResponse getAccessToken(String refreshToken) {
        return null;
    }

    @Override
    public UserAccount getUserByUserName(String username) {
        return userAccountDao.getUserByUserName(username);
    }
}
