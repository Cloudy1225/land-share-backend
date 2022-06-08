package com.tencent.wxcloudrun.controller.C2C_Chat_Controllers;


import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.dto.C2C_Chat_Dtos.MessageDto;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.MessageVO;
import com.tencent.wxcloudrun.service.C2C_Chat_Services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController // REST形式，返回json等，不返回整个页面
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final Logger logger;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
        this.logger =  LoggerFactory.getLogger(MessageController.class);
    }

    @PostMapping("/createMessage")
    public Response createMessage(@RequestBody MessageDto messageDto) {
        logger.info("/message/createMessage post request, 目的：发送消息");

        if(this.messageService.insertMessage(messageDto) != null) {
            return Response.buildSuccess("消息发送成功");
        }else {
            return Response.buildFailed("20003", "消息发送失败");
        }

    }

    @GetMapping("/getMessage")
    public Response getMessage(@RequestParam(value = "messageID") String messageID, @RequestParam(value = "openid") String openid) {
        logger.info("/message/getMessage get request, 目的：获取指定消息, 用户openid：{}", openid);

        MessageVO vo = this.messageService.getMessage(messageID, openid);

        if (vo != null) {
            return Response.buildSuccess("获取指定消息成功", vo);
        }else {
            return Response.buildFailed("20004", "消息不存在");
        }
    }

    @PostMapping("/getMessageList")
    public Response getMessageList(@RequestBody HashMap<String, String> map) {
        String conversationID = map.get("conversationID");
        Long time = Long.parseLong(map.get("time"));
        String openid = map.get("openid");
        Integer count = Integer.parseInt(map.get("count"));
        logger.info("/message/getMessageList post request, 目的：分页获取指定会话的消息列表, 用户openid：{}", openid);


        List<MessageVO> vos = this.messageService.getMessageList(conversationID, time, count, openid);
        return Response.buildSuccess("消息列表获取成功", vos);
    }

}