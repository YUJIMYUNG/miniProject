package view;

import java.util.Scanner;

public class VoteView {

    private static VoteView voteView = new VoteView();
    private VoteView(){}
    public static VoteView getInstance() {
        return voteView;
    } // 싱글턴 ed

    Scanner scanner = new Scanner(System.in); // 스캐너 입력 객체

    // 투표

    // 1. 투표 작성 함수
    public void VoteFunction() {
     // BoardView.getInstance().boardPrint();
        System.out.println("투표할 게시물 선택 : ");
        int choose = scanner.nextInt();
    }   /// 현재 VoteView 클래스 작업 중...

    //
} // VoteView ed
