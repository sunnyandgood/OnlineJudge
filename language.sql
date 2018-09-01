/*
MySQL Data Transfer
Source Host: localhost
Source Database: language
Target Host: localhost
Target Database: language
Date: 2018/9/1 22:09:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
CREATE TABLE `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_result` text,
  `answer_time` date DEFAULT NULL,
  PRIMARY KEY (`answer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_chapter` varchar(20) DEFAULT NULL,
  `question_difficult` int(5) NOT NULL,
  `question_title` text NOT NULL,
  `question_time` date DEFAULT NULL,
  `question_author` varchar(20) DEFAULT NULL,
  `question_answer` text,
  `question_para1` varchar(100) DEFAULT NULL,
  `question_para2` varchar(100) DEFAULT NULL,
  `question_para3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for score
-- ----------------------------
CREATE TABLE `score` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `score_degree` double DEFAULT NULL,
  `answer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`score_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `nuser_number` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');
INSERT INTO `admin` VALUES ('2', 'dd', 'dd');
INSERT INTO `admin` VALUES ('10', '', '');
INSERT INTO `answer` VALUES ('1', '1', '1', '老客户你就会', '2018-08-23');
INSERT INTO `question` VALUES ('1', '1', '2', '抵抗力古哈迪斯，发货地回来看见', '2018-09-01', 'qwe', 'dsfdgfcvvb', '88', '78', '66');
INSERT INTO `question` VALUES ('2', '2', '2', '申达股份的话', '2018-08-29', 'asd', 'gfdrygtrf', '66', '88', '99');
INSERT INTO `question` VALUES ('3', '', '3', '地方银行个人', '2018-09-05', '问问', '我', '3', '3', '3');
INSERT INTO `question` VALUES ('6', null, '1', '热点覆盖的', '2018-09-13', null, null, null, null, null);
INSERT INTO `question` VALUES ('8', null, '3', 'hklh', '2018-09-04', null, null, null, null, null);
INSERT INTO `question` VALUES ('9', null, '2', 'lkh', '2018-09-14', null, null, null, null, null);
INSERT INTO `question` VALUES ('10', null, '5', 'ljo', '2018-09-22', null, null, null, null, null);
INSERT INTO `question` VALUES ('17', '', '1', '11', '2018-09-22', '', '', '', '', '');
INSERT INTO `question` VALUES ('12', '', '2', '水电费', '2018-09-01', '', '', '', '', '');
INSERT INTO `question` VALUES ('18', '', '2', '水电费', '2018-09-01', '', '', '', '', '');
INSERT INTO `question` VALUES ('14', '', '2', '申达股份的话', '2018-08-23', '', '', '', '', '');
INSERT INTO `question` VALUES ('5', null, '3', '紧固件', null, null, null, null, null, null);
INSERT INTO `score` VALUES ('1', '1', '1', '100', '1');
INSERT INTO `score` VALUES ('2', '1', '6', '100', '5');
INSERT INTO `user` VALUES ('1', '1607064140', 'sunny', '1607064140');
INSERT INTO `user` VALUES ('2', '1607064188', '李四', '1607064188');
INSERT INTO `user` VALUES ('12', '11', '小华', '1607064188');
INSERT INTO `user` VALUES ('3', '1', '1', '1');
