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

 Date: 05/01/2017 14:17:45 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `CommityActive`
-- ----------------------------
DROP TABLE IF EXISTS `CommityActive`;
CREATE TABLE `CommityActive` (
  `CAId` varchar(36) NOT NULL,
  `Cid` varchar(36) NOT NULL,
  `CAStartTime` datetime NOT NULL,
  `CAEndTime` datetime NOT NULL,
  `CATitle` varchar(255) NOT NULL,
  `CAThings` varchar(255) DEFAULT NULL,
  `CACreatUserId` varchar(36) NOT NULL,
  `CAImportent` int(11) DEFAULT '0',
  `CAImgPath` varchar(255) DEFAULT NULL COMMENT '海报位置',
  PRIMARY KEY (`CAId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `CommityInfo`
-- ----------------------------
DROP TABLE IF EXISTS `CommityInfo`;
CREATE TABLE `CommityInfo` (
  `CId` varchar(36) NOT NULL,
  `CName` varchar(255) NOT NULL,
  `CCreateTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Cintroduce` varchar(255) DEFAULT NULL,
  `CTag` varchar(255) DEFAULT NULL,
  `CHeadImg` varchar(255) DEFAULT NULL,
  `CIsNMin` int(11) NOT NULL DEFAULT '1' COMMENT '是否接新',
  `CNMDemand` text COMMENT '招新需求',
  `CMeMCount` int(11) DEFAULT NULL,
  `CNotice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `CommityMember`
-- ----------------------------
DROP TABLE IF EXISTS `CommityMember`;
CREATE TABLE `CommityMember` (
  `CMid` varchar(36) NOT NULL,
  `CId` varchar(36) NOT NULL,
  `UUuid` varchar(36) NOT NULL,
  `UJoinTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Utype` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CMid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `MessageAttach`
-- ----------------------------
DROP TABLE IF EXISTS `MessageAttach`;
CREATE TABLE `MessageAttach` (
  `MAId` varchar(36) NOT NULL,
  `MId` varchar(36) NOT NULL,
  `MCreateTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MAFileName` varchar(255) NOT NULL,
  `MAFilePath` varchar(255) DEFAULT NULL,
  `MAIsReaseved` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`MAId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `MessageBox`
-- ----------------------------
DROP TABLE IF EXISTS `MessageBox`;
CREATE TABLE `MessageBox` (
  `MId` varchar(36) NOT NULL,
  `MCreateTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MSenderId` varchar(36) NOT NULL,
  `MTarId` varchar(36) NOT NULL,
  `MTitle` varchar(255) NOT NULL,
  `MThings` text,
  `MIsReaded` int(11) NOT NULL DEFAULT '0',
  `MImportant` int(11) NOT NULL DEFAULT '0',
  `MCheck` int(11) NOT NULL DEFAULT '-2',
  `MSpcType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `UserExtend`
-- ----------------------------
DROP TABLE IF EXISTS `UserExtend`;
CREATE TABLE `UserExtend` (
  `UUuid` varchar(36) NOT NULL,
  `UNackName` varchar(255) NOT NULL,
  `USex` varchar(4) NOT NULL,
  `UBIrthday` date DEFAULT NULL,
  `UTag` varchar(255) DEFAULT NULL,
  `USign` varchar(255) DEFAULT NULL,
  `UHeadImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UUuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `UserFriend`
-- ----------------------------
DROP TABLE IF EXISTS `UserFriend`;
CREATE TABLE `UserFriend` (
  `UFId` varchar(36) NOT NULL,
  `UFUuid` varchar(36) NOT NULL COMMENT '朋友关系起始者',
  `USUuid` varchar(36) NOT NULL COMMENT '关系人 搜索时需要两个字段同时搜索',
  `UFStatus` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UFId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `UserLogin`
-- ----------------------------
DROP TABLE IF EXISTS `UserLogin`;
CREATE TABLE `UserLogin` (
  `UUuid` varchar(36) NOT NULL,
  `UPwd` varchar(255) NOT NULL,
  `UAName` varchar(255) DEFAULT NULL,
  `UTel` varchar(255) DEFAULT NULL,
  `UMail` varchar(255) DEFAULT NULL,
  `UAccStatus` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UUuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `UserWork`
-- ----------------------------
DROP TABLE IF EXISTS `UserWork`;
CREATE TABLE `UserWork` (
  `UWid` varchar(36) NOT NULL,
  `UUuid` varchar(36) NOT NULL,
  `WorkId` varchar(36) NOT NULL,
  `isFreq` int(11) NOT NULL,
  `delayTime` datetime DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UWid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `Work`
-- ----------------------------
DROP TABLE IF EXISTS `Work`;
CREATE TABLE `Work` (
  `WorkId` varchar(36) NOT NULL,
  `WorkFC` varchar(255) NOT NULL,
  `WorkSC` varchar(255) NOT NULL,
  `WorkType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`WorkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  View structure for `userworkview`
-- ----------------------------
DROP VIEW IF EXISTS `userworkview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `userworkview` AS select `UserWork`.`UWid` AS `UWid`,`UserWork`.`UUuid` AS `UUuid`,`UserWork`.`WorkId` AS `WorkId`,`Work`.`WorkFC` AS `WorkFC`,`Work`.`WorkSC` AS `WorkSC`,`UserWork`.`isFreq` AS `isFreq`,`UserWork`.`status` AS `status`,`UserWork`.`delayTime` AS `delayTime` from (`UserWork` join `Work` on((`UserWork`.`WorkId` = `Work`.`WorkId`)));

SET FOREIGN_KEY_CHECKS = 1;
