package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.FriendRelation;
import com.chzu.ice.chat.dao.FriendRelationManageDao;
import com.chzu.ice.chat.pojo.FriendRelationRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRelationManageServiceImpl implements FriendRelationManageService {
    @Autowired
    private FriendRelationManageDao friendRelationManageDao;

    @Override
    public void addFriendRelation(FriendRelation friendRelation) {
        friendRelationManageDao.addFriendRelation(friendRelation);
    }

    @Override
    public FriendRelation findFriendRelationByName(String username, String friendName) {
        return friendRelationManageDao.findFriendRelationByName(username,friendName);
    }

    @Override
    public List<FriendRelationRep> getAllFriendRelationsByUserName(String username) {
        System.out.println(username);
        return friendRelationManageDao.getAllFriendRelationsByUserName(username);
    }
}
