package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.UserDao;
import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            if (user.getUserName().equals(nowUser.getUserName())) {
                return 1;
            }
            else if(user.getUserTel().equals(nowUser.getUserTel()))
            {
                return 2;
            }
        }
        userDao.addUser(user);
        return 0;
    }

    @Override
    public User loginUser(String userName,String userPwd) {
        User user = userDao.findUserByName(userName);
        if(user == null || !user.getUserPwd().equals(userPwd)){
            return null;
        }
        return user;
    }
}
