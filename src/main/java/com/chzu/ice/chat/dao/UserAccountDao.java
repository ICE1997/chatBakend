package com.chzu.ice.chat.dao;

import com.chzu.ice.chat.pojo.bean.Principal;
import com.chzu.ice.chat.pojo.bean.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @author mason
 * 用户账户操作DAO
 */
@Mapper
public interface UserAccountDao {
    @Insert("insert into user_account (username,password) values (#{username},#{password})")
    void register(String username, String password);

    @Select("select username from user_account where username = #{username}")
    UserAccount getUserByUserName(String username);

    @Select("select username , password from user_account where username = #{username}")
    Principal loadUserByUserName(String username);
}
