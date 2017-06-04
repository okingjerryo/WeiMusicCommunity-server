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

 Date: 06/04/2017 21:28:09 PM
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
--  Table structure for `CommityBonus`
-- ----------------------------
DROP TABLE IF EXISTS `CommityBonus`;
CREATE TABLE `CommityBonus` (
  `CBId`   VARCHAR(36) NOT NULL,
  `CMid`   VARCHAR(36) NOT NULL,
  `CBonus` INT(11)     NOT NULL DEFAULT '0',
  PRIMARY KEY (`CBId`)
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
                                           'CommitySpace/89ad59ed-755e-4b67-b7e2-73bf1cfb1293/1494498359263.png', '1',
                                           '认真 负责 开心', '5', '( ´﹀` )礼貌的微笑  修改的测试\n \n\n\n\n', '2017-05-23 12:09:21');
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
INSERT INTO `CommityMember` VALUES ('4191dad9-3c9a-47f0-8184-a61260912109', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
                                    '4191dad9-3c9a-47f0-8184-a61260915189', '2017-06-03 14:28:35', '1'),
  ('69a3befe-9f8f-468b-8ef9-ded9568f733d', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
   'c015ea85-6701-4b7c-afae-860b39f59c8d', '2017-05-05 00:18:32', '4'),
  ('abdca7e6-ad5b-4dba-8d94-77fac4884cw1', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
   'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '2017-05-24 18:28:16', '3'),
  ('dbba0714-8863-48cf-81ea-8afcbf1dije4', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
   'dbba0714-8863-48cf-81ea-8afcbf1dec9a', '2017-06-08 14:26:38', '1'),
  ('e7d14d71-867c-4fe4-a495-37f1107aei3j', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
   'e7d14d71-867c-4fe4-a495-37f1107aef6b', '2017-06-01 14:32:30', '1'),
  ('fb248402-3ee1-434b-bf69-8cab4345b190', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
   'fb248402-3ee1-434b-bf69-8cab4345b172', '2017-06-01 14:36:36', '1');
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
  `MSenderType` INT(11)               DEFAULT '1'
  COMMENT '0 系统 1 个人 2 社团 3 项目',
  PRIMARY KEY (`MId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `MessageBox`
-- ----------------------------
BEGIN;
INSERT INTO `MessageBox` VALUES
  ('101128d7-8106-47a4-ad5a-fbe507730da1', '2017-06-02 07:31:45', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6',
   'c015ea85-6701-4b7c-afae-860b39f59c8d', '您的项目大坏蛋歌手阶段即将完成', '策划您好，很高兴的告诉您您的项目大坏蛋歌手阶段即将完成。请进入相关项目检查提交文件,及时审核', '1',
   '0', '141e5d31-c25d-4d86-9f54-92d0984796c3', '3'),
  ('104d258b-ec4b-44d3-97b3-ec09923b4f2d', '2017-06-01 01:45:19', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'e7d14d71-867c-4fe4-a495-37f1107aef6b', '答复：一个万能的小社团社团项目：大坏蛋的后期工种加入申请', '您好，很高兴收到您的信息，很高兴的通知您，您的加入申请已通过，欢迎加入我们这个项目中~', '0', '-2', NULL, '3'),
  ('3fc7de41-0536-477b-b60c-bcfbc80bf972', '2017-06-02 04:24:47', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '您的项目大坏蛋歌手阶段即将完成', '策划您好，很高兴的告诉您您的项目大坏蛋歌手阶段即将完成。请进入相关项目检查提交文件,及时审核', '1', '0', '141e5d31-c25d-4d86-9f54-92d0984796c3', '3'),
  ('63d2d4b7-0cfc-4fec-9f28-11803d6f1cd5', '2017-06-02 07:53:11', 'e7d14d71-867c-4fe4-a495-37f1107aef6b', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '您的项目大坏蛋后期阶段即将完成', '策划您好，很高兴的告诉您您的项目大坏蛋后期阶段即将完成。请进入相关项目检查提交文件,及时审核', '1', '0', '8ed04f32-b023-448b-8336-4ad76ff100bb', '3'),
  ('667ebf4f-42a4-41f4-aec7-54a3223f67f8', '2017-06-02 01:09:08', 'fb248402-3ee1-434b-bf69-8cab4345b172', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '一个万能的小社团社团项目：啦啦啦的美工工种加入申请', '策划你好，我是本社团的美工爱小菲。最近在社团中了解到了这个项目，对其中的美工部分很感兴趣，想加入该项目与您一同完成。如果确认我可以胜任，希望能准许加入，期待与您一同完成这个优秀的项目。谢谢~~', '1', '1', 'dcc6f9a8-a843-4b69-8e3b-0a14980e85bb', '3'),
  ('6e9ff864-e8d1-486f-9338-746f6772ee7e', '2017-06-02 01:07:12', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '答复：一个万能的小社团社团项目：啦啦啦的歌手工种加入申请', '您好，很高兴收到您的信息，很高兴的通知您，您的加入申请已通过，欢迎加入我们这个项目中~', '0', '-2', NULL, '3'),
  ('7c0f3949-20e1-4149-a830-d707cf538e6b', '2017-06-02 07:56:38', 'e7d14d71-867c-4fe4-a495-37f1107aef6b', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '您的项目大坏蛋后期阶段即将完成', '策划您好，很高兴的告诉您您的项目大坏蛋后期阶段即将完成。请进入相关项目检查提交文件,及时审核', '1', '0', '8ed04f32-b023-448b-8336-4ad76ff100bb', '3'),
  ('7f821437-7d3d-4fd8-bc4f-a6fb0e7b16c4', '2017-06-02 01:06:40', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '一个万能的小社团社团项目：啦啦啦的歌手工种加入申请', '策划你好，我是本社团的歌手哈哈，我是宝宝。最近在社团中了解到了这个项目，对其中的歌手部分很感兴趣，想加入该项目与您一同完成。如果确认我可以胜任，希望能准许加入，期待与您一同完成这个优秀的项目。谢谢~~', '1', '1', 'af9520d6-20f7-4811-901e-78e52c24e7c8', '3'),
  ('7ff0fb5f-57a2-4ab5-8d8a-d09068181889', '2017-06-01 01:24:51', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '一个万能的小社团社团项目：大坏蛋的歌手工种加入申请', '策划你好，我是本社团的歌手哈哈，我是宝宝。最近在社团中了解到了这个项目，对其中的歌手部分很感兴趣，想加入该项目与您一同完成。如果确认我可以胜任，希望能准许加入，期待与您一同完成这个优秀的项目。谢谢~~', '1', '1', '9d77729c-e192-4ec5-a1cb-0e9b66af2d61', '3'),
  ('825d10ec-a241-40c3-a317-82eab0c7c517', '2017-06-01 01:44:04', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'fb248402-3ee1-434b-bf69-8cab4345b172', '答复：一个万能的小社团社团项目：大坏蛋的美工工种加入申请', '您好，很高兴收到您的信息，很高兴的通知您，您的加入申请已通过，欢迎加入我们这个项目中~', '0', '-2', NULL, '3'),
  ('833f2b8f-e2f7-464b-8be9-14e73b54bd90', '2017-06-01 07:47:20', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '项目：大坏蛋到了您的阶段', '您好，您加入的项目：大坏蛋到了您的阶段请及时制作并上传您的阶段文件', '0', '-2', NULL, '3'),
  ('8d35aff8-599a-47c4-83ab-f45860bdeca6', '2017-06-01 01:40:10', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '答复：一个万能的小社团社团项目：大坏蛋的歌手工种加入申请', '您好，很高兴收到您的信息，很高兴的通知您，您的加入申请已通过，欢迎加入我们这个项目中~', '0', '-2', NULL, '3'),
  ('913f13c2-b25e-48a0-b047-418d414fe0b3', '2017-06-02 01:10:25', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'e7d14d71-867c-4fe4-a495-37f1107aef6b', '答复：一个万能的小社团社团项目：啦啦啦的后期工种加入申请', '您好，很高兴收到您的信息，很高兴的通知您，您的加入申请已通过，欢迎加入我们这个项目中~', '0', '-2', NULL, '3'),
  ('9179a011-4242-4447-84f6-0c87686c7168', '2017-06-02 07:36:33', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'e7d14d71-867c-4fe4-a495-37f1107aef6b', '项目：大坏蛋到了您的阶段', '您好，您加入的项目：大坏蛋到了您的阶段。请及时制作并上传您的阶段文件', '0', '-2', NULL, '3'),
  ('96f8ec63-f872-4630-ad88-c1d99a3c6f0d', '2017-06-02 07:57:34', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'fb248402-3ee1-434b-bf69-8cab4345b172', '项目：大坏蛋到了您的阶段', '您好，您加入的项目：大坏蛋到了您的阶段。请及时制作并上传您的阶段文件', '0', '-2', NULL, '3'),
  ('9d6429df-53a3-4420-a226-24995a29b9d1', '2017-06-01 08:11:34', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '项目：大坏蛋到了您的阶段', '您好，您加入的项目：大坏蛋到了您的阶段请及时制作并上传您的阶段文件', '0', '-2', NULL, '3'),
  ('a4b99a60-c2ca-4faa-a946-59103c88efe6', '2017-06-01 01:39:13', 'fb248402-3ee1-434b-bf69-8cab4345b172', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '一个万能的小社团社团项目：大坏蛋的美工工种加入申请', '策划你好，我是本社团的美工爱小菲。最近在社团中了解到了这个项目，对其中的美工部分很感兴趣，想加入该项目与您一同完成。如果确认我可以胜任，希望能准许加入，期待与您一同完成这个优秀的项目。谢谢~~', '1', '1', '832f67b3-250f-454a-87a0-79d5a5fde1f2', '3'),
  ('c51da38f-6c79-42b5-b066-2c070d881981', '2017-06-02 01:10:22', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'fb248402-3ee1-434b-bf69-8cab4345b172', '答复：一个万能的小社团社团项目：啦啦啦的美工工种加入申请', '您好，很高兴收到您的信息，很高兴的通知您，您的加入申请已通过，欢迎加入我们这个项目中~', '0', '-2', NULL, '3'),
  ('cecc8d22-6666-471b-b7be-e447e2c432e4', '2017-06-02 08:17:41', 'fb248402-3ee1-434b-bf69-8cab4345b172', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '离成功只剩最后一步：您的项目大坏蛋即将全部完成', '策划您好，很高兴的告诉您离成功只剩最后一步：您的项目大坏蛋即将全部完成。请进入相关项目检查全部工种的文件,进行最终的审核。', '0', '-2', '3cc9d507-6f54-4982-8b37-fc8d516b3c97', '3'),
  ('cf17d63e-dff0-4198-ae89-1b58622e5130', '2017-06-02 07:30:32', 'c015ea85-6701-4b7c-afae-860b39f59c8d', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '审核结果:您的项目大坏蛋歌手阶段即将完成', '您好，很高兴收到您提交的文件。很遗憾的告诉您，由于某些原因您的阶段审核没有通过，请及时对相关文件进行修改', '0', '-2', NULL, '3'),
  ('e53969e7-5ca8-4066-b1c5-66521e5ebe0f', '2017-06-02 01:08:21', 'e7d14d71-867c-4fe4-a495-37f1107aef6b', 'c015ea85-6701-4b7c-afae-860b39f59c8d', '一个万能的小社团社团项目：啦啦啦的后期工种加入申请', '策划你好，我是本社团的后期yitiji。最近在社团中了解到了这个项目，对其中的后期部分很感兴趣，想加入该项目与您一同完成。如果确认我可以胜任，希望能准许加入，期待与您一同完成这个优秀的项目。谢谢~~', '1', '1', 'eea9329b-431a-432a-beee-a5c0257925d6', '3'),
  ('e7321127-d1a0-4ccf-a189-c80e0c7f0128', '2017-06-02 07:55:51', 'c015ea85-6701-4b7c-afae-860b39f59c8d',
   'e7d14d71-867c-4fe4-a495-37f1107aef6b', '审核结果:您的项目大坏蛋后期阶段即将完成',
   '您好，很高兴收到您提交的文件。很遗憾的告诉您，由于某些原因您的阶段审核没有通过，请及时对相关文件进行修改', '0', '-2', NULL, '3'),
  ('eb4d45be-60f8-4728-a20d-b36e2f8f953f', '2017-06-01 01:34:30', 'e7d14d71-867c-4fe4-a495-37f1107aef6b',
   'c015ea85-6701-4b7c-afae-860b39f59c8d', '一个万能的小社团社团项目：大坏蛋的后期工种加入申请',
   '策划你好，我是本社团的后期yitiji。最近在社团中了解到了这个项目，对其中的后期部分很感兴趣，想加入该项目与您一同完成。如果确认我可以胜任，希望能准许加入，期待与您一同完成这个优秀的项目。谢谢~~', '1', '1',
   '6b952793-b394-4bab-8362-89e84fb8523a', '3');
COMMIT;

-- ----------------------------
--  Table structure for `ProjectDynamic`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectDynamic`;
CREATE TABLE `ProjectDynamic` (
  `PDId`   VARCHAR(36)  NOT NULL,
  `Time`   DATETIME     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Word`   VARCHAR(255) DEFAULT NULL,
  `PWId`   VARCHAR(36)  NOT NULL,
  `PFId`   VARCHAR(36)  DEFAULT NULL,
  `PDType` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`PDId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `ProjectDynamic`
-- ----------------------------
BEGIN;
INSERT INTO `ProjectDynamic` VALUES ('0e044afa-8fac-4601-b760-1f9a0a6c5b3d', '2017-06-02 13:17:31',
                                     '在2017年06月02日 13:17:31 星期五 ,歌手哈哈，我是宝宝更新了项目:大坏蛋的文件，并备注了：歌曲干音',
                                     '9d77729c-e192-4ec5-a1cb-0e9b66af2d61', '141e5d31-c25d-4d86-9f54-92d0984796c3',
                                     '更新'),
  ('179642ac-6128-4382-b1d2-8acf5aea21eb', '2017-05-14 17:15:15', '在2017年05月14日 17:15:15 星期日 ,策划小宝宝创建了项目:啦啦啦', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c', NULL, '创建'),
  ('1ad0de1c-19ae-4af1-8eec-ca0d9794cbac', '2017-06-02 20:51:21', '在2017年06月02日 20:51:22 星期五 ,后期yitiji更新了项目:大坏蛋的文件，并备注了：后期1版本', '6b952793-b394-4bab-8362-89e84fb8523a', '8ed04f32-b023-448b-8336-4ad76ff100bb', '更新'),
  ('2c084ee5-d67a-4a69-8764-71c86ad069de', '2017-06-01 16:46:49', '在2017年06月01日 16:46:50 星期四 ,策划白小菲更新了项目:大坏蛋的文件，并备注了：伴奏文件', '17ec6693-014d-487e-90e1-feb94d35c456', '634fc886-04c1-4017-9e9c-2623e1cec42f', '更新'),
  ('2ca7fe21-71d9-4253-bd0e-7e23a029032a', '2017-05-29 23:48:16', '在2017年05月29日 23:48:16 星期一 ,策划菲小白创建了项目:测试2', '9419780d-db27-458f-9264-0746ae2300cf', NULL, '创建'),
  ('32b3cac9-76a6-4312-b5f7-f307b01119d0', '2017-05-17 14:38:13', '在2017年05月17日 14:31:52 星期三 ,策划破喉咙更新了项目:啦啦啦的文件，并备注了：这是一个关于文件上传的测试', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c', '940f7a2f-d1d1-4da3-8a2c-7224da5696e1', '更新'),
  ('39a7f436-f945-4717-a250-add47b42acbb', '2017-05-24 00:56:04', '在2017年05月24日 00:56:05 星期三 ,策划小白兔更新了项目:啦啦啦的文件，并备注了：对于数据库插入功能的测试', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c', '4171e6a1-9d75-4869-9f21-5662b69fa128', '更新'),
  ('3ed580c7-1cf5-4845-ada2-5082d3bfdd02', '2017-05-14 17:27:36', '在2017年05月14日 17:27:36 星期日 ,策划小宝宝创建了项目:老板', 'd295ea07-6b0f-4609-9d08-d97827e0d873', NULL, '创建'),
  ('40d99bff-87b8-481a-a97a-8547e16c84f3', '2017-06-02 21:16:53', '在2017年06月02日 21:16:54 星期五 ,美工爱小菲更新了项目:大坏蛋的文件，并备注了：美工海报', '832f67b3-250f-454a-87a0-79d5a5fde1f2', '3cc9d507-6f54-4982-8b37-fc8d516b3c97', '更新'),
  ('4596918c-d257-4d41-a2d6-55b07d2c2cfc', '2017-05-14 17:17:45', '在2017年05月14日 17:17:45 星期日 ,策划小宝宝创建了项目:大坏蛋', '03f7629c-d4a7-4c93-ab16-e364f887c536', NULL, '创建'),
  ('629c78fd-a315-42ee-ab13-8b70e7aea482', '2017-05-24 00:57:33', '在2017年05月24日 00:57:33 星期三 ,策划小白兔更新了项目:啦啦啦的文件，并备注了：对于数据库插入功能的测试', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c', 'f21a5bf1-6f2a-4692-83b2-b71e9a3a57ab', '更新'),
  ('73467d7e-f252-4cbd-8192-d7e65738f7b9', '2017-05-17 15:22:58',
   '在2017年05月17日 15:22:58 星期三 ,策划破喉咙更新了项目:啦啦啦的文件，并备注了：3号文件', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
   '790d86a6-f20d-4213-9a3b-56644a304ea2', '更新'), ('8ee28de2-32b0-4807-b871-649333c00146', '2017-05-24 02:13:23',
                                                   '在2017年05月24日 02:13:23 星期三 ,策划小白兔更新了项目:啦啦啦的文件，并备注了：文件上传测试',
                                                   'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
                                                   'd1038aba-2043-40b4-a3bb-546b8eea7a51', '更新'),
  ('9712f4c2-e2ca-4a07-8d0e-b1ead10cf065', '2017-05-17 15:32:43',
   '在2017年05月17日 15:32:44 星期三 ,策划破喉咙更新了项目:啦啦啦的文件，并备注了：5号文件', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
   'ad05c501-c920-401a-8e6b-57bd2a8a9170', '更新'), ('a973f880-9a9a-42c8-b2fa-11f3fffc086d', '2017-05-17 15:23:26',
                                                   '在2017年05月17日 15:23:26 星期三 ,策划破喉咙更新了项目:啦啦啦的文件，并备注了：4号文件',
                                                   'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
                                                   'aad39626-68c7-497d-8126-1c094fa0e91a', '更新'),
  ('be37485e-c73b-429b-886e-275488c40073', '2017-05-17 14:50:32',
   '在2017年05月17日 14:47:37 星期三 ,策划破喉咙更新了项目:啦啦啦的文件，并备注了：文件上传测试2', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
   '063ba3b0-e974-4f85-82bd-1f6e407e4f1a', '更新'), ('c1ec1456-616c-4d57-9291-35019dac9d44', '2017-05-17 16:34:35',
                                                   '在2017年05月17日 16:34:36 星期三 ,策划破喉咙更新了项目:啦啦啦的文件，并备注了： 听歌用',
                                                   'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
                                                   '743114b9-18fd-4e52-adfa-70a50e6b652f', '更新'),
  ('eb1da681-863e-4461-9a0d-0e338010c708', '2017-05-17 14:53:28',
   '在2017年05月17日 14:53:29 星期三 ,策划破喉咙更新了项目:啦啦啦的文件，并备注了：文件测试', 'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
   '1d21793f-bc3d-43d9-a46b-027c9d3d6606', '更新'),
  ('fe894a4b-e30f-492a-b0c2-3f7bf7359703', '2017-05-24 01:53:44', '在2017年05月24日 01:53:45 星期三 ,策划小白兔创建了项目:新建社团测试',
   '1ba7faa7-af47-4116-a403-7855f1452ebb', NULL, '创建');
COMMIT;

-- ----------------------------
--  Table structure for `ProjectFile`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectFile`;
CREATE TABLE `ProjectFile` (
  `PFId`            VARCHAR(36)  NOT NULL,
  `PFPath`          VARCHAR(255) NOT NULL,
  `PWId`            VARCHAR(36)  NOT NULL,
  `PFCreateTime`    DATETIME     NOT NULL,
  `PFNotice`        VARCHAR(255)          DEFAULT NULL,
  `PFIsUseComplete` INT(11)      NOT NULL DEFAULT '0',
  PRIMARY KEY (`PFId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `ProjectFile`
-- ----------------------------
BEGIN;
INSERT INTO `ProjectFile` VALUES ('141e5d31-c25d-4d86-9f54-92d0984796c3',
                                  'ProjectSpace/9d77729c-e192-4ec5-a1cb-0e9b66af2d61/2017-06-02T13:17:31.339+08:00.mp3',
                                  '9d77729c-e192-4ec5-a1cb-0e9b66af2d61', '2017-06-02 00:17:31', '歌曲干音', '1'),
  ('3cc9d507-6f54-4982-8b37-fc8d516b3c97',
   'ProjectSpace/832f67b3-250f-454a-87a0-79d5a5fde1f2/2017-06-02T21:16:53.641+08:00.jpg',
   '832f67b3-250f-454a-87a0-79d5a5fde1f2', '2017-06-02 08:16:54', '美工海报', '0'), ('634fc886-04c1-4017-9e9c-2623e1cec42f',
                                                                                 'ProjectSpace/17ec6693-014d-487e-90e1-feb94d35c456/2017-06-01T16:46:45.820+08:00.mp3',
                                                                                 '17ec6693-014d-487e-90e1-feb94d35c456',
                                                                                 '2017-06-01 03:46:49', '伴奏文件', '1'),
  ('743114b9-18fd-4e52-adfa-70a50e6b652f',
   'ProjectSpace/c016dc80-4eac-4c3d-9632-c41693ad4f8c/2017-05-17T16:34:34.716+08:00.wav',
   'c016dc80-4eac-4c3d-9632-c41693ad4f8c', '2017-05-17 03:34:36', ' 听歌用', '0'), ('8ed04f32-b023-448b-8336-4ad76ff100bb',
                                                                                 'ProjectSpace/6b952793-b394-4bab-8362-89e84fb8523a/2017-06-02T20:51:21.720+08:00.mp3',
                                                                                 '6b952793-b394-4bab-8362-89e84fb8523a',
                                                                                 '2017-06-02 07:51:22', '后期1版本', '0'),
  ('aad39626-68c7-497d-8126-1c094fa0e91a',
   'ProjectSpace/c016dc80-4eac-4c3d-9632-c41693ad4f8c/2017-05-17T15:23:21.229+08:00.wav',
   'c016dc80-4eac-4c3d-9632-c41693ad4f8c', '2017-05-17 02:23:26', '4号文件', '0'), ('ad05c501-c920-401a-8e6b-57bd2a8a9170',
                                                                                 'ProjectSpace/c016dc80-4eac-4c3d-9632-c41693ad4f8c/2017-05-17T15:31:48.412+08:00.wav',
                                                                                 'c016dc80-4eac-4c3d-9632-c41693ad4f8c',
                                                                                 '2017-05-17 02:32:44', '5号文件', '0'),
  ('d1038aba-2043-40b4-a3bb-546b8eea7a51',
   'ProjectSpace/c016dc80-4eac-4c3d-9632-c41693ad4f8c/2017-05-24T02:13:21.079+08:00.mp3',
   'c016dc80-4eac-4c3d-9632-c41693ad4f8c', '2017-05-23 13:13:23', '文件上传测试', '0'),
  ('f21a5bf1-6f2a-4692-83b2-b71e9a3a57ab',
   'ProjectSpace/c016dc80-4eac-4c3d-9632-c41693ad4f8c/2017-05-24T00:57:30.807+08:00.mp3',
   'c016dc80-4eac-4c3d-9632-c41693ad4f8c', '2017-05-23 11:57:33', '对于数据库插入功能的测试', '0');
COMMIT;

-- ----------------------------
--  Table structure for `ProjectFileReview`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectFileReview`;
CREATE TABLE `ProjectFileReview` (
  `PRId`       VARCHAR(36)  NOT NULL,
  `PWFileTime` VARCHAR(255) NOT NULL,
  `PFId`       VARCHAR(36)  NOT NULL,
  `PRTitle`    VARCHAR(255) NOT NULL,
  `PRThing`    VARCHAR(255)          DEFAULT NULL,
  `PRIsDeal`   INT(11)      NOT NULL DEFAULT '0',
  `PRDealTime` DATETIME              DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `RPWId`      VARCHAR(36)  NOT NULL,
  PRIMARY KEY (`PRId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `ProjectFileReview`
-- ----------------------------
BEGIN;
INSERT INTO `ProjectFileReview` VALUES
  ('576ac3e9-666a-4810-a3c0-be7db3fc48fc', '01:05.567', '8ed04f32-b023-448b-8336-4ad76ff100bb', 'haishichadian',
   'haishichadian', '1', '2017-06-03 12:18:22', '6b952793-b394-4bab-8362-89e84fb8523a'),
  ('6770ed8f-c5ac-4c8b-9d51-5bc9a365316f', '01:43.600', '8ed04f32-b023-448b-8336-4ad76ff100bb', 'eeeeeeee', 'eeeeee',
   '1', '2017-06-03 12:07:55', '17ec6693-014d-487e-90e1-feb94d35c456'),
  ('ad39d29d-7ec7-4af4-800a-168e464231eb', '01:10.886', '8ed04f32-b023-448b-8336-4ad76ff100bb', 'jididjd', 'djfkldjfj',
   '1', '2017-06-03 12:21:03', '6b952793-b394-4bab-8362-89e84fb8523a');
COMMIT;

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
  `PLrc`       TEXT,
  PRIMARY KEY (`PId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `ProjectInfo`
-- ----------------------------
BEGIN;
INSERT INTO `ProjectInfo` VALUES ('618f357f-3600-4702-a8c8-47d206b8fb46', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
                                  'c015ea85-6701-4b7c-afae-860b39f59c8d', NULL, '大坏蛋', '略略略', '2017-05-14 04:17:40',
                                  '3', '翻唱',
                                  '譬如朝露\n作曲/编曲：Braska\n作词：择荇\n演唱：奇然\n翻唱：Mingkii\n2017年生日贺曲\n后期：良\n海报、视频底图、海报题字：白露是见\n视频：炫酷九老板\n\n\n还未将沧海挹作飞沫\n在渊岳倾颓间悄然干涸\n此身如朝露托体同山阿\n可记得昔日血雨磅礴\n\n还未等幽兰开于暮色\n在司云崖顶上姿妍寂寞\n此身如朝露低悬茎萼\n待拂晓便孑然零落\n\n竟来不及问一句人生几何\n能白驹过隙前对酒当歌\n连生死和诀别都一一错过\n错过这眼中片刻不舍\n\n还未赠烈酒酩酊斟酌\n在故人长离后浇入丘壑\n此身如朝露去日苦多\n任春秋都穿肠而过\n\n竟来不及问一句人生几何\n能白驹过隙前对酒当歌\n连生死和诀别都一一错过\n错过这眼中片刻不舍\n\n有人曾接过陨铁锈剑震怒山河\n悲欢如颈项间紫藤纹身玲珑勾勒\n是谁在风刀霜剑之中立于身侧\n这一世光阴吝啬\n来世坎坷 能遇你几回合\n\n譬如朝露 - 奇然\n--《奇示录》原创古风专辑'),
  ('638cc817-429f-458c-8b98-4b749e59afbe', '89ad59ed-755e-4b67-b7e2-73bf1cfb1293',
   'c015ea85-6701-4b7c-afae-860b39f59c8d', NULL, '啦啦啦', 'Oh my god', '2017-05-14 04:15:11', '1', '翻唱', '这是一个测试');
COMMIT;

-- ----------------------------
--  Table structure for `ProjectState`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectState`;
CREATE TABLE `ProjectState` (
  `StateId`   INT(11)      NOT NULL,
  `StateName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`StateId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `ProjectState`
-- ----------------------------
BEGIN;
INSERT INTO `ProjectState`
VALUES ('0', '募集成员中'), ('1', '等待策划下发文件'), ('2', '歌手试唱'), ('3', '后期制作'), ('4', '美工绘图'), ('5', '策划最终确认'), ('6', '项目完成');
COMMIT;

-- ----------------------------
--  Table structure for `ProjectWork`
-- ----------------------------
DROP TABLE IF EXISTS `ProjectWork`;
CREATE TABLE `ProjectWork` (
  `PWId`         VARCHAR(36) NOT NULL,
  `PId`          VARCHAR(36) NOT NULL,
  `UWid`         VARCHAR(36) NOT NULL,
  `UJoinTime`    DATETIME             DEFAULT NULL,
  `UApply`       INT(11)     NOT NULL DEFAULT '0',
  `PWIsComplete` INT(11)     NOT NULL DEFAULT '0',
  PRIMARY KEY (`PWId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `ProjectWork`
-- ----------------------------
BEGIN;
INSERT INTO `ProjectWork` VALUES ('17ec6693-014d-487e-90e1-feb94d35c456', '618f357f-3600-4702-a8c8-47d206b8fb46',
                                  '17ec6693-014d-487e-90e1-feb94d35c470', '2017-05-11 14:14:55', '1', '1'),
  ('6b952793-b394-4bab-8362-89e84fb8523a', '618f357f-3600-4702-a8c8-47d206b8fb46',
   '7204cbb7-8497-46b8-9b74-26c37399e760', '2017-06-16 21:46:57', '1', '0'),
  ('832f67b3-250f-454a-87a0-79d5a5fde1f2', '618f357f-3600-4702-a8c8-47d206b8fb46',
   'd36b751c-1a03-4f6c-85df-d8a1b37e8980', '2017-06-23 21:47:00', '1', '0'),
  ('9d77729c-e192-4ec5-a1cb-0e9b66af2d61', '618f357f-3600-4702-a8c8-47d206b8fb46',
   'c439e8fb-022a-4e16-b4bb-084a2ec58300', '2017-06-15 21:47:03', '1', '1'),
  ('af9520d6-20f7-4811-901e-78e52c24e7c8', '638cc817-429f-458c-8b98-4b749e59afbe',
   'c439e8fb-022a-4e16-b4bb-084a2ec58300', '2017-06-01 20:39:15', '1', '0'),
  ('c016dc80-4eac-4c3d-9632-c41693ad4f8c', '638cc817-429f-458c-8b98-4b749e59afbe',
   '17ec6693-014d-487e-90e1-feb94d35c470', '2017-05-14 04:15:11', '1', '0'),
  ('dcc6f9a8-a843-4b69-8e3b-0a14980e85bb', '638cc817-429f-458c-8b98-4b749e59afbe',
   'd36b751c-1a03-4f6c-85df-d8a1b37e8980', '2017-06-01 20:39:20', '1', '0'),
  ('eea9329b-431a-432a-beee-a5c0257925d6', '638cc817-429f-458c-8b98-4b749e59afbe',
   '7204cbb7-8497-46b8-9b74-26c37399e760', '2017-06-01 20:39:23', '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `UserExtend`
-- ----------------------------
DROP TABLE IF EXISTS `UserExtend`;
CREATE TABLE `UserExtend` (
  `UUuid`     VARCHAR(36)  NOT NULL,
  `UNackName` VARCHAR(255) NOT NULL,
  `USex`      VARCHAR(4)   NOT NULL,
  `UBirthday` DATETIME     DEFAULT NULL,
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
INSERT INTO `UserExtend`
VALUES ('179642ac-6128-4382-b1d2-8acf5aea21ec', 'testDel', '男', '1998-05-24 01:28:09', NULL, NULL, NULL),
  ('4191dad9-3c9a-47f0-8184-a61260915189', 'hamu', '男', '1990-07-01 10:00:00', NULL, NULL,
   'CommonSpace/default_personal_image.png'),
  ('abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '哈哈，我是宝宝', '女', '1990-07-05 10:00:00', NULL, '',
   'UserSpace/abdca7e6-ad5b-4dba-8d94-77fac4884cd6/1496046625289.png'),
  ('c015ea85-6701-4b7c-afae-860b39f59c8d', '白小菲', '男', '1994-11-16 10:00:00', NULL, '白又白，两只耳朵竖起来。',
   'UserSpace/c015ea85-6701-4b7c-afae-860b39f59c8d/1495105602964.png'),
  ('c015ea85-6701-4b7c-afae-860b39f59i3d', '万能的仓鼠', '男', '1994-11-18 00:00:00', NULL, '啦啦啦啦', NULL),
  ('c015ea85-6701-4b7c-afae-860b39f5e89', 'tester', '男', '1999-05-20 00:00:00', NULL, '噢噢噢噢', NULL),
  ('dbba0714-8863-48cf-81ea-8afcbf1dec9a', '黄海峰', '男', '1996-06-30 11:00:00', NULL, '',
   'UserSpace/dbba0714-8863-48cf-81ea-8afcbf1dec9a/1496137299333.png'),
  ('e7d14d71-867c-4fe4-a495-37f1107aef6b', 'yitiji', '女', '1990-07-18 10:00:00', NULL, NULL,
   'UserSpace/e7d14d71-867c-4fe4-a495-37f1107aef6b/1496298704019.png'),
  ('fb248402-3ee1-434b-bf69-8cab4345b172', '爱小菲', '男', '1990-07-10 10:00:00', NULL, NULL,
   'UserSpace/fb248402-3ee1-434b-bf69-8cab4345b172/1496047133670.png');
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
--  Records of `UserLogin`
-- ----------------------------
BEGIN;
INSERT INTO `UserLogin`
VALUES ('4191dad9-3c9a-47f0-8184-a61260915189', '827ccb0eea8a706c4c34a16891f84e7b', 'okingjerryo', NULL, NULL, '0'),
  ('abdca7e6-ad5b-4dba-8d94-77fac4884cd6', '21232f297a57a5a743894a0e4a801fc3', 'admin', '', '', '0'),
  ('c015ea85-6701-4b7c-afae-860b39f59c8d', '827ccb0eea8a706c4c34a16891f84e7b', '12345', '13896979721',
   '304456667@qq.com', '0'),
  ('dbba0714-8863-48cf-81ea-8afcbf1dec9a', '827ccb0eea8a706c4c34a16891f84e7b', '', '18333135352', '', '0'),
  ('e7d14d71-867c-4fe4-a495-37f1107aef6b', '827ccb0eea8a706c4c34a16891f84e7b', 'tester1', NULL, NULL, '0'),
  ('fb248402-3ee1-434b-bf69-8cab4345b172', '827ccb0eea8a706c4c34a16891f84e7b', '304462227', NULL, NULL, '0');
COMMIT;

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
--  Records of `UserWork`
-- ----------------------------
BEGIN;
INSERT INTO `UserWork` VALUES ('17ec6693-014d-487e-90e1-feb94d35c470', 'c015ea85-6701-4b7c-afae-860b39f59c8d',
                               'cd044c0d-ece6-4efc-b758-6e6c32d43a15', '1', NULL, '0'),
  ('65b25af1-4ed8-41ad-af29-6ac3e2fb2545', 'e7d14d71-867c-4fe4-a495-37f1107aef6b',
   '88ccad85-934e-4fa1-98ba-f91072b50a7a', '1', NULL, '0'),
  ('6f774064-5f9f-4d30-a4ff-bd03f18c96ab', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6',
   'cd044c0d-ece6-4efc-b758-6e6c32d43a15', '1', NULL, '0'),
  ('7204cbb7-8497-46b8-9b74-26c37399e760', 'e7d14d71-867c-4fe4-a495-37f1107aef6b',
   '25eac861-ea5e-46a5-ac27-cd1d777823de', '1', NULL, '0'),
  ('8ec1aa2d-708d-4d49-816f-e6d120c858b6', 'e7d14d71-867c-4fe4-a495-37f1107aef6b',
   'd61f25b4-e0ea-4691-a83a-71bf96b6e661', '1', NULL, '0'),
  ('c439e8fb-022a-4e16-b4bb-084a2ec58300', 'abdca7e6-ad5b-4dba-8d94-77fac4884cd6',
   'd61f25b4-e0ea-4691-a83a-71bf96b6e661', '1', NULL, '0'),
  ('c47c5e21-787a-45d0-861d-34eb629bdbd0', 'dbba0714-8863-48cf-81ea-8afcbf1dec9a',
   '305de965-d553-4e5f-b72a-b48a4fb309fd', '1', NULL, '0'),
  ('d36b751c-1a03-4f6c-85df-d8a1b37e8980', 'fb248402-3ee1-434b-bf69-8cab4345b172',
   '88ccad85-934e-4fa1-98ba-f91072b50a7a', '1', NULL, '0');
COMMIT;

-- ----------------------------
--  Table structure for `Work`
-- ----------------------------
DROP TABLE IF EXISTS `Work`;
CREATE TABLE `Work` (
  `WorkId`    VARCHAR(36)  NOT NULL,
  `WorkFC`    VARCHAR(255) NOT NULL,
  `WorkSC`    VARCHAR(255) NOT NULL,
  `WorkType`  VARCHAR(255)          DEFAULT NULL,
  `WorkBonus` INT(11)      NOT NULL DEFAULT '1',
  PRIMARY KEY (`WorkId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Records of `Work`
-- ----------------------------
BEGIN;
INSERT INTO `Work` VALUES ('25eac861-ea5e-46a5-ac27-cd1d777823de', '歌曲', '后期', '普通', '3'),
  ('305de965-d553-4e5f-b72a-b48a4fb309fd', '歌曲', '编曲', '普通', '5'),
  ('4eb972b0-b609-47c6-b4a1-42580ee4eea0', '歌曲', '曲作', '普通', '4'),
  ('88ccad85-934e-4fa1-98ba-f91072b50a7a', '通用', '美工', '普通', '2'),
  ('cd044c0d-ece6-4efc-b758-6e6c32d43a15', '歌曲', '策划', '策划', '2'),
  ('d61f25b4-e0ea-4691-a83a-71bf96b6e661', '歌曲', '歌手', '普通', '2'),
  ('e5c41db1-f14b-43ab-98d3-2bd161cde857', '歌曲', '词作', '普通', '2');
COMMIT;

-- ----------------------------
--  View structure for `sel_CommityApplyMsg`
-- ----------------------------
DROP VIEW IF EXISTS `sel_CommityApplyMsg`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_CommityApplyMsg` AS
  SELECT
    `CommityMember`.`CMid`      AS `CMid`,
    `CommityMember`.`CId`       AS `CId`,
    `CommityMember`.`UUuid`     AS `UUuid`,
    `CommityMember`.`UJoinTime` AS `UJoinTime`,
    `CommityMember`.`Utype`     AS `Utype`,
    `CommityInfo`.`CName`       AS `CName`,
    `UserExtend`.`UNackName`    AS `UNackName`
  FROM ((`CommityMember`
    JOIN `CommityInfo` ON ((`CommityMember`.`CId` = `CommityInfo`.`CId`))) JOIN `UserExtend`
      ON ((`CommityMember`.`UUuid` = `UserExtend`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_CommityBonus`
-- ----------------------------
DROP VIEW IF EXISTS `sel_CommityBonus`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_CommityBonus` AS
  SELECT
    `CommityBonus`.`CBId`    AS `CBId`,
    `CommityBonus`.`CMid`    AS `CMid`,
    `CommityBonus`.`CBonus`  AS `CBonus`,
    `CommityMember`.`CId`    AS `CId`,
    `CommityMember`.`UUuid`  AS `UUuid`,
    `UserExtend`.`UNackName` AS `UNackName`,
    `UserExtend`.`UHeadImg`  AS `UHeadImg`
  FROM ((`CommityBonus`
    JOIN `CommityMember` ON ((`CommityBonus`.`CMid` = `CommityMember`.`CMid`))) JOIN `UserExtend`
      ON ((`CommityMember`.`UUuid` = `UserExtend`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_CommityInfoPO`
-- ----------------------------
DROP VIEW IF EXISTS `sel_CommityInfoPO`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_CommityInfoPO` AS
  SELECT
    `CommityInfo`.`CId`         AS `CId`,
    `CommityInfo`.`CName`       AS `CName`,
    `CommityInfo`.`Cintroduce`  AS `Cintroduce`,
    `CommityInfo`.`CCreateTime` AS `CCreateTime`,
    `CommityInfo`.`CTag`        AS `CTag`,
    `CommityInfo`.`CHeadImg`    AS `CHeadImg`,
    `CommityInfo`.`CIsNMin`     AS `CIsNMin`,
    `CommityInfo`.`CNMDemand`   AS `CNMDemand`,
    `CommityInfo`.`CMeMCount`   AS `CMeMCount`,
    `CommityInfo`.`CNotice`     AS `CNotice`,
    `CommityInfo`.`CNoteCTime`  AS `CNoteCTime`,
    `CommityMember`.`CMid`      AS `CMid`,
    `CommityMember`.`UUuid`     AS `UUuid`,
    `CommityMember`.`UJoinTime` AS `UJoinTime`,
    `CommityType`.`UTypeName`   AS `UTypeName`,
    `CommityMember`.`Utype`     AS `Utype`
  FROM ((`CommityMember`
    JOIN `CommityInfo` ON ((`CommityMember`.`CId` = `CommityInfo`.`CId`))) JOIN `CommityType`
      ON ((`CommityMember`.`Utype` = `CommityType`.`UTypeId`)));

-- ----------------------------
--  View structure for `sel_CommityMemList`
-- ----------------------------
DROP VIEW IF EXISTS `sel_CommityMemList`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_CommityMemList` AS
  SELECT
    `CommityMember`.`CId`    AS `CId`,
    `UserExtend`.`UNackName` AS `UNackName`,
    `UserExtend`.`USex`      AS `USex`,
    `UserExtend`.`UBirthday` AS `UBIrthday`,
    `UserExtend`.`UTag`      AS `UTag`,
    `UserExtend`.`USign`     AS `USign`,
    `UserExtend`.`UHeadImg`  AS `UHeadImg`,
    `UserExtend`.`UUuid`     AS `UUuid`
  FROM (`CommityMember`
    JOIN `UserExtend` ON ((`CommityMember`.`UUuid` = `UserExtend`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_CommityMessage`
-- ----------------------------
DROP VIEW IF EXISTS `sel_CommityMessage`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_CommityMessage` AS
  SELECT
    `MessageBox`.`MId`         AS `MId`,
    `MessageBox`.`MCreateTime` AS `MCreateTime`,
    `MessageBox`.`MTarId`      AS `MTarId`,
    `MessageBox`.`MTitle`      AS `MTitle`,
    `MessageBox`.`MThings`     AS `MThings`,
    `MessageBox`.`MIsReaded`   AS `MIsReaded`,
    `MessageBox`.`MCheck`      AS `MCheck`,
    `MessageBox`.`MSpcId`      AS `MSpcId`,
    `MessageBox`.`MSenderType` AS `MSenderType`,
    `MessageBox`.`MSenderId`   AS `MSenderId`,
    `CommityInfo`.`CName`      AS `SenderName`
  FROM (`MessageBox`
    JOIN `CommityInfo` ON ((`MessageBox`.`MTarId` = `CommityInfo`.`CId`)));

-- ----------------------------
--  View structure for `sel_MailBoxProject`
-- ----------------------------
DROP VIEW IF EXISTS `sel_MailBoxProject`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_MailBoxProject` AS
  SELECT
    `MessageBox`.`MId`         AS `MId`,
    `MessageBox`.`MCreateTime` AS `MCreateTime`,
    `MessageBox`.`MSenderId`   AS `MSenderId`,
    `MessageBox`.`MTarId`      AS `MTarId`,
    `MessageBox`.`MTitle`      AS `MTitle`,
    `MessageBox`.`MThings`     AS `MThings`,
    `MessageBox`.`MIsReaded`   AS `MIsReaded`,
    `MessageBox`.`MCheck`      AS `MCheck`,
    `MessageBox`.`MSpcId`      AS `MSpcId`,
    `MessageBox`.`MSenderType` AS `MSenderType`,
    `UserExtend`.`UNackName`   AS `SenderName`
  FROM (`MessageBox`
    JOIN `UserExtend` ON ((`MessageBox`.`MSenderId` = `UserExtend`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_PersonalInfoPO`
-- ----------------------------
DROP VIEW IF EXISTS `sel_PersonalInfoPO`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_PersonalInfoPO` AS
  SELECT
    `UserExtend`.`UUuid`     AS `UUuid`,
    `UserExtend`.`UNackName` AS `UNackName`,
    `UserExtend`.`UBirthday` AS `UBIrthday`,
    `UserExtend`.`USex`      AS `USex`,
    `UserExtend`.`UTag`      AS `UTag`,
    `UserExtend`.`USign`     AS `USign`,
    `UserExtend`.`UHeadImg`  AS `UHeadImg`,
    `UserLogin`.`UAName`     AS `UAName`,
    `UserLogin`.`UTel`       AS `UTel`,
    `UserLogin`.`UMail`      AS `UMail`
  FROM (`UserExtend`
    JOIN `UserLogin` ON ((`UserExtend`.`UUuid` = `UserLogin`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_PersonalMessage`
-- ----------------------------
DROP VIEW IF EXISTS `sel_PersonalMessage`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_PersonalMessage` AS
  SELECT
    `MessageBox`.`MId`         AS `MId`,
    `MessageBox`.`MCreateTime` AS `MCreateTime`,
    `MessageBox`.`MSenderId`   AS `MSenderId`,
    `MessageBox`.`MTarId`      AS `MTarId`,
    `MessageBox`.`MTitle`      AS `MTitle`,
    `MessageBox`.`MThings`     AS `MThings`,
    `MessageBox`.`MIsReaded`   AS `MIsReaded`,
    `MessageBox`.`MCheck`      AS `MCheck`,
    `MessageBox`.`MSpcId`      AS `MSpcId`,
    `MessageBox`.`MSenderType` AS `MSenderType`,
    `UserExtend`.`UNackName`   AS `SenderName`
  FROM (`MessageBox`
    JOIN `UserExtend` ON ((`MessageBox`.`MSenderId` = `UserExtend`.`UUuid`)));

-- ----------------------------
--  View structure for `sel_ProjectDynWithFile`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectDynWithFile`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_ProjectDynWithFile` AS
  SELECT
    `ProjectDynamic`.`PDId`   AS `PDId`,
    `ProjectDynamic`.`Time`   AS `Time`,
    `ProjectDynamic`.`PDType` AS `PDType`,
    `UserExtend`.`UNackName`  AS `UNackName`,
    `Work`.`WorkSC`           AS `WorkSC`,
    `ProjectInfo`.`PTitle`    AS `PTitle`,
    `ProjectFile`.`PFNotice`  AS `PFNotice`,
    `ProjectFile`.`PFId`      AS `PFId`,
    `ProjectFile`.`PFPath`    AS `PFPath`
  FROM ((((((`ProjectDynamic`
    JOIN `ProjectWork` ON ((`ProjectDynamic`.`PWId` = `ProjectWork`.`PWId`))) JOIN `ProjectInfo`
      ON ((`ProjectWork`.`PId` = `ProjectInfo`.`PId`))) JOIN `UserWork`
      ON ((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) JOIN `UserExtend`
      ON ((`UserWork`.`UUuid` = `UserExtend`.`UUuid`))) JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`))) JOIN
    `ProjectFile` ON ((`ProjectDynamic`.`PFId` = `ProjectFile`.`PFId`)));

-- ----------------------------
--  View structure for `sel_ProjectDynWithNoFile`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectDynWithNoFile`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_ProjectDynWithNoFile` AS
  SELECT
    `ProjectDynamic`.`PDId`   AS `PDId`,
    `ProjectDynamic`.`Time`   AS `Time`,
    `ProjectDynamic`.`PDType` AS `PDType`,
    `ProjectInfo`.`PTitle`    AS `PTitle`,
    `UserExtend`.`UNackName`  AS `UNackName`,
    `Work`.`WorkSC`           AS `WorkSC`
  FROM (((((`ProjectDynamic`
    JOIN `ProjectWork` ON ((`ProjectDynamic`.`PWId` = `ProjectWork`.`PWId`))) JOIN `UserWork`
      ON ((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) JOIN `UserExtend`
      ON ((`UserWork`.`UUuid` = `UserExtend`.`UUuid`))) JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`))) JOIN
    `ProjectInfo` ON ((`ProjectWork`.`PId` = `ProjectInfo`.`PId`)));

-- ----------------------------
--  View structure for `sel_ProjectFileDetail`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectFileDetail`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_ProjectFileDetail` AS
  SELECT
    `ProjectWork`.`PId`             AS `PId`,
    `ProjectFile`.`PFId`            AS `PFId`,
    `ProjectFile`.`PFPath`          AS `PFPath`,
    `ProjectFile`.`PFCreateTime`    AS `PFCreateTime`,
    `ProjectFile`.`PFNotice`        AS `PFNotice`,
    `ProjectWork`.`PWIsComplete`    AS `PWIsComplete`,
    `Work`.`WorkSC`                 AS `WorkSC`,
    `ProjectFile`.`PFIsUseComplete` AS `PFIsUseComplete`
  FROM (((`ProjectFile`
    JOIN `ProjectWork` ON ((`ProjectFile`.`PWId` = `ProjectWork`.`PWId`))) JOIN `UserWork`
      ON ((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`)));

-- ----------------------------
--  View structure for `sel_ProjectInfo`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectInfo`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_ProjectInfo` AS
  SELECT
    `ProjectInfo`.`PId`        AS `PId`,
    `ProjectInfo`.`CId`        AS `CId`,
    `ProjectInfo`.`CreatUUuid` AS `CreatUUuid`,
    `UserExtend`.`UNackName`   AS `UNackName`,
    `ProjectInfo`.`PCreatTime` AS `PCreatTime`,
    `ProjectInfo`.`PEndTime`   AS `PEndTime`,
    `ProjectInfo`.`PTitle`     AS `PTitle`,
    `ProjectInfo`.`PIntroduce` AS `PIntroduce`,
    `ProjectInfo`.`PState`     AS `PState`,
    `ProjectState`.`StateName` AS `StateName`,
    `ProjectInfo`.`PType`      AS `PType`
  FROM ((`ProjectInfo`
    JOIN `UserExtend` ON ((`ProjectInfo`.`CreatUUuid` = `UserExtend`.`UUuid`))) JOIN `ProjectState`
      ON ((`ProjectInfo`.`PState` = `ProjectState`.`StateId`)));

-- ----------------------------
--  View structure for `sel_ProjectInfoPO`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectInfoPO`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_ProjectInfoPO` AS
  SELECT
    `ProjectInfo`.`PId`        AS `PId`,
    `UserWork`.`UUuid`         AS `UUuid`,
    `ProjectInfo`.`PType`      AS `PType`,
    `ProjectInfo`.`PTitle`     AS `PTitle`,
    `ProjectWork`.`UJoinTime`  AS `UJoinTIme`,
    `ProjectInfo`.`PCreatTime` AS `PCreatTime`,
    `ProjectInfo`.`PIntroduce` AS `PIntroduce`,
    `ProjectInfo`.`CreatUUuid` AS `CreatUUuid`,
    `CommityInfo`.`CName`      AS `CName`,
    `Work`.`WorkId`            AS `WorkId`,
    `Work`.`WorkFC`            AS `WorkFC`,
    `Work`.`WorkSC`            AS `WorkSC`,
    `ProjectInfo`.`PState`     AS `PState`,
    `ProjectState`.`StateName` AS `StateName`,
    `ProjectWork`.`PWId`       AS `PWId`,
    `ProjectInfo`.`PLrc`       AS `PLrc`
  FROM (((((`ProjectWork`
    JOIN `UserWork` ON ((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) JOIN `ProjectInfo`
      ON ((`ProjectWork`.`PId` = `ProjectInfo`.`PId`))) JOIN `ProjectState`
      ON ((`ProjectInfo`.`PState` = `ProjectState`.`StateId`))) JOIN `CommityInfo`
      ON ((`ProjectInfo`.`CId` = `CommityInfo`.`CId`))) JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`)))
  ORDER BY `ProjectWork`.`UJoinTime`;

-- ----------------------------
--  View structure for `sel_ProjectWorkApplicateMsg`
-- ----------------------------
DROP VIEW IF EXISTS `sel_ProjectWorkApplicateMsg`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_ProjectWorkApplicateMsg` AS
  SELECT
    `ProjectWork`.`PWId`       AS `PWId`,
    `ProjectWork`.`PId`        AS `PId`,
    `ProjectWork`.`UWid`       AS `UWid`,
    `ProjectWork`.`UJoinTime`  AS `UJoinTIme`,
    `CommityInfo`.`CName`      AS `CName`,
    `ProjectInfo`.`PTitle`     AS `PTitle`,
    `ProjectInfo`.`PType`      AS `PType`,
    `ProjectInfo`.`PCreatTime` AS `PCreatTime`,
    `UserWork`.`UUuid`         AS `UUuid`,
    `Work`.`WorkSC`            AS `WorkSC`,
    `UserExtend`.`UNackName`   AS `UNackName`,
    `UserExtend`.`UHeadImg`    AS `UHeadImg`,
    `ProjectWork`.`UApply`     AS `UApply`,
    `ProjectInfo`.`CreatUUuid` AS `CreatUUuid`,
    `ProjectState`.`StateName` AS `StateName`
  FROM ((((((`ProjectWork`
    JOIN `ProjectInfo` ON ((`ProjectWork`.`PId` = `ProjectInfo`.`PId`))) JOIN `ProjectState`
      ON ((`ProjectInfo`.`PState` = `ProjectState`.`StateId`))) JOIN `UserWork`
      ON ((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`))) JOIN
    `UserExtend` ON ((`UserWork`.`UUuid` = `UserExtend`.`UUuid`))) JOIN `CommityInfo`
      ON ((`ProjectInfo`.`CId` = `CommityInfo`.`CId`)));

-- ----------------------------
--  View structure for `sel_addCommityApply`
-- ----------------------------
DROP VIEW IF EXISTS `sel_addCommityApply`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_addCommityApply` AS
  SELECT
    `MessageBox`.`MId`         AS `MId`,
    `MessageBox`.`MCreateTime` AS `MCreateTime`,
    `MessageBox`.`MSenderId`   AS `MSenderId`,
    `MessageBox`.`MTarId`      AS `MTarId`,
    `MessageBox`.`MTitle`      AS `MTitle`,
    `MessageBox`.`MThings`     AS `MThings`,
    `MessageBox`.`MIsReaded`   AS `MIsReaded`,
    `MessageBox`.`MCheck`      AS `MCheck`,
    `MessageBox`.`MSpcId`      AS `MSpcId`,
    `UserExtend`.`UNackName`   AS `UNackName`
  FROM (`MessageBox`
    JOIN `UserExtend` ON ((`MessageBox`.`MSenderId` = `UserExtend`.`UNackName`)));

-- ----------------------------
--  View structure for `sel_commitymemview`
-- ----------------------------
DROP VIEW IF EXISTS `sel_commitymemview`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_commitymemview` AS
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
--  View structure for `sel_projectWorkDetail`
-- ----------------------------
DROP VIEW IF EXISTS `sel_projectWorkDetail`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `sel_projectWorkDetail` AS
  SELECT
    `ProjectWork`.`PWId`         AS `PWId`,
    `ProjectWork`.`PId`          AS `PId`,
    `ProjectWork`.`UWid`         AS `UWid`,
    `ProjectWork`.`UJoinTime`    AS `UJoinTime`,
    `ProjectWork`.`UApply`       AS `UApply`,
    `ProjectWork`.`PWIsComplete` AS `PWIsComplete`,
    `UserWork`.`UUuid`           AS `UUuid`,
    `Work`.`WorkSC`              AS `WorkSC`,
    `Work`.`WorkBonus`           AS `WorkBonus`,
    `UserExtend`.`UHeadImg`      AS `UHeadImg`,
    `UserExtend`.`UNackName`     AS `UNackName`,
    `ProjectInfo`.`CId`          AS `CId`
  FROM ((((`ProjectWork`
    JOIN `UserWork` ON ((`ProjectWork`.`UWid` = `UserWork`.`UWid`))) JOIN `UserExtend`
      ON ((`UserWork`.`UUuid` = `UserExtend`.`UUuid`))) JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`))) JOIN
    `ProjectInfo` ON ((`ProjectWork`.`PId` = `ProjectInfo`.`PId`)));

-- ----------------------------
--  View structure for `userworkview`
-- ----------------------------
DROP VIEW IF EXISTS `userworkview`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`connect`@`%`
  SQL SECURITY DEFINER VIEW `userworkview` AS
  SELECT
    `UserWork`.`UWid`        AS `UWid`,
    `UserWork`.`UUuid`       AS `UUuid`,
    `UserWork`.`WorkId`      AS `WorkId`,
    `Work`.`WorkFC`          AS `WorkFC`,
    `Work`.`WorkSC`          AS `WorkSC`,
    `UserWork`.`isFreq`      AS `isFreq`,
    `UserWork`.`status`      AS `status`,
    `UserWork`.`delayTime`   AS `delayTime`,
    `UserExtend`.`UNackName` AS `UNackName`,
    `UserExtend`.`UHeadImg`  AS `UHeadImg`
  FROM ((`UserWork`
    JOIN `Work` ON ((`UserWork`.`WorkId` = `Work`.`WorkId`))) JOIN `UserExtend`
      ON ((`UserWork`.`UUuid` = `UserExtend`.`UUuid`)));

SET FOREIGN_KEY_CHECKS = 1;
