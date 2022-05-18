package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.LandPostDao;
import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.dto.LandRecommendDto;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.service.LandPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
public class LandPostSeviceImpl implements LandPostService {

    private final LandPostDao landPostDao;

    @Autowired //自动注入Dao对象
    public LandPostSeviceImpl(LandPostDao landPostDao){
        this.landPostDao = landPostDao; // 实例化DAO对象，以操作数据库
    }


    @Override
    public int createLandPost(LandPostPO landPostPO) {
        return landPostDao.insertLandPost(landPostPO);
    }

    @Override
    public ArrayList<LandPostVO> getMyLandPosts(String openid) {
        ArrayList<LandPostPO> myLandPosts = landPostDao.selectByOpenid(openid);
        return POtoVOUtil.landPostPOToVO(myLandPosts);
    }

    @Override
    public int updateLandPost(LandPostPO landPostPO) {
        return landPostDao.updateLandPost(landPostPO);
    }

    @Override
    public int deleteLandPost(Integer lid) {
        return landPostDao.deleteLandPost(lid);
    }

    @Override
    public ArrayList<LandPostVO> get10LandPosts(LocalDateTime submitTime) {
        ArrayList<LandPostPO> landPostPOS =  landPostDao.select10BySubmitTime(submitTime);
        return POtoVOUtil.landPostPOToVO(landPostPOS);
    }

    @Override
    public ArrayList<LandPostVO> getLandPostsByFilters(LandFilterDto landFilterDto) {
        ArrayList<LandPostPO> landPostPOS = landPostDao.selectByFilters(landFilterDto);
        return POtoVOUtil.landPostPOToVO(landPostPOS);
    }

    @Override
    public ArrayList<LandPostVO> getLandPostsByLids(ArrayList<Integer> lids) {
        ArrayList<LandPostPO> landPostPOS = landPostDao.selectByLids(lids);
        return POtoVOUtil.landPostPOToVO(landPostPOS);
    }

    @Override
    public ArrayList<LandPostVO> recommendLandPosts(LandRecommendDto landRecommendDto) {
        String adInfo = landRecommendDto.getAdInfo();
        adInfo = adInfo.split("/")[1];
        landRecommendDto.setAdInfo(adInfo);

        ArrayList<LandPostPO> landPostPOS = landPostDao.selectByRecommend(landRecommendDto);

        return POtoVOUtil.landPostPOToVO(landPostPOS);
    }


}
