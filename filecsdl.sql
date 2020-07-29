create database atmdb;
use atmdb;
create table user(
	account_number varchar(50) primary key,
    pass varchar(50),
    name varchar(50),
    sdt varchar(50),
    sodu int,
    status varchar(50)
);
create table history(
	account_number varchar(50),
    history_msg varchar(100),
    FOREIGN KEY (account_number) REFERENCES user(account_number)
);
use atmdb;
insert into atmdb.user values("A","A","A","A",10000000,"Mở");

insert into atmdb.user values("B","B","B","B",1000000000);
use atmdb;
update user set status="Khóa" where account_number='A';
update user set sodu=100000000 where account_number='B';


/**/

create database atmdb_1;
use atmdb_1;
create table user(
	account_number varchar(50) primary key,
    pass varchar(50),
    name varchar(50),
    sdt varchar(50),
    sodu int
);
create table history(
	account_number varchar(50),
    history_msg varchar(100),
    FOREIGN KEY (account_number) REFERENCES user(account_number)
);

use atmdb_1;
create table receipt(
	account_number varchar(50),
    filename varchar(100),
    location varchar(100),
    FOREIGN KEY (account_number) REFERENCES user(account_number)
);
