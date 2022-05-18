package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.po.LandRequirePO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.model.vo.LandRequireVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

public class POtoVOUtil {

    public static ArrayList<LandPostVO> landPostPOToVO(ArrayList<LandPostPO> pos) {
        ArrayList<LandPostVO> vos = new ArrayList<>();

        String address = null;
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

            address = po.getAddress();
            adInfo = po.getAdInfo();
            if(adInfo.equals("//")){
                district = address;
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


    public static ArrayList<LandRequireVO> landRequirePOToVO(ArrayList<LandRequirePO> pos) {
        ArrayList<LandRequireVO> vos = new ArrayList<>();

        String address = null;
        String adInfo = null;
        String district = null;
        Double area = null;
        String landType = null;
        String transferType = null;
        String title = null;

        for (LandRequirePO po : pos) {
            LandRequireVO vo = new LandRequireVO();
            BeanUtils.copyProperties(po, vo);
            address = po.getAddress();
            adInfo = po.getAdInfo();
            if (adInfo.equals("//")) {
                district = address;
            } else {
                district = adInfo.replaceAll("/", "");
            }

            area = po.getArea();
            landType = po.getLandType().replaceAll("/", "");
            transferType = po.getTransferType();
            title = (district + "需要约" + area.intValue() + "亩" + landType + transferType);
            vo.setDistrict(district);
            vo.setTitle(title);
            vos.add(vo);
        }
        return vos;
    }



}
