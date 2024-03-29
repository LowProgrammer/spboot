DECLARE num NUMBER;
BEGIN
SELECT COUNT(1) INTO num FROM USER_TABLES WHERE TABLE_NAME = UPPER('TB_APP_VERSION') ;
IF num > 0 THEN
EXECUTE IMMEDIATE 'DROP TABLE TB_APP_VERSION' ;
END IF;
END;
/

DECLARE
  C NUMBER;
BEGIN
SELECT COUNT(*) INTO C
FROM ALL_SEQUENCES
  WHERE SEQUENCE_NAME = 'SEQ_TB_APP_VERSION_ID';
  IF (C > 0) THEN
    EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_TB_APP_VERSION_ID';
END IF;
END
;
/

CREATE TABLE TB_APP_VERSION
(
	id NUMBER NOT NULL,
	type VARCHAR2(10) NOT NULL,    -- 类型：FULL 全量  INCR 增量
	version VARCHAR2(20) NOT NULL,    -- 版本
	term_type VARCHAR2(10) NOT NULL,    -- 终端类型 Android、IOS
	pkg_type VARCHAR2(10) NOT NULL,    -- 升级包类型：Native 原生程序包、RN  RN程序包
	update_force NUMBER(3) NOT NULL,    -- 是否强制更新 1 是 0 否
	pkg_path VARCHAR2(512) NOT NULL,    -- 升级包路径
	"COMMENT" VARCHAR2(200),
	add_time NUMBER(38) NOT NULL,    -- 添加时间
	update_time NUMBER(38)    -- 更新时间
);

COMMENT ON COLUMN TB_APP_VERSION.type IS '类型：FULL 全量  INCR 增量'
;

COMMENT ON COLUMN TB_APP_VERSION.version IS '版本'
;

COMMENT ON COLUMN TB_APP_VERSION.term_type IS '终端类型 Android、IOS'
;

COMMENT ON COLUMN TB_APP_VERSION.pkg_type IS '升级包类型：Native 原生程序包、RN  RN程序包'
;

COMMENT ON COLUMN TB_APP_VERSION.update_force IS '是否强制更新 1 是 0 否'
;

COMMENT ON COLUMN TB_APP_VERSION.pkg_path IS '升级包路径'
;

COMMENT ON COLUMN TB_APP_VERSION.add_time IS '添加时间'
;

COMMENT ON COLUMN TB_APP_VERSION.update_time IS '更新时间'
;

ALTER TABLE TB_APP_VERSION 
 ADD CONSTRAINT PK_TB_APP_VERSION
	PRIMARY KEY (id) USING INDEX
;

CREATE SEQUENCE SEQ_TB_APP_VERSION_ID
	INCREMENT BY 1 
	START WITH 1 
	NOMAXVALUE 
	MINVALUE  1 
	NOCYCLE 
	NOCACHE 
	NOORDER
;

CREATE OR REPLACE TRIGGER TRG_TB_APP_VERSION_ID
	BEFORE INSERT 
	ON TB_APP_VERSION
	FOR EACH ROW 
	BEGIN 
		SELECT SEQ_TB_APP_VERSION_ID.NEXTVAL 
		INTO :NEW.ID
		FROM DUAL; 
	END;