package com.tencent.wxcloudrun.dao;


import com.tencent.wxcloudrun.dto.CollectionDto;
import com.tencent.wxcloudrun.model.po.CollectionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface CollectionDao {

    // 增加一条收藏记录
    int insertCollection(CollectionPO collectionPO);

    // 通过cid删除一条收藏记录
    int deleteByCid(Integer cid);

    // 通过openid和lid删除一条收藏记录
    int deleteByOpenidAndLid(CollectionDto collectionDto);

    // 通过openid和lid查找收藏记录
    CollectionPO selectByOpenidAndLid(CollectionDto collectionDto);

    // 通过openid查找收藏记录的lid
    ArrayList<Integer> selectLidsByOpenid(String openid);
}
