DECLARE num NUMBER;
BEGIN
SELECT COUNT(1) INTO num FROM USER_TABLES WHERE TABLE_NAME = UPPER('TB_USER_ROLE') ;
IF num > 0 THEN
EXECUTE IMMEDIATE 'DROP TABLE TB_USER_ROLE' ;
END IF;
END;
/

DECLARE
  C NUMBER;
BEGIN
SELECT COUNT(*) INTO C
FROM ALL_SEQUENCES
  WHERE SEQUENCE_NAME = 'SEQ_TB_USER_ROLE_ID';
  IF (C > 0) THEN
    EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_TB_USER_ROLE_ID';
END IF;
END
;
/
CREATE TABLE TB_USER_ROLE
(
	ID NUMBER(38) NOT NULL,
	user_id NUMBER(38) NOT NULL,    -- 用户ID
	role_id NUMBER(38) NOT NULL,    -- 角色ID
	add_time NUMBER(13) NOT NULL    -- 添加时间
);

COMMENT ON TABLE TB_USER_ROLE IS '用户角色关系表'
;

COMMENT ON COLUMN TB_USER_ROLE.user_id IS '用户ID'
;

COMMENT ON COLUMN TB_USER_ROLE.role_id IS '角色ID'
;

COMMENT ON COLUMN TB_USER_ROLE.add_time IS '添加时间'
;

ALTER TABLE TB_USER_ROLE 
 ADD CONSTRAINT PK_TB_USER_ROLE
	PRIMARY KEY (ID) USING INDEX
;
ALTER TABLE TB_USER_ROLE 
 ADD CONSTRAINT unique_user_role UNIQUE (user_id,role_id) USING INDEX
;

CREATE SEQUENCE SEQ_TB_USER_ROLE_ID
	INCREMENT BY 1 
	START WITH 1 
	NOMAXVALUE 
	MINVALUE  1 
	NOCYCLE 
	NOCACHE 
	NOORDER
;

create or replace trigger TRG_TB_USER_ROLE_ID
  before insert on TB_USER_ROLE  
  for each row
declare
  -- local variables here
begin
  SELECT SEQ_TB_USER_ROLE_ID.NEXTVAL 
		INTO :NEW.ID
		FROM DUAL; 
end TRG_TB_USER_ROLE_ID;