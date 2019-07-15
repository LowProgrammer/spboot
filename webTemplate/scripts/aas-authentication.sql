DROP TABLE IF EXISTS `tb_user_role` CASCADE
;

DROP TABLE IF EXISTS `tb_user` CASCADE
;

DROP TABLE IF EXISTS `tb_role` CASCADE
;

DROP TABLE IF EXISTS `tb_permission` CASCADE
;

DROP TABLE IF EXISTS `tb_component` CASCADE
;

CREATE TABLE `tb_user_role`
(
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`user_id` BIGINT NOT NULL COMMENT '用户ID',
	`role_id` BIGINT NOT NULL COMMENT '角色ID',
	`add_time` BIGINT NOT NULL COMMENT '添加时间',
	CONSTRAINT `PK_user_role` PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表'
;

CREATE TABLE `tb_user`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT '用户ID',
	`user_num` VARCHAR(20) NOT NULL COMMENT '用户编号，客户号或者员工号',
	`user_name` VARCHAR(50) NOT NULL,
	`user_attr` VARCHAR(1000) COMMENT '用户属性，JSON格式',
	`add_time` BIGINT NOT NULL COMMENT '添加时间',
	CONSTRAINT `PK_user` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表'
;

CREATE TABLE `tb_role`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT '角色ID',
	`name` VARCHAR(50) NOT NULL COMMENT '角色名称',
	`is_def` SMALLINT default 0 COMMENT '是否默认 1 是 0 否',
	`comment` VARCHAR(500) COMMENT '角色备注',
	`add_time` BIGINT NOT NULL COMMENT '添加时间',
	CONSTRAINT `PK_role` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表'
;

CREATE TABLE `tb_permission`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`auth_obj` SMALLINT NOT NULL COMMENT '授权对象： 1 用户 2 角色',
	`obj_id` BIGINT NOT NULL,
	`comp_id` BIGINT NOT NULL COMMENT '组件ID',
	`add_time` BIGINT NOT NULL COMMENT '添加时间',
	CONSTRAINT `PK_user_privilege` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表'
;

CREATE TABLE `tb_component`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT ,
	`parent_id` BIGINT NOT NULL default 0 COMMENT '父组件ID',
	`cuid` VARCHAR(32) NOT NULL COMMENT '组件唯一标识',
	`name` VARCHAR(50) NOT NULL COMMENT '组件名称',
	`type` SMALLINT NOT NULL COMMENT '组件类型：1 菜单  2 权限要素',
	`sort` INT NOT NULL default 0,
	`enable` SMALLINT NOT NULL default 1 COMMENT '是否可用 1 可用 0 不可用',
	`term_type` VARCHAR(50) COMMENT '终端类型 1-IOS 2-Android 3-Web 多个用逗号隔开',
	`url` VARCHAR(256) COMMENT '菜单URL',
	`add_time` BIGINT NOT NULL COMMENT '添加时间',
	`comment` VARCHAR(500) COMMENT '备注',
	CONSTRAINT `PK_component` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组件表'
;


ALTER TABLE `tb_user_role` 
 ADD CONSTRAINT `unique_user_role` UNIQUE (`user_id`,`role_id`)
;

ALTER TABLE `tb_permission` 
 ADD CONSTRAINT `unique_obj_comp` UNIQUE (`auth_obj`,`obj_id`,`comp_id`)
;