package com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs;

import com.tencent.wxcloudrun.enums.C2C_Chat_Enums.MessageType;
import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.MessagePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会话最新的消息，用于前端会话列表的展示
 *
 * @author Cloudy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastMessageVO {

    /**
     * 根据 MessagePO构造 LastMessagePO
     * @param po MessagePO
     * @param myOpenid 判断是消息发送还是接收方
     */
    public LastMessageVO(MessagePO po, String myOpenid) {
        this.lastTime = po.getTime();

        Boolean isRevoked = po.getIsRevoked(); // 消息是否撤回
        Boolean isDeleted; // 消息是否删除
        if(myOpenid.equals(po.getFrom())) { // 我是消息发送方
            isDeleted = po.getIsFromDeleted();
            if(isDeleted) { // 消息已被我删除
                this.type = MessageType.MSG_TEXT;
                this.messageForShow = "[消息已删除]";
            }
            if(isRevoked) { //消息已撤回
                this.type = MessageType.MSG_TEXT;
                this.messageForShow = "[你撤回了一条消息]";
            }
            if(!isDeleted && !isRevoked){
                this.type = po.getType();
                this.setPayLoad(po.getPayload());
            }

        } else { // 我是消息接收方
            isDeleted = po.getIsToDeleted();
            if(isDeleted) { // 消息已被我删除
                this.type = MessageType.MSG_TEXT;
                this.messageForShow = "[消息已删除]";
            }
            if(isRevoked) { //消息已撤回
                this.type = MessageType.MSG_TEXT;
                this.messageForShow = "[对方撤回了一条消息]";
            }
            if(!isDeleted && !isRevoked){
                this.type = po.getType();
                this.setPayLoad(po.getPayload());
            }
        }
    }

    private void setPayLoad(String payLoad) {

        switch (this.type) {
            case MSG_TEXT: {
                this.messageForShow = payLoad;
                break;
            }
            case MSG_IMAGE: {
                this.messageForShow = "[图片]";
                break;
            }
            case MSG_AUDIO: {
                this.messageForShow = "[语音]";
                break;
            }
            case MSG_VIDEO: {
                this.messageForShow = "[视频]";
                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * 消息类型
     */
    private MessageType type;

    /**
     * 最新消息的内容，用于展示。可能值：文本消息内容、"[图片]"、"[语音]"、"[位置]"、"[表情]"、"[文件]"、"[自定义消息]"。
     */
    private String messageForShow;

    /**
     * 当前会话最新消息的时间戳，单位：秒
     */
    private Long lastTime;

}
