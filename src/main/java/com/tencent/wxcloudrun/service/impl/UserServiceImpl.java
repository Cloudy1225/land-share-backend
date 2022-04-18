package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.dao.UserDao;
import com.tencent.wxcloudrun.model.po.User;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired //自动注入Dao对象
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao; // 实例化DAO对象，以操作数据库
    }


    @Override
    public int createUser(User user){
       return userDao.insertUser(user);
    }

    @Override
    public User findByOpenid(String openid) {
        return userDao.selectByOpenid(openid);
    }

}
