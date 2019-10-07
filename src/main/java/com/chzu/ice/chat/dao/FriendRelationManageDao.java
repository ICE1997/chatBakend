package com.chzu.ice.chat.dao;

import com.chzu.ice.chat.pojo.bean.FriendRelation;
import com.chzu.ice.chat.pojo.gson.FriendRelationResp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendRelationManageDao {
    @Insert("insert into friend_relation (user_name,friend_name,friend_topic) values(#{userName},#{friendName},#{friendTopic})")
    void addFriendRelation(FriendRelation friendRelation);

    @Select("select * from friend_relation where user_name = #{username} ")
    List<FriendRelationResp> getAllFriendRelationsByUserName(String username);

    @Select("select * from friend_relation where user_name = #{username} and friend_name = #{friendName}")
    FriendRelation findFriendRelationByName(String username, String friendName);
}
