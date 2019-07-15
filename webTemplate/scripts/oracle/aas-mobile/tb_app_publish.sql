DECLARE num NUMBER;
BEGIN
SELECT COUNT(1) INTO num FROM USER_TABLES WHERE TABLE_NAME = UPPER('TB_APP_PUBLISH') ;
IF num > 0 THEN
EXECUTE IMMEDIATE 'DROP TABLE TB_APP_PUBLISH' ;
END IF;
END;
/

DECLARE
  C NUMBER;
BEGIN
SELECT COUNT(*) INTO C
FROM ALL_SEQUENCES
  WHERE SEQUENCE_NAME = 'SEQ_TB_CLIENT_SETTING_ID';
  IF (C > 0) THEN
    EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_TB_CLIENT_SETTING_ID';
END IF;
END
;
/
CREATE TABLE TB_APP_PUBLISH (
  id number(13) NOT NULL,
  version varchar2(20),
  term_type varchar2(10),
  update_force number(4),
  pkg_path varchar2(512),
  plist_path varchar2(512),
  publish varchar2(10),
  "COMMENT" clob,
  add_time number(13),
  update_time number(13)
);

COMMENT ON COLUMN TB_APP_PUBLISH.version IS '版本'
;
COMMENT ON COLUMN TB_APP_PUBLISH.term_type IS '终端类型 Android、IOS'
;
COMMENT ON COLUMN TB_APP_PUBLISH.update_force IS '是否强制更新 1 是 0 否'
;
COMMENT ON COLUMN TB_APP_PUBLISH.pkg_path IS '包路径'
;
COMMENT ON COLUMN TB_APP_PUBLISH.publish IS '发布状态，1-发布，2-禁用'
;

ALTER TABLE TB_APP_PUBLISH
 ADD CONSTRAINT PK_TB_APP_PUBLISH
	PRIMARY KEY (id) USING INDEX
;

CREATE SEQUENCE SEQ_TB_APP_PUBLISH_ID
	INCREMENT BY 1
	START WITH 1
	NOMAXVALUE
	MINVALUE  1
	NOCYCLE
	NOCACHE
	NOORDER
;

CREATE OR REPLACE TRIGGER TRG_TB_APP_PUBLISH_ID
	BEFORE INSERT
	ON TB_APP_PUBLISH
	FOR EACH ROW
	BEGIN
		SELECT SEQ_TB_APP_PUBLISH_ID.NEXTVAL
		INTO :NEW.ID
		FROM DUAL;
	END;