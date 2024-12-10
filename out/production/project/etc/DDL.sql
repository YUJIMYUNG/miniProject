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
/*
drop table comment;
drop table vote;
drop table board;
*/
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
vote_idx int unsigned auto_increment not null, # 투표번호
vote_content varchar(100), # 투표 내용
member_idx int unsigned, # 투표 작성자(참조키)
vote_deadline datetime, # 투표 마감날짜
vote_status boolean not null default true, # 투표 활성화 여부
foreign key(member_idx) references members(member_idx), # 게시물 테이블에서 참조
primary key(vote_idx) # 투표번호(기본키)
);
# drop table vote;
select * from vote;

# 투표 집계 테이블
create table if not exists votecount(
vote_idx int unsigned, # 투표번호(참조키)
vote_choice varchar(50), # 투표 선택지 내용
vote_count int, # 득표수
foreign key(vote_idx) references vote(vote_idx), # 투표 테이블에서 참조
primary key(vote_idx) # 투표번호(기본키)
);
# drop table votecount;
select * from votecount;