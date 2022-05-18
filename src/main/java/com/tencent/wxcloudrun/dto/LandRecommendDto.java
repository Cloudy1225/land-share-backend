package com.tencent.wxcloudrun.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 推荐土地的参数
 *
 * @author Cloudy
 */
public class LandRecommendDto {

    /**
     * 当前土地lid，用于排除自身
     */
    private Integer lid;

    /**
     * 行政区划
     * eg: "江苏省/南京市/玄武区" 或 ”//"
     */
    private String adInfo;

    /**
     * 土地类型
     * eg: "耕地/水田" or "耕地"
     */
    private String landType;

    /**
     * 流转方式
     * eg: "出租"
     */
    private String transferType;

//    /**
//     * 土地面积
//     * 单位：亩
//     */
//    private Double area;
//
//    /**
//     * 流转时间
//     * 单位：年
//     */
//    private Double transferTime;
//
//    /**
//     * 单价
//     * 单位：元/亩/年
//     */
//    private Double price;

}
