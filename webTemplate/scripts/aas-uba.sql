/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.28
Source Server Version : 50619
Source Host           : 192.168.3.28:3306
Source Database       : dd

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2018-08-31 17:39:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for md_event_setting
-- ----------------------------
DROP TABLE IF EXISTS `md_event_setting`;
CREATE TABLE `md_event_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(60) DEFAULT NULL COMMENT 'APPID\n',
  `type` varchar(60) DEFAULT NULL COMMENT '事件类型',
  `action` varchar(100) DEFAULT NULL COMMENT '具体动作',
  `comment` varchar(500) DEFAULT NULL COMMENT '描述\n',
  `add_time` bigint(20) DEFAULT NULL,
  `sort` int(255) DEFAULT NULL COMMENT '排序',
  `type_name` varchar(255) DEFAULT NULL COMMENT '事件名称',
  `status` varchar(255) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
