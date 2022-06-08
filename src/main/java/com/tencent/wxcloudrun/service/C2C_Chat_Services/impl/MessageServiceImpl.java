package com.tencent.wxcloudrun.service.C2C_Chat_Services.impl;

import com.tencent.wxcloudrun.dao.C2C_Chat_Daos.MessageDao;
import com.tencent.wxcloudrun.dto.C2C_Chat_Dtos.MessageDto;
import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.MessagePO;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.MessageVO;
import com.tencent.wxcloudrun.service.C2C_Chat_Services.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public MessagePO insertMessage(MessageDto dto) {
        MessagePO po = new MessagePO();
        BeanUtils.copyProperties(dto, po);
        return this.messageDao.insertMessage(po) ==  1 ? po : null;
    }

    @Override
    public MessageVO getMessage(String messageID, String openid) {
        MessagePO po = this.messageDao.selectById(messageID);
        if(po == null) {
            return null;
        }
        if(openid.equals(po.getFrom())) {
            return new MessageVO(po, true);
        }else {
            return new MessageVO(po, false);
        }
    }

    @Override
    public List<MessageVO> getMessageList(String conversationID, Long time, Integer count, String openid) {
        List<MessagePO> pos = this.messageDao.selectByConversationID(conversationID, time, count);
        List<MessageVO> vos = new ArrayList<>(pos.size());
        for (int i = pos.size()-1; i >=0; i--) {
            MessagePO po = pos.get(i);
            if(openid.equals(po.getFrom())) { // 如果我是消息发送方
                if(!po.getIsFromDeleted()) { // 消息未删除
                    vos.add(new MessageVO(po, true));
                }
            }else {
                if(!po.getIsToDeleted()){
                    vos.add(new MessageVO(po, false));
                }
            }
        }
        return vos;
    }

    @Override
    public Boolean revokeMessage(String messageID) {
        return this.messageDao.setIsRevokedTrue(messageID) == 1;
    }

    @Override
    public Boolean deleteMessage(String messageID, String openid) {
        return this.messageDao.setIsDeletedTrue(messageID, openid) ==  1;
    }

}
