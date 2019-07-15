DROP TABLE IF EXISTS `tb_client_setting` CASCADE
;

CREATE TABLE `tb_client_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `client_id` varchar(64) DEFAULT NULL COMMENT '客户端ID',
  `name` varchar(64) DEFAULT NULL COMMENT '客户端名称',
  `secret` varchar(255) DEFAULT NULL COMMENT '秘钥',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` varchar(10) DEFAULT '0' COMMENT '状态，1:启用，0:停用',
  `add_time` bigint(20) DEFAULT NULL COMMENT '添加时间',
  `ext` longtext COMMENT '参数配置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;