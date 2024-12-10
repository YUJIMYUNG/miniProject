package model;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentDto {
    // 필드
    private int comment_idx; // 댓글번호
    private int member_idx; // 회원번호
    private String member_name; // 조회용 회원 이름
    private int board_idx; // 게시글 번호
    private String comment_content; // 댓글 내용
    private LocalDateTime comment_date; // 댓글 작성 날짜시간
    private int comment_update; // 댓글 수정여부
    private int comment_delete; // 댓글 삭제 여부

    // 생성자
    public CommentDto(int comment_idx, int member_idx, String member_name, int board_idx, String comment_content, LocalDateTime comment_date, int comment_update, int comment_delete) {
        this.comment_idx = comment_idx;
        this.member_idx = member_idx;
        this.member_name = member_name;
        this.board_idx = board_idx;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
        this.comment_update = comment_update;
        this.comment_delete = comment_delete;
    }

    //1.  조회 함수에서 필요한 생성자
    public CommentDto(int comment_idx, String member_name, String comment_content) {
        this.comment_idx = comment_idx;
        this.member_name = member_name;
        this.comment_content = comment_content;
    }

    //2. 댓글 등록 함수에서 필요한 생성Wk
    public CommentDto(int comment_idx, int board_idx, String comment_content) {
        this.comment_idx = comment_idx;
        this.board_idx = board_idx;
        this.comment_content = comment_content;
    }

    //3. 수정 함수에서 필요한 생성자
    public CommentDto(int comment_idx, String comment_content) {
        this.comment_idx = comment_idx;
        this.comment_content = comment_content;
    }

    // getter, setter
    public int getComment_idx() {
        return comment_idx;
    }

    public void setComment_idx(int comment_idx) {
        this.comment_idx = comment_idx;
    }

    public int getMember_idx() {
        return member_idx;
    }

    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }

    public int getBoard_idx() {
        return board_idx;
    }

    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public LocalDateTime getComment_date() {
        return comment_date;
    }

    public void setComment_date(LocalDateTime comment_date) {
        this.comment_date = comment_date;
    }

    public int getComment_update() {
        return comment_update;
    }

    public void setComment_update(int comment_update) {
        this.comment_update = comment_update;
    }

    public int getComment_delete() {
        return comment_delete;
    }

    public void setComment_delete(int comment_delete) {
        this.comment_delete = comment_delete;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    // toString 재정의
    @Override
    public String toString() {
        return "CommentDto{" +
                "comment_idx=" + comment_idx +
                ", member_idx=" + member_idx +
                ", member_name='" + member_name + '\'' +
                ", board_idx=" + board_idx +
                ", comment_content='" + comment_content + '\'' +
                ", comment_date=" + comment_date +
                ", comment_update=" + comment_update +
                ", comment_delete=" + comment_delete +
                '}';
    }//toString end
}// class end
