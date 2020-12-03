package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from user where userTel = #{userTel} ")
    public User findUserByTel(String userTel);

    @Update("update user set teamId = #{teamId} where userTel = #{userTel} ")
    public void addTeamByName(@Param("userTel") String userTel,@Param("teamId") Integer teamId);

    @Update("update user set userName = #{userName} where userTel = #{userTel}")
    public void changeUserName(@Param("userName") String userName,@Param("userTel") String userTel);
}
