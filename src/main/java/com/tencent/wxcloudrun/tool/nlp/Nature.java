package com.tencent.wxcloudrun.tool.nlp;

/**
 * 词性
 *
 * @author Cloudy
 */
public enum Nature {

    /**
     * 省级行政区
     */
    PROVINCE,

    /**
     * 市级行政区
     */
    CITY,

    /**
     * 县级行政区
     */
    COUNTY,

    /**
     * 父土地类型
     */
    LANDTYPE_PARENT,

    /**
     * 子土地类型
     */
    LANDTYPE_CHILD,

    /**
     * 流转方式
     */
    TRANSFERTYPE,

    /**
     * 流转年限
     */
    TRANSFERTIME,

    /**
     * 土地面积
     */
    AREA,

    /**
     * 土地单价
     */
    PRICE,

    /**
     * 发布日期，只判断年
     */
    Date
}