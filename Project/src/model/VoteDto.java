package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class VoteDto {

    // 투표 테이블 필드
    private int vote_idx; // 투표 번호
    private String vote_content; // 투표 내용
    private int board_idx; // 투표 게시물 번호
    private int member_idx; // 투표 작성자 번호
    private String member_name; // 조회용 투표 작성자 이름
    private LocalDateTime vote_deadline; // 투표 마감일자
    private boolean vote_status; // 투표 활성화 여부

    // 집계 테이블 필드
    private ArrayList<String> vote_choice; // 투표 선택지
    private String choice; // 조회용 투표 선택지
    private int vote_count; // 투표 득표수

    // 투표 생성에 필요한 생성자
    public VoteDto(String vote_content, int board_idx, int member_idx, LocalDateTime vote_deadline, ArrayList<String> vote_choice) {
        this.vote_content = vote_content;
        this.board_idx = board_idx;
        this.member_idx = member_idx;
        this.vote_deadline = vote_deadline;
        this.vote_choice = vote_choice;
    }

    // 투표 조회에 필요한 생성자


    public VoteDto(String vote_content, int board_idx, String member_name, LocalDateTime vote_deadline, String choice, int vote_count) {
        this.vote_content = vote_content;
        this.board_idx = board_idx;
        this.member_name = member_name;
        this.vote_deadline = vote_deadline;
        this.choice = choice;
        this.vote_count = vote_count;
    }

    // 생성자
    public VoteDto(int vote_idx, String vote_content, int board_idx, int member_idx, LocalDateTime vote_deadline, boolean vote_status, ArrayList<String> vote_choice, int vote_count) {
        this.vote_idx = vote_idx;
        this.vote_content = vote_content;
        this.board_idx = board_idx;
        this.member_idx = member_idx;
        this.vote_deadline = vote_deadline;
        this.vote_status = vote_status;
        this.vote_choice = vote_choice;
        this.vote_count = vote_count;
    }

    // getter and setter
    public int getVote_idx() {
        return vote_idx;
    }

    public void setVote_idx(int vote_idx) {
        this.vote_idx = vote_idx;
    }

    public String getVote_content() {
        return vote_content;
    }

    public void setVote_content(String vote_content) {
        this.vote_content = vote_content;
    }

    public int getBoard_idx() {
        return board_idx;
    }

    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }

    public int getMember_idx() {
        return member_idx;
    }

    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public LocalDateTime getVote_deadline() {
        return vote_deadline;
    }

    public void setVote_deadline(LocalDateTime vote_deadline) {
        this.vote_deadline = vote_deadline;
    }

    public boolean isVote_status() {
        return vote_status;
    }

    public void setVote_status(boolean vote_status) {
        this.vote_status = vote_status;
    }

    public ArrayList<String> getVote_choice() {
        return vote_choice;
    }

    public void setVote_choice(ArrayList<String> vote_choice) {
        this.vote_choice = vote_choice;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public String toString() {
        return "VoteDto{" +
                "vote_idx=" + vote_idx +
                ", vote_content='" + vote_content + '\'' +
                ", board_idx=" + board_idx +
                ", member_idx=" + member_idx +
                ", member_name='" + member_name + '\'' +
                ", vote_deadline=" + vote_deadline +
                ", vote_status=" + vote_status +
                ", vote_choice=" + vote_choice +
                ", choice='" + choice + '\'' +
                ", vote_count=" + vote_count +
                '}';
    }
} // New_voteDto ed
