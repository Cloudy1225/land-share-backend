package com.tencent.wxcloudrun.controller.C2C_Chat_Controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.dto.C2C_Chat_Dtos.MessageDto;
import com.tencent.wxcloudrun.enums.C2C_Chat_Enums.ReceiveType;
import com.tencent.wxcloudrun.enums.C2C_Chat_Enums.MessageType;
import com.tencent.wxcloudrun.enums.C2C_Chat_Enums.SendType;
import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.MessagePO;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.MessageVO;
import com.tencent.wxcloudrun.service.C2C_Chat_Services.ConversationService;
import com.tencent.wxcloudrun.service.C2C_Chat_Services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现聊天的WebSocket服务类
 *
 * "@ServerEndpoint"注解是一个类层次的注解，
 * 它的功能主要是将目前的类定义成一个websocket服务器端，
 * 注解的值将被用于监听用户连接的终端访问URL地址，
 * 客户端可以通过这个URL来连接到WebSocket服务器端
 *
 * @author Cloudy
 */
@RestController
@ServerEndpoint(value = "/chat/{openid}")
public class ChatController {

    /**
     * 用于输出日志信息
     */
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    /**
     * 实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key为用户标识，
     * 并且map需要是线程安全的，故选择concurrent包的线程安全Map来实现
     */
    private static final ConcurrentHashMap<String,ChatController> connections = new ConcurrentHashMap<String, ChatController>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 当前连接的用户openid
     */
    private String openid;

    /**
     * 用户消息的持久化相关工作
     * 必须设置为static，并通过set方法注入（WebSocket为多线程，SpringBoot默认单线程）
     */
    private static MessageService messageService;

    private static ConversationService conversationService;

    @Autowired
    public void setMessageService(MessageService _messageService){
        messageService = _messageService;
    }

    @Autowired
    public void setConversationService(ConversationService _conversationService) {
        conversationService = _conversationService;
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 连接会话，需要通过它来给客户端发送数据
     * @param openid 连接者
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("openid") String openid) {
        logger.info("WebSocket onOpen，与用户{}成功建立连接", openid);

        this.session = session;
        this.openid = openid;
        connections.put(openid, this);
    }

