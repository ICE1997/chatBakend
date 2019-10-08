package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.pojo.bean.FriendRelation;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.FriendRelationResp;
import com.chzu.ice.chat.pojo.gson.FriendRelationReq;
import com.chzu.ice.chat.pojo.gson.BaseResponse;
import com.chzu.ice.chat.service.FriendRelationManageService;
import com.chzu.ice.chat.service.AuthService;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/friend")
public class FriendRestController {
    private AuthService authService;
    private FriendRelationManageService friendRelationManageService;

    @Autowired
    public FriendRestController(AuthService authService, FriendRelationManageService friendRelationManageService) {
        this.authService = authService;
        this.friendRelationManageService = friendRelationManageService;
    }

    @RequestMapping(value = "/loadFriends", method = RequestMethod.POST)
    public BaseResponse<List<FriendRelationResp>> loadFriends(@RequestBody FriendRelationReq relationReq) {
        System.out.println(relationReq.userName);
        List<FriendRelationResp> relationResponses = friendRelationManageService.getAllFriendRelationsByUserName(relationReq.userName);
        return ResultUtil.loadFriendsSucceed(relationResponses);
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public boolean addFriend(FriendRelation friendRelation) {
        boolean flag = false;
        UserAccount t1 = authService.findUserByUserName(friendRelation.getFriendName());
        FriendRelation fr = friendRelationManageService.findFriendRelationByName(friendRelation.getUserName(), friendRelation.getFriendName());

        if (fr == null && !friendRelation.getFriendName().equals(friendRelation.getUserName()) && t1 != null) {
            try {
                friendRelation.setFriendTopic(t1.getTopic());
                friendRelationManageService.addFriendRelation(friendRelation);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
