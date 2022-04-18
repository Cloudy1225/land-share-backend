package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.po.User;

public interface UserService {

    // 创建用户
    int createUser(User user);

    // 通过openid查询用户
    User findByOpenid(String openid);

}
