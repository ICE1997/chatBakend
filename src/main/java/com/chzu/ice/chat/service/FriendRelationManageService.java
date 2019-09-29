package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.FriendRelation;
import com.chzu.ice.chat.pojo.FriendRelationRep;

import java.util.List;

public interface FriendRelationManageService {
    void addFriendRelation(FriendRelation friendRelation);
    FriendRelation findFriendRelationByName(String username, String friendName);
    List<FriendRelationRep> getAllFriendRelationsByUserName(String username);
}
