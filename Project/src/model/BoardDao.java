package model;

public class BoardDao {
    // 싱글톤
    public static BoardDao boardDao=new BoardDao();
    BoardDao(){}
    public static BoardDao getInstance() {return boardDao;}

} // class end
