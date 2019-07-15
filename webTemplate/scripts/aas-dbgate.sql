/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.28
Source Server Version : 50619
Source Host           : 192.168.3.28:3306
Source Database       : dd

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2018-08-07 11:33:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_dbgate_backup
-- ----------------------------
DROP TABLE IF EXISTS `tb_dbgate_backup`;
CREATE TABLE `tb_dbgate_backup` (
`id`  int(16) UNSIGNED NOT NULL AUTO_INCREMENT,
`backup_time`  datetime NULL DEFAULT NULL ,
`data`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`note`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for tb_dbgate_service
-- ----------------------------
DROP TABLE IF EXISTS `tb_dbgate_service`;
CREATE TABLE `tb_dbgate_service` (
`id`  int(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`module_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块分组' ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称' ,
`func`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能码' ,
`content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储过程名称' ,
`parameters_in`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`parameters_out`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`comment`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`version`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本' ,
`type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象模式' ,
`protocol`  int(11) NULL DEFAULT NULL COMMENT '服务类型' ,
`modify_date`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for tb_dbgate_service_ext
-- ----------------------------
DROP TABLE IF EXISTS `tb_dbgate_service_ext`;
CREATE TABLE `tb_dbgate_service_ext` (
`id`  int(16) NOT NULL AUTO_INCREMENT,
`func`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务编码' ,
`datasource`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源' ,
`status`  int(16) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 0|停用;1|启用' ,
`auth`  int(16) NULL DEFAULT 0 COMMENT '登录认证后可访问，1:是 0:否' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for tb_dbgate_service_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_dbgate_service_test`;
CREATE TABLE `tb_dbgate_service_test` (
`id`  int(16) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
`func`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务ID' ,
`in_param`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '输入参数' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Indexes structure for table tb_dbgate_service
-- ----------------------------
CREATE UNIQUE INDEX `UNIQUE_FUNC` ON `tb_dbgate_service`(`func`) USING BTREE ;

-- ----------------------------
-- Indexes structure for table tb_dbgate_service_ext
-- ----------------------------
CREATE UNIQUE INDEX `UNIQUE_FUNC` ON `tb_dbgate_service_ext`(`func`) USING BTREE ;
