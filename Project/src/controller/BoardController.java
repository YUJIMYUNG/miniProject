package controller;

public class BoardController {
    // 싱글톤
    public static BoardController boardController=new BoardController();
    BoardController(){}
    public static BoardController getInstance() {return boardController;}

} // class end
