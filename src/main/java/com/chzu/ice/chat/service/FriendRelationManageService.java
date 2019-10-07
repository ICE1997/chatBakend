package com.chzu.ice.chat.service;

import com.chzu.ice.chat.pojo.bean.FriendRelation;
import com.chzu.ice.chat.pojo.gson.FriendRelationResp;

import java.util.List;

public interface FriendRelationManageService {
    void addFriendRelation(FriendRelation friendRelation);
    FriendRelation findFriendRelationByName(String username, String friendName);
    List<FriendRelationResp> getAllFriendRelationsByUserName(String username);
}
