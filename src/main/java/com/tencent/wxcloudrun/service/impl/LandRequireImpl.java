package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.LandRequireDao;
import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.model.po.LandRequirePO;
import com.tencent.wxcloudrun.model.vo.LandRequireVO;
import com.tencent.wxcloudrun.service.LandRequireService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
class LandRequireImpl implements LandRequireService {

    private final LandRequireDao landRequireDao;

    @Autowired //自动注入Dao对象
    public LandRequireImpl(LandRequireDao landRequireDao){
        this.landRequireDao = landRequireDao; // 实例化DAO对象，以操作数据库
    }


    private ArrayList<LandRequireVO> po2VO(ArrayList<LandRequirePO> pos){
        ArrayList<LandRequireVO> vos = new ArrayList<>();
        String adInfo = null;
        String district = null;
        Double area = null;
        String landType = null;
        String transferType = null;
        String title = null;
        for (LandRequirePO po : pos){
            LandRequireVO vo = new LandRequireVO();
            BeanUtils.copyProperties(po, vo);
            adInfo = po.getAdInfo();
            if(adInfo.equals("//")){
                district = adInfo;
            }else {
                district = adInfo.replaceAll("/", "");
            }

            area = po.getArea();
            landType = po.getLandType().replaceAll("/", "");
            transferType = po.getTransferType();
            title = (district + "约" + area.intValue() + "亩" + landType + transferType);
            vo.setDistrict(district);
            vo.setTitle(title);
            vos.add(vo);
        }
        return vos;
    }
    @Override
    public int createLandRequire(LandRequirePO landRequirePO) {
        return landRequireDao.insertLandRequire(landRequirePO);
    }

    @Override
    public ArrayList<LandRequireVO> getMyLandRequires(String openid) {
        ArrayList<LandRequirePO> myLandRequires = landRequireDao.selectByOpenid(openid);
        return this.po2VO(myLandRequires);
    }

    @Override
    public int updateLandRequire(LandRequirePO landRequirePO) {
        return landRequireDao.updateLandRequire(landRequirePO);
    }

    @Override
    public int deleteLandRequire(Integer lid) {
        return landRequireDao.deleteLandRequire(lid);
    }

    @Override
    public ArrayList<LandRequireVO> get10LandRequire(LocalDateTime submitTime) {
        ArrayList<LandRequirePO> landRequirePOS =  landRequireDao.select10BySubmitTime(submitTime);
        return this.po2VO(landRequirePOS);
    }

    @Override
    public ArrayList<LandRequireVO> getLandRequiresByFilters(LandFilterDto landFilterDto) {
        ArrayList<LandRequirePO> landRequirePOS = landRequireDao.selectByFilters(landFilterDto);
        return this.po2VO(landRequirePOS);
    }

    @Override
    public ArrayList<LandRequireVO> getLandRequiresByLids(ArrayList<Integer> lrids) {
        ArrayList<LandRequirePO> landRequirePOS = landRequireDao.selectByLids(lrids);
        return this.po2VO(landRequirePOS);
    }
}