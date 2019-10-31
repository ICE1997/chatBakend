package com.chzu.ice.chat.service.impl;

import com.chzu.ice.chat.dao.UserAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author mason
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountDao.loadUserByUserName(username);
    }
}
