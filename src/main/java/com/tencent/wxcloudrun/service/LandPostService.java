package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.po.LandPostPO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface LandPostService {

    // 新建土地发布
    int createLandPost(LandPostPO landPostPO);

    // 通过openid查询我的发布
    ArrayList<LandPostPO> getMyLandPosts(String openid);

    // 更新土地发布信息
    int updateLandPost(LandPostPO landPostPO);

    // 删除土地发布
    int deleteLandPost(Integer lid);

    // 首页获取10条土地信息
    ArrayList<LandPostPO> get10LandPosts(LocalDateTime submitTime);
}
