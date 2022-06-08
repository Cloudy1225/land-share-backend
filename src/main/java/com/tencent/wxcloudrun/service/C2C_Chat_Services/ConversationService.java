package com.tencent.wxcloudrun.service.C2C_Chat_Services;


import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.ConversationVO;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.UserProfileVO;

import java.util.List;

/**
 * 会话服务接口
 *
 * @author Cloudy
 */
public interface ConversationService {

    /**
     * 获取用户资料，用于会话
     * @param openid 用户openid
     * @return UserProfileVO
     */
    UserProfileVO getUserProfile(String openid);

    /**
     * 通过ID查询会话
     * @param conversationID 会话ID
     * @param openid 查询者
     * @return VO
     */
    ConversationVO findConversation(String conversationID, String openid);


    /**
     * 创建会话
     * @param openid1 会话方1
     * @param openid2 会话方2
     * @return true/false
     */
    Boolean createConversation(String conversationID, String openid1, String openid2);

    /**
     * 根据会话双方判断会话是否存在
     * @param openid1 用户1
     * @param openid2  用户2
     * @return true/false
     */
    Boolean isConversationExisted(String openid1, String openid2);

    /**
     * 根据会话双方获取会话ID，以判断双方是否已经新建会话
     * @param openid1 用户1
     * @param openid2 用户2
     * @return conversationID / null
     */
    String getConversationID(String openid1, String openid2);

    /**
     * 获取openid用户的所有会话
     * @param openid 用户唯一标识
     * @return 会话数组
     */
    List<ConversationVO> getConversationList(String openid);

    /**
     * 根据会话 ID 删除会话，只删除会话，不删除历史消息
     * @param conversationID 待删除会话ID
     * @param openid 删除者
     * @return 是否删除成功
     */
    Boolean deleteConversation(String conversationID, String openid);

    /**
     * 置顶或取消置顶会话
     * @param conversationID 被操作的会话ID
     * @param isPinned true为置顶；false为取消置顶
     * @param openid 操作者
     * @return true/false
     */
    Boolean pinConversation(String conversationID, Boolean isPinned, String openid);

    /**
     * 将某会话下的未读消息状态设置为已读
     * @param conversationID 未读消息所属会话ID
     * @param openid 读消息者
     * @return true/false
     */
    Boolean setMessageRead(String conversationID, String openid);
}
