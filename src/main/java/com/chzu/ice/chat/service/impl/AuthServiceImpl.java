package com.chzu.ice.chat.service.impl;

import com.chzu.ice.chat.mapper.UserAccountMapper;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.resp.data.LoginData;
import com.chzu.ice.chat.service.AuthService;
import com.chzu.ice.chat.util.JwtUtil;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author mason
 * AuthService的实现，用于注册、登录
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil tokenUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse register(RegisterReq registerReq) {
        String username = registerReq.getUsername();
        String password = registerReq.getPassword();
        try {
            if (getUserByUserName(username) != null) {
                return ResultUtil.registerFailedForUserExist(null);
            } else {
                String topic = "usr" + UUID.randomUUID().toString().replaceAll("-", "");
                userAccountMapper.addTopic(username, topic);
                userAccountMapper.register(username, new BCryptPasswordEncoder().encode(password));
                return ResultUtil.registerSucceed(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.registerFailedForSystemError(null);
    }

    @Override
    public BaseResponse login(LoginReq loginReq) {
        if (getUserByUserName(loginReq.getUsername()) != null) {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getUsername());
            String accessToken = tokenUtil.generateAccessToken(userDetails);
            String refreshToken = tokenUtil.generateRefreshToken(userDetails);
            String topic = userAccountMapper.getTopicByUsername(loginReq.getUsername());
            LoginData loginData = new LoginData();
            loginData.refreshToken = refreshToken;
            loginData.accessToken = accessToken;
            loginData.topic = topic;
            if (userAccountMapper.getPublicKeyByUsername(loginReq.getUsername()) == null) {
                userAccountMapper.addPublicKeyWithUsername(loginReq.getUsername(), loginReq.getPublicKey());
            } else {
                userAccountMapper.updatePublicKeyWithUsername(loginReq.getUsername(), loginReq.getPublicKey());
            }
            return ResultUtil.loginSucceed(loginData);

        } else {
            return ResultUtil.loginFailedForUserNotExist(null);
        }
    }

    @Override
    public BaseResponse getAccessToken() {
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        UserDetails u = (UserDetails) contextHolder.getAuthentication().getDetails();
        UserDetails userDetails = userDetailsService.loadUserByUsername(u.getUsername());
        String accessToken = tokenUtil.generateAccessToken(userDetails);
        return ResultUtil.getAccessTokenSucceed(accessToken);
    }

    @Override
    public UserAccount getUserByUserName(String username) {
        return userAccountMapper.getUserByUserName(username);
    }
}
