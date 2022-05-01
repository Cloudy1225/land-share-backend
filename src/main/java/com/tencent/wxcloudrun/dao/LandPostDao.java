package com.tencent.wxcloudrun.dao;


import com.tencent.wxcloudrun.model.po.LandPostPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LandPostDao {

    // 增加一个土地发布
    int insertLandPost(LandPostPO landPostPO);
}
