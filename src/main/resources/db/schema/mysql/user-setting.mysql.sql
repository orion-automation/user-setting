-- liquibase formatted sql

-- changeset eorion:1 dbms:mysql
CREATE TABLE IF NOT EXISTS ENHANCEMENT_USER_SETTING
(
    ID  INT UNSIGNED  AUTO_INCREMENT  PRIMARY KEY,
    USER_ID varchar(128) unique not null,
    PREFERENCE_JSON varchar(2000) null
);