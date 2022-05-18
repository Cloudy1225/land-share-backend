package com.tencent.wxcloudrun.service;

import java.util.ArrayList;

/**
 * PO转VO的接口
 *
 * @param <PO> PO
 * @param <VO> VO
 * @author Cloudy
 */
public interface POtoVO<PO, VO>{

    /**
     * PO 转 VO
     * @param pos POs
     * @return VOs
     */
    ArrayList<VO> poToVO(ArrayList<PO> pos);
}
