package com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs;


import com.tencent.wxcloudrun.enums.C2C_Chat_Enums.MessageType;
import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.MessagePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息VO，用于描述一条消息具有的属性，如类型、消息的内容、所属的会话 ID 等.
 *
 * @author Cloudy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {

    /**
     * 根据PO构造VO
     * @param po 消息持久化对象
     * @param isSender 是否是发送者，用于设置消息流向
     */
    public MessageVO(MessagePO po, Boolean isSender) {
        this.messageID = po.getMessageID();
        this.conversationID = po.getConversationID();
        // 单发，只要判断流向即可
        this.isOut = isSender;
        this.from = po.getFrom();
        this.to = po.getTo();
        this.type = po.getType();
        this.payload = po.getPayload();;
        this.time = po.getTime();
        this.isRevoked = po.getIsRevoked();
        this.isPeerRead = po.getIsPeerRead();
        this.isDeleted = isSender ? po.getIsFromDeleted() : po.getIsToDeleted();
    }

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
     * 消息的流向：true为发送，false为接收
     */
    private Boolean isOut;

    /**
     * 消息时间戳。单位：秒
     */
    private Long time;

//    /**
//     * 消息状态：
//     * unSend(未发送)
//     * success(发送成功)
//     * fail(发送失败)
//     */
//    private String status;

    /**
     * 是否被撤回的消息，true 标识被撤回的消息
     */
    private Boolean isRevoked;

//    /**
//     * 消息发送者的昵称
//     */
//    private String nickName;
//
//    /**
//     * 消息发送者的昵称
//     */
//    private String avatarUrl;

    /**
     * C2C 消息对端是否已读，true 标识对端已读
     */
    private Boolean isPeerRead;

    /**
     * 是否被删除的消息，true 标识被删除的消息
     */
    private Boolean isDeleted;

}
