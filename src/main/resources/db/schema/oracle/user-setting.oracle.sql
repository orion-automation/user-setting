-- liquibase formatted sql

-- changeset eorion:1 dbms:oracle
CREATE TABLE ENHANCEMENT_USER_SETTING
(
    ID                   number(10,0),
    USER_ID              nvarchar2(128) unique not null,
    PREFERENCE_JSON      nvarchar2(2000),
    PRIMARY KEY (ID)
);

-- changeset eorion:2 dbms:oracle
CREATE SEQUENCE ENHANCEMENT_USER_SETTING_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
NOCYCLE;

-- changeset eorion:3 dbms:oracle
CREATE OR REPLACE TRIGGER ENHANCEMENT_USER_SETTING_ID_INSERT
BEFORE INSERT ON ENHANCEMENT_USER_SETTING
FOR EACH ROW
BEGIN
  :NEW.ID := ENHANCEMENT_USER_SETTING_SEQ.NEXTVAL;
END;
/