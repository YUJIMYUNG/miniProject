package controller;

public class CommentController {
    private static CommentController commentController = new CommentController();
    private CommentController(){}
    public static CommentController getInstance(){return commentController;}

    // 1204 유지명 깃 병합 충돌 없이 잘 돼라!
} //class end
