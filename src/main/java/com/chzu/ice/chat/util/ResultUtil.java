package com.chzu.ice.chat.util;

import com.chzu.ice.chat.pojo.gson.FriendRelationResp;
import com.chzu.ice.chat.pojo.gson.BaseResponse;

import java.util.List;

public class ResultUtil {
    public static BaseResponse<Object> registerSucceed(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10101", "注册成功!");
        baseResponse.setData(obj);
        return baseResponse;
    }


    public static BaseResponse<Object> registerFailedForUserExist(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10102", "注册失败，用户已存在!");
        baseResponse.setData(obj);
        return baseResponse;
    }

    public static BaseResponse<Object> registerFailedForSystemError(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10103", "注册失败，错误未知");
        baseResponse.setData(obj);
        return baseResponse;
    }

    public static BaseResponse loginSuccess(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10201", "登录成功!");
        baseResponse.setData(obj);
        return baseResponse;
    }

    public static BaseResponse<Object> loginFailedForUserNotExist(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10202", "登录失败，用户不存在!");
        baseResponse.setData(obj);
        return baseResponse;
    }

    public static BaseResponse<Object> loginFailedForWrongPassword(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10203", "登录失败，密码错误!");
        baseResponse.setData(obj);
        return baseResponse;
    }

    public static BaseResponse<Object> addFriendSucceed(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10301", "新增好友成功!");
        baseResponse.setData(obj);
        return baseResponse;
    }

    public static BaseResponse<Object> addFriendFailedForNoSuchUser(Object obj) {
        BaseResponse<Object> baseResponse = new BaseResponse<>("10302", "新增失败,无此用户!");
        baseResponse.setData(obj);
        return baseResponse;
    }

    public static BaseResponse<List<FriendRelationResp>> loadFriendsSucceed(List<FriendRelationResp> friends) {
        BaseResponse<List<FriendRelationResp>> baseResponse = new BaseResponse<>("10401", "获取朋友成功!");
        baseResponse.setData(friends);
        return baseResponse;
    }
}
