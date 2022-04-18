package com.tencent.wxcloudrun.dao;



import com.tencent.wxcloudrun.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    // 增加用户
    int insertUser(User user); // 返回插入的行数，感觉可以为void

    // 通过openid查询用户
    User selectByOpenid(String openid);
}
