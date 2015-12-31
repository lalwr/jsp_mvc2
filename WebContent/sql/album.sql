create table album(
num int NOT NULL auto_increment primary key ,
writer VARCHAR(20) not null,
subject VARCHAR(100) not null,
email VARCHAR(40),
content VARCHAR(200) NOT NULL,
PASSWD VARCHAR(10) NOT NULL,
REG_DATE TIMESTAMP NOT NULL,
READCOUNT INT(4) DEFAULT 0,
IP VARCHAR(20) NOT NULL,
IMAGE VARCHAR(40)
);