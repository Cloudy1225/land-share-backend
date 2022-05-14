CREATE TABLE `Counters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '1',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `UserInformation` (
    `uid` int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, #我们自定义的用户唯一标识
    `openid` varchar(100) NOT NULL, # 小程序自动生成的唯一标识
    `telenumber` char(11) NULL,
    `username` varchar(9) NULL, # 真实姓名
    `idnumber` char(18) NULL, # 身份证号
    `role` char(1) NOT NULL DEFAULT '1', # 用户权限
    PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; # InnoDB支持外键等

ALTER TABLE `UserInformation` ALTER COLUMN `role` DROP DEFAULT ; # 如果你的用户信息表role的默认值为'1'就不需要执行下面两句

ALTER TABLE `UserInformation` ALTER COLUMN `role` SET DEFAULT '1';

CREATE TABLE LandPost (
    lid int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '土地发布id',
    landType varchar(15) NOT NULL COMMENT '土地类型',
    transferType varchar(6) NOT NULL COMMENT '流转方式',
    area double NOT NULL COMMENT '土地面积',
    transferTime double NOT NULL COMMENT '流转时间',
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

CREATE TABLE Article (
      aid int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '文章id',
      title varchar(100) NOT NULL COMMENT '文章标题',
      type varchar(12) NOT NULL COMMENT '文章类型',
      url varchar(255) NOT NULL COMMENT '文章https地址',
      fileID varchar(255) NOT NULL COMMENT '云托管fileID',
      time varchar(30) NOT NULL COMMENT '文章发布时间',
      PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Collection (
    cid int(7) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '收藏id',
    openid varchar(100) NOT NULL COMMENT '用户openid',
    lid int UNSIGNED ZEROFILL NOT NULL COMMENT '土地编号',
    time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
    PRIMARY KEY (cid),
    FOREIGN KEY(lid) REFERENCES LandPost(lid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;