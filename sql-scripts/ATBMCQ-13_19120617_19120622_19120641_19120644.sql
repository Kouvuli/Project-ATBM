
--- PHAN HE 1 ---

-- SELECT PRIVILEGE
create or replace procedure GRANT_SELECT (GRANTEE in varchar2, iOBJECT in varchar2, BOOL in number) --GRANTEE can be username or rolename, BOOL: 1 equals true, else false.
is
begin
    if BOOL = 1 then
        execute immediate 'grant select on ' || iOBJECT || ' to ' || GRANTEE || ' with grant option';
    else
        execute immediate 'grant select on ' || iOBJECT || ' to ' || GRANTEE;
    end if;
end;
/

create or replace procedure REVOKE_SELECT (GRANTEE in varchar2, iOBJECT in varchar2)
is
begin
        execute immediate 'revoke select on ' || iOBJECT || ' from ' || GRANTEE;
end;
/

-- INSERT PRIVILEGE
create or replace procedure GRANT_INSERT (GRANTEE in varchar2, iOBJECT in varchar2, BOOL in number) --GRANTEE can be username or rolename, BOOL: 1 equals true, else false.
is
begin
    if BOOL = 1 then
        execute immediate 'grant insert on ' || iOBJECT || ' to ' || GRANTEE || ' with grant option';
    else
        execute immediate 'grant insert on ' || iOBJECT || ' to ' || GRANTEE;
    end if;
end;
/

create or replace procedure REVOKE_INSERT (GRANTEE in varchar2, iOBJECT in varchar2)
is
begin
        execute immediate 'revoke insert on ' || iOBJECT || ' from ' || GRANTEE;
end;
/
-- DELETE PRIVILEGE
create or replace procedure GRANT_DELETE (GRANTEE in varchar2, iOBJECT in varchar2, BOOL in number) --GRANTEE can be username or rolename, BOOL: 1 equals true, else false.
is
begin
    if BOOL = 1 then
        GRANT_SELECT(GRANTEE,iOBJECT,1);
        execute immediate 'grant delete on ' || iOBJECT || ' to ' || GRANTEE || ' with grant option';
    else
        GRANT_SELECT(GRANTEE,iOBJECT,0);
        execute immediate 'grant delete on ' || iOBJECT || ' to ' || GRANTEE;
    end if;
end;
/

create or replace procedure REVOKE_DELETE (GRANTEE in varchar2, iOBJECT in varchar2)
is
begin
    REVOKE_SELECT(GRANTEE,iOBJECT);
    execute immediate 'revoke delete on ' || iOBJECT || ' from ' || GRANTEE;
end;
/

-- UPDATE PRIVILEGE
create or replace procedure GRANT_UPDATE (GRANTEE in varchar2, iOBJECT in varchar2, BOOL in number) --GRANTEE can be username or rolename, BOOL: 1 equals true, else false.
is
begin
    if BOOL = 1 then
        GRANT_SELECT(GRANTEE,iOBJECT,1);
        execute immediate 'grant update on ' || iOBJECT || ' to ' || GRANTEE || ' with grant option';
    else
        GRANT_SELECT(GRANTEE,iOBJECT,0);
        execute immediate 'grant update on ' || iOBJECT || ' to ' || GRANTEE;
    end if;
end;
/

create or replace procedure REVOKE_UPDATE (GRANTEE in varchar2, iOBJECT in varchar2)
is
begin
    REVOKE_SELECT(GRANTEE,iOBJECT);
    execute immediate 'revoke update on ' || iOBJECT || ' from ' || GRANTEE;
end;
/

create or replace procedure GRANT_COL_UPDATE (GRANTEE in varchar2, iCOL in varchar2, iOBJECT in varchar2, BOOL in number)
is
begin
    if BOOL = 1 then
        execute immediate 'grant update ' || '(' || iCOL || ')' || ' on ' || iOBJECT || ' to ' || GRANTEE || ' with grant option';
    else
        execute immediate 'grant update ' || '(' || iCOL || ')' || ' on ' || iOBJECT || ' to ' || GRANTEE;
    end if;
end; 
/

-- GRANT/REVOKE role
create or replace procedure GRANT_ROLE(GRANTEE in varchar2, iROLE in varchar2, BOOL in number)
is
begin
    if BOOL = 1 then
        execute immediate 'grant ' || iROLE || ' to ' || GRANTEE || ' with grant option';
    else
        execute immediate 'grant ' || iROLE || ' to ' || GRANTEE;
    end if;
end;
/

create or replace procedure REVOKE_ROLE(GRANTEE in varchar2, iROLE in varchar2)
is
begin
    execute immediate 'revoke ' || iROLE || ' from ' || GRANTEE;
end;
/

-- GRANT/REVOKE system privilege
create or replace procedure GRANT_SYS_PRIV(GRANTEE in varchar2, SYS_PRIV in varchar2, BOOL in number)
is
begin
    if BOOL = 1 then
        execute immediate 'grant ' || SYS_PRIV || ' to ' || GRANTEE || ' with admin option';
    else
        execute immediate 'grant ' || SYS_PRIV || ' to ' || GRANTEE;
    end if;
