package com.chzu.ice.chat.service;

import com.chzu.ice.chat.dao.UserAccountDao;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.req.LoginReq;
import com.chzu.ice.chat.pojo.gson.req.RegisterReq;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.resp.data.LoginData;
import com.chzu.ice.chat.util.JavaTokenUtil;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JavaTokenUtil tokenUtil;

    @Override
    public BaseResponse register(RegisterReq registerReq) {
        try {
            if (loadUserByUserName(registerReq.getUsername()) != null) {
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

    public BaseResponse login(LoginReq loginReq) {
        System.out.println(loginReq.getUsername());
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        System.out.println(upToken);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getUsername());
        System.out.println(userDetails.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        System.out.println(token);


//        if (loadUserByUserName(loginReq.getUsername()) != null) {
//            UserAccount account = userAccountDao.login(loginReq.getUsername(), loginReq.getPassword());
//            if (account != null) {
//                LoginData loginData = new LoginData();
//                return ResultUtil.loginSucceed(loginData);
//            } else {
//                return ResultUtil.loginFailedForWrongPassword(null);
//            }
//        } else {
//            return ResultUtil.loginFailedForUserNotExist(null);
//        }
        LoginData loginData = new LoginData();
        loginData.topic = token;
        return ResultUtil.loginSucceed(loginData);

    }

    @Override
    public UserAccount loadUserByUserName(String username) {
        return userAccountDao.loadUserByUserName(username);
    }
}
