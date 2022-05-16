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
public class LandRequirePO {

    private Integer lrid;

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

    private String telenumber;

    private String openid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;

}