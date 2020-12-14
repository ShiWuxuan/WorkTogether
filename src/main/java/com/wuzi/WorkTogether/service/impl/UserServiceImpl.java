package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.UserDao;
import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.domain.dto.UserDto;
import com.wuzi.WorkTogether.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/11/30 13:32
 * @lastEditor
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    /**
     * 用户注册（用户名，手机号都不可重复，手机号得符合规定）
     * @param userTel
     * @param userPwd
     * @param confirmUserPwd
     * @return
     */
    @Override
    public int registerUser(String userTel,String userPwd,String confirmUserPwd) {
        List<User> users = findAllUser();
        for(User nowUser : users)
        {
            if(userTel.equals(nowUser.getUserTel()))
            {
                return 1;
            }
            else if(!testUserTel(userTel)){
                return 2;
            }
        }
        if(userPwd.length() < 8)
        {
            return 3;
        }
        else if(!userPwd.equals(confirmUserPwd))
        {
            System.out.println(userPwd);
            System.out.println(confirmUserPwd);
            return 4;
        }
        User user = new User();
        user.setUserName("用户"+userTel);
        user.setUserTel(userTel);
        user.setUserPwd(userPwd);
        userDao.addUser(user);
        return 0;
    }

    /**
     * 用户登录
     * @param userTel
     * @param userPwd
     * @return
     */
    @Override
    public User loginUser(String userTel,String userPwd) {
        User user = userDao.findUserByTel(userTel);
        if(user == null || !user.getUserPwd().equals(userPwd)){
            return null;
        }
        return user;
    }

    /**
     * 判断用户输入的手机号是否合法
     * @param userTel
     * @return
     */
    @Override
    public boolean testUserTel(String userTel) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(userTel.length()!=11)
        {
            return false;
        }
        else
        {
            Pattern telPattern = Pattern.compile(regex);
            Matcher m = telPattern.matcher(userTel);
            return m.matches();
        }
    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }

    /**
     * 更改用户名
     * @param userName
     * @param userId
     * @return
     */
    @Override
    public boolean changeUserName(String userName, Integer userId) {
        List<User> users = findAllUser();

        for(User nowUser : users)
        {
            if (userName.equals(nowUser.getUserName()))
            {
                return false;
            }
        }
        userDao.changeUserName(userName,userId);
        return true;
    }

    /**
     * 更改用户密码
     * @param userPwd
     * @param userId
     * @param newUserPwd
     * @param confirmUserPwd
     * @return
     */
    @Override
    public Integer changeUserPwd(String userPwd, Integer userId,String newUserPwd,String confirmUserPwd) {
        if(newUserPwd.length()<8)
        {
            return 1;
        }
        User user = userDao.findUserById(userId);
        if(user.getUserPwd().equals(newUserPwd))
        {
            return 2;
        }
        if(!user.getUserPwd().equals(userPwd))
        {
            return 3;
        }
        if(!newUserPwd.equals(confirmUserPwd))
        {
            return 4;
        }
        userDao.changeUserPwd(newUserPwd,userId);
        return 0;
    }

}

