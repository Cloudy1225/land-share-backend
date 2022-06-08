package com.tencent.wxcloudrun.controller.C2C_Chat_Controllers;

import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.exception.MyServiceException;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.ConversationVO;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.UserProfileVO;
import com.tencent.wxcloudrun.service.C2C_Chat_Services.ConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 用于处理与会话有关的请求
 *
 * @author Cloudy
 */
@RestController // REST形式，返回json等，不返回整个页面
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationService conversationService;
    private final Logger logger;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
        this.logger =  LoggerFactory.getLogger(ConversationController.class);
    }

    @GetMapping("/getMyOpenid")
    public Response getMyOpenid(@RequestHeader("X-WX-OPENID") String openid) {
        logger.info("/conversation/getMyOpenid get request, 目的：获取我的Openid，用户openid：{}", openid);

        return Response.buildSuccess("获取openid成功", openid);
    }

    @GetMapping("/getMyProfile")
    public Response getMyProfile(@RequestHeader("X-WX-OPENID") String openid) {
        logger.info("/conversation/getMyProfile get request, 目的：获取我的资料，用户openid：{}", openid);

        UserProfileVO userProfileVO = this.conversationService.getUserProfile(openid);

        return Response.buildSuccess("获取我的资料成功", userProfileVO);
    }

    @GetMapping("/getUserProfile")
    public Response getUserProfile(@RequestParam String openid) {
        logger.info("/conversation/getUserProfile get request, 目的：获取用户资料，用户openid：{}", openid);

        UserProfileVO userProfileVO = this.conversationService.getUserProfile(openid);

        return Response.buildSuccess("获取用户资料成功", userProfileVO);
    }

    @PostMapping("/createConversation")
    public Response createConversation(@RequestBody HashMap<String, String> map) {
        String conversationID = map.get("conversationID");
        String openid1 = map.get("openid1");
        String openid2 = map.get("openid2");
        logger.info("/conversation/createConversation post request, 目的：新建会话，用户openid: {}, {}", openid1, openid2);

        String id = this.conversationService.getConversationID(openid1, openid2);
        if(id == null) { // 会话不存在，则新建
            if(!this.conversationService.createConversation(conversationID, openid1, openid2)) {
                throw new MyServiceException("20002", "会话创建失败");
            }
        }
        return Response.buildSuccess("已新建会话", map);
    }

    @GetMapping("/findConversation")
    public Response findConversation(@RequestParam(value = "conversationID") String conversationID, @RequestParam(value = "openid") String openid) {
        logger.info("/conversation/findConversation get request, 目的：获取指定会话，用户openid: {}", openid);

        ConversationVO vo = this.conversationService.findConversation(conversationID, openid);

        if(vo == null) {
            return Response.buildFailed("20008", "会话不存在");
        }else {
            return Response.buildSuccess("会话获取成功", vo);
        }
    }

    @GetMapping("/getConversationList")
    public Response getConversationList(@RequestParam String openid) {
        logger.info("/conversation/getConversationList get request, 目的：获取会话列表，用户openid: {}", openid);

        return Response.buildSuccess("获取会话列表成功", this.conversationService.getConversationList(openid));
    }

    @GetMapping("/pinConversation")
    public Response pinConversation(@RequestParam(value = "conversationID") String conversationID, @RequestParam(value = "isPinned") Boolean isPinned, @RequestParam(value = "openid") String openid) {
        logger.info("/conversation/pinConversation get request, 目的：置顶/取消置顶会话，用户openid: {}", openid);

        if(this.conversationService.pinConversation(conversationID, isPinned, openid)){
            String msg = isPinned ? "会话置顶成功" : "会话取消置顶成功";
            return Response.buildSuccess(msg);

        }else {
            String msg = isPinned ? "会话置顶失败" : "会话取消置顶失败";
            return Response.buildFailed("20005", msg);
        }
    }

    @GetMapping("/deleteConversation")
    public Response deleteConversation(@RequestParam(value = "conversationID") String conversationID, @RequestParam(value = "openid") String openid) {
        logger.info("/conversation/deleteConversation get request, 目的：删除会话，用户openid: {}", openid);

        if(this.conversationService.deleteConversation(conversationID, openid)) {
            return Response.buildSuccess("删除会话成功");
        }else {
            return Response.buildFailed("20006", "删除会话失败");
        }
    }

    @GetMapping("/setMessageRead")
    public Response setMessageRead(@RequestParam(value = "conversationID") String conversationID, @RequestParam(value = "openid") String openid) {
        logger.info("/conversation/setMessageRead get request, 目的：设置会话消息已读，会话ID: {}", conversationID);

        this.conversationService.setMessageRead(conversationID, openid);
        return Response.buildFailed("20007", "设置会话消息已读成功");
    }


}
