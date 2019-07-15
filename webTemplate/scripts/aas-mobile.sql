DROP TABLE IF EXISTS `tb_app_crash` CASCADE
;

DROP TABLE IF EXISTS `tb_app_publish` CASCADE
;

DROP TABLE IF EXISTS `tb_app_version` CASCADE
;

CREATE TABLE `tb_app_crash` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  `channel` varchar(60) DEFAULT NULL COMMENT '渠道',
  `brand` varchar(60) DEFAULT NULL COMMENT '品牌',
  `model` varchar(60) DEFAULT NULL COMMENT '设备型号',
  `os` varchar(60) DEFAULT NULL COMMENT '操作系统',
  `os_version` varchar(60) DEFAULT NULL COMMENT '操作系统版本',
  `resolution` varchar(60) DEFAULT NULL COMMENT '分辨率',
  `facilitator` varchar(60) DEFAULT NULL COMMENT '运营商',
  `app_version` varchar(60) DEFAULT NULL COMMENT 'App版本',
  `react_version` varchar(60) DEFAULT NULL COMMENT 'React版本号',
  `comp_version` varchar(60) DEFAULT NULL COMMENT '组件版本号',
  `imei` varchar(60) DEFAULT NULL COMMENT '设备ID',
  `type` varchar(200) DEFAULT NULL COMMENT '异常类型，默认为崩溃记录',
  `uid` varchar(200) DEFAULT NULL COMMENT '访客标识',
  `datetime` bigint(20) DEFAULT NULL COMMENT '时间戳',
  `user_id` varchar(45) DEFAULT NULL COMMENT '用户ID',
  `error` mediumtext COMMENT '错误内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_app_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `term_type` varchar(10) DEFAULT NULL COMMENT '终端类型 Android、IOS',
  `update_force` tinyint(4) DEFAULT NULL COMMENT '是否强制更新 1 是 0 否',
  `pkg_path` varchar(512) DEFAULT NULL COMMENT '包路径',
  `plist_path` varchar(512) DEFAULT NULL,
  `publish` varchar(10) DEFAULT NULL COMMENT '发布状态，1-发布，2-禁用',
  `comment` longtext,
  `add_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_app_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL COMMENT '类型：FULL 全量  INCR 增量',
  `version` varchar(20) NOT NULL COMMENT '版本',
  `term_type` varchar(10) NOT NULL COMMENT '终端类型 Android、IOS',
  `update_force` tinyint(4) NOT NULL COMMENT '是否强制更新 1 是 0 否',
  `pkg_path` varchar(512) NOT NULL COMMENT '升级包路径',
  `comment` varchar(200) DEFAULT NULL,
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
