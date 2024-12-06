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
        System.out.println("투표 주제 작성 : ");
        int choose = scanner.nextInt();
        System.out.println("");
    }

    // 2. 투표 조회 함수

    // 3. 투표 마감 함수
} // VoteView ed
