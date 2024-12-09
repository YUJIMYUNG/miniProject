package view;

import controller.VoteController;
import model.BoardDto;
import model.MemberDto;

import java.util.ArrayList;
import java.util.Scanner;

public class VoteView {

    private static VoteView voteView = new VoteView();
    private VoteView(){}
    public static VoteView getInstance() {
        return voteView;
    } // 싱글턴 ed

    Scanner scanner = new Scanner(System.in); // 스캐너 입력 객체

    // 1. 투표 생성 함수
    public void VoteWrite() {
        System.out.println("투표 내용 작성 : ");
        String content = scanner.nextLine();
        scanner.nextLine();

        System.out.println("선택지 수 입력 : ");
        int vote_num = scanner.nextInt();
        scanner.nextLine();

        String[] vote_count = new String[vote_num];
        for (int i = 0; i < vote_num; i++) {
            String[] vote_count1 = new String[i];
            System.out.printf("%d번째 선택지 입력",(i+1));
            vote_count1[i] = scanner.nextLine();
            scanner.nextLine();
            vote_count[i] = vote_count1[i];
        } // for ed

        boolean result = VoteController.getInstance().VoteWrite(content,vote_num,vote_count);
        if (result) {
            System.out.println("투표 작성 성공");
        } else {
            System.out.println("투표 작성 실패");
        } // if ed
    } // VoteWrite ed

    // 2. 투표 조회 함수



} // VoteView ed
