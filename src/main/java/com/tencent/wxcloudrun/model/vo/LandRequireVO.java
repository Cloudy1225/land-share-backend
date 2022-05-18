package com.tencent.wxcloudrun.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandRequireVO {

    private Integer lrid;

    private String landType;

    private String transferType;

    private Double area;

    private Double transferTime;

    private Double price;

    private String address;

    private Double longtitude;

    private Double latitude;

    private String adInfo; // eg: "江苏省/南京市/玄武区"
    private String district; // eg: "江苏省南京市玄武区"

    /**
     * district+area+'亩'+landType+transferType
     */
    private String title;

    private String description;

    private String telenumber;

    private String openid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;

}
