package controller;

public class CommentController {
    private static CommentController commentController = new CommentController();
    private CommentController(){}
    public static CommentController getInstance(){return commentController;}

} //class end
