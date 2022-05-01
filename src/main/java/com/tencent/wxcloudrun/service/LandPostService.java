package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.po.LandPostPO;

public interface LandPostService {

    // 新建土地发布
    int createLandPost(LandPostPO landPostPO);
}
