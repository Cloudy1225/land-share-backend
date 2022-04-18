package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.po.User;

public interface UserService {

    // 创建用户
    int createUser(User user);

    // 通过openid查询用户
    User findByOpenid(String openid);

    // 注销用户
    int deleteUser(String openid);

    // 完成实名
    int realName(User user);
}
