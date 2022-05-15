package com.tencent.wxcloudrun.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionVO {

    private String openid;

    private ArrayList<LandPostVO> myCollection;

}
