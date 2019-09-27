package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.bean.FriendRelation;
import com.chzu.ice.chat.bean.UserAccount;
import com.chzu.ice.chat.service.FriendRelationManageService;
import com.chzu.ice.chat.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public boolean addFriend(FriendRelation friendRelation) {
        boolean flag = false;
        UserAccount t = userAccountService.findUserByUserName(friendRelation.getUserName());
        UserAccount t1 = userAccountService.findUserByUserName(friendRelation.getFriendName());
        FriendRelation fr = friendRelationManageService.getFriendRelationByName(friendRelation.getUserName(), friendRelation.getFriendName());

        if (fr == null && !friendRelation.getFriendName().equals(friendRelation.getUserName()) && t != null && t1 != null) {
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
