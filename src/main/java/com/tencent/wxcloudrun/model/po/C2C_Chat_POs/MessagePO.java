package com.tencent.wxcloudrun.model.po.C2C_Chat_POs;


import com.tencent.wxcloudrun.enums.C2C_Chat_Enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息持久化对象
 *
 * @author Cloudy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePO {

    /**
     * 消息id
     */
    private String messageID;

    /**
     * 消息所属的会话 ID
     */
    private String conversationID;

    /**
     * 发送方的 openid，在消息发送时，会默认设置为当前登录的用户
     */
    private String from;

    /**
     * 接收方的 openid
     */
    private String to;

    /**
     * 消息类型
     */
    private MessageType type;

    /**
     * 消息的内容，文字或url
     */
    private String payload;

    /**
     * 消息时间戳。单位：秒
     */
    private Long time;

    /**
     * 是否被撤回的消息，true 标识被撤回的消息
     */
    private Boolean isRevoked;

    /**
     * C2C 消息对端是否已读，true 标识对端已读
     */
    private Boolean isPeerRead;

    /**
     * 发送方是否被删除的消息，true 标识被删除的消息
     */
    private Boolean isFromDeleted;

    /**
     * 接收方是否被删除的消息，true 标识被删除的消息
     */
    private Boolean isToDeleted;

}
