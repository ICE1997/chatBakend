package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.FriendRelation;
import com.chzu.ice.chat.dao.FriendRelationManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRelationManageServiceImpl implements FriendRelationManageService {
    @Autowired
    private FriendRelationManageDao friendRelationManageDao;

    @Override
    public void addFriendRelation(FriendRelation friendRelation) {
        friendRelationManageDao.addFriendRelation(friendRelation);
    }

    @Override
    public FriendRelation getFriendRelationByName(String username, String friendName) {
        return friendRelationManageDao.getFriendRelationByName(username,friendName);
    }
}