end;
/

create or replace procedure REVOKE_SYS_PRIV (GRANTEE in varchar2, SYS_PRIV in varchar2)
is
begin
    execute immediate 'revoke ' || SYS_PRIV || ' from ' || GRANTEE;    
end;
/


-- CREATE/DROP USER
create or replace procedure CREATE_USER (user_name in varchar2, pwd in varchar2)
is
begin
    execute immediate 'alter session set "_oracle_script"=true';
    execute immediate 'create user ' || user_name || ' identified by ' || pwd;
    execute immediate 'grant create session to ' || user_name;
end;
/

create or replace procedure DROP_USER (user_name in varchar2)
is
begin
    execute immediate 'drop user ' || user_name || ' cascade';
end;
/


-- CREATE/DROP ROLE
create or replace procedure CREATE_ROLE (role_name in varchar2)
is
begin
    execute immediate 'alter session set "_oracle_script"=true';
    execute immediate 'create role ' || role_name;
end;
/

create or replace procedure DROP_ROLE (role_name in varchar2)
is
begin
    execute immediate 'drop role ' || role_name;
end;
/

--- PHAN HE 2 ---
Alter session set "_ORACLE_SCRIPT"=true; 

DROP USER DB CASCADE;
CREATE USER DB IDENTIFIED BY 1;
GRANT CREATE SESSION TO DB;
GRANT CREATE ANY TRIGGER TO DB
GRANT CREATE PROCEDURE TO DB;
GRANT EXECUTE ON ENCRYPT_AES TO DB;
GRANT EXECUTE ON DECRYPT_AES TO DB;
GRANT EXECUTE ON DBMS_RLS TO DB;
ALTER USER DB QUOTA UNLIMITED ON USERS;
/
--------------------------------------

-- CREATE TABLE

drop table db.HSBA_DV
cascade constraints
purge;

drop table db.HSBA
cascade constraints
purge;

drop table db.BENHNHAN
cascade constraints
purge;

drop table db.NHANVIEN
cascade constraints
purge;

drop table db.CSYT
cascade constraints
purge;

drop table db.THONGBAO
cascade constraints
purge;
/
---------------------------------------

create table db.HSBA
(
    MAHSBA NVARCHAR2(10),
    MABN NVARCHAR2(10),
    NGAY DATE,
    CHANDOAN NVARCHAR2(300),
    MABS NVARCHAR2(10),
    MAKHOA NVARCHAR2(10),
    MACSYT NVARCHAR2(10),
    KETLUAN NVARCHAR2(300),
    CONSTRAINT PK_HSBA PRIMARY KEY(MAHSBA)
);

create table db.HSBA_DV
(
    MAHSBA NVARCHAR2(10),
    MADV NVARCHAR2(10),
    NGAY DATE,
    MAKTV NVARCHAR2(10),
    KETQUA NVARCHAR2(100),
    CONSTRAINT PK_HSBA_DV PRIMARY KEY(MAHSBA, MADV, NGAY)
);

create table db.BENHNHAN
(
    MABN NVARCHAR2(10),
    MACSYT NVARCHAR2(10),
    TENBN NVARCHAR2(100),
    CMND NVARCHAR2(12),
    NGAYSINH DATE,
    SONHA NVARCHAR2(5),
    TENDUONG NVARCHAR2(50),
    QUANHUYEN NVARCHAR2(20),
    TINHTP NVARCHAR2(50),
    TIEUSUBENH NVARCHAR2(300),
    TIEUSUBENHGD NVARCHAR2(300),
    DIUNGTHUOC RAW(2000),
    CONSTRAINT PK_BENHNHAN PRIMARY KEY(MABN)
);

create table db.CSYT
(
    MACSYT NVARCHAR2(10),
    TENCSYT NVARCHAR2(100),
    DCCSYT NVARCHAR2(100),
    SDTCSYT NVARCHAR2(10),
    CONSTRAINT PK_CSYT PRIMARY KEY(MACSYT)
);

create table db.NHANVIEN
(
    MANV NVARCHAR2(10),
    HOTEN NVARCHAR2(100),
    PHAI NVARCHAR2(4),
    NGAYSINH DATE,
    CMND NVARCHAR2(12),
    QUEQUAN NVARCHAR2(100),
    SDT NVARCHAR2(10),
    CSYT NVARCHAR2(10),
    VAITRO NVARCHAR2(20),
    CHUYENKHOA NVARCHAR2(10),
    LUONG RAW(2000),
    CONSTRAINT PK_NHANVIEN PRIMARY KEY(MANV)
);

create table db.THONGBAO
(
    NOIDUNG NCLOB,
    NGAYGIO DATE,
    DIADIEM NVARCHAR2(300),
    LVL nvarchar2(50), -- LEVEL
    CPRM nvarchar2(50),
    GR nvarchar2(50) -- GROUP
);
/
-----------------------------------------