    /**
     * 连接关闭时调用的方法
     */
    @OnClose
    public void onClose() {
        logger.info("WebSocket onClose，与用户{}断开连接", this.openid);

        connections.remove(this.openid);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 客户端对应的会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject json = JSON.parseObject(message);
        logger.info("收到的json: {}", json);

        ReceiveType eventType = ReceiveType.valueOf(json.getString("event"));
        switch (eventType) {
            case PING:{
                logger.info("ping");
                this.pongMsg();
                break;
            }
            case CREATE_MSG: {
                logger.info("WebSocket onMessage，用户{}发送消息", this.openid);
                this.createMSG(json);
                break;
            }
            case DELETE_MSG: {
                logger.info("WebSocket onMessage，用户{}删除消息", this.openid);
                this.deleteMSG(json);
                break;
            }
            case REVOKE_MSG: {
                logger.info("WebSocket onMessage，用户{}撤回消息", this.openid);
                this.revokeMSG(json);
                break;
            }
            case READ_MSG: {
                logger.info("WebSocket onMessage，用户{}已读消息", this.openid);
                this.readMSG(json);
                break;
            }
            case CLOSE: {
                logger.info("WebSocket onMessage，用户{}请求断开连接", this.openid);
                this.close();
                break;
            }
        }
    }


    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("WebSocket onError, 连接发生错误，用户openid：{}", this.openid);
        logger.info("错误信息：{}",error.toString());
        error.printStackTrace();
    }

    private void pongMsg() {
        String responseJsonString = this.getResponseJsonString(SendType.PONG, null);
        logger.info("pong");
        try {
//            synchronized (this.session) {
                this.session.getBasicRemote().sendText(responseJsonString);
                logger.info("pong的session：{}", session.toString());
//            }
        } catch (IOException e) {
            logger.error("WebSocket onMessage，pong错误，用户：{}", this.openid);
            e.printStackTrace();
        }
    }

    /**
     * 当WebSocket接收到的事件是发送消息时调用
     * @param json 接收的json格式数据
     */
    private void createMSG(JSONObject json) {
        JSONObject dataJson = json.getJSONObject("message");
        // 解析json至Dto中
        MessageDto messageDto = new MessageDto();
        String messageID = dataJson.getString("messageID");
        messageDto.setMessageID(messageID);
        messageDto.setConversationID(dataJson.getString("conversationID"));
        messageDto.setFrom(this.openid);
        String toOpenid = dataJson.getString("to");
        messageDto.setTo(toOpenid);
        messageDto.setType(MessageType.valueOf(dataJson.getString("type")));
        messageDto.setPayload(dataJson.getString("payload"));
        messageDto.setTime(System.currentTimeMillis());
        MessagePO messagePO = messageService.insertMessage(messageDto);
        boolean isSaved = messagePO != null;
        // 通知发送者消息是否保存成功
        JSONObject data = new JSONObject();
        data.put("messageID", messageID);
        data.put("isSended", isSaved);
        String responseJsonString = this.getResponseJsonString(SendType.MSG_SENDED, data);
        logger.info("通知发送者消息发送成功: {}", responseJsonString);
        try {
//            synchronized (this.session) {
                this.session.getBasicRemote().sendText(responseJsonString);
                logger.info("发送消息的session：{}", session.toString());
//            }
        } catch (IOException e) {
            logger.error("WebSocket onMessage，通知消息发送结果时错误，用户：{}，数据：{}", this.openid, responseJsonString);
            e.printStackTrace();
        }
        if(isSaved){ // 保存成功才通知对方
            // 通知接收者
            ChatController receiver = connections.get(toOpenid);
            if (receiver != null) { // 接收者在线，将消息转发给接受者
                MessageVO messageVO = new MessageVO(messagePO, false);
                Session receiverSession = receiver.session;
                // 将VO转为JSONObject
                JSONObject data1 = JSON.parseObject(JSON.toJSONString(messageVO));
                data1.replace("type", messageVO.getType().toString());
                String responseText = this.getResponseJsonString(SendType.MSG_RECEIVED, data1);
                logger.info("通知接收者有新消息: {}", responseText);

                try {
                    synchronized (receiverSession) {
                        receiverSession.getBasicRemote().sendText(responseText);
                    }
                } catch (IOException e) {
                    logger.error("WebSocket onMessage，转发消息时错误，用户：{}，数据：{}", receiver.openid, responseText);
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteMSG(JSONObject json) {
        String messageID = json.getString("messageID");
        Boolean isDeleted = messageService.deleteMessage(messageID, this.openid);

        JSONObject data = new JSONObject();
        data.put("messageID", messageID);
        data.put("isDeleted", isDeleted);
        String responseJsonString = this.getResponseJsonString(SendType.MSG_DELETED, data);
        try {
            synchronized (this.session) {
                this.session.getBasicRemote().sendText(responseJsonString);
            }
        }catch (IOException e) {
            logger.error("WebSocket onMessage，通知消息删除结果时错误，用户：{}，数据：{}", this.openid, responseJsonString);
            e.printStackTrace();
        }


    }

    private void revokeMSG(JSONObject json) {
        String messageID = json.getString("messageID");
        Boolean isRevoked = messageService.revokeMessage(messageID);
        // 通知撤回者撤回结果
        JSONObject data = new JSONObject();
        data.put("messageID", messageID);
        data.put("isRevoked", isRevoked);
        String responseText = this.getResponseJsonString(SendType.MSG_REVOKED, data);
        try {
            synchronized (this.session) {
                this.session.getBasicRemote().sendText(responseText);
            }
        } catch (IOException e) {
            logger.error("WebSocket onMessage，通知消息撤回结果时错误，用户：{}，数据：{}", this.openid, responseText);
            e.printStackTrace();
        }
        if(isRevoked) { // 通知对方有消息撤回
            String toOpenid = messageService.getMessage(messageID, this.openid).getTo();
            ChatController receiver = connections.get(toOpenid);
            if (receiver != null) {
                Session toSession = receiver.session;
                JSONObject data1 = new JSONObject();
                data1.put("messageID", messageID);
                String responseText1 = this.getResponseJsonString(SendType.MSG_REVOKED_BY_PEER, data1);
                try {
                    synchronized (toSession) {
                        toSession.getBasicRemote().sendText(responseText1);
                    }
                } catch (IOException e) {
                    logger.error("WebSocket onMessage，通知对方消息撤回时错误，用户：{}，数据：{}", this.openid, responseText1);
                    e.printStackTrace();
                }
            }
        }
    }

    private void readMSG(JSONObject json) {
        String conversationID = json.getString("conversationID");
        // 是否有消息被设置了未读
        boolean isRead = conversationService.setMessageRead(conversationID, this.openid);
        JSONObject data = new JSONObject();
        data.put("conversationID", conversationID);
        data.put("isRead", isRead);
        String responseText = this.getResponseJsonString(SendType.MSG_READ, data);
        try {
            synchronized (this.session) {
                this.session.getBasicRemote().sendText(responseText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isRead) { // 有消息被设置为已读，则告知对方。
            String toOpenid = json.getString("toOpenid");
            ChatController receiver = connections.get(toOpenid);
            if(receiver != null) {
                Session toSession = receiver.session;
                JSONObject data1 = new JSONObject();
                data1.put("conversationID", conversationID);
                String responseText1 = this.getResponseJsonString(SendType.MSG_READ_BY_PEER, data1);
                try {
                    synchronized (toSession) {
                        toSession.getBasicRemote().sendText(responseText1);
                    }
                } catch (IOException e) {
                    logger.error("WebSocket onMessage，通知对方消息已读时错误，用户：{}，数据：{}", this.openid, responseText1);
                    e.printStackTrace();
                }
            }
        }
    }

    private void close() {
        String responseJsonString = this.getResponseJsonString(SendType.BYE, null);
        try {
            synchronized (this.session) {
                this.session.getBasicRemote().sendText(responseJsonString);
            }
        } catch (IOException e) {
            logger.error("WebSocket onMessage，告知用户socket已关闭时错误，用户：{}", this.openid);
            e.printStackTrace();
        }
        try {
            this.session.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connections.remove(this.openid);
        }
    }

    /**
     * 通用的获取返回结果Json字符串，标识是否成功
     * @param eventType 标识发生给客户端信息的类型
     * @param data 返回数据
     * @return JSON的字符串结果
     */
    private String getResponseJsonString(SendType eventType, JSONObject data) {
        JSONObject json = new JSONObject();

        json.put("event", eventType.toString());
        json.put("data", data);
        return json.toJSONString();
    }

}
