/*
 Navicat Premium Data Transfer

 Source Server         : localCon
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : WeiCommity

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 05/10/2017 15:14:09 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `CommityActive`
-- ----------------------------
DROP TABLE IF EXISTS `CommityActive`;
CREATE TABLE `CommityActive` (
  `CAId`          VARCHAR(36)  NOT NULL,
  `Cid`           VARCHAR(36)  NOT NULL,
  `CAStartTime`   DATETIME     NOT NULL,
  `CAEndTime`     DATETIME     NOT NULL,
  `CATitle`       VARCHAR(255) NOT NULL,
  `CAThings`      VARCHAR(255) DEFAULT NULL,
  `CACreatUserId` VARCHAR(36)  NOT NULL,
  `CAImgPath`     VARCHAR(255) DEFAULT NULL
  COMMENT '海报位置',
  PRIMARY KEY (`CAId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `CommityInfo`
-- ----------------------------
DROP TABLE IF EXISTS `CommityInfo`;
CREATE TABLE `CommityInfo` (
  `CId`         VARCHAR(36)  NOT NULL,
  `CName`       VARCHAR(255) NOT NULL,
  `CCreateTime` DATETIME              DEFAULT NULL,
  `Cintroduce`  VARCHAR(255)          DEFAULT NULL,
  `CTag`        VARCHAR(255)          DEFAULT NULL,
  `CHeadImg`    VARCHAR(255)          DEFAULT NULL,
  `CIsNMin`     INT(11)      NOT NULL DEFAULT '1'
  COMMENT '是否接新',
  `CNMDemand`   TEXT COMMENT '招新需求',
  `CMeMCount`   INT(11)               DEFAULT NULL,
  `CNotice`     VARCHAR(255)          DEFAULT NULL,
  `CNoteCTime`  DATETIME              DEFAULT NULL,
  PRIMARY KEY (`CId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `CommityInfo`
-- ----------------------------
BEGIN;
INSERT INTO `CommityInfo` VALUES
  ('89ad59ed-755e-4b67-b7e2-73bf1cfb1293', '一个万能的小社团', '2017-05-10 00:21:48', '全新的社团 全新的开始', '古风 现代',
                                           'CommitySpace/89ad59ed-755e-4b67-b7e2-73bf1cfb12931494346482647.png', '1',
                                           '认真 负责 开心', '0', '( ´﹀` )礼貌的微笑  ffghduuf\n \n\n\n\n', NULL);
COMMIT;

-- ----------------------------
--  Table structure for `CommityMember`
-- ----------------------------
DROP TABLE IF EXISTS `CommityMember`;
CREATE TABLE `CommityMember` (
  `CMid`      VARCHAR(36) NOT NULL,
  `CId`       VARCHAR(36) NOT NULL,
  `UUuid`     VARCHAR(36) NOT NULL,
  `UJoinTime` DATETIME    NOT NULL,
  `Utype`     INT(11)     NOT NULL DEFAULT '0',
  PRIMARY KEY (`CMid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `CommityMember`
-- ----------------------------
BEGIN;
INSERT INTO `CommityMember` VALUES ('69a3befe-9f8f-468b-8ef9-ded9568f733d', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
                                    'c015ea85-6701-4b7c-afae-860b39f59c8d', '2017-05-05 00:18:32', '4'),
  ('c015ea85-6701-4b7c-afae-860b78n9d3', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293', 'c015ea85-6701-4b7c-afae-860b39f59i3d',
   '2017-05-05 14:00:58', '1');
COMMIT;

-- ----------------------------
--  Table structure for `CommityType`
-- ----------------------------
DROP TABLE IF EXISTS `CommityType`;
CREATE TABLE `CommityType` (
  `UTypeId`   INT(11)      NOT NULL,
  `UTypeName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`UTypeId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `CommityType`
-- ----------------------------
BEGIN;
INSERT INTO `CommityType` VALUES ('-1', '小黑屋'), ('0', '审核中'), ('1', '正式成员'), ('2', '管理员'), ('3', '副社长'), ('4', '社长');
COMMIT;

-- ----------------------------
--  Table structure for `MessageBox`
-- ----------------------------
DROP TABLE IF EXISTS `MessageBox`;
CREATE TABLE `MessageBox` (
  `MId`         VARCHAR(36)  NOT NULL,
  `MCreateTime` DATETIME     NOT NULL,
  `MSenderId`   VARCHAR(36)  NOT NULL,
  `MTarId`      VARCHAR(36)  NOT NULL,
  `MTitle`      VARCHAR(255) NOT NULL,
  `MThings`     TEXT,
  `MIsReaded`   INT(11)      NOT NULL DEFAULT '0',
  `MCheck`      INT(11)      NOT NULL DEFAULT '-2',
  `MSpcId`      VARCHAR(255)          DEFAULT NULL,
  PRIMARY KEY (`MId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `ProjectDynamic`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectDynamic`;
CREATE TABLE `ProjectDynamic` (
  `PDId`   VARCHAR(36)  NOT NULL,
  `Time`   DATETIME     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Word`   VARCHAR(255) DEFAULT NULL,
  `PWId`   VARCHAR(36)  NOT NULL,
  `PFId`   VARCHAR(36)  NOT NULL,
  `PDType` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`PDId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `ProjectFile`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectFile`;
CREATE TABLE `ProjectFile` (
  `PFId`         VARCHAR(36)  NOT NULL,
  `PFPath`       VARCHAR(255) NOT NULL,
  `PWId`         VARCHAR(36)  NOT NULL,
  `PFCreateTime` DATETIME     NOT NULL,
  `PFNotice`     VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`PFId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `ProjectInfo`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectInfo`;
CREATE TABLE `ProjectInfo` (
  `PId`        VARCHAR(36)  NOT NULL,
  `CId`        VARCHAR(36)  NOT NULL,
  `CreatUUuid` VARCHAR(36)  NOT NULL,
  `PEndTime`   DATETIME              DEFAULT NULL,
  `PTitle`     VARCHAR(255) NOT NULL,
  `PIntroduce` TEXT,
  `PCreatTime` DATETIME     NOT NULL,
  `PState`     INT(11)      NOT NULL DEFAULT '0',
  `PType`      VARCHAR(255) NOT NULL,
  PRIMARY KEY (`PId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `ProjectWork`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectWork`;
CREATE TABLE `ProjectWork` (
  `PWId`      VARCHAR(36) NOT NULL,
  `PId`       VARCHAR(36) NOT NULL,
  `UUuid`     VARCHAR(36) NOT NULL,
  `UWid`      VARCHAR(36) NOT NULL,
  `UJoinTIme` DATETIME    NOT NULL,
  PRIMARY KEY (`PWId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `UserExtend`
-- ----------------------------
DROP TABLE IF EXISTS `UserExtend`;
CREATE TABLE `UserExtend` (
  `UUuid`     VARCHAR(36)  NOT NULL,
  `UNackName` VARCHAR(255) NOT NULL,
  `USex`      VARCHAR(4)   NOT NULL,
  `UBIrthday` DATE         DEFAULT NULL,
  `UTag`      VARCHAR(255) DEFAULT NULL,
  `USign`     VARCHAR(255) DEFAULT NULL,
  `UHeadImg`  VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`UUuid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `UserExtend`
-- ----------------------------
BEGIN;
INSERT INTO `UserExtend` VALUES ('c015ea85-6701-4b7c-afae-860b39f59c8d', '小宝宝', '女', '1994-11-17', NULL, '煎饼果子来一套！！',
                                 'UserSpace/c015ea85-6701-4b7c-afae-860b39f59c8d/1493905036369.png'),
  ('c015ea85-6701-4b7c-afae-860b39f59i3d', '万能的仓鼠', '男', '1994-11-18', NULL, '啦啦啦啦', NULL),
  ('c015ea85-6701-4b7c-afae-860b39f5e89', 'tester', '男', '1999-05-20', NULL, '噢噢噢噢', NULL);
COMMIT;

-- ----------------------------
--  Table structure for `UserFriend`
-- ----------------------------
DROP TABLE IF EXISTS `UserFriend`;
CREATE TABLE `UserFriend` (
  `UFId`     VARCHAR(36) NOT NULL,
  `UFUuid`   VARCHAR(36) NOT NULL
  COMMENT '朋友关系起始者',
  `USUuid`   VARCHAR(36) NOT NULL
  COMMENT '关系人 搜索时需要两个字段同时搜索',
  `UFStatus` INT(11)     NOT NULL DEFAULT '0',
  PRIMARY KEY (`UFId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `UserLogin`
-- ----------------------------
DROP TABLE IF EXISTS `UserLogin`;
CREATE TABLE `UserLogin` (
  `UUuid`      VARCHAR(36)  NOT NULL,
  `UPwd`       VARCHAR(255) NOT NULL,
  `UAName`     VARCHAR(255)          DEFAULT NULL,
  `UTel`       VARCHAR(255)          DEFAULT NULL,
  `UMail`      VARCHAR(255)          DEFAULT NULL,
  `UAccStatus` VARCHAR(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UUuid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `UserWork`
-- ----------------------------
DROP TABLE IF EXISTS `UserWork`;
CREATE TABLE `UserWork` (
  `UWid`      VARCHAR(36) NOT NULL,
  `UUuid`     VARCHAR(36) NOT NULL,
  `WorkId`    VARCHAR(36) NOT NULL,
  `isFreq`    INT(11)     NOT NULL,
  `delayTime` DATETIME             DEFAULT NULL,
  `status`    INT(11)     NOT NULL DEFAULT '0',
  PRIMARY KEY (`UWid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `Work`
-- ----------------------------
DROP TABLE IF EXISTS `Work`;
CREATE TABLE `Work` (
  `WorkId`   VARCHAR(36)  NOT NULL,
  `WorkFC`   VARCHAR(255) NOT NULL,
  `WorkSC`   VARCHAR(255) NOT NULL,
  `WorkType` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`WorkId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  View structure for `commitymemview`
-- ----------------------------
DROP VIEW IF EXISTS `commitymemview`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `commitymemview` AS
  SELECT
    `CommityMember`.`CMid`      AS `CMid`,
    `CommityMember`.`CId`       AS `CId`,
    `CommityInfo`.`CName`       AS `CName`,
    `CommityMember`.`UUuid`     AS `UUuid`,
    `UserExtend`.`UNackName`    AS `UNackName`,
    `CommityMember`.`UJoinTime` AS `UJoinTime`,
    `CommityMember`.`Utype`     AS `Utype`,
    `CommityType`.`UTypeName`   AS `UTypeName`
  FROM (((`CommityMember`
    JOIN `CommityType` ON ((`CommityMember`.`Utype` = `CommityType`.`UTypeId`))) JOIN `UserExtend`
      ON ((`CommityMember`.`UUuid` = `UserExtend`.`UUuid`))) JOIN `CommityInfo`
      ON ((`CommityInfo`.`CId` = `CommityMember`.`CId`)));

-- ----------------------------
--  View structure for `userworkview`
-- ----------------------------
DROP VIEW IF EXISTS `userworkview`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `userworkview` AS
  SELECT
    `UserWork`.`UWid`      AS `UWid`,
    `UserWork`.`UUuid`     AS `UUuid`,
    `UserWork`.`WorkId`    AS `WorkId`,
    `Work`.`WorkFC`        AS `WorkFC`,
    `Work`.`WorkSC`        AS `WorkSC`,
    `UserWork`.`isFreq`    AS `isFreq`,
    `UserWork`.`status`    AS `status`,
    `UserWork`.`delayTime` AS `delayTime`
  FROM (`UserWork`
    JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`)));

SET FOREIGN_KEY_CHECKS = 1;