alter table db.BENHNHAN
add constraint FK_BENHNHAN_CSYT
foreign key (MACSYT)
references db.CSYT(MACSYT);

alter table db.NHANVIEN
add constraint FK_NHANVIEN_CSYT
foreign key (CSYT)
references db.CSYT(MACSYT);

alter table db.HSBA_DV
add constraint FK_HSBA_DV_HSBA
foreign key (MAHSBA)
references db.HSBA;

alter table db.HSBA
add constraint FK_HSBA_BENHNHAN
foreign key (MABN)
references db.BENHNHAN(MABN);

alter table db.HSBA
add constraint FK_HSBA_CSYT
foreign key (MACSYT)
references db.CSYT(MACSYT);

alter table db.HSBA
add constraint FK_HSBA_NHANVIEN
foreign key (MABS)
references db.NHANVIEN(MANV);
/
-----------------------------------------

        -- Ma hoa
create or replace function encrypt_aes
    (dat in varchar2, id_user in varchar2)
    return raw
as
    aes_key raw(32) := UTL_RAW.CAST_TO_RAW(rpad(id_user,32,'ph2'));
    encryption_type pls_integer := DBMS_CRYPTO.ENCRYPT_AES256 + DBMS_CRYPTO.CHAIN_CBC + DBMS_CRYPTO.PAD_PKCS5;
begin
    return dbms_crypto.encrypt
    (
        src => UTL_I18N.STRING_TO_RAW (dat, 'AL32UTF8'),
        typ => encryption_type,
        key => aes_key
    );
end;
/
        -- Giai ma
create or replace function decrypt_aes
    (dat in raw, id_user in varchar2)
    return varchar2
as
    decrypted_raw raw(2000);
    aes_key raw(32) := UTL_RAW.CAST_TO_RAW(rpad(id_user,32,'ph2'));
    encryption_type pls_integer := DBMS_CRYPTO.ENCRYPT_AES256 + DBMS_CRYPTO.CHAIN_CBC + DBMS_CRYPTO.PAD_PKCS5;
begin
    decrypted_raw := dbms_crypto.decrypt
    (
        src => dat,
        typ => encryption_type,
        key => aes_key
    );
    return UTL_I18N.RAW_TO_CHAR (decrypted_raw, 'AL32UTF8');
end;
/

 --- AUDIT ---
 
    --- STANDARD ---
GRANT SELECT on SYS.AUD$ TO DB;
GRANT SELECT ON DBA_AUDIT_TRAIL TO DB;
GRANT SELECT ON UNIFIED_AUDIT_TRAIL TO DB;
/
---kich hoat viec ghi nhat ky toan he thong

ALTER SYSTEM SET AUDIT_TRAIL=DB,EXTENDED SCOPE=SPFILE; -- SQL_TEST record
/*
ALTER SYSTEM SET audit_trail=db SCOPE=SPFILE; -- no SQL_TEXT record
ALTER SYSTEM SET audit_trail=XML SCOPE = SPFILE; -- no SQL_TEXT record and save in os
ALTER SYSTEM SET audit_trail=os SCOPE=SPFILE; -- SQL_TEXT record and save in os
*/
/

--SHUTDOWN IMMEDIATE; -- restart
--STARTUP;

/*
show parameter audit_file_dest; -- coi dia chi luu
show parameter audit_trail; coi che do audit 
SHOW PARAMETER AUDIT;
*/

-- Audit table
Audit all on DB.HSBA_DV by ACCESS;
Audit all on DB.HSBA by ACCESS;
Audit all on DB.BENHNHAN by ACCESS;
Audit all on DB.NHANVIEN by ACCESS;
Audit all on DB.CSYT by ACCESS;
Audit all on DB.THONGBAO by ACCESS;

Audit drop any table by ACCESS; 
Audit create any table by ACCESS; 
Audit delete any table by ACCESS;
Audit create any trigger by ACCESS;

Audit create any trigger by DB;

Audit all on TC2 by ACCESS;
Audit all on TC3 by ACCESS;
Audit all on TC4 by ACCESS;
Audit all on TC5 by ACCESS;
Audit all on PERSONAL_NV by ACCESS;
Audit all on PERSONAL_BN by ACCESS;
Audit all on NO_RECORDS by ACCESS;

Audit all on DB.THONGBAO by Session;

AUDIT SESSION;

AUDIT NOT EXISTS BY ACCESS ;

AUDIT NETWORK;

NOAUDIT NETWORK;
/* SHOW RECORD AUDIT
-- format
column username format a9
column owner format a5
column obj_name format a10
column action_name format a11
column sql_text format a40
*/
/
-- audit log
select * from dba_stmt_audit_opts; -- cac quyen he thong

select * from DBA_PRIV_AUDIT_OPTS; -- cac quyen he thong

