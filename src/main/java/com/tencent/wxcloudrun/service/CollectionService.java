package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.CollectionDto;
import com.tencent.wxcloudrun.model.po.CollectionPO;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.CollectionVO;

import java.util.ArrayList;


public interface CollectionService {

    // 增加一条收藏记录
    int insertCollection(CollectionPO collectionPO);

    // 通过cid删除一条收藏记录
    int deleteByCid(Integer cid);

    // 通过openid和lid删除一条收藏记录
    int deleteByOpenidAndLid(CollectionDto collectionDto);

    // 通过openid和lid查找收藏记录
    CollectionPO selectByOpenidAndLid(CollectionDto collectionDto);

    // 获取我的收藏
    CollectionVO getMyCollection(String openid);
}
