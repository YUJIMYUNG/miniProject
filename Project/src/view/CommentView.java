package view;

public class CommentView {
    private static CommentView commentView = new CommentView();
    private CommentView(){}
    public static CommentView getInstance(){return commentView;}
}// class end
