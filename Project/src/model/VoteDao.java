package model;

public class VoteDao extends Dao{

    private static VoteDao voteDao = new VoteDao();
    private VoteDao() {}
    public static VoteDao getInstance() {
        return voteDao;
    } // 싱글턴 ed

} // VoteDao ed