select * from DBA_OBJ_AUDIT_OPTS -- cac quyen tren object
where Owner ='DB';
/
select audit_type,dbusername,action_name,object_schema,object_name,current_user,target_user,SQL_TEXT,audit_option from UNIFIED_AUDIT_TRAIL
where audit_type = 'Standard'
order by event_timestamp desc;
/


    --- FGA ---

GRANT EXECUTE ON DBMS_FGA TO DB;
-- CAC CHINH SACH

--1.
--CHECK WHO INSERT,UPDATE,SELECT KETQUA COLUMN
begin DBMS_FGA.DROP_POLICY(
   object_schema =>  'DB', 
   object_name => 'HSBA_DV', 
   policy_name => 'HSBA_DV_AUDIT');
end;
/
begin dbms_fga.add_policy(
object_schema => 'DB',
object_name => 'HSBA_DV',
policy_name => 'HSBA_DV_AUDIT',
audit_column => 'KETQUA',
enable => TRUE,
statement_types => 'UPDATE,INSERT,DELETE,SELECT');
end;
/


--2.
--CHECK WHO INSERT,UPDATE,SELECT CHUANDOAN COLUMN
begin DBMS_FGA.DROP_POLICY(
   object_schema =>  'DB', 
   object_name => 'HSBA', 
   policy_name => 'HSBA_AUDIT');
end;
/
begin dbms_fga.add_policy(
object_schema => 'DB',
object_name => 'HSBA',
policy_name => 'HSBA_AUDIT',
audit_column => 'CHANDOAN',
enable => TRUE,
statement_types => 'UPDATE,INSERT,DELETE,SELECT');
end;
/

--3.
--CHECK TIENSUBENH,TIENSUBENHCOLUM COLUMN
begin DBMS_FGA.DROP_POLICY(
   object_schema =>  'DB', 
   object_name => 'BENHNHAN', 
   policy_name => 'BENHNHAN_AUDIT');
end;
/
begin dbms_fga.add_policy(
object_schema => 'DB',
object_name => 'BENHNHAN',
policy_name => 'BENHNHAN_AUDIT',
audit_column => 'TIEUSUBENH,TIEUSUBENHGD,DIUNGTHUOC',
enable => TRUE,
statement_types => 'UPDATE,INSERT,DELETE,SELECT');
end;
/

--4.
--CHECK SELECT TIENSUBENH,TIENSUBENHCOLUM COLUMN
begin DBMS_FGA.DROP_POLICY(
   object_schema =>  'DB', 
   object_name => 'NHANVIEN', 
   policy_name => 'NHANVIEN_AUDIT');
end;
/
begin dbms_fga.add_policy(
object_schema => 'DB',
object_name => 'NHANVIEN',
policy_name => 'NHANVIEN_AUDIT',
audit_column => 'SDT,CMND,HOTEN,VAITRO',
enable => TRUE,
statement_types => 'SELECT',
audit_column_opts => dbms_fga.all_columns);
end;
/

/*-------------------------------*/

-- audit log
select audit_type, dbusername, action_name, event_timestamp, sql_text, fga_policy_name 
from UNIFIED_AUDIT_TRAIL 
where audit_type = 'FineGrainedAudit'
order by event_timestamp desc;
/

-- kiem tra cac chinh sach audit da tao
select * from DBA_AUDIT_POLICIES;
SELECT * FROM DBA_AUDIT_POLICY_COLUMNS;
/


--- 7 TIEU CHI ---
-- TC1
-- create user trigger
create or replace trigger after_insert_nv
after insert
    on db.NHANVIEN
    for each row
declare
    exist number;
    PRAGMA AUTONOMOUS_TRANSACTION;
