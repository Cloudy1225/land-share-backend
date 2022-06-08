package com.tencent.wxcloudrun.dao.C2C_Chat_Daos;

import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.ConversationPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 会话Dao
 *
 * @author Cloudy
 */
@Repository
@Mapper
public interface ConversationDao {

    /**
     * 插入一条会话记录，用于新建会话
     * @param po 会话持久化对象
     * @return 1/0
     */
    int insertConversation(ConversationPO po);

    /**
     * 通过ID查询会话
     * @param conversationID 会话ID
     * @return PO
     */
    ConversationPO selectByID(String conversationID);

    /**
     * 根据会话双方查找会话
     * @param openid1 会话者1
     * @param openid2 会话者2
     * @return 会话PO
     */
    ConversationPO selectByTwoOpenids(String openid1, String openid2);

    /**
     * 获取openid用户的所有会话
     * @param openid 用户唯一标识
     * @return 会话列表
     */
    List<ConversationPO> selectByOpenid(String openid);

    /**
     * 更新两个isDeleted属性
     * @param conversationID 会话ID
     * @param openid 更新者
     * @param type 为false则将两个字段都设为false；为true则将对应用户的设为true
     * @return 1/0
     */
    int updateIsDeleted(@Param("conversationID") String conversationID, @Param("openid") String openid, @Param("type") Boolean type);

    /**
     * 更新isPinned属性
     * @param conversationID 被更新者
     * @param isPinned 更新后值
     * @param openid 更新者
     * @return 1/0
     */
    int updateIsPinned(@Param("conversationID") String conversationID, @Param("isPinned") Boolean isPinned, @Param("openid") String openid);

    int setUnreadCount0(@Param("conversationID") String conversationID, @Param("openid") String openid);
}
