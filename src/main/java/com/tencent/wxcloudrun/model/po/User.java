package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 用户uid
     */
    private Integer uid;

    /**
     * 小程序自动生成的用户id
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像URL
     */
    private String avatarUrl;

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
     * 用户身份
     */
    private Character role;

}
