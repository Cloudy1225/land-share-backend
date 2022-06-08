package com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs;


import com.tencent.wxcloudrun.model.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于聊天会话的用户资料对象，描述用户的昵称、头像地址等
 *
 * @author Cloudy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVO {

    public UserProfileVO(User user) {
        this.uid = user.getUid();
        this.openid = user.getOpenid();
        this.nickName = user.getNickName();
        this.avatarUrl = user.getAvatarUrl();
    }

    /**
     * 用户uid
     */
    private Integer uid;

    /**
     * 用户openid
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

}
