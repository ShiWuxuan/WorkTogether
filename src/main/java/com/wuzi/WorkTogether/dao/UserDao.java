package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/11/30 12:48
 * @lastEditor
 */
@Repository
public interface UserDao {

    @Select("select * from user")
    public List<User> findAllUser();

    @Insert("insert into user (userId,userName,userPwd,userTel,teamName)values(#{userId},#{userName},#{userPwd},#{userTel},#{teamName})")
    public void addUser(User user);

    @Select("select * from user where userName = #{userName} ")
    public User findUserByName(String userName);

}
