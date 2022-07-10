Alter session set "_ORACLE_SCRIPT"=true; 

DROP USER DB CASCADE;
CREATE USER DB IDENTIFIED BY 1;
GRANT CREATE SESSION TO DB;
GRANT CREATE ANY TRIGGER TO DB
GRANT CREATE PROCEDURE TO DB;
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
    DIUNGTHUOC NVARCHAR2(300),
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
    CONSTRAINT PK_NHANVIEN PRIMARY KEY(MANV)
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
----------------------------------------

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
end;
/

-- ADDING DATA

    --- CSYT ---
insert into db.CSYT values ('CS1','TT','null','null');
insert into db.CSYT values ('CS2','CTT','null','null');
insert into db.CSYT values ('CS3','NT','null','null');
/
    --- NHANVIEN ---
insert into db.NHANVIEN values ('NV1','A','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Thanh tra','null');
insert into db.NHANVIEN values ('NV2','B','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Y si/bac si','null');
insert into db.NHANVIEN values ('NV3','C','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS2','Y si/bac si','null');
insert into db.NHANVIEN values ('NV4','D','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS3','Y si/bac si','null');
insert into db.NHANVIEN values ('NV5','E','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Nghien cuu','TK'); -- khoa than kinh
insert into db.NHANVIEN values ('NV6','F','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS2','Nghien cuu','TM'); -- khoa tim mach
insert into db.NHANVIEN values ('NV7','G','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS3','Nghien cuu','TH'); -- khoa tieu hoa
insert into db.NHANVIEN values ('NV8','K','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS1','Co so y te','null');
insert into db.NHANVIEN values ('NV9','L','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS2','Co so y te','null');
insert into db.NHANVIEN values ('NV10','M','null',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','CS3','Co so y te','null');
/
    --- BENHNHAN ---
insert into db.BENHNHAN values ('BN1','CS1','H','1',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','null','null','null','null');
insert into db.BENHNHAN values ('BN2','CS2','I','2',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','null','null','null','null');
insert into db.BENHNHAN values ('BN3','CS3','J','3',TO_DATE('1990/01/01', 'yyyy/mm/dd'),'null','null','null','null','null','null','null');
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

----------------------------------------

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
select * from db.nhanvien
-- TC4

create or replace procedure TC4
is
begin
    for S in (select * from db.NHANVIEN where VAITRO = 'Y si/bac si')
    loop
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA as select * from db.HSBA where MABS = '''||S.MANV||'''';
        execute immediate 'create or replace view db.'||S.MANV||'_HSBA_DV as select * from db.HSBA_DV where MAHSBA in (select MAHSBA from db.'||S.MANV||'_HSBA)';
        execute immediate 'create or replace view db.'||S.MANV||'_BN as select * from db.BENHNHAN where MABN in (select MABN from db.'||S.MANV||'_HSBA)';
        
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
    sec_relevant_cols => 'DIUNGTHUOC');
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
    sec_relevant_cols => 'CMND');
end;
/