begin
    execute immediate 'select count(*) from dba_users where username = '''||:new.MANV||'''' into exist;
    if exist > 0 then
        execute immediate 'drop user '||:new.MANV||' cascade';
    end if;
    
    execute immediate 'create user '||:new.MANV||' identified by 1';
    execute immediate 'grant create session to '||:new.MANV;
    execute immediate 'grant execute on decrypt_aes to ' ||:new.MANV;
end;
/
create or replace trigger after_insert_bn
after insert
    on db.BENHNHAN
    for each row
declare
    exist number;
    PRAGMA AUTONOMOUS_TRANSACTION;
begin
    execute immediate 'select count(*) from dba_users where username = '''||:new.MABN||'''' into exist;
    if exist > 0 then
        execute immediate 'drop user '||:new.MABN||' cascade';
    end if;
    
    execute immediate 'create user '||:new.MABN|| ' identified by 1';
    execute immediate 'grant create session to '||:new.MABN;
    execute immediate 'grant execute on decrypt_aes to ' ||:new.MABN;
end;
/
----------------------------------------

--- ADDING DATA ---

    --- CSYT ---
insert into db.CSYT values ('CS1','TT','null','null');
insert into db.CSYT values ('CS2','CTT','null','null');
insert into db.CSYT values ('CS3','NT','null','null');
/
    --- NHANVIEN ---
insert into db.NHANVIEN values ('NV1','A','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Thanh tra','null',encrypt_aes('1000','NV1'));
insert into db.NHANVIEN values ('NV2','B','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Y si/bac si','null',encrypt_aes('1000','NV2'));
insert into db.NHANVIEN values ('NV3','C','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS2','Y si/bac si','null',encrypt_aes('1000','NV3'));
insert into db.NHANVIEN values ('NV4','D','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS3','Y si/bac si','null',encrypt_aes('1000','NV4'));
insert into db.NHANVIEN values ('NV5','E','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Nghien cuu','TK',encrypt_aes('1000','NV5')); -- khoa than kinh
insert into db.NHANVIEN values ('NV6','F','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS2','Nghien cuu','TM',encrypt_aes('1000','NV6')); -- khoa tim mach
insert into db.NHANVIEN values ('NV7','G','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS3','Nghien cuu','TH',encrypt_aes('1000','NV7')); -- khoa tieu hoa
insert into db.NHANVIEN values ('NV8','K','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Co so y te','null',encrypt_aes('1000','NV8'));
insert into db.NHANVIEN values ('NV9','L','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS2','Co so y te','null',encrypt_aes('1000','NV9'));
insert into db.NHANVIEN values ('NV10','M','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS3','Co so y te','null',encrypt_aes('1000','NV10'));
/
    --- BENHNHAN ---
insert into db.BENHNHAN values ('BN1','CS1','H','1',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','null','null','null',encrypt_aes('null','BN1'));
insert into db.BENHNHAN values ('BN2','CS2','I','2',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','null','null','null',encrypt_aes('null','BN2'));
insert into db.BENHNHAN values ('BN3','CS3','J','3',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','null','null','null',encrypt_aes('null','BN3'));
/    
    --- HSBA ---
insert into db.HSBA values ('HS1','BN1',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','NV2','TK','CS1','null');
insert into db.HSBA values ('HS2','BN2',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','NV3','TM','CS2','null');    
insert into db.HSBA values ('HS3','BN3',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','NV4','TH','CS3','null');
/    
    --- HSBA_DV ---
insert into db.HSBA_DV values ('HS1','DV1',TO_DATE('2022/06/23', 'yyyy/mm/dd'),'NV2','Tot');
insert into db.HSBA_DV values ('HS2','DV2',TO_DATE('2022/06/25', 'yyyy/mm/dd'),'NV3','Trung binh');
insert into db.HSBA_DV values ('HS3','DV3',TO_DATE('2022/06/27', 'yyyy/mm/dd'),'NV4','Khong tot');    
/    
    --- THONGBAO ---
insert into db.THONGBAO (NOIDUNG,NGAYGIO,DIADIEM,LVL,CPRM,GR) values ('SENSITIVE',
    TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','S','null','null');
insert into db.THONGBAO (NOIDUNG,NGAYGIO,DIADIEM,LVL,CPRM,GR) values ('SENSITIVE::CTT',
    TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','S','null','CTT');
insert into db.THONGBAO (NOIDUNG,NGAYGIO,DIADIEM,LVL,CPRM,GR) values ('PUBLIC',
    TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','P','null','null');
insert into db.THONGBAO (NOIDUNG,NGAYGIO,DIADIEM,LVL,CPRM,GR) values ('SENSITIVE:CS:',
    TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','S','CS','null');
/

-- TC2

drop role THANHTRA;
create role THANHTRA;
grant create session to THANHTRA;
grant select on db.BENHNHAN to THANHTRA;
grant select on db.NHANVIEN to THANHTRA;
grant select on db.HSBA_DV to THANHTRA;
grant select on db.HSBA to THANHTRA;
grant select on db.CSYT to THANHTRA;
grant exempt access policy to THANHTRA;
/

create or replace procedure TC2
is
begin
    for S in (select * from db.NHANVIEN where VAITRO = 'Thanh tra')
    loop
        execute immediate 'grant THANHTRA to ' ||S.MANV;
    end loop;
end;
/
begin  TC2; end;
/

----------------------------------------

-- TC3
create or replace procedure TC3
is
    temp_num number := 0;
    exist number;
begin
    for S in (select * from db.nhanvien where vaitro = 'Co so y te')
    loop
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA as select * from db.HSBA where MACSYT = '''||S.CSYT||'''';
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA_DV as select * from db.HSBA_DV where MAHSBA in (select MAHSBA from db.'||S.MANV||'_HSBA)';
        
        execute immediate 'grant select, insert, delete on db.'||S.MANV||'_HSBA to '||S.MANV;
        execute immediate 'grant select, insert, delete on db.'||S.MANV||'_HSBA_DV to '||S.MANV;
    end loop;
end;
/
begin TC3; end;
/
----------------------------------------
create or replace function IS_DBM(username in varchar2)
    return boolean
is
begin
    for s in (select * from db.NHANVIEN where vaitro = 'Co so y te')
    loop
        if username = s.MANV then
            return true;
        end if;
    end loop;
    return false;
end;
/
drop trigger prevent_logon;
/
create or replace trigger prevent_logon
    after logon on database
declare
    current_day pls_integer;
    restricted_users boolean;
    restricted_timeline boolean;
begin
    
    restricted_users := IS_DBM(user);
    current_day := extract(day from sysdate);
    restricted_timeline := (current_day < 5 or current_day > 27);
    
    if restricted_users and restricted_timeline then
        raise_application_error(-20001, 'Inappropriate day!');
    end if;
end;
/
----------------------------------------

-- TC4

create or replace procedure TC4
is
begin
    for S in (select * from db.NHANVIEN where VAITRO = 'Y si/bac si')
    loop
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA as select * from db.HSBA where MABS = '''||S.MANV||'''';
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA_DV as select * from db.HSBA_DV where MAHSBA in (select MAHSBA from db.'||S.MANV||'_HSBA)';
        execute immediate 'create or replace view db.'||S.MANV||'_BN as select *'||
            ' from db.BENHNHAN where MABN in (select MABN from db.'||S.MANV||'_HSBA)';
        execute immediate 'grant select on db.'||S.MANV||'_HSBA to '||S.MANV;
        execute immediate 'grant select on db.'||S.MANV||'_HSBA_DV to '||S.MANV;
        execute immediate 'grant select on db.'||S.MANV||'_BN to '||S.MANV;
    end loop;
end;
/
begin TC4; end;
/
----------------------------------------

-- TC5

create or replace procedure TC5
is
begin
    for S in (select * from db.NHANVIEN where VAITRO = 'Nghien cuu')
    loop
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA as select * from db.HSBA where MACSYT = '''||S.CSYT||''' and MAKHOA = '''||S.CHUYENKHOA||'''';
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA_DV as select * from db.HSBA_DV where MAHSBA in (select MAHSBA from db.'||S.MANV||'_HSBA)';
        
        execute immediate 'grant select on db.'||S.MANV||'_HSBA to '||S.MANV;
        execute immediate 'grant select on db.'||S.MANV||'_HSBA_DV to '||S.MANV;
    end loop;
end;
/
begin TC5; end;
/
----------------------------------------
-- TC6
create or replace function PERSONAL_BN
    (p_schema in varchar2 default null, 
    p_object in varchar2 default null) 
    return varchar2
is
    num1 number := 0;
begin
    select count(*) into num1 from db.BENHNHAN
    where MABN = user;
    if (num1 > 0) then 
        return 'MABN = user';
    else 
        return '1=1';
    end if;
    
exception
    when others then return '1=1';
end;
/
create or replace function PERSONAL_NV
    (p_schema in varchar2 default null, 
    p_object in varchar2 default null) 
    return varchar2
is
    num2 number := 0;
begin
    select count(*) into num2 from db.NHANVIEN
    where MANV = user;
    if (num2 > 0) then
        return 'MANV = user';
    else
        return '1=1';
    end if;
exception
    when others then return '1=1';
end;
/
create or replace function NO_RECORDS
    (p_schema in varchar2 default null, 
    p_object in varchar2 default null) 
    return varchar2
is
    num1 number;
    num2 number;
begin
    select count(*) into num1 from db.BENHNHAN
    where MABN = user;
    select count(*) into num2 from db.NHANVIEN
    where MANV = user;
    
    if (num1 > 0 or num2 > 0) then
        return '1=0';
    else
        return '1=1';
    end if;
exception
    when others then return '1=1';
end;
/
----------------------------------------

create or replace procedure grant_privs_bn_nv
is
begin
    for s in (select * from db.benhnhan)
    loop
        execute immediate 'grant select, update on db.benhnhan to ' ||s.mabn;
        
    end loop;
    
    for s in (select * from db.nhanvien)
    loop
        execute immediate 'grant select, update on db.nhanvien to ' ||s.manv;
    end loop;
end;
/
begin grant_privs_bn_nv; end;
/
begin
dbms_rls.drop_policy
    (object_schema => 'DB',
    object_name => 'BENHNHAN',
    policy_name => 'BN_PERSONAL');
end;
/
begin
dbms_rls.add_policy
    (object_schema => 'DB',
    object_name => 'BENHNHAN',
    policy_name => 'BN_PERSONAL',
    function_schema => 'SYS',
    policy_function => 'PERSONAL_BN',
    statement_types => 'SELECT, UPDATE',
    update_check => TRUE);
end;
/
begin
dbms_rls.drop_policy
    (object_schema => 'DB',
    object_name => 'BENHNHAN',
    policy_name => 'BN_ENCRYPTED');
end;
/
begin
    dbms_rls.add_policy
    (object_schema => 'DB',
    object_name => 'BENHNHAN',
    policy_name => 'BN_ENCRYPTED',
    function_schema => 'SYS',
    policy_function => 'NO_RECORDS',
    statement_types => 'UPDATE',
    update_check => TRUE,
    sec_relevant_cols => 'DIUNGTHUOC'); --Them vao cac cot duoc ma hoa
end;
/
----------------------------------------
begin
dbms_rls.drop_policy
    (object_schema => 'DB',
    object_name => 'NHANVIEN',
    policy_name => 'NV_PERSONAL');
end;
/
begin
dbms_rls.add_policy
    (object_schema => 'DB',
    object_name => 'NHANVIEN',
    policy_name => 'NV_PERSONAL',
    function_schema => 'SYS',
    policy_function => 'PERSONAL_NV',
    statement_types => 'SELECT, UPDATE',
    update_check => TRUE);
end;
/
begin
dbms_rls.drop_policy
    (object_schema => 'DB',
    object_name => 'NHANVIEN',
    policy_name => 'NV_ENCRYPTED');
end;
/
begin
    dbms_rls.add_policy
    (object_schema => 'DB',
    object_name => 'NHANVIEN',
    policy_name => 'NV_ENCRYPTED',
    function_schema => 'SYS',
    policy_function => 'NO_RECORDS',
    statement_types => 'UPDATE',
    update_check => TRUE,
    sec_relevant_cols => 'LUONG'); --Them vao cac cot duoc ma hoa
end;
/
-- TC7

-- CONFIGURE AND ENABLE OLS
begin LBACSYS.CONFIGURE_OLS; end;
begin lbacsys.ols_enforcement.enable_ols; end;

-- SHUTDOWN IMMEDIATE;
-- STARTUP;

SELECT VALUE FROM V$OPTION WHERE PARAMETER = 'Oracle Label Security';
select name, status, description from dba_ols_status;

alter user lbacsys identified by lbacsys account unlock;

-- Luon dieu chinh policy bang tai khoan LBACSYS
/
    -- TAO CHINH SACH LABEL
begin
    sa_sysdba.drop_policy(
    policy_name => 'ACCESS_THONGBAO',
    drop_column => TRUE);
end;
/    
begin
    sa_sysdba.create_policy(
    policy_name => 'ACCESS_THONGBAO',
    column_name => 'OLS_COL');
end;
/

        --- OLS USER ---
        
-- OLS_SEC: USER QUAN LY USER TRUY CAP DU LIEU OLS
DROP USER OLS_SEC CASCADE;
CREATE USER OLS_SEC IDENTIFIED BY 1;
GRANT CREATE SESSION TO OLS_SEC;
GRANT CREATE USER, DROP USER, CREATE ROLE, DROP ANY ROLE TO OLS_SEC;
GRANT ACCESS_THONGBAO_DBA TO OLS_SEC;
GRANT SA_USER_ADMIN TO OLS_SEC;
/

-- OLS_ADMIN: USER QUAN LY CHINH SACH BAO MAT
DROP USER OLS_ADMIN CASCADE;
CREATE USER OLS_ADMIN IDENTIFIED BY 1;
GRANT CREATE SESSION TO OLS_ADMIN;
GRANT ACCESS_THONGBAO_DBA TO OLS_ADMIN;
GRANT EXECUTE ON SA_COMPONENTS TO OLS_ADMIN;
GRANT EXECUTE ON SA_LABEL_ADMIN TO OLS_ADMIN;
GRANT EXECUTE ON SA_POLICY_ADMIN TO OLS_ADMIN;
/
    -- TAO LEVEL

begin
sa_components.create_level
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'PUBLIC',
    short_name => 'P',
    level_num => 100);
end;
/
begin
sa_components.create_level
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'SENSITIVE',
    short_name => 'S',
    level_num => 200);
end;
/
    -- TAO COMPARTMENT

begin
    sa_components.create_compartment
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'NGOAITRU',
    short_name => 'NGT',
    comp_num => 100);
end;
/
begin
    sa_components.create_compartment
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'NOITRU',
    short_name => 'NT',
    comp_num => 200);
end;
/
begin
    sa_components.create_compartment
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'CHUYENSAU',
    short_name => 'CS',
    comp_num => 300);
end;
/
    -- TAO GROUP

        -- I: SO Y TE TP.HCM  
begin
sa_components.create_group
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'SOYTE_TPHCM',
    short_name => 'S',
    group_num => 100,
    parent_name => NULL);
end;
/
        -- II: TRUNG TAM
begin
sa_components.create_group
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'TRUNGTAM',
    short_name => 'TT',
    group_num => 200,
    parent_name => 'S');
end;
/
        -- II: CAN TRUNG TAM
begin
sa_components.create_group
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'CANTRUNGTAM',
    short_name => 'CTT',
    group_num => 250,
    parent_name => 'S');
end;
/
        -- II: NGOAI THANH
begin
sa_components.create_group
    (policy_name => 'ACCESS_THONGBAO',
    long_name => 'NGOAITHANH',
    short_name => 'NT',
    group_num => 275,
    parent_name => 'S');
end;            
/

    -- TAO LABEL TAG
/*
    Dinh nghia cau truc LABEL TAG:
(+) Gom 5 ki tu so
(+) Kí tu dau: 1 - PUBLIC, 2 - SENSTIVE
(+) Kí tu 2: 1 2 3 - So luong COMPARTMENT có trong TAG
(+) Kí tu 3: Tong ID cua COMPARTMENT. ID lan luot là 1 2 3 doi voi NGT NT CS
(+) Kí tu 4: So luong GROUP
(+) Kí tu 5: Tong ID cua GROUP. ID lan luot là 1 2 3 4 doi voi S TT CTT NT
*/
begin
sa_label_admin.create_label
    (policy_name => 'ACCESS_THONGBAO',
    label_tag => 10000,
    label_value => 'P');
end;
/
begin
sa_label_admin.create_label
    (policy_name => 'ACCESS_THONGBAO',
    label_tag => 20000,
    label_value => 'S');
end;
/
begin
sa_label_admin.create_label
    (policy_name => 'ACCESS_THONGBAO',
    label_tag => 20012,
    label_value => 'S::TT');
end;
/
begin
sa_label_admin.create_label
    (policy_name => 'ACCESS_THONGBAO',
    label_tag => 20013,
    label_value => 'S::CTT');
end;
/
begin
sa_label_admin.create_label
    (policy_name => 'ACCESS_THONGBAO',
    label_tag => 20014,
    label_value => 'S::NT');
end;
/

begin
sa_label_admin.create_label
    (policy_name => 'ACCESS_THONGBAO',
    label_tag => 21300,
    label_value => 'S:CS:');
end;
/
----------------------------------------

begin
sa_policy_admin.apply_table_policy
    (policy_name => 'ACCESS_THONGBAO',
    schema_name => 'db',
    table_name => 'THONGBAO',
    table_options => 'NO_CONTROL');
end;
/
update db.THONGBAO set ols_col = char_to_label('ACCESS_THONGBAO', 'P');
update db.THONGBAO set ols_col = char_to_label('ACCESS_THONGBAO', 'S')
    where LVL = 'S';
update db.THONGBAO set ols_col = char_to_label('ACCESS_THONGBAO', 'S::TT')
    where LVL = 'S' and GR = 'TT';
update db.THONGBAO set ols_col = char_to_label('ACCESS_THONGBAO', 'S::CTT')
    where LVL = 'S' and GR = 'CTT';
update db.THONGBAO set ols_col = char_to_label('ACCESS_THONGBAO', 'S::NT')
    where LVL = 'S' and GR = 'NT';
update db.THONGBAO set ols_col = char_to_label('ACCESS_THONGBAO', 'S:CS:')
    where LVL = 'S' and CPRM = 'CS';
/
begin
sa_policy_admin.remove_table_policy
    (policy_name => 'ACCESS_THONGBAO',
    schema_name => 'db',
    table_name => 'THONGBAO');
end;
/
begin
sa_policy_admin.apply_table_policy
    (policy_name => 'ACCESS_THONGBAO',
    schema_name => 'db',
    table_name => 'THONGBAO',
    table_options => 'READ_CONTROL, WRITE_CONTROL, CHECK_CONTROL');
end;
/

create or replace procedure LABEL_TAGGING
is
begin
    for S in (select * from db.nhanvien where vaitro = 'Y si/bac si')
    loop
        sa_user_admin.set_user_labels('ACCESS_THONGBAO',S.MANV,'P');
        execute immediate 'grant select on db.thongbao to ' ||S.MANV;
    end loop;
end;
/
begin label_tagging; end;
/
----------------------------------------

-- OLS TESTING
drop user giamdoc cascade;
drop user giamdoc_tt cascade;
drop user giamdoc_ctt cascade;
drop user giamdoc_cs_nt cascade;
/
create user giamdoc identified by 1;
create user giamdoc_tt identified by 1;
create user giamdoc_ctt identified by 1;
create user giamdoc_cs_nt identified by 1;
/
grant create session to giamdoc;
grant create session to giamdoc_tt;
grant create session to giamdoc_ctt;
grant create session to giamdoc_cs_nt;
/
grant select, update on db.thongbao to giamdoc;
grant select on db.thongbao to giamdoc_tt;
grant select on db.thongbao to giamdoc_ctt;
grant select on db.thongbao to giamdoc_cs_nt;
/

begin 
    sa_user_admin.set_user_privs
    (policy_name => 'ACCESS_THONGBAO',
    user_name => 'GIAMDOC',
    PRIVILEGES => 'FULL');
    sa_user_admin.set_user_labels('ACCESS_THONGBAO','GIAMDOC_TT','S::TT'); 
    sa_user_admin.set_user_labels('ACCESS_THONGBAO','GIAMDOC_CTT','S::CTT'); 
    sa_user_admin.set_user_labels('ACCESS_THONGBAO','GIAMDOC_CS_NT','S:CS:NT'); 
end;
/
