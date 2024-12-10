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
            String sql = "insert into vote(vote_content,vote_deadline) values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,voteDto.getVote_content());

        } catch (SQLException e) {
            e.getMessage();
        }
        boolean result = true;
        return result;
    }

    /// 2.투표 조회 및 투표 함수

} // VoteDao ed
