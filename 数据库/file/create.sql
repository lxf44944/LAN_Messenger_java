create table T_user(
	id char(5) primary key,
	name varchar2(20),
	password varchar2(16) default '123456',
	sex varchar2(3) ,
	age number(3),
	address varchar2(60),
	regdate date default sysdate,
	isonline varchar2(4) default '离线'
);
comment on table T_user is '用户信息';
comment on column T_user.id is '编号（QQ号）';
comment on column T_user.name is '真实姓名';
comment on column T_user.password is '密码';
comment on column T_user.sex is '性别';
comment on column T_user.age is '年龄';
comment on column T_user.address is '地址';
comment on column T_user.regdate is '注册时间';
comment on column T_user.isonline is '在线状态';

