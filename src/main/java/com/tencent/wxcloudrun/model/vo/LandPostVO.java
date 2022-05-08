package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandPostVO {

    private Integer lid;

    private String landType;

    private String transferType;

    private Double area;

    private Double transferTime;

    private Double price;

    private String address;

    private Double longtitude;

    private Double latitude;

    private String adInfo;

    private String description;

    private String pictureFileID;

    private String videoFileID;

    private String warrantsFileID;

    private String telenumber;

    // 审核状态
    private Integer status;

    private String openid;

}
