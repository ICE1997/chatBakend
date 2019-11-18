package com.chzu.ice.chat.controller;

import com.chzu.ice.chat.pojo.bean.Principal;
import com.chzu.ice.chat.pojo.gson.req.AddFriendReq;
import com.chzu.ice.chat.pojo.gson.resp.BaseResponse;
import com.chzu.ice.chat.pojo.gson.resp.data.LoadAllFriendRelationsData;
import com.chzu.ice.chat.service.AuthService;
import com.chzu.ice.chat.service.FriendRelationManageService;
import com.chzu.ice.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        List<LoadAllFriendRelationsData> relationResponses = friendRelationManageService.getAllFriendRelationsByUserName(principal.getUsername());
        return ResultUtil.loadFriendsSucceed(relationResponses);
    }

    @PostMapping(value = "/addFriend")
    public BaseResponse addFriend(@RequestBody AddFriendReq addFriendReq) {
        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Principal principal = (Principal) securityContextHolder.getAuthentication().getPrincipal();
        String username = principal.getUsername();
        if (username.equals(addFriendReq.friendName)) {
            return ResultUtil.addFriendFailedForCantAddSelf(null);
        } else {
            if (authService.getUserByUserName(addFriendReq.friendName) == null) {
                return ResultUtil.addFriendFailedForNoSuchUser(null);
            } else {
                if (friendRelationManageService.findFriendRelationByName(username, addFriendReq.friendName) != null) {
                    return ResultUtil.addFriendFailedForAdded(null);
                } else {
                    friendRelationManageService.addFriendWithName(username, addFriendReq.friendName);
                    return ResultUtil.addFriendSucceed(null);
                }
            }
        }
    }
}
