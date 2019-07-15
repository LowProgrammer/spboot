ALTER TABLE `tb_client_setting`
ADD COLUMN `remark` varchar(255) NULL COMMENT '备注' AFTER `secret`;