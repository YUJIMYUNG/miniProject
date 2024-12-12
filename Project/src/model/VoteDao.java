package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VoteDao extends Dao{

    private static VoteDao voteDao = new VoteDao();
    private VoteDao() {}
    public static VoteDao getInstance() {
        return voteDao;
    } // 싱글턴 ed

    /// 1.투표 작성 함수
    public boolean VoteWrite(VoteDto voteDto) {
        try {
            String sql = "insert into vote(vote_content,vote_deadline) values(?,?)"; // SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); // SQL 기재 준비 및 ps 에 자동생성된 키 반환
            ps.setString(1,voteDto.getVote_content());
            ps.setTimestamp(2,java.sql.Timestamp.valueOf(voteDto.getVote_deadline()));
            ps.executeUpdate(); // SQL 실행

            // 생성된 vote_idx 가져오기
            ResultSet rs = ps.getGeneratedKeys();
            int voteIdx = 0;
            if (rs.next()) {
                voteIdx = rs.getInt(1); // 자동 생성된 키 값
            }

            String sql2 = "insert into votecount(vote_idx, vote_choice, vote_count) values(?, ?, 0)"; // SQL2 작성
            PreparedStatement ps2 = conn.prepareStatement(sql2); // SQL2 기재 준비
            for (String choice : voteDto.getVote_choice()) { // vote_choice에 저장된 ArrayList 의 수만큼 DB에 저장
                ps2.setInt(1, voteIdx);
                ps2.setString(2,choice);
            ps2.executeUpdate(); // SQL 실행
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } // VoteWrite ed

    /// 2.투표 조회 및 투표 함수
    //public ArrayList<VoteDto> VotePage(int board_idx) {}
} // VoteDao ed
