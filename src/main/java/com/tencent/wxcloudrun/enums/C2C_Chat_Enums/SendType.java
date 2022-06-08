package com.tencent.wxcloudrun.enums.C2C_Chat_Enums;

/**
 * 标识WebSocket发送的事件类型
 *
 * @author Cloudy
 */
public enum SendType {
    /**
     * 用于心跳检测
     */
    PONG,

    /**
     * 告知客户端socket已关闭
     */
    BYE,

    /**
     * 用于告知发送者消息已发送
     */
    MSG_SENDED,

    /**
     * 用于告知接收者有新消息进来了
     */
    MSG_RECEIVED,

    /**
     * 用于告知自己消息撤回成功
     */
    MSG_REVOKED,

    /**
     * 用于告知消息被对方撤回
     */
    MSG_REVOKED_BY_PEER,

    /**
     * 用于告知删除者删除成功
     */
    MSG_DELETED,

    /**
     * 用于告知自己设置已读成功
     */
    MSG_READ,

    /**
     * 用于告知自己对方已读
     */
    MSG_READ_BY_PEER
}
