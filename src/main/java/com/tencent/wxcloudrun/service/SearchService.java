package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.model.vo.LandRequireVO;

import java.util.ArrayList;


/**
 * 模糊搜索服务接口
 *
 * @author Cloudy
 */
public interface SearchService {

    /**
     * 搜索土地
     * @param input 用户搜索框输入
     * @return 查询结果
     */
    ArrayList<LandPostVO> searchLandPosts(String input);

    /**
     * 搜索土地需求
     * @param input 用户搜索框输入
     * @return 查询结果
     */
    ArrayList<LandRequireVO> searchLandRequires(String input);
}
