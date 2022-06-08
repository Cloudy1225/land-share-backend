package com.tencent.wxcloudrun.service.C2C_Chat_Services.impl;

import com.tencent.wxcloudrun.dao.C2C_Chat_Daos.ConversationDao;
import com.tencent.wxcloudrun.dao.C2C_Chat_Daos.MessageDao;
import com.tencent.wxcloudrun.dao.UserDao;
import com.tencent.wxcloudrun.exception.MyServiceException;
import com.tencent.wxcloudrun.model.po.C2C_Chat_POs.ConversationPO;
import com.tencent.wxcloudrun.model.po.User;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.ConversationVO;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.LastMessageVO;
import com.tencent.wxcloudrun.model.vo.C2C_Chat_VOs.UserProfileVO;
import com.tencent.wxcloudrun.service.C2C_Chat_Services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationDao conversationDao;
    private final UserDao userDao;
    private final MessageDao messageDao;

    @Autowired
    public ConversationServiceImpl(ConversationDao conversationDao, UserDao userDao, MessageDao messageDao) {
        this.conversationDao = conversationDao;
        this.userDao = userDao;
        this.messageDao = messageDao;
    }

    @Override
    public UserProfileVO getUserProfile(String openid) {
        User user = this.userDao.selectByOpenid(openid);
        if(user !=null){
            return new UserProfileVO(user);
        }else {
            throw new MyServiceException("20001", "用户已注销");
        }

    }

    @Override
    public ConversationVO findConversation(String conversationID, String openid) {
        return this.poToVO(this.conversationDao.selectByID(conversationID), openid);
    }

    @Override
    public Boolean createConversation(String conversationID, String openid1, String openid2) {
        ConversationPO po = new ConversationPO();
        po.setConversationID(conversationID);
        po.setOpenid1(openid1);
        po.setOpenid2(openid2);
        po.setLastMessageID("NONE");
        return this.conversationDao.insertConversation(po) == 1;
    }

    @Override
    public Boolean isConversationExisted(String openid1, String openid2) {
        return this.conversationDao.selectByTwoOpenids(openid1, openid2) != null;
    }

    @Override
    public String getConversationID(String openid1, String openid2) {
        ConversationPO po = this.conversationDao.selectByTwoOpenids(openid1, openid2);
        return po==null ? null : po.getConversationID();
    }

    @Override
    public List<ConversationVO> getConversationList(String openid) {
        List<ConversationPO> pos = this.conversationDao.selectByOpenid(openid);
        int size = pos.size();
        List<ConversationVO> vos = new ArrayList<>(size);
        List<ConversationVO> unPinnedVOs = new ArrayList<>(size);
        for (ConversationPO po : pos) {
            if (openid.equals(po.getOpenid1())) { // 我是会话用户1
                if(!po.getIsDeleted1()) { // 会话未被我删除
                    if(po.getIsPinned1()) { // 会话被我置顶
                        vos.add(this.poToVO(po, openid));
                    }else {
                        unPinnedVOs.add(this.poToVO(po, openid));
                    }
                }
            }else {
                if(!po.getIsDeleted2()) {
                    if(po.getIsPinned2()) {
                        vos.add(this.poToVO(po, openid));
                    }else {
                        unPinnedVOs.add(this.poToVO(po, openid));
                    }
                }
            }
        }
        Comparator<ConversationVO> comparator = new Comparator<ConversationVO>() {
            @Override
            public int compare(ConversationVO o1, ConversationVO o2) {
                if(o2.getLastMessage() == null) {
                    return 1;
                }else if(o1.getLastMessage() == null) {
                    return -1;
                }else {
                    return (int) (o2.getLastMessage().getLastTime()-o1.getLastMessage().getLastTime());
                }
            }
        };
        // 按消息时间排序
        vos.sort(comparator);
        unPinnedVOs.sort(comparator);
        // 置顶消息在非置顶消息前面
        vos.addAll(unPinnedVOs);
        return vos;
    }

    @Override
    public Boolean deleteConversation(String conversationID, String openid) {
        try {
            this.conversationDao.updateIsDeleted(conversationID, openid, true);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean pinConversation(String conversationID, Boolean isPinned, String openid) {
        try {
            this.conversationDao.updateIsPinned(conversationID, isPinned, openid);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean setMessageRead(String conversationID, String openid) {
        try {
            this.conversationDao.setUnreadCount0(conversationID, openid);
            this.messageDao.setIsPeerReadTrue(conversationID, openid);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 会话PO转VO
     * @param po 会话PO
     * @param myOpenid 当前会话者
     * @return 会话VO
     */
    private ConversationVO poToVO(ConversationPO po, String myOpenid) {
        ConversationVO vo = new ConversationVO();
        vo.setConversationID(po.getConversationID());
        String openid1 = po.getOpenid1();
        String openid2 = po.getOpenid2();
        User another;
        if(myOpenid.equals(openid1)){ // 我是会话用户1
            another = this.userDao.selectByOpenid(openid2);
            vo.setUnreadCount(po.getUnreadCount1());
            vo.setIsPinned(po.getIsPinned1());
        }else { // 我是会话用户2
            another = this.userDao.selectByOpenid(openid1);
            vo.setUnreadCount(po.getUnreadCount2());
            vo.setIsPinned(po.getIsPinned2());
        }
        if(another == null) {
            throw new MyServiceException("20001", "用户已注销");
        }
        // 设置对方资料
        vo.setUserProfile(new UserProfileVO(another));
        // 设置最新消息
        String lastMessageID = po.getLastMessageID();
        if(!lastMessageID.equals("NONE")) { // 存在消息时
            vo.setLastMessage(new LastMessageVO(this.messageDao.selectById(lastMessageID), myOpenid));
        }
        return vo;
    }
}
