package view;

import controller.BoardController;

public class BoardView {
    // 싱글톤
    public static BoardView boardView=new BoardView();
    BoardView(){}
    public static BoardView getInstance() {return boardView;}

    void mainBoard(){

    } // func end
} // class end
