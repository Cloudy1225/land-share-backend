package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.dao.CollectionDao;
import com.tencent.wxcloudrun.dao.LandPostDao;
import com.tencent.wxcloudrun.dto.CollectionDto;
import com.tencent.wxcloudrun.model.po.CollectionPO;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.CollectionVO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Service
public class CollectionServiceImpl implements CollectionService {


    private final CollectionDao collectionDao;
    private final LandPostDao landPostDao;

    @Autowired
    public CollectionServiceImpl(CollectionDao collectionDao, LandPostDao landPostDao) {
        this.collectionDao = collectionDao;
        this.landPostDao = landPostDao;
    }

    @Override
    public int insertCollection(CollectionPO collectionPO) {
        return collectionDao.insertCollection(collectionPO);
    }

    @Override
    public int deleteByCid(Integer cid) {
        return collectionDao.deleteByCid(cid);
    }

    @Override
    public int deleteByOpenidAndLid(CollectionDto collectionDto) {
        return collectionDao.deleteByOpenidAndLid(collectionDto);
    }

    @Override
    public CollectionPO selectByOpenidAndLid(CollectionDto collectionDto) {
        return collectionDao.selectByOpenidAndLid(collectionDto);
    }

    @Override
    public CollectionVO getMyCollection(String openid) {
        CollectionVO collectionVO = new CollectionVO(openid, null);
        ArrayList<Integer> lids =  collectionDao.selectLidsByOpenid(openid);

        ArrayList<LandPostPO> myCollection= landPostDao.selectByLids(lids);

        // 按收藏时间排序
        ArrayList<LandPostPO> sortedMyCollection = new ArrayList<>();
        for (Integer lid: lids){
            for (LandPostPO landPostPO: myCollection){
                if(lid.equals(landPostPO.getLid())){
                    sortedMyCollection.add(landPostPO);
                }
            }
        }

        collectionVO.setMyCollection(sortedMyCollection);
        return collectionVO;
    }
}
