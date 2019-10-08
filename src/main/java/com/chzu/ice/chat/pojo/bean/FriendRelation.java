package com.chzu.ice.chat.pojo.bean;

public class FriendRelation {
    private int id;
    private String userName;
    private String friendName;
    private String friendTopic;

    public FriendRelation() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendTopic() {
        return friendTopic;
    }

    public void setFriendTopic(String friendTopic) {
        this.friendTopic = friendTopic;
    }
}