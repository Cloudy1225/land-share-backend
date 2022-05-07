package com.tencent.wxcloudrun.model.po;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandPostPO {

    private Integer lid;

    private String landType;

    private String transferType;

    private Double area;

    private Double transferTime;

    private Double price;

    private String address;

    private Double longtitude;

    private Double latitude;

    private String description;

    private String pictureFileID;

    private String videoFileID;

    private String warrantsFileID;

    private String telenumber;

    // 审核状态
    private Integer status;

    private String openid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;

}
