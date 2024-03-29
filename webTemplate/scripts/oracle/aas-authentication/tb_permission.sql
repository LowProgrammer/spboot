DECLARE num NUMBER;
BEGIN
SELECT COUNT(1) INTO num FROM USER_TABLES WHERE TABLE_NAME = UPPER('TB_PERMISSION') ;
IF num > 0 THEN
EXECUTE IMMEDIATE 'DROP TABLE TB_PERMISSION' ;
END IF;
END;
/

DECLARE
  C NUMBER;
BEGIN
SELECT COUNT(*) INTO C
FROM ALL_SEQUENCES
  WHERE SEQUENCE_NAME = 'SEQ_TB_PERMISSION_ID';
  IF (C > 0) THEN
    EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_TB_PERMISSION_ID';
END IF;
END
;
/

CREATE TABLE TB_PERMISSION
(
	id NUMBER(38) NOT NULL,
	auth_obj NUMBER(10) NOT NULL,    -- 授权对象： 1 用户 2 角色
	obj_id NUMBER(38) NOT NULL,
	comp_id NUMBER(38) NOT NULL,    -- 组件ID
	add_time NUMBER(38) NOT NULL    -- 添加时间
)
;

ALTER TABLE TB_PERMISSION
 ADD CONSTRAINT PK_TB_USER_PRIVILEGE
	PRIMARY KEY (id) USING INDEX
;

ALTER TABLE TB_PERMISSION
 ADD CONSTRAINT unique_obj_comp UNIQUE (auth_obj,obj_id,comp_id) USING INDEX
;

COMMENT ON TABLE TB_PERMISSION IS '权限表'
;

COMMENT ON COLUMN TB_PERMISSION.auth_obj IS '授权对象： 1 用户 2 角色'
;

COMMENT ON COLUMN TB_PERMISSION.comp_id IS '组件ID'
;

COMMENT ON COLUMN TB_PERMISSION.add_time IS '添加时间'
;

CREATE SEQUENCE SEQ_TB_PERMISSION_ID
	INCREMENT BY 1
	START WITH 1
	NOMAXVALUE
	MINVALUE  1
	NOCYCLE
	NOCACHE
	NOORDER
;

CREATE OR REPLACE TRIGGER TRG_TB_PERMISSION_ID
	BEFORE INSERT
	ON TB_PERMISSION
	FOR EACH ROW
	BEGIN
		SELECT SEQ_TB_PERMISSION_ID.NEXTVAL
		INTO :NEW.id
		FROM DUAL;
	END;

