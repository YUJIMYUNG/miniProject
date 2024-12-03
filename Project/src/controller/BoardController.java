package controller;

public class BoardController {
    public static BoardController boardController=new BoardController();
    BoardController(){}

    public static BoardController getInstance() {
        return boardController;
    }
}
