package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class VoteDto {

    // 투표 테이블 필드
    private int vote_idx;
    private String vote_content;
    private int member_idx;
    private LocalDateTime vote_deadline;
    private boolean vote_status;

    // 집계 테이블 필드
    private ArrayList<String> vote_choice;
    private int vote_count;

    // 생성자
    public VoteDto(int vote_idx, String vote_content, int member_idx, LocalDateTime vote_deadline, boolean vote_status, int vote_num, String[] vote_choice, int vote_count) {
        this.vote_idx = vote_idx;
        this.vote_content = vote_content;
        this.member_idx = member_idx;
        this.vote_deadline = vote_deadline;
        this.vote_status = vote_status;
        this.vote_num = vote_num;
        this.vote_choice = vote_choice;
        this.vote_count = vote_count;
    }

    // 투표 생성에 필요한 생성자
    public VoteDto(String vote_content, ArrayList vote_choice) {
        this.vote_content = vote_content;

    }

    //선택지 생성에 필요한 생성자


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

    public int getMember_idx() {
        return member_idx;
    }

    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
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

    public int getVote_num() {
        return vote_num;
    }

    public void setVote_num(int vote_num) {
        this.vote_num = vote_num;
    }

    public String[] getVote_choice() {
        return vote_choice;
    }

    public void setVote_choice(String[] vote_choice) {
        this.vote_choice = vote_choice;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    // toString
    @Override
    public String toString() {
        return "New_VoteDto{" +
                "vote_idx=" + vote_idx +
                ", vote_content='" + vote_content + '\'' +
                ", member_idx=" + member_idx +
                ", vote_deadline=" + vote_deadline +
                ", vote_status=" + vote_status +
                ", vote_num=" + vote_num +
                ", vote_choice='" + vote_choice + '\'' +
                ", vote_count=" + vote_count +
                '}';
    }
} // New_voteDto ed
