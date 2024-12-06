create database if not exists teamDB;
use teamDB;

#회원 테이블
create table if not exists members(
member_idx int unsigned auto_increment,
member_name varchar(20) not null,
member_email varchar(100) not null unique,
member_pwd varchar(20),
birthdate date,
member_phone varchar(15),
member_date datetime default current_timestamp,
department varchar(20),
in_active boolean default true,
primary key(member_idx)
);
# drop table members;
select * from members;

# 게시판 테이블
create table if not exists board(
board_idx int unsigned auto_increment,
board_topic int,
board_status boolean default false,
board_version int default 0,
board_title varchar(100) not null,
board_content longText,
member_idx int unsigned,
foreign key(member_idx) references members(member_idx),
board_date datetime,
board_update datetime,
primary key(board_idx)
);
# drop table board;
select * from board;

# 댓글 테이블
create table if not exists comment(
comment_idx int unsigned auto_increment,
member_idx int unsigned,
board_idx int unsigned,
comment_content longText,
comment_date dateTime,
comment_update boolean default true,
comment_delete boolean default true,
foreign key(member_idx) references members(member_idx),
foreign key(board_idx) references board(board_idx),
primary key(comment_idx)
);
# drop table comment;
select * from comment;

# 투표 테이블
create table if not exists vote(
vote_idx int unsigned auto_increment not null,
board_idx int unsigned,
member_idx int unsigned,
vote_act boolean, # 투표 활성화 여부. true일 시 투표 가능, false일 시 투표 마감.
vote_type boolean,
foreign key(board_idx) references board(board_idx),
foreign key(member_idx) references members(member_idx),
unique (board_idx,member_idx),
primary key(vote_idx)
);
# drop table vote;
select * from vote;