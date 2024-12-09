package view;

import controller.VoteController;
import model.BoardDto;
import model.MemberDto;
import model.VoteDto;

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
        System.out.println("투표 내용 작성 : "); // 투표 내용 입력
        String vote_content = scanner.nextLine();
        scanner.nextLine();

        ArrayList<String> votedtos = new ArrayList<>(); // 선택지 저장 객체 생성
        while (true) {
            System.out.print("1.선택지 입력 2.선택지 입력 종료 : "); // 선택지 입력을 받는 함수
            int choose = scanner.nextInt();
            if (choose == 1) {
                System.out.print("선택지 내용 입력 : ");
                String content = scanner.next();
                votedtos.add(content);
                System.out.println("선택지 입력 완료");
            } else if (choose == 2) {
                break;
            } else {
                System.out.println("유효하지 않은 입력입니다.");
            }
        } // while ed

        boolean result = VoteController.getInstance().VoteWrite(vote_content,votedtos);
        if (result) {
            System.out.println("투표 작성 성공");
        } else {
            System.out.println("투표 작성 실패");
        } // if ed
    } // VoteWrite ed

    // 2. 투표 조회 함수



} // VoteView ed
