package com.tencent.wxcloudrun.dto.C2C_Chat_Dtos;

import com.tencent.wxcloudrun.enums.C2C_Chat_Enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 接收客户端发送过来的消息
 *
 * @author Cloudy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    /**
     * 消息ID
     */
    private String messageID;
    /**
     * 消息所属的会话 ID
     */
    private String conversationID;

    /**
     * 发送方的 openid
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
}
