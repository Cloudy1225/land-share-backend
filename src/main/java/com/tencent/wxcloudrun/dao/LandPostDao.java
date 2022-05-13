package com.tencent.wxcloudrun.dao;


import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
@Mapper
public interface LandPostDao {

    // 增加一个土地发布
    int insertLandPost(LandPostPO landPostPO);

    // 通过openid查询用户所有的LandPost
    ArrayList<LandPostPO> selectByOpenid(String openid);

    // 更新土地发布信息
    int updateLandPost(LandPostPO landPostPO);

    // 删除土地发布
    int deleteLandPost(Integer lid);

    // 返回<=submitTime的10条土地信息，用于首页展示
    ArrayList<LandPostPO> select10BySubmitTime(LocalDateTime submitTime);

    //
    ArrayList<LandPostPO> selectByFilters(LandFilterDto landFilterDto);
}
