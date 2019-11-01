package com.chzu.ice.chat.service.impl;

import com.chzu.ice.chat.mapper.FriendRelationManageMapper;
import com.chzu.ice.chat.pojo.bean.FriendRelation;
import com.chzu.ice.chat.pojo.gson.resp.data.LoadAllFriendRelationsData;
import com.chzu.ice.chat.service.FriendRelationManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mason
 */
@Service
public class FriendRelationManageServiceImpl implements FriendRelationManageService {
    @Autowired
    private FriendRelationManageMapper friendRelationManageMapper;

    @Override
    public void addFriendRelation(FriendRelation friendRelation) {
        friendRelationManageMapper.addFriendRelation(friendRelation);
    }

    @Override
    public FriendRelation findFriendRelationByName(String username, String friendName) {
        return friendRelationManageMapper.findFriendRelationByName(username, friendName);
    }

    @Override
    public List<LoadAllFriendRelationsData> getAllFriendRelationsByUserName(String username) {
        System.out.println("getAll:" + username);
        return friendRelationManageMapper.getAllFriendRelationsByUserName(username);
    }
}
