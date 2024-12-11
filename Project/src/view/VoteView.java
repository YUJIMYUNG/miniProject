package view;

import controller.VoteController;
import model.BoardDto;
import model.MemberDto;
import model.VoteDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class VoteView {

    private static VoteView voteView = new VoteView();
    private VoteView(){}
    public static VoteView getInstance() {
        return voteView;
    } // 싱글턴 ed

    Scanner scanner = new Scanner(System.in); // 스캐너 입력 객체

    /// 1. 투표 생성 함수
    public void VoteWrite() {

        // 투표 내용 입력
        System.out.print("투표 내용 입력 : ");
        String vote_content = scanner.nextLine();

        // 투표 마감 날짜 입력
        System.out.print("투표 마감 날짜 입력(YYYY-MM-DD HH:mm) : ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deadLine = LocalDateTime.parse(input, formatter);

        // 투표 선택지 입력
        ArrayList<String> votedtos_choices = new ArrayList<>(); // 선택지 저장 객체 생성
        while (true) {
            System.out.print("1.선택지 입력 2.선택지 입력 종료 : "); // 선택지 입력을 받는 함수
            int choose = scanner.nextInt();
            if (choose == 1) {
                System.out.print("선택지 내용 입력 : ");
                String content = scanner.next();
                votedtos_choices.add(content);
                // 선택지를 받아 리스트에 저장
                System.out.println("선택지 입력 완료");
            } else if (choose == 2) {
                System.out.println("선택지 입력 종료");
                break;
            } else {
                System.out.println("유효하지 않은 입력입니다.");
            }
        } // while ed
            for (int i = 0; i < votedtos_choices.size(); i++) {
                System.out.println(votedtos_choices.get(i));
            }
        // 투표 내용과 선택지를 저장한 객체를 컨트롤러로 보내고 결과 수신
        boolean result = VoteController.getInstance().VoteWrite(vote_content,deadLine,votedtos_choices);
        if (result) {
            System.out.println("투표 작성 성공");
        } else {
            System.out.println("투표 작성 실패");
        } // if ed
    } // VoteWrite ed

    // 2. 투표 조회 함수



} // VoteView ed

/// 예제 확인용
        /*
        LocalDateTime dt=LocalDateTime.of(2024,1,1,1,1);
        String dtFormat1=dt.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
        System.out.println(dt);

        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue());
        */