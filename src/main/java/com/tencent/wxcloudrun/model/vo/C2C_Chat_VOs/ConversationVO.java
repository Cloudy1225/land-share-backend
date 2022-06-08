package com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会话对象，用于描述会话具有的属性，如消息未读计数、最新消息等。
 *
 * @author Cloudy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationVO {

    /**
     * 会话ID
     */
    private String conversationID;

    /**
     * 未读计数
     */
    private Integer unreadCount;

    /**
     * 会话最新消息
     */
    private LastMessageVO lastMessage;

    /**
     * C2C会话的对方用户资料
     */
    private UserProfileVO userProfile;

    /**
     * 会话消息是否置顶
     */
    private Boolean isPinned;
}
