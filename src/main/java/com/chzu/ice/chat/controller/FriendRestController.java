package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.bean.FriendRelation;
import com.chzu.ice.chat.bean.UserAccount;
import com.chzu.ice.chat.pojo.FriendRelationRep;
import com.chzu.ice.chat.pojo.FriendRelationReq;
import com.chzu.ice.chat.pojo.ResponseJ;
import com.chzu.ice.chat.service.FriendRelationManageService;
import com.chzu.ice.chat.service.UserAccountService;
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
    private UserAccountService userAccountService;
    private FriendRelationManageService friendRelationManageService;

    @Autowired
    public FriendRestController(UserAccountService userAccountService, FriendRelationManageService friendRelationManageService) {
        this.userAccountService = userAccountService;
        this.friendRelationManageService = friendRelationManageService;
    }

    @RequestMapping(value = "/loadFriends", method = RequestMethod.POST)
    public ResponseJ<List<FriendRelationRep>> loadFriends(@RequestBody FriendRelationReq relationReq) {
        System.out.println(relationReq.userName);
        List<FriendRelationRep> relationResponses = friendRelationManageService.getAllFriendRelationsByUserName(relationReq.userName);
        return ResultUtil.loadFriendsSucceed(relationResponses);
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public boolean addFriend(FriendRelation friendRelation) {
        boolean flag = false;
        UserAccount t1 = userAccountService.findUserByUserName(friendRelation.getFriendName());
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
