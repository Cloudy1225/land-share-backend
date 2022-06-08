CREATE TABLE `Counters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '1',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS UserInformation;
CREATE TABLE UserInformation (
    uid int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户uid',
    openid varchar(150) NOT NULL COMMENT '小程序用户唯一id',
    nickName varchar(150) NOT NULL COMMENT '用户昵称',
    avatarUrl varchar(150) NOT NULL COMMENT '头像url' DEFAULT 'https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/chat/avatar/defaultAvatar.png?sign=e8dcd45fa29d7bca373ebed5288fc1bb&t=1653836062',
    telenumber char(11) NULL COMMENT '手机号码',
    username varchar(9) NULL COMMENT '真实姓名',
    idnumber char(18) NULL COMMENT '身份证号',
    role char(1) NOT NULL DEFAULT '1' COMMENT '用户权限',
    PRIMARY KEY (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; # InnoDB支持外键等

BEGIN ;
    INSERT INTO UserInformation VALUES (null, 'lyh', '辉辉', 'https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/chat/avatar/defaultAvatar.png?sign=e8dcd45fa29d7bca373ebed5288fc1bb&t=1653836062', '18355442634', '刘云辉', '340421200212253412', '2');
    INSERT INTO UserInformation VALUES (null, 'yxb', '博博', 'https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/chat/avatar/defaultAvatar.png?sign=e8dcd45fa29d7bca373ebed5288fc1bb&t=1653836062', '18355442634', '于欣博', '340421200212243412', '2');
    INSERT INTO UserInformation VALUES (null, 'zhs', '松松', 'https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/chat/avatar/defaultAvatar.png?sign=e8dcd45fa29d7bca373ebed5288fc1bb&t=1653836062', '18355442634', '张怀松', '340421200212253412', '2');
    INSERT INTO UserInformation VALUES (null, 'xrs', '石石', 'https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/chat/avatar/defaultAvatar.png?sign=e8dcd45fa29d7bca373ebed5288fc1bb&t=1653836062', '18355442634', '徐润石', '340421200212253412', '2');
COMMIT ;

DROP TABLE IF EXISTS LandPost;
CREATE TABLE LandPost (
    lid int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '土地发布id',
    landType varchar(15) NOT NULL COMMENT '土地类型',
    transferType varchar(6) NOT NULL COMMENT '流转方式',
    area double NOT NULL COMMENT '土地面积',
    transferTime double NOT NULL COMMENT '流转年限',
    price double NOT NULL COMMENT '流转单价',
    address varchar(30) NOT NULL COMMENT '土地位置',
    longtitude double NULL COMMENT '经度',
    latitude double NULL COMMENT '纬度',
    adInfo varchar(50) NULL COMMENT '行政规划信息',
    description varchar(1500) NULL COMMENT '土地描述',
    pictureFileID varchar(1000) NULL COMMENT '图片云托管fileID',
    videoFileID varchar(150) NULL COMMENT '视频云托管fileID',
    warrantsFileID varchar(1000) NOT NULL COMMENT '土地凭证图片云托管fileID',
    telenumber char(11) NOT NULL COMMENT '联系电话',
    status int NOT NULL DEFAULT 0 COMMENT '审核状态-1，0，1',
    openid varchar(100) NOT NULL COMMENT '发布者openid',
    submitTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
    PRIMARY KEY (lid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS Article;
CREATE TABLE Article (
      aid int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '文章id',
      title varchar(100) NOT NULL COMMENT '文章标题',
      type varchar(12) NOT NULL COMMENT '文章类型',
      url varchar(255) NOT NULL COMMENT '文章https地址',
      fileID varchar(255) NOT NULL COMMENT '云托管fileID',
      time varchar(30) NOT NULL COMMENT '文章发布时间',
      PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS Collection;
CREATE TABLE Collection (
    cid int(7) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '收藏id',
    openid varchar(100) NOT NULL COMMENT '用户openid',
    lid int UNSIGNED ZEROFILL NOT NULL COMMENT '土地编号',
    time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
    PRIMARY KEY (cid),
    FOREIGN KEY(lid) REFERENCES LandPost(lid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS LandRequire;
CREATE TABLE LandRequire (
    lrid int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '土地需求id',
    landType varchar(15) NOT NULL COMMENT '土地类型',
    transferType varchar(6) NOT NULL COMMENT '流转方式',
    area double NOT NULL COMMENT '土地面积',
    transferTime double NOT NULL COMMENT '流转年限',
    price double NOT NULL COMMENT '期望单价',
    address varchar(30) NOT NULL COMMENT '需求位置',
    longtitude double NULL COMMENT '经度',
    latitude double NULL COMMENT '纬度',
    adInfo varchar(50) NULL COMMENT '行政规划信息',
    description varchar(1500) NULL COMMENT '需求描述',
    telenumber char(11) NOT NULL COMMENT '联系电话',
    openid varchar(100) NOT NULL COMMENT '发布者openid',
    submitTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
    PRIMARY KEY (lrid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS TriggerWhenDeleteUser;
# DELIMITER //
CREATE TRIGGER TriggerWhenDeleteUser
    AFTER DELETE ON UserInformation
    FOR EACH ROW
    BEGIN
        DELETE FROM LandPost WHERE LandPost.openid=OLD.openid;
        DELETE FROM Collection WHERE Collection.openid=OLD.openid;
        DELETE FROM LandRequire WHERE LandRequire.openid=OLD.openid;
    END;
# DELIMITER ;

DROP TABLE IF EXISTS Conversation;
CREATE TABLE Conversation (
    conversationID varchar(255) NOT NULL COMMENT '会话ID',
    openid1 varchar(150) NOT NULL COMMENT '用户1的openid',
    openid2 varchar(150) NOT NULL COMMENT '用户2的openid',
    unreadCount1 int NOT NULL DEFAULT 0 COMMENT '用户1未读计数',
    unreadCount2 int NOT NULL DEFAULT 0 COMMENT '用户2未读计数',
    isPinned1 boolean NOT NULL DEFAULT false COMMENT '用户1是否置顶会话',
    isPinned2 boolean NOT NULL DEFAULT false COMMENT '用户2是否置顶会话',
    isDeleted1 boolean NOT NULL DEFAULT false COMMENT '用户1是否删除会话',
    isDeleted2 boolean NOT NULL DEFAULT false COMMENT '用户2是否删除会话',
    lastMessageID varchar(150) NOT NULL DEFAULT 'NONE' COMMENT '会话最新的消息id，-1说明会话暂无消息',
    PRIMARY KEY (conversationID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS Message;
CREATE TABLE Message (
    messageID varchar(150) NOT NULL COMMENT '消息ID',
    conversationID varchar(255) NOT NULL COMMENT '消息所属的会话ID',
    `from` varchar(150) NOT NULL COMMENT '发送方openid',
    `to` varchar(150) NOT NULL COMMENT '接收方openid',
    type varchar(30) NOT NULL COMMENT '消息类型',
    payload text NOT NULL COMMENT '消息的内容，文字或url',
    time long NOT NULL COMMENT '消息时间戳，单位：秒',
    isRevoked boolean NOT NULL DEFAULT false COMMENT '是否被撤回的消息，true标识被撤回的消息',
    isPeerRead boolean NOT NULL DEFAULT false COMMENT '消息对端是否已读，true标识对端已读',
    isFromDeleted boolean NOT NULL DEFAULT false COMMENT '接收方是否删除，true标识删除',
    isToDeleted boolean NOT NULL DEFAULT false COMMENT '接收方是否删除，true标识删除',
    PRIMARY KEY (messageID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS TriggerWhenInsertMessage;
CREATE TRIGGER TriggerWhenInsertMessage
    AFTER INSERT ON Message
    FOR EACH ROW
BEGIN
    UPDATE Conversation SET isDeleted1=false, isDeleted2=false WHERE conversationID=NEW.conversationID;
    UPDATE Conversation SET lastMessageID=NEW.messageID WHERE conversationID=NEW.conversationID;
    UPDATE Conversation SET unreadCount1=(unreadCount1+1) WHERE conversationID=NEW.conversationID and openid1=NEW.`to`;
    UPDATE Conversation SET unreadCount2=(unreadCount2+1) WHERE conversationID=NEW.conversationID and openid2=NEW.`to`;
END;