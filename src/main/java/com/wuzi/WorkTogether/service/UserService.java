package com.wuzi.WorkTogether.service;

import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.domain.dto.UserDto;

import java.util.List;
/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/11/30 13:20
 * @lastEditor
 */
public interface UserService {

    public int registerUser(String userTel,String userPwd,String confirmPwd);
    public User loginUser(String userTel,String userPwd);
    public List<User> findAllUser();
    public boolean testUserTel(String userTel);
    public boolean changeUserName(String userName,Integer userId);
    public User findUserById(Integer userId);
    public Integer changeUserPwd(String userPwd,Integer userId,String newUserPwd,String confirmUserPwd);
}

