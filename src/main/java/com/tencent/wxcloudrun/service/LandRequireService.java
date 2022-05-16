package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.model.po.LandRequirePO;
import com.tencent.wxcloudrun.model.vo.LandRequireVO;

import java.time.LocalDateTime;
import java.util.ArrayList;


public interface LandRequireService {

    // 发布土地需求
    int createLandRequire(LandRequirePO landRequirePO);

    // 通过openid查询我的需求
    ArrayList<LandRequireVO> getMyLandRequires(String openid);

    // 更新土地需求信息
    int updateLandRequire(LandRequirePO landRequirePO);

    // 删除土地需求
    int deleteLandRequire(Integer lid);

    // 首页获取10条土地需求信息
    ArrayList<LandRequireVO> get10LandRequire(LocalDateTime submitTime);

    // 通过筛选条件获取土地需求
    ArrayList<LandRequireVO> getLandRequiresByFilters(LandFilterDto landFilterDto);

    // 通过土地lrid获取土地
    ArrayList<LandRequireVO> getLandRequiresByLids(ArrayList<Integer> lrids);
}
