package com.chzu.ice.chat.dao;

import com.chzu.ice.chat.pojo.bean.User;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserAccountDao {
    @Insert("insert into user_account (username,password) values (#{username},#{password})")
    void register(String username, String password);

    @Select("select * from user_account where username = #{username} and password = #{password} limit 1")
    UserAccount login(String username, String password);

    @Select("select username from user_account where username = #{username}")
    UserAccount loadUserByUserName(String username);

    @Select("select username , password from user_account where username = #{username}")
    User getUserByUserName(String username);
}
