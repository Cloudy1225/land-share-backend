package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.LandPostDao;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.service.LandPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
