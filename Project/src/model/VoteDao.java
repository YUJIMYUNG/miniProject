package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            PreparedStatement ps = conn.prepareStatement(sql); // SQL 기재 준비
            ps.setString(1,voteDto.getVote_content());
            ps.setTimestamp(2,java.sql.Timestamp.valueOf(voteDto.getVote_deadline()));
            ps.executeUpdate(); // SQL 실행

            String sql2 = "insert into votecount(vote_choice) values(?)"; // SQL2 작성
            PreparedStatement ps2 = conn.prepareStatement(sql2); // SQL2 기재 준비
            for (int i = 0; i < voteDto.getVote_choice().size(); i++) { // vote_choice에 저장된 ArrayList 의 수만큼 DB에 저장
            ps2.setString(1,voteDto.getVote_choice().get(i));
            ps.executeUpdate(); // SQL 실행
            }
            return true;
        } catch (SQLException e) {
            e.getMessage();
        }
        return false;
    }

    /// 2.투표 조회 및 투표 함수

} // VoteDao ed
