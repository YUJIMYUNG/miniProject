package model;

public class BoardDao {
    public static BoardDao boardDao=new BoardDao();
    BoardDao(){}

    public static BoardDao getInstance() {
        return boardDao;
    }
}
