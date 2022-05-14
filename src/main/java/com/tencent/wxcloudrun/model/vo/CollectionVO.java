package com.tencent.wxcloudrun.model.vo;


import com.tencent.wxcloudrun.model.po.LandPostPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionVO {

    private String openid;

    private ArrayList<LandPostPO> myCollection;

}
