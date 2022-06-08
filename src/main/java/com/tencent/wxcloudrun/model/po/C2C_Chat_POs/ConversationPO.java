package com.tencent.wxcloudrun.model.po.C2C_Chat_POs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会话持久化对象
 *
 * @author Cloudy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationPO {

    /**
     * 会话ID
     */
    private String conversationID;

    /**
     * 会话用户1的openid
     */
    private String openid1;

    /**
     * 会话用户2的openid
     */
    private String openid2;

    /**
     * 用户1未读计数
     */
    private Integer unreadCount1;

    /**
     * 用户2未读计数
     */
    private Integer unreadCount2;

    /**
     * 用户1是否置顶会话
     */
    private Boolean isPinned1;

    /**
     * 用户2是否置顶会话
     */
    private Boolean isPinned2;

    /**
     * 用户1是否删除会话
     */
    private Boolean isDeleted1;

    /**
     * 用户2是否删除会话
     */
    private Boolean isDeleted2;

    /**
     * 会话最新的消息id
     */
    private String lastMessageID;
}
