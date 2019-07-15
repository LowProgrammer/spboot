DROP TABLE IF EXISTS `tb_push_terminal` CASCADE
;

CREATE TABLE `tb_push_terminal`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`platform` VARCHAR(50) NOT NULL COMMENT '平台：ios、android',
	`token` VARCHAR(200) NOT NULL COMMENT '机器码',
	`alias` VARCHAR(50) COMMENT '别名',
	`brand` VARCHAR(50) COMMENT '品牌：apple、huawei、xiaomi、oppo、vivo、meizu',
	`sys_model` VARCHAR(50) COMMENT '型号',
	`sys_version` VARCHAR(10) COMMENT '版本',
	`badge` INTEGER NOT NULL default 0 COMMENT '未读消息数',
	`add_time` BIGINT COMMENT '创建时间',
	`modify_time` BIGINT COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='推送终端'
;


DROP TABLE IF EXISTS `tb_push_message` CASCADE
;
CREATE TABLE `tb_push_message` (
  `id` varchar(32) NOT NULL COMMENT '32位GUID',
  `message` varchar(2048) NOT NULL  COMMENT '消息内容',
  `add_time` bigint(20) DEFAULT NULL  COMMENT '添加时间',
  `status` smallint(6) NOT NULL DEFAULT '0'  COMMENT '1 成功 0 失败',
  `remark` varchar(500) DEFAULT ''  COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_push_result` CASCADE
;
CREATE TABLE `tb_push_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sdk` varchar(50) NOT NULL,
  `terminal_id` bigint(20) DEFAULT NULL,
  `message_id` varchar(32) NOT NULL,
  `push_time` bigint(20) NOT NULL COMMENT '推送时间',
  `push_times` smallint(6) NOT NULL COMMENT '推送次数',
  `status` smallint(6) NOT NULL COMMENT '1 成功 0 失败',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_push_tag` CASCADE
;
CREATE TABLE `tb_push_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` VARCHAR(50) COMMENT '别名',
  `tag` VARCHAR(200) COMMENT '标签',
  `status` smallint(6) NOT NULL COMMENT '1 正常 0 删除',
  `add_time` BIGINT COMMENT '创建时间',
	`modify_time` BIGINT COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;