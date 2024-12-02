package model;

public class MemberDao {
    private static MemberDao projectManagerDao = new MemberDao();
    private MemberDao(){};
    public static MemberDao getInstance(){return projectManagerDao;}
}
