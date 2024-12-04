package view;

import controller.BoardController;
import model.BoardDto;

import java.util.ArrayList;

public class BoardView {
    // 싱글톤
    public static BoardView boardView=new BoardView();
    BoardView(){}
    public static BoardView getInstance() {return boardView;}

    void mainBoard(){

        while(true){

        }

    } // main end

    /*
    void boardPrint(){
        ArrayList<BoardDto> list = BoardController.getInstance().boardPrint();
    }
    */

} // class end
