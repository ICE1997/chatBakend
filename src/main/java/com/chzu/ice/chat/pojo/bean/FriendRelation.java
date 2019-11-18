package com.chzu.ice.chat.pojo.bean;

/**
 * @author mason
 */
public class FriendRelation {
    private String userName;
    private String friendName;
    private String friendTopic;
    private String friendPublicKey;

    public FriendRelation() {
    }

    public String getFriendPublicKey() {
        return friendPublicKey;
    }

    public void setFriendPublicKey(String friendPublicKey) {
        this.friendPublicKey = friendPublicKey;
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
