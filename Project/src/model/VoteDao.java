package model;

public class VoteDao extends Dao{

    private static VoteDao voteDao = new VoteDao();
    private VoteDao() {}
    public static VoteDao getInstance() {
        return voteDao;
    } // 싱글턴 ed

    // 1.투표 작성 함수
    public boolean VoteWrite(VoteDto voteDto) {
        try {
            String sql = "insert into vote(vote_content"
        }
        boolean result = true;
        return result;
    }

} // VoteDao ed
