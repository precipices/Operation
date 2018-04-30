-------------------------------------------------------���ݿ���Ϊoperation_system-----------------------------------------------------------

--worker��8�����ԣ�id,password,name,sex,birth,position,call,section
create table worker(
id varchar(20) primary key,
password varchar(20),
name nvarchar(20),
sex nchar(1),
birth date,
position nvarchar(20),
call varchar(20),
section nvarchar(20)
)
--patient��5�����ԣ�id,name,sex,birth,call
create table patient(
id varchar(20) primary key,
name nvarchar(20),
sex nchar(1),
birth date,
call varchar(20)
)
--operation��11������:id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord
create table operation(
id varchar(20) primary key,
name nvarchar(20),
beginTime date,
roomId varchar(20),
patientId varchar(20),
doctorId varchar(20),
nurseId varchar(20),
anesthetistId varchar(20),
doctorRecord varchar(20),
nurseRecord varchar(20),
anesthetistRecord varchar(20)
)
--room��1������:id
create table room(
id varchar(20) primary key
)

--���������
insert into room values('01') insert into room values('02')insert into room values('03') insert into room values('04')insert into room values('05')
insert into room values('06') insert into room values('07')insert into room values('08') insert into room values('09')insert into room values('10')
insert into room values('11') insert into room values('12')insert into room values('13') insert into room values('14')insert into room values('15')
insert into room values('16') insert into room values('17')insert into room values('18') insert into room values('19')insert into room values('20')

--�ڿƣ���ƣ������ƣ����ƣ��ۿƣ����Ǻ�ƣ���ǻ�ƣ�Ƥ���ƣ�����ƣ���Ⱦ��
--���ҽ��
insert into worker values('w0001', '123', '����', '��', '1980-1-1', 'ҽ��', '158-0000-0001','�ۿ�')
insert into worker values('w0002', '123', '����', 'Ů', '1982-2-2', 'ҽ��', '158-0000-0002','��ǻ��')
insert into worker values('w0003', '123', '����', '��', '1984-3-3', 'ҽ��', '158-0000-0003','�����')
insert into worker values('w0004', '123', '����', 'Ů', '1986-4-4', 'ҽ��', '158-0000-0004','����')
--��ӻ�ʿ
insert into worker values('w1001', '123', '����', 'Ů', '1997-1-1', '��ʿ', '158-0001-0001','�ۿ�')
insert into worker values('w1002', '123', '����', 'Ů', '1996-2-2', '��ʿ', '158-0001-0002','��ǻ��')
insert into worker values('w1003', '123', '����', 'Ů', '1995-3-3', '��ʿ', '158-0001-0003','�����')
insert into worker values('w1004', '123', '����', 'Ů', '1994-4-4', '��ʿ', '158-0001-0004','����')
--�������ʦ
insert into worker values('w2001', '123', '��һ', '��', '1988-1-1', '����ʦ', '158-0002-0001','�ۿ�')
insert into worker values('w2002', '123', '�Զ�', 'Ů', '1989-2-2', '����ʦ', '158-0002-0002','��ǻ��')
insert into worker values('w2003', '123', '����', '��', '1990-3-3', '����ʦ', '158-0002-0003','�����')
insert into worker values('w2004', '123', '����', 'Ů', '1991-4-4', '����ʦ', '158-0002-0004','����')
--��ӹ���Ա
insert into worker values('w0000', '123', 'Ǯĳĳ', '��', '1991-4-4', '����Ա', '158-0002-0004','�����')


--��Ӳ���
insert into patient values('p0001', '����', '��', '1990-1-11', '135-0000-0010')
insert into patient values('p0002', '����', 'Ů', '1991-1-12', '135-0000-0020')
insert into patient values('p0003', '����', '��', '1992-1-13', '135-0000-0030')
insert into patient values('p0004', '����', 'Ů', '1993-1-14', '135-0000-0040')


