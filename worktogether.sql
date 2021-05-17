/*
Navicat MySQL Data Transfer

Source Server         : myserver
Source Server Version : 80023
Source Host           : 81.69.98.32:3306
Source Database       : worktogether

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2021-05-17 16:49:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `logId` int NOT NULL AUTO_INCREMENT,
  `teamId` varchar(255) NOT NULL,
  `memberId` int NOT NULL,
  `logType` int DEFAULT NULL,
  `submitTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `logTitle` varchar(255) DEFAULT NULL,
  `logContent` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '一号队伍', '2', '0', '2021-05-17 08:22:49', '日志1', '这是第一篇日志');
INSERT INTO `log` VALUES ('2', '一号队伍', '2', '1', '2021-05-17 08:24:31', '周志1', '这是第一篇周志');
INSERT INTO `log` VALUES ('3', '一号队伍', '2', '2', '2021-05-17 08:25:06', '月周志1', '这是第一篇月志');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `userId` int NOT NULL,
  `likeNumber` int DEFAULT '0',
  `detail` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '测试1', '2021-05-06 16:31:59', '2', '1', '这只是一个测试');
INSERT INTO `post` VALUES ('2', '测试2', '2021-05-17 16:37:51', '2', '2', '这只是一个测试');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `postId` int NOT NULL,
  `userId` int NOT NULL,
  `content` varchar(1023) DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '1', '2', '第一条回复', '2021-05-17 16:26:36');
INSERT INTO `record` VALUES ('2', '1', '2', '第二条回复', '2021-05-17 16:35:04');

-- ----------------------------
-- Table structure for subtask
-- ----------------------------
DROP TABLE IF EXISTS `subtask`;
CREATE TABLE `subtask` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subTaskId` int NOT NULL,
  `taskId` int NOT NULL,
  `content` varchar(1023) NOT NULL,
  `weight` int NOT NULL,
  `isComplete` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of subtask
-- ----------------------------
INSERT INTO `subtask` VALUES ('1', '1', '1', '11111', '10', '0');
INSERT INTO `subtask` VALUES ('2', '2', '1', '22222', '50', '0');
INSERT INTO `subtask` VALUES ('3', '3', '1', '33333', '40', '0');
INSERT INTO `subtask` VALUES ('4', '1', '2', '第一', '20', '0');
INSERT INTO `subtask` VALUES ('5', '2', '2', '第二', '20', '0');
INSERT INTO `subtask` VALUES ('6', '3', '2', '第三', '35', '0');
INSERT INTO `subtask` VALUES ('7', '4', '2', '第四', '25', '0');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `taskId` int NOT NULL AUTO_INCREMENT,
  `teamId` varchar(63) NOT NULL,
  `taskName` varchar(255) NOT NULL,
  `memberId` int DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `taskProgress` int NOT NULL DEFAULT '0',
  `priority` int DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '1', '测试', '1', '2021-05-28 03:57', '0', '0');
INSERT INTO `task` VALUES ('2', '3', '测试1', '2', '2021-05-20 11:36', '0', '2');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `teamId` int NOT NULL AUTO_INCREMENT,
  `teamName` varchar(255) NOT NULL,
  `leaderTel` varchar(255) DEFAULT NULL,
  `memberTel` varchar(255) DEFAULT NULL,
  `memberNum` int NOT NULL,
  `memberNumLimit` int NOT NULL,
  `teamIntroduction` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  PRIMARY KEY (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', '一号队伍', '18966666666', '18966666666,18911112222', '2', '5', '我们是第一支队伍');
INSERT INTO `team` VALUES ('3', '二号团队', '18911112222', '18911112222', '1', '3', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `userPwd` varchar(255) NOT NULL,
  `teamName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `userTel` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'swx', '12345678', '一号队伍', '18966666666');
INSERT INTO `user` VALUES ('2', 'ddd', '12345678', '一号队伍,二号团队', '18911112222');
