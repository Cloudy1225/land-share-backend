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

@Service
public class CollectionServiceImpl extends LandPostPOtoVO implements CollectionService {


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
        ArrayList<Integer> lids = collectionDao.selectLidsByOpenid(openid);
        if (lids.size() == 0) {
            return collectionVO;
        }

        ArrayList<LandPostPO> landPostPOS = landPostDao.selectByLids(lids);
        ArrayList<LandPostVO> myCollection = this.poToVO(landPostPOS);

        // 按收藏时间排序
        ArrayList<LandPostVO> sortedMyCollection = new ArrayList<>();
        for (Integer lid : lids) {
            for (LandPostVO landPostVO : myCollection) {
                if (lid.equals(landPostVO.getLid())) {
                    sortedMyCollection.add(landPostVO);
                }
            }
        }

        collectionVO.setMyCollection(sortedMyCollection);
        return collectionVO;
    }

}