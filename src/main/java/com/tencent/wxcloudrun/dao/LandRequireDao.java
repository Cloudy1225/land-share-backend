package com.tencent.wxcloudrun.dao;


import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.po.LandRequirePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Mapper
@Repository
public interface LandRequireDao {

    //增加一个土地需求
    int insertLandRequire (LandRequirePO landRequirePo);

    //删除一个土地需求
    int deleteLandRequire(Integer lrid);

    // 更新土地发布信息
    int updateLandRequire(LandRequirePO landRequirePO);

    //查询某个user发布的所有土地需求
    ArrayList <LandRequirePO> selectByOpenid(String openid);



    // 通过筛选条件查询土地需求
    ArrayList <LandRequirePO> selectByFilters(LandFilterDto landFilterDto);

    // 通过lrid查询土地需求更改
    ArrayList <LandRequirePO> selectByLids(ArrayList<Integer> lrids);


    ArrayList<LandRequirePO> select10BySubmitTime(LocalDateTime submitTime);
}