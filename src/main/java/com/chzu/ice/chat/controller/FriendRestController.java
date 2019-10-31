package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.pojo.bean.FriendRelation;
import com.chzu.ice.chat.pojo.bean.Principal;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.resp.data.LoadAllFriendRelationsData;
import com.chzu.ice.chat.service.AuthService;
import com.chzu.ice.chat.service.FriendRelationManageService;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author mason
 */
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

    @PostMapping(value = "/loadFriends")
    public BaseResponse<List<LoadAllFriendRelationsData>> loadFriends() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Principal principal = (Principal) securityContext.getAuthentication().getPrincipal();
        System.out.println(principal.getUsername());
        List<LoadAllFriendRelationsData> relationResponses = friendRelationManageService.getAllFriendRelationsByUserName(principal.getUsername());
        return ResultUtil.loadFriendsSucceed(relationResponses);
    }

    @PostMapping(value = "/addFriend")
    public boolean addFriend(FriendRelation friendRelation) {
        boolean flag = false;
        UserAccount t1 = authService.getUserByUserName(friendRelation.getFriendName());
        FriendRelation fr = friendRelationManageService.findFriendRelationByName(friendRelation.getUserName(), friendRelation.getFriendName());
        if (fr == null && !friendRelation.getFriendName().equals(friendRelation.getUserName()) && t1 != null) {
            try {
                friendRelationManageService.addFriendRelation(friendRelation);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
