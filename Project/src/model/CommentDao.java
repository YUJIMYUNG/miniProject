package model;

public class CommentDao extends Dao{
    private static CommentDao commentDao = new CommentDao();
    private CommentDao(){}
    public static CommentDao getInstance(){return commentDao;}
}// class end
