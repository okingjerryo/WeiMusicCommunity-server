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

 Date: 05/13/2017 14:08:13 PM
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
  `CCreateTime` datetime DEFAULT NULL,
  `Cintroduce` varchar(255) DEFAULT NULL,
  `CTag` varchar(255) DEFAULT NULL,
  `CHeadImg` varchar(255) DEFAULT NULL,
  `CIsNMin` int(11) NOT NULL DEFAULT '1' COMMENT '是否接新',
  `CNMDemand` text COMMENT '招新需求',
  `CMeMCount` int(11) DEFAULT NULL,
  `CNotice` varchar(255) DEFAULT NULL,
  `CNoteCTime` datetime DEFAULT NULL,
  PRIMARY KEY (`CId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `CommityInfo`
-- ----------------------------
BEGIN;
INSERT INTO `CommityInfo` VALUES ('89ad59ed-755e-4b67-b7e2-73bf1cfb1293', '一个万能的小社团', '2017-05-10 00:21:48', '全新的社团 全新的开始', '古风 现代', 'CommitySpace/89ad59ed-755e-4b67-b7e2-73bf1cfb1293/1494498359263.png', '1', '认真 负责 开心', '0', '( ´﹀` )礼貌的微笑  啦啦啦\n \n\n\n\n', '2017-05-11 23:35:26');
COMMIT;

