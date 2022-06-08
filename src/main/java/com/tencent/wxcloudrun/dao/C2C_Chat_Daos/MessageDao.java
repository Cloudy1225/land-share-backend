package com.tencent.wxcloudrun.dao.C2C_Chat_Daos;


import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.MessagePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息Dao
 *
 * @author Cloudy
 */
@Repository
@Mapper
public interface MessageDao {

    /**
     * 插入一条消息
     * @param messagePO 消息持久化对象
     * @return 1/0
     */
    int insertMessage(MessagePO messagePO);

    /**
     * 通过消息id查询消息
     * @param messageID 消息ID
     * @return 消息PO
     */
    MessagePO selectById(String messageID);

    /**
     * 分页拉取指定会话的消息列表
     * @param conversationID 消息所属的会话ID
     * @param time 用于分页续拉，为-1时拉取最新消息
     * @param count 需要拉取的消息数量
     * @return 消息VO列表
     */
    List<MessagePO> selectByConversationID(@Param("conversationID") String conversationID, @Param("time") Long time, @Param("count") Integer count);

    /**
     * 将某会话下的未读消息状态设置为已读
     * @param conversationID 未读消息所属会话ID
     * @param openid 读消息者
     * @return 由未读到已读的消息数
     */
    int setIsPeerReadTrue(@Param("conversationID") String conversationID, @Param("openid") String openid);

    /**
     * 设置isRevoked为true，用于撤回消息
     * @param messageID 被撤回消息ID
     * @return 1/0
     */
    int setIsRevokedTrue(String messageID);

    /**
     * 设置用户对应的isDeleted为true，用于删除消息
     * @param messageID 被删除消息ID
     * @param openid 删除者
     * @return 1/0
     */
    int setIsDeletedTrue(@Param("messageID") String messageID, @Param("openid") String openid);
}
