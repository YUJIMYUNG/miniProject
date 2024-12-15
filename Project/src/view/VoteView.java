package view;

import controller.VoteController;
import controller.MemberController;
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
    public void VoteWrite(int board_idx) {

        // 투표 내용 입력
        System.out.print("투표 내용 입력 : ");
        String vote_content = scanner.nextLine();


        // 투표를 생성한 작성한 작성자의 작성자 번호 가져오기
        int logimMemberIdx = MemberController.getInstance().getLoginMemberIdx();

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
        boolean result = VoteController.getInstance().VoteWrite(vote_content,board_idx,logimMemberIdx,deadLine,votedtos_choices);
        if (result) {
            System.out.println("투표 작성 성공");
        } else {
            System.out.println("투표 작성 실패");
        } // if ed
    } // VoteWrite ed

    // 2. 투표 조회 함수
    public void VotePage(int board_idx) {
        // 객체 요청
        ArrayList<VoteDto> result = VoteController.getInstance().VotePage(board_idx);
        // 결과 조회
        System.out.println("====================");
        System.out.println(result.get(0).getVote_content());
        System.out.println("작성자 : " + result.get(0).getMember_name());
        System.out.println("마감날짜 : " + result.get(0).getVote_deadline());
        System.out.println("====================");
        for(int i = 0; i < result.size(); i++) {
            System.out.println((i+1)+"."+result.get(i).getChoice()+"\t득표수 : "+result.get(i).getVote_count());
        } // for ed
        if (now.isAfter(result.get(0).getVote_deadline())) { // 마감시간과 현재시간을 비교해 지났으면 투표 종료 처리
            System.out.println();
            System.out.println("[-투표가 종료되었습니다.-]");
        } else {
            while (true) {
                System.out.print("1. 투표하기 2. 뒤로가기 : ");
                int choose = scanner.nextInt();
                if (choose == 1) {
                    System.out.print("투표할 선택지를 작성하기 : ");
                    scanner.nextLine();
                    String str = scanner.nextLine();
                    voteView.VoteUpdate(str);
                    break;
                } else if (choose == 2) {
                    break;
                } else {
                    System.out.println("유효하지 않은 입력입니다.");
                } // if else ed
            } // while ed
        } // if ed
    } // VotePage ed

    public void VoteUpdate(String str) {
        VoteController.getInstance().VoteUpdate(str);
        System.out.println("투표 완료");
    }

} // VoteView ed

/// 예제 확인용
        /*
        LocalDateTime dt=LocalDateTime.of(2024,1,1,1,1);
        String dtFormat1=dt.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
        System.out.println(dt);

        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue());
        */