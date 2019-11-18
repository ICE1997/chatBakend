package com.chzu.ice.chat.mapper;

import com.chzu.ice.chat.pojo.bean.FriendRelation;
import com.chzu.ice.chat.pojo.gson.resp.data.LoadAllFriendRelationsData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mason
 */
@Mapper
public interface FriendRelationManageMapper {
    /**
     * 增加好友
     * TODO:应该给改成好友的唯一用户名即可
     *
     *
     */
    void addFriendRelation(String username,String friendName);

    /**
     * 根据用户名获取所有好友关系
     * TODO:待转化为XML形式
     *
     * @param username 用户名
     * @return 好友信息的集合
     */
    @Select("select a.username,a.friend_name,b.topic as friendTopic,c.public_key as friendPublicKey from friend_relation as a, username_topic as b, user_public_key as c where a.username = #{username} and a.friend_name = b.username and a.friend_name = c.username ")
    List<LoadAllFriendRelationsData> getAllFriendRelationsByUserName(String username);

    /**
     * 判断数据库中是否有相同的好友关系存在
     *
     * @param username   用户名
     * @param friendName 好友名
     * @return 好友关系记录
     */
    FriendRelation findFriendRelationByName(String username, String friendName);
}