--�������
insert into operation values('o0001','����1','2018-1-1','10','p0001','w0001','w1001','w2001','ҽ����¼1','��ʿ��¼1','����ʦ��¼1')
insert into operation values('o0002','����2','2018-2-2','20','p0002','w0002','w1002','w2002','ҽ����¼2','��ʿ��¼2','����ʦ��¼2')
insert into operation values('o0003','����3','2018-3-3','11','p0003','w0003','w1003','w2003','ҽ����¼3','��ʿ��¼3','����ʦ��¼3')
insert into operation values('o0004','����4','2018-5-18','12','p0004','w0004','w1004','w2004',null,null,null)
insert into operation values('o0005','����5','2018-5-20','13','p0001','w0001','w1001','w2001',null,null,null)

----------------------------------------------------����Ϊ�������ݿ����Ӳ�������-----------------------------------------------------------------


select * from worker
select * from patient
select * from operation
select * from room


select * from worker where position = '��ʿ'
select * from worker where position = '����ʦ'
select * from worker where position = 'ҽ��'
--drop table worker
--drop table patient
--drop table operation
--drop table room

----ϵͳ�õ���sql���:
-------------------------------------------------------Worker-----------------------------------------------------------------------
--id��Ա��
select id,password,name,sex,birth,position,call,section from worker where id='w0001'
--��ѯĳ���пյ�Ա��
select id,password,name,sex,birth,position,call,section from worker where position = '��ʿ' and id not in (select nurseId from operation where beginTime = '2018-5-18')
select id,password,name,sex,birth,position,call,section from worker where position = '����ʦ' and id not in (select anesthetistId from operation where beginTime = '2018-5-18')
select id,password,name,sex,birth,position,call,section from worker where position = 'ҽ��' and id not in (select doctorId from operation where beginTime = '2018-5-18')
--����Ա��
insert into worker (id,password,name,sex,birth,position,call,section) values('w1005', '123', '����', 'Ů', '1995-5-5', '��ʿ', '158-0001-0005','����')
--ɾ��Ա��
delete from worker where id = 'w1005'
--------------------------------------------------------Patient-----------------------------------------------------------------
--��ѯ���в���
select id,name,sex,birth,call from patient
--��ѯ��������
select id,name,sex,birth,call from patient where id = 'p0001'
select id,name,sex,birth,call from patient where name = '����'
--���Ӳ���
insert into patient (id,name,sex,birth,call) values('p0005', '���', 'Ů', '1993-8-8', '135-0000-0050')
--ɾ������
delete from patient where id='p0005'
---------------------------------------------------------Operation----------------------------------------------------------------------------
--��ѯ��������
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation
--����id�鵥������
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where id='o0001'
--�������ڲ�����
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where beginTime='2018-3-3'
--��ѯ��ĳ����֮�������
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where beginTime>='2018-3-3'
--����������֮�������
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where beginTime between '2018-1-1' and '2018-3-3'
--����Ա��id������
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where doctorId = 'w0001' or nurseId='w0001' or anesthetistId='w0001'
--����Ա��id�����ڲ�����
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where (doctorId = 'w0001' or nurseId='w0001' or anesthetistId='w0001') and beginTime = '2018-1-1'
--����Ա��id��������֮�������
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where (doctorId = 'w0001' or nurseId='w0001' or anesthetistId='w0001') and beginTime between '2018-2-2' and '2018-7-7'
--���ݲ���id������
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where patientId = 'p0001'
--��������
insert into operation (id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord) 
values('o0006','����6','2018-5-20','14','p0001','w0001','w1001','w2001',null,null,null)
--ɾ������
delete from operation where id='o0006'
--����idΪ�������������¼
update operation set doctorRecord = '1232131' where id='o0006'
update operation set nurseRecord = '1232131' where id='o0006'
update operation set anesthetistRecord = '1232131' where id='o0006'
--����id�޸�����������Ա
update operation set doctorId = 'w0002' where id = 'o0006'
update operation set nurseId = 'w1002' where id = 'o0006'
update operation set anesthetistId = 'w2002' where id = 'o0006'
--����id�޸�������
update operation set roomId = '19' where id = 'o0006'
----------------------------------------------------------------------------------------------
--��ѯĳ���пյ�������
select id from room where id not in (select roomId from operation where beginTime = '2018-1-1')
--��ѯĳ�����ұ�ռ�õ�ʱ��
select beginTime from operation where roomId='10'


select * from worker
select * from patient
select * from operation


select * from worker where position = '��ʿ'
select * from worker where position = '����ʦ'
select * from worker where position = 'ҽ��'