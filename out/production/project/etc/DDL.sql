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
comment_date dateTime default current_timestamp,
comment_update boolean default true,
comment_delete boolean default true,
foreign key(member_idx) references member(member_idx),
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
foreign key(member_idx) references member(member_idx), # 게시물 테이블에서 참조
primary key(vote_idx) # 투표번호(기본키)
);
# drop table vote;
select * from vote;

# 투표 집계 테이블 # 12/11 수정. vote_num 추가.
create table if not exists votecount(
vote_num int unsigned auto_increment not null, # 투표집계번호
vote_idx int unsigned, # 투표번호
vote_choice varchar(50), # 투표 선택지 내용
vote_count int, # 득표수
foreign key(vote_idx) references vote(vote_idx), # 투표 테이블에서 참조
primary key(vote_num) # 투표집계번호(기본키)
);
# drop table votecount;
select * from votecount;

# 샘플 데이터

INSERT INTO member (member_name, member_email, member_pwd, birthdate, member_phone, department)
VALUES
('김철수', 'kim@example.com', 'pass123', '1990-05-15', '010-1234-5678', '개발팀'),
('이영희', 'lee@example.com', 'pass456', '1988-03-22', '010-2345-6789', '인사팀'),
('박지민', 'park@example.com', 'pass789', '1995-11-30', '010-3456-7890', '마케팅팀'),
('정민수', 'jung@example.com', 'pass321', '1992-07-25', '010-4567-8901', '개발팀'),
('홍길동', 'hong@example.com', 'pass654', '1985-12-10', '010-5678-9012', '기획팀');

INSERT INTO board (board_topic, board_title, board_content, member_idx, board_date, board_update)
VALUES
(1, '12월 팀 회의 안내', '12월 정기 팀 회의를 안내드립니다.', 1, NOW(), NOW()),
(2, '연말 결산 보고서', '2024년 연말 결산 보고서입니다.', 2, NOW(), NOW()),
(1, '신규 프로젝트 계획', '신규 프로젝트 계획을 공유드립니다.', 3, NOW(), NOW()),
(3, '사내 동호회 모집', '사내 동호회 신규 회원을 모집합니다.', 4, NOW(), NOW()),
(2, '연말 회식 일정', '부서별 연말 회식 일정 안내입니다.', 5, NOW(), NOW());

INSERT INTO comment (member_idx, board_idx, comment_content)
VALUES
(2, 1, '회의 참석하도록 하겠습니다.'),
(3, 1, '일정 확인했습니다.'),
(4, 2, '보고서 검토 완료했습니다.'),
(1, 3, '프로젝트 일정 조율이 필요해 보입니다.'),
(5, 4, '동호회 가입 신청합니다.');

INSERT INTO comment (member_idx, board_idx, comment_content)
VALUES
(1, 1, 'test용 댓글입니다. 수정이 필욜합니다.');

INSERT INTO vote (vote_content, member_idx, vote_deadline, vote_status)
VALUES
('연말 회식 날짜 투표', 1, '2024-12-20 18:00:00', true),
('신규 프로젝트 우선순위 결정', 2, '2024-12-15 17:00:00', true),
('사내 동호회 시간 선호도 조사', 3, '2024-12-25 12:00:00', true),
('팀 워크숍 장소 선정', 4, '2024-12-18 15:00:00', true),
('신년회 메뉴 선정', 5, '2024-12-30 18:00:00', true);