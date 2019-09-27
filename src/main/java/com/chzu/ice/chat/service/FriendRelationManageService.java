package com.chzu.ice.chat.service;

import com.chzu.ice.chat.bean.FriendRelation;

public interface FriendRelationManageService {
    void addFriendRelation(FriendRelation friendRelation);
    FriendRelation getFriendRelationByName(String username,String friendName);
}
