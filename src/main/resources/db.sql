CREATE TABLE `Counters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '1',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `UserInformation` (
    `uid` int(7)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, #我们自定义的用户唯一标识
    `openid` varchar(100) NOT NULL, # 小程序自动生成的唯一标识
    `telenumber` char(11) NULL,
    `username` varchar(9) NULL, # 真实姓名
    `idnumber` char(18) NULL, # 身份证号
    `collection` varchar(10) NULL, # 我的收藏
    `role` char(1) NOT NULL DEFAULT '0', # 用户权限
    PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; # InnoDB支持外键等