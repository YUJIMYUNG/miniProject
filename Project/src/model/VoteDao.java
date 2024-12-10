package model;

public class VoteDao extends Dao{

    private static VoteDao voteDao = new VoteDao();
    private VoteDao() {}
    public static VoteDao getInstance() {
        return voteDao;
    } // 싱글턴 ed

    // 1.투표 작성 함수
    public boolean VoteWrite(New_VoteDto voteDto) {
        
        boolean result = true;
        return result;
    }

} // VoteDao ed
