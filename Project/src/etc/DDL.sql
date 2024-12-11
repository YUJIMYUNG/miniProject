create database if not exists teamDB;
use teamDB;

#회원 테이블
create table if not exists member(
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
# drop table member;
select * from member;

# 게시판 테이블
create table if not exists board(
board_idx int unsigned auto_increment,
primary key(board_idx),
board_topic int,
board_status boolean default false,
board_version int default 0,
board_title varchar(100) not null,
board_content longText,
member_idx int unsigned,
foreign key(member_idx) references member(member_idx),
board_date datetime,
board_update datetime,
in_active boolean default true
);

drop table comment;
drop table vote;
drop table board;
delete from board where board_idx=5;

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
vote_choose int, # 선택지를 구분하는 인덱스
board_idx int unsigned,
member_idx int unsigned,
vote_type int not null, # 찬반여부만 받는게 아닌 다수결 투표 형식으로 받을 수 있도록 int 값으로 변경
vote_content varchar
foreign key(board_idx) references board(board_idx),
foreign key(member_idx) references members(member_idx),
unique (board_idx,member_idx),
primary key(vote_idx)
);
# drop table vote;
select * from vote;