package com.tencent.wxcloudrun.service.C2C_Chat_Services;

import com.tencent.wxcloudrun.dto.C2C_Chat_Dtos.MessageDto;
import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.MessagePO;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.MessageVO;

import java.util.List;

/**
 * 消息服务接口
 *
 * @author Cloudy
 */
public interface MessageService {

    /**
     * 向数据库中插入一条新消息
     * @param dto 消息Dto，包含新建消息所必须的属性
     * @return 消息PO/null
     */
    MessagePO insertMessage(MessageDto dto);

    /**
     * 通过消息ID查询消息
     * @param messageID 消息ID
     * @param openid 查询者
     * @return 消息VO
     */
    MessageVO getMessage(String messageID, String openid);

    /**
     * 分页拉取指定会话的消息列表
     * @param conversationID 消息所属的会话ID
     * @param time 用于分页续拉，为-1时拉取最新消息
     * @param count 需要拉取的消息数量
     * @param openid 消息拉取者
     * @return 消息VO列表
     */
    List<MessageVO> getMessageList(String conversationID, Long time, Integer count, String openid);


    /**
     * 撤回消息
     * @param messageID 被撤回的消息ID
     * @return true/false
     */
    Boolean revokeMessage(String messageID);

    /**
     * 删除消息（不是真的删除）
     * @param messageID 被删除消息ID
     * @param openid 删除者
     * @return true/false
     */
    Boolean deleteMessage(String messageID, String openid);
}
