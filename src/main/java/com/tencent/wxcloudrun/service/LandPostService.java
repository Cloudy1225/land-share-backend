package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.dto.LandRecommendDto;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface LandPostService {

    // 新建土地发布
    int createLandPost(LandPostPO landPostPO);

    // 通过openid查询我的发布
    ArrayList<LandPostVO> getMyLandPosts(String openid);

    // 更新土地发布信息
    int updateLandPost(LandPostPO landPostPO);

    // 删除土地发布
    int deleteLandPost(Integer lid);

    // 首页获取10条土地信息
    ArrayList<LandPostVO> get10LandPosts(LocalDateTime submitTime);

    // 通过筛选条件获取土地
    ArrayList<LandPostVO> getLandPostsByFilters(LandFilterDto landFilterDto);

    // 通过土地lid获取土地
    ArrayList<LandPostVO> getLandPostsByLids(ArrayList<Integer> lids);

    /**
     * 推荐土地
     * @param landRecommendDto 已知土地信息
     * @return 推荐土地列表
     */
    ArrayList<LandPostVO> recommendLandPosts(LandRecommendDto landRecommendDto);
}
