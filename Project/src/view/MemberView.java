package view;

public class MemberView {
    private static MemberView memberView = new MemberView();
    private MemberView(){};
    public static MemberView getInstance(){return memberView;};
}
