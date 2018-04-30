-------------------------------------------------------数据库名为operation_system-----------------------------------------------------------

--worker表8个属性：id,password,name,sex,birth,position,call,section
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
--patient表5个属性：id,name,sex,birth,call
create table patient(
id varchar(20) primary key,
name nvarchar(20),
sex nchar(1),
birth date,
call varchar(20)
)
--operation表11个属性:id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord
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
--room表1个属性:id
create table room(
id varchar(20) primary key
)

--添加手术室
insert into room values('01') insert into room values('02')insert into room values('03') insert into room values('04')insert into room values('05')
insert into room values('06') insert into room values('07')insert into room values('08') insert into room values('09')insert into room values('10')
insert into room values('11') insert into room values('12')insert into room values('13') insert into room values('14')insert into room values('15')
insert into room values('16') insert into room values('17')insert into room values('18') insert into room values('19')insert into room values('20')

--内科，外科，妇产科，儿科，眼科，耳鼻喉科，口腔科，皮肤科，精神科，传染科
--添加医生
insert into worker values('w0001', '123', '张三', '男', '1980-1-1', '医生', '158-0000-0001','眼科')
insert into worker values('w0002', '123', '张四', '女', '1982-2-2', '医生', '158-0000-0002','口腔科')
insert into worker values('w0003', '123', '张五', '男', '1984-3-3', '医生', '158-0000-0003','精神科')
insert into worker values('w0004', '123', '张六', '女', '1986-4-4', '医生', '158-0000-0004','儿科')
--添加护士
insert into worker values('w1001', '123', '王五', '女', '1997-1-1', '护士', '158-0001-0001','眼科')
insert into worker values('w1002', '123', '王六', '女', '1996-2-2', '护士', '158-0001-0002','口腔科')
insert into worker values('w1003', '123', '王七', '女', '1995-3-3', '护士', '158-0001-0003','精神科')
insert into worker values('w1004', '123', '王八', '女', '1994-4-4', '护士', '158-0001-0004','儿科')
--添加麻醉师
insert into worker values('w2001', '123', '赵一', '男', '1988-1-1', '麻醉师', '158-0002-0001','眼科')
insert into worker values('w2002', '123', '赵二', '女', '1989-2-2', '麻醉师', '158-0002-0002','口腔科')
insert into worker values('w2003', '123', '赵三', '男', '1990-3-3', '麻醉师', '158-0002-0003','精神科')
insert into worker values('w2004', '123', '赵四', '女', '1991-4-4', '麻醉师', '158-0002-0004','儿科')
--添加管理员
insert into worker values('w0000', '123', '钱某某', '男', '1991-4-4', '管理员', '158-0002-0004','精神科')


--添加病人
insert into patient values('p0001', '李四', '男', '1990-1-11', '135-0000-0010')
insert into patient values('p0002', '李五', '女', '1991-1-12', '135-0000-0020')
insert into patient values('p0003', '李六', '男', '1992-1-13', '135-0000-0030')
insert into patient values('p0004', '李七', '女', '1993-1-14', '135-0000-0040')


--添加手术
insert into operation values('o0001','手术1','2018-1-1','10','p0001','w0001','w1001','w2001','医生记录1','护士记录1','麻醉师记录1')
insert into operation values('o0002','手术2','2018-2-2','20','p0002','w0002','w1002','w2002','医生记录2','护士记录2','麻醉师记录2')
insert into operation values('o0003','手术3','2018-3-3','11','p0003','w0003','w1003','w2003','医生记录3','护士记录3','麻醉师记录3')
insert into operation values('o0004','手术4','2018-5-18','12','p0004','w0004','w1004','w2004',null,null,null)
insert into operation values('o0005','手术5','2018-5-20','13','p0001','w0001','w1001','w2001',null,null,null)

----------------------------------------------------以上为创建数据库和添加测试数据-----------------------------------------------------------------


select * from worker
select * from patient
select * from operation
select * from room


select * from worker where position = '护士'
select * from worker where position = '麻醉师'
select * from worker where position = '医生'
--drop table worker
--drop table patient
--drop table operation
--drop table room

----系统用到的sql语句:
-------------------------------------------------------Worker-----------------------------------------------------------------------
--id查员工
select id,password,name,sex,birth,position,call,section from worker where id='w0001'
--查询某天有空的员工
select id,password,name,sex,birth,position,call,section from worker where position = '护士' and id not in (select nurseId from operation where beginTime = '2018-5-18')
select id,password,name,sex,birth,position,call,section from worker where position = '麻醉师' and id not in (select anesthetistId from operation where beginTime = '2018-5-18')
select id,password,name,sex,birth,position,call,section from worker where position = '医生' and id not in (select doctorId from operation where beginTime = '2018-5-18')
--增加员工
insert into worker (id,password,name,sex,birth,position,call,section) values('w1005', '123', '王九', '女', '1995-5-5', '护士', '158-0001-0005','儿科')
--删除员工
delete from worker where id = 'w1005'
--------------------------------------------------------Patient-----------------------------------------------------------------
--查询所有病人
select id,name,sex,birth,call from patient
--查询单个病人
select id,name,sex,birth,call from patient where id = 'p0001'
select id,name,sex,birth,call from patient where name = '李四'
--增加病人
insert into patient (id,name,sex,birth,call) values('p0005', '李八', '女', '1993-8-8', '135-0000-0050')
--删除病人
delete from patient where id='p0005'
---------------------------------------------------------Operation----------------------------------------------------------------------------
--查询所有手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation
--根据id查单个手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where id='o0001'
--根据日期查手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where beginTime='2018-3-3'
--查询自某日期之后的手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where beginTime>='2018-3-3'
--查两个日期之间的手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where beginTime between '2018-1-1' and '2018-3-3'
--根据员工id查手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where doctorId = 'w0001' or nurseId='w0001' or anesthetistId='w0001'
--根据员工id和日期查手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where (doctorId = 'w0001' or nurseId='w0001' or anesthetistId='w0001') and beginTime = '2018-1-1'
--根据员工id查两日期之间的手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where (doctorId = 'w0001' or nurseId='w0001' or anesthetistId='w0001') and beginTime between '2018-2-2' and '2018-7-7'
--根据病人id查手术
select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord 
from operation 
where patientId = 'p0001'
--新增手术
insert into operation (id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord) 
values('o0006','手术6','2018-5-20','14','p0001','w0001','w1001','w2001',null,null,null)
--删除手术
delete from operation where id='o0006'
--根据id为手术添加手术记录
update operation set doctorRecord = '1232131' where id='o0006'
update operation set nurseRecord = '1232131' where id='o0006'
update operation set anesthetistRecord = '1232131' where id='o0006'
--根据id修改手术参与人员
update operation set doctorId = 'w0002' where id = 'o0006'
update operation set nurseId = 'w1002' where id = 'o0006'
update operation set anesthetistId = 'w2002' where id = 'o0006'
--根据id修改手术室
update operation set roomId = '19' where id = 'o0006'
----------------------------------------------------------------------------------------------
--查询某天有空的手术室
select id from room where id not in (select roomId from operation where beginTime = '2018-1-1')
--查询某手术室被占用的时间
select beginTime from operation where roomId='10'


select * from worker
select * from patient
select * from operation


select * from worker where position = '护士'
select * from worker where position = '麻醉师'
select * from worker where position = '医生'