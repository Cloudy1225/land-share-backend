package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.LandPostDao;
import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.service.LandPostService;
import org.springframework.beans.BeanUtils;
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


    private ArrayList<LandPostVO> poToVO(ArrayList<LandPostPO> pos){

        ArrayList<LandPostVO> vos = new ArrayList<>();

        String adInfo = null;
        String district = null;
        Double area = null;
        String landType = null;
        String transferType = null;
        String title = null;
        String pictureFileID = null;
        String defaultPicture = null;
        for (LandPostPO po : pos){
            LandPostVO vo = new LandPostVO();
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

            pictureFileID = po.getPictureFileID();
            if(pictureFileID != null && !pictureFileID.equals("")){
                defaultPicture = pictureFileID.split("\\|")[0];
            }

            vo.setDistrict(district);
            vo.setTitle(title);
            vo.setDefaultPicture(defaultPicture);

            vos.add(vo);
        }

        return vos;
    }

    @Override
    public int createLandPost(LandPostPO landPostPO) {
        return landPostDao.insertLandPost(landPostPO);
    }

    @Override
    public ArrayList<LandPostVO> getMyLandPosts(String openid) {
        ArrayList<LandPostPO> myLandPosts = landPostDao.selectByOpenid(openid);
        return this.poToVO(myLandPosts);
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
        return this.poToVO(landPostPOS);
    }

    @Override
    public ArrayList<LandPostVO> getLandPostsByFilters(LandFilterDto landFilterDto) {
        ArrayList<LandPostPO> landPostPOS = landPostDao.selectByFilters(landFilterDto);
        return this.poToVO(landPostPOS);
    }

    @Override
    public ArrayList<LandPostVO> getLandPostsByLids(ArrayList<Integer> lids) {
        ArrayList<LandPostPO> landPostPOS = landPostDao.selectByLids(lids);
        return this.poToVO(landPostPOS);
    }

}
