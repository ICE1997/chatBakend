package com.chzu.ice.chat.util;

import com.chzu.ice.chat.pojo.FriendRelationRep;
import com.chzu.ice.chat.pojo.ResponseJ;

import java.util.List;

public class ResultUtil {
    public static ResponseJ<Object> registerSucceed(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10101", "注册成功!");
        responseJ.setData(obj);
        return responseJ;
    }


    public static ResponseJ<Object> registerFailedForUserExist(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10102", "注册失败，用户已存在!");
        responseJ.setData(obj);
        return responseJ;
    }

    public static ResponseJ<Object> registerFailedForSystemError(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10103", "注册失败，错误未知");
        responseJ.setData(obj);
        return responseJ;
    }

    public static ResponseJ loginSuccess(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10201", "登录成功!");
        responseJ.setData(obj);
        return responseJ;
    }

    public static ResponseJ<Object> loginFailedForUserNotExist(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10202", "登录失败，用户不存在!");
        responseJ.setData(obj);
        return responseJ;
    }

    public static ResponseJ<Object> loginFailedForWrongPassword(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10203", "登录失败，密码错误!");
        responseJ.setData(obj);
        return responseJ;
    }

    public static ResponseJ<Object> addFriendSucceed(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10301", "新增好友成功!");
        responseJ.setData(obj);
        return responseJ;
    }

    public static ResponseJ<Object> addFriendFailedForNoSuchUser(Object obj) {
        ResponseJ<Object> responseJ = new ResponseJ<>("10302", "新增失败,无此用户!");
        responseJ.setData(obj);
        return responseJ;
    }

    public static ResponseJ<List<FriendRelationRep>> loadFriendsSucceed(List<FriendRelationRep> friends) {
        ResponseJ<List<FriendRelationRep>> responseJ = new ResponseJ<>("10401", "获取朋友成功!");
        responseJ.setData(friends);
        return responseJ;
    }
}
