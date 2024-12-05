package controller;

public class BoardController {
    // 싱글톤
    public static BoardController boardController=new BoardController();
    BoardController(){}
    public static BoardController getInstance() {return boardController;}

    // 병합 테스트
} // class end