-- ----------------------------
--  Table structure for `CommityMember`
-- ----------------------------
DROP TABLE IF EXISTS `CommityMember`;
CREATE TABLE `CommityMember` (
  `CMid` varchar(36) NOT NULL,
  `CId` varchar(36) NOT NULL,
  `UUuid` varchar(36) NOT NULL,
  `UJoinTime` datetime NOT NULL,
  `Utype` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CMid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `CommityMember`
-- ----------------------------
BEGIN;
INSERT INTO `CommityMember` VALUES ('69a3befe-9f8f-468b-8ef9-ded9568f733d', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '2017-05-05 00:18:32', '4'), ('c015ea85-6701-4b7c-afae-860b78n9d3', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293', 'c015ea85-6701-4b7c-afae-860b39f59i3d', '2017-05-05 14:00:58', '-1');
COMMIT;

-- ----------------------------
--  Table structure for `CommityType`
-- ----------------------------
DROP TABLE IF EXISTS `CommityType`;
CREATE TABLE `CommityType` (
  `UTypeId` int(11) NOT NULL,
  `UTypeName` varchar(255) NOT NULL,
  PRIMARY KEY (`UTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `MId` varchar(36) NOT NULL,
  `MCreateTime` datetime NOT NULL,
  `MSenderId` varchar(36) NOT NULL,
  `MTarId` varchar(36) NOT NULL,
  `MTitle` varchar(255) NOT NULL,
  `MThings` text,
  `MIsReaded` int(11) NOT NULL DEFAULT '0',
  `MCheck` int(11) NOT NULL DEFAULT '-2',
  `MSpcId` varchar(255) DEFAULT NULL,
  `MSenderType` int(11) DEFAULT '1' COMMENT '0 系统 1 个人 2 社团 3 项目',
  PRIMARY KEY (`MId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ProjectDynamic`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectDynamic`;
CREATE TABLE `ProjectDynamic` (
  `PDId` varchar(36) NOT NULL,
  `Time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Word` varchar(255) DEFAULT NULL,
  `PWId` varchar(36) NOT NULL,
  `PFId` varchar(36) DEFAULT NULL,
  `PDType` varchar(255) NOT NULL,
  PRIMARY KEY (`PDId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ProjectFile`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectFile`;
CREATE TABLE `ProjectFile` (
  `PFId` varchar(36) NOT NULL,
  `PFPath` varchar(255) NOT NULL,
  `PWId` varchar(36) NOT NULL,
  `PFCreateTime` datetime NOT NULL,
  `PFNotice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PFId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ProjectInfo`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectInfo`;
CREATE TABLE `ProjectInfo` (
  `PId` varchar(36) NOT NULL,
  `CId` varchar(36) NOT NULL,
  `CreatUUuid` varchar(36) NOT NULL,
  `PEndTime` datetime DEFAULT NULL,
  `PTitle` varchar(255) NOT NULL,
  `PIntroduce` text,
  `PCreatTime` datetime NOT NULL,
  `PState` int(11) NOT NULL DEFAULT '0',
  `PType` varchar(255) NOT NULL,
  PRIMARY KEY (`PId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ProjectState`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectState`;
CREATE TABLE `ProjectState` (
  `StateId` int(11) NOT NULL,
  `StateName` varchar(255) NOT NULL,
  PRIMARY KEY (`StateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ProjectState`
-- ----------------------------
BEGIN;
INSERT INTO `ProjectState` VALUES ('0', '募集成员中'), ('1', '等待策划下发文件'), ('2', '歌手试唱'), ('3', '后期制作'), ('4', '美工绘图'), ('5', '策划最终确认'), ('6', '项目完成');
COMMIT;

-- ----------------------------
--  Table structure for `ProjectWork`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectWork`;
CREATE TABLE `ProjectWork` (
  `PWId` varchar(36) NOT NULL,
  `PId` varchar(36) NOT NULL,
  `UWid` varchar(36) NOT NULL,
  `UJoinTIme` datetime NOT NULL,
  PRIMARY KEY (`PWId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `UserExtend`
-- ----------------------------
DROP TABLE IF EXISTS `UserExtend`;
CREATE TABLE `UserExtend` (
  `UUuid` varchar(36) NOT NULL,
  `UNackName` varchar(255) NOT NULL,
  `USex` varchar(4) NOT NULL,
  `UBIrthday` datetime DEFAULT NULL,
  `UTag` varchar(255) DEFAULT NULL,
  `USign` varchar(255) DEFAULT NULL,
  `UHeadImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UUuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `UserExtend`
-- ----------------------------
BEGIN;
INSERT INTO `UserExtend` VALUES ('c015ea85-6701-4b7c-afae-860b39f59c8d', '小宝宝', '女', '1994-11-17 00:00:00', null, '煎饼果子来一套！！', 'UserSpace/c015ea85-6701-4b7c-afae-860b39f59c8d/1493905036369.png'), ('c015ea85-6701-4b7c-afae-860b39f59i3d', '万能的仓鼠', '男', '1994-11-18 00:00:00', null, '啦啦啦啦', null), ('c015ea85-6701-4b7c-afae-860b39f5e89', 'tester', '男', '1999-05-20 00:00:00', null, '噢噢噢噢', null);
COMMIT;

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
--  Records of `UserLogin`
-- ----------------------------
BEGIN;
INSERT INTO `UserLogin` VALUES ('170fee44-bc01-41e9-a9ed-b57e0443a3d0', '827ccb0eea8a706c4c34a16891f84e7b', 'admin', null, null, '0'), ('c015ea85-6701-4b7c-afae-860b39f59c8d', '827ccb0eea8a706c4c34a16891f84e7b', 'lalala', null, null, '0');
COMMIT;

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
--  Records of `Work`
-- ----------------------------
BEGIN;
INSERT INTO `Work` VALUES ('25eac861-ea5e-46a5-ac27-cd1d777823de', '歌曲', '后期', '普通'), ('305de965-d553-4e5f-b72a-b48a4fb309fd', '歌曲', '编曲', '普通'), ('4eb972b0-b609-47c6-b4a1-42580ee4eea0', '歌曲', '曲作', '普通'), ('88ccad85-934e-4fa1-98ba-f91072b50a7a', '通用', '美工', '普通'), ('cd044c0d-ece6-4efc-b758-6e6c32d43a15', '歌曲', '策划', '策划'), ('d61f25b4-e0ea-4691-a83a-71bf96b6e661', '歌曲', '歌手', '普通'), ('e5c41db1-f14b-43ab-98d3-2bd161cde857', '歌曲', '词作', '普通');
COMMIT;

-- ----------------------------
--  View structure for `sel_CommityMemList`
-- ----------------------------
DROP VIEW IF EXISTS `sel_CommityMemList`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `sel_CommityMemList` AS select `CommityMember`.`CId` AS `CId`,`UserExtend`.`UNackName` AS `UNackName`,`UserExtend`.`USex` AS `USex`,`UserExtend`.`UBIrthday` AS `UBIrthday`,`UserExtend`.`UTag` AS `UTag`,`UserExtend`.`USign` AS `USign`,`UserExtend`.`UHeadImg` AS `UHeadImg`,`UserExtend`.`UUuid` AS `UUuid` from (`CommityMember` join `UserExtend` on((`CommityMember`.`UUuid` = `UserExtend`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_PersonalInfoPO`
-- ----------------------------
DROP VIEW IF EXISTS `sel_PersonalInfoPO`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `sel_PersonalInfoPO` AS select `UserExtend`.`UUuid` AS `UUuid`,`UserExtend`.`UNackName` AS `UNackName`,`UserExtend`.`UBIrthday` AS `UBIrthday`,`UserExtend`.`USex` AS `USex`,`UserExtend`.`UTag` AS `UTag`,`UserExtend`.`USign` AS `USign`,`UserExtend`.`UHeadImg` AS `UHeadImg`,`UserLogin`.`UAName` AS `UAName`,`UserLogin`.`UTel` AS `UTel`,`UserLogin`.`UMail` AS `UMail` from (`UserExtend` join `UserLogin` on((`UserExtend`.`UUuid` = `UserLogin`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_ProjectDynWithNoFile`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectDynWithNoFile`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `sel_ProjectDynWithNoFile` AS select `ProjectDynamic`.`PDId` AS `PDId`,`ProjectDynamic`.`Time` AS `Time`,`Work`.`WorkSC` AS `WorkSC`,`UserExtend`.`UNackName` AS `UNackName`,`ProjectDynamic`.`PDType` AS `PDType`,`ProjectInfo`.`PTitle` AS `PTitle` from (((((`ProjectDynamic` join `ProjectWork` on((`ProjectDynamic`.`PWId` = `ProjectWork`.`PWId`))) join `ProjectInfo` on((`ProjectWork`.`PId` = `ProjectInfo`.`PId`))) join `UserWork` on((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) join `UserExtend` on((`UserWork`.`UUuid` = `UserExtend`.`UUuid`))) join `Work` on((`UserWork`.`WorkId` = `Work`.`WorkId`)));

-- ----------------------------
--  View structure for `sel_ProjectInfo`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectInfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `sel_ProjectInfo` AS select `ProjectInfo`.`PId` AS `PId`,`ProjectInfo`.`CId` AS `CId`,`ProjectInfo`.`CreatUUuid` AS `CreatUUuid`,`UserExtend`.`UNackName` AS `UNackName`,`ProjectInfo`.`PCreatTime` AS `PCreatTime`,`ProjectInfo`.`PEndTime` AS `PEndTime`,`ProjectInfo`.`PTitle` AS `PTitle`,`ProjectInfo`.`PIntroduce` AS `PIntroduce`,`ProjectInfo`.`PState` AS `PState`,`ProjectState`.`StateName` AS `StateName`,`ProjectInfo`.`PType` AS `PType` from ((`ProjectInfo` join `UserExtend` on((`ProjectInfo`.`CreatUUuid` = `UserExtend`.`UUuid`))) join `ProjectState` on((`ProjectInfo`.`PState` = `ProjectState`.`StateId`)));

-- ----------------------------
--  View structure for `sel_ProjectInfoPO`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectInfoPO`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `sel_ProjectInfoPO` AS select `ProjectInfo`.`PId` AS `PId`,`UserWork`.`UUuid` AS `UUuid`,`ProjectInfo`.`PType` AS `PType`,`ProjectInfo`.`PTitle` AS `PTitle`,`ProjectWork`.`UJoinTIme` AS `UJoinTIme`,`ProjectInfo`.`PCreatTime` AS `PCreatTime`,`ProjectInfo`.`PIntroduce` AS `PIntroduce`,`ProjectInfo`.`CreatUUuid` AS `CreatUUuid`,`CommityInfo`.`CName` AS `CName`,`Work`.`WorkId` AS `WorkId`,`Work`.`WorkFC` AS `WorkFC`,`Work`.`WorkSC` AS `WorkSC`,`ProjectInfo`.`PState` AS `PState` from ((((`ProjectWork` join `UserWork` on((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) join `ProjectInfo` on((`ProjectWork`.`PId` = `ProjectInfo`.`PId`))) join `CommityInfo` on((`ProjectInfo`.`CId` = `CommityInfo`.`CId`))) join `Work` on((`UserWork`.`WorkId` = `Work`.`WorkId`))) order by `ProjectWork`.`UJoinTIme`;

-- ----------------------------
--  View structure for `sel_commitymemview`
-- ----------------------------
DROP VIEW IF EXISTS `sel_commitymemview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `sel_commitymemview` AS select `CommityMember`.`CMid` AS `CMid`,`CommityMember`.`CId` AS `CId`,`CommityInfo`.`CName` AS `CName`,`CommityMember`.`UUuid` AS `UUuid`,`UserExtend`.`UNackName` AS `UNackName`,`CommityMember`.`UJoinTime` AS `UJoinTime`,`CommityMember`.`Utype` AS `Utype`,`CommityType`.`UTypeName` AS `UTypeName` from (((`CommityMember` join `CommityType` on((`CommityMember`.`Utype` = `CommityType`.`UTypeId`))) join `UserExtend` on((`CommityMember`.`UUuid` = `UserExtend`.`UUuid`))) join `CommityInfo` on((`CommityInfo`.`CId` = `CommityMember`.`CId`)));

-- ----------------------------
--  View structure for `userworkview`
-- ----------------------------
DROP VIEW IF EXISTS `userworkview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`connect`@`%` SQL SECURITY DEFINER VIEW `userworkview` AS select `UserWork`.`UWid` AS `UWid`,`UserWork`.`UUuid` AS `UUuid`,`UserWork`.`WorkId` AS `WorkId`,`Work`.`WorkFC` AS `WorkFC`,`Work`.`WorkSC` AS `WorkSC`,`UserWork`.`isFreq` AS `isFreq`,`UserWork`.`status` AS `status`,`UserWork`.`delayTime` AS `delayTime` from (`UserWork` join `Work` on((`UserWork`.`WorkId` = `Work`.`WorkId`)));

SET FOREIGN_KEY_CHECKS = 1;
