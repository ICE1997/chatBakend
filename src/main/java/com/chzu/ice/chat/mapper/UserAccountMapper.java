package com.chzu.ice.chat.mapper;

import com.chzu.ice.chat.pojo.bean.Principal;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author mason
 * 用户账户操作DAO
 */
@Mapper
public interface UserAccountMapper {
    /**
     * 用于注册
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 用于根据用户名检验用户是否存在
     * TODO:需要被改成返回布尔值
     *
     * @param username 用户名
     * @return 用户账户
     */
    UserAccount getUserByUserName(String username);

    /**
     * 根据用户名获取用户（用于验证）
     *
     * @param username 用户名
     * @return 用户相关的信息，用于验证
     */
    Principal loadUserByUserName(String username);

    /**
     * 根据用户名生成一个topic
     * TODO:这样做不合适，等待新的解决方法
     *
     * @param username 用户名
     * @param topic    MQTT的Topic
     */
    void addTopic(String username, String topic);

    /**
     * 根据用户名返回该用户的Topic
     *
     * @param username 用户名
     * @return 用户topic
     */
    String getTopicByUsername(String username);

    String getPublicKeyByUsername(String username);

    void addPublicKeyWithUsername(String username, String publicKey);

    void updatePublicKeyWithUsername(String username, String publicKey);
}
