package com.wuzi.WorkTogether.service;

import com.wuzi.WorkTogether.domain.User;

import java.util.List;
/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/11/30 13:20
 * @lastEditor
 */
public interface UserService {

    public int registerUser(User user);
    public User loginUser(String userName,String userPwd);
    public List<User> findAllUser();
}
