package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    /**
     * 用户uid
     */
    private Integer uid;

    /**
     * 小程序自动生成的用户id
     */
    private String openid;

    /**
     * 用户手机号
     */
    private String telenumber;

    /**
     * 真实姓名
     */
    private String username;

    /**
     * 身份证号
     */
    private String idnumber;

    /**
     * 我的收藏
     */
    private String collection;

    /**
     * 用户身份
     */
    private Character role;

}
