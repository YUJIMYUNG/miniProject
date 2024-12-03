package model;

public class VoteDto {
    private int vote_idx;  // 투표번호
    private int board_idx; // 게시판번호
    private int member_idx; // 회원번호
    private boolean vote_type; // 찬반 여부

    // 생성자
    public VoteDto(int vote_idx, int board_idx, int member_idx, boolean vote_type) {
        this.vote_idx = vote_idx;
        this.board_idx = board_idx;
        this.member_idx = member_idx;
        this.vote_type = vote_type;
    }

    // getter and setter

    public int getVote_idx() {
        return vote_idx;
    }

    public void setVote_idx(int vote_idx) {
        this.vote_idx = vote_idx;
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

    public boolean isVote_type() {
        return vote_type;
    }

    public void setVote_type(boolean vote_type) {
        this.vote_type = vote_type;
    }

    // toString
    @Override
    public String toString() {
        return "VoteDto{" +
                "vote_idx=" + vote_idx +
                ", board_idx=" + board_idx +
                ", member_idx=" + member_idx +
                ", vote_type=" + vote_type +
                '}';
    }
}
