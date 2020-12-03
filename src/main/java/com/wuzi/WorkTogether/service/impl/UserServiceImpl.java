package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.UserDao;
import com.wuzi.WorkTogether.domain.User;
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

    @Override
    public int registerUser(User user) {
        List<User> users = findAllUser();

        for(User nowUser : users)
        {
            if (user.getUserName().equals(nowUser.getUserName()))
            {
                return 1;
            }
            else if(user.getUserTel().equals(nowUser.getUserTel()))
            {
                return 2;
            }
            else if(!testUserTel(user.getUserTel())){
                return 3;
            }
        }
        if(user.getUserPwd().length() < 8)
        {
            return 4;
        }
        userDao.addUser(user);
        return 0;
    }

    @Override
    public User loginUser(String userTel,String userPwd) {
        User user = userDao.findUserByTel(userTel);
        if(user == null || !user.getUserPwd().equals(userPwd)){
            return null;
        }
        return user;
    }

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
    public boolean changeUserName(String userName, String userTel) {
        List<User> users = findAllUser();

        for(User nowUser : users)
        {
            if (userName.equals(nowUser.getUserName()))
            {
                return false;
            }
        }
        userDao.changeUserName(userName,userTel);
        return true;
    }
}
