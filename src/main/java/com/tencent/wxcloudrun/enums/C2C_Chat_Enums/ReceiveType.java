package com.tencent.wxcloudrun.enums.C2C_Chat_Enums;

/**
 * 标识WebSocket接收的事件类型，标识消息是新键/删除/撤回
 *
 * @author Cloudy
 */
public enum ReceiveType {
    /**
     * 用于心跳检测
     */
    PING,

    /**
     * 说明客户端要求断开连接
     */
    CLOSE,

    /**
     * 新建消息
     */
    CREATE_MSG,

    /**
     * 删除消息
     */
    DELETE_MSG,

    /**
     * 撤回消息
     */
    REVOKE_MSG,

    /**
     * 已读消息
     */
    READ_MSG

//    /**
//     * 新建会话
//     */
//    CREATE_CVST,
//
//    /**
//     * 置顶会话
//     */
//    PIN_CVST,
//
//    /**
//     * 删除会话
//     */
//    DELETE_CVST
}
