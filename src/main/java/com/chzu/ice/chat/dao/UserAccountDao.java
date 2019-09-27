package com.chzu.ice.chat.dao;

import com.chzu.ice.chat.bean.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAccountDao {
    @Insert("insert into user_account (username,password,topic) values (#{username},#{password},#{topic})")
    void register(UserAccount userAccount);

    @Select("select * from user_account where username = #{username} and password = #{password}")
    UserAccount login(UserAccount userAccount);

    @Select("select * from user_account where username = #{username}")
    UserAccount findUserByUserName(String username);
}
