package view;

import controller.VoteController;
import model.BoardDto;
import model.MemberDto;
import model.VoteDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        //int logimMemberIdx = MemberController.getInstance().getLoginMemberIdx();
        int lmiDemo = 1;

        // 투표 마감 날짜 입력
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deadLine;
        while (true) {
            System.out.print("투표 마감 날짜 입력(YYYY-MM-DD HH:mm) : ");
            String input = scanner.nextLine();
            try {
                LocalDateTime dateTime = LocalDateTime.parse(input,formatter);
                LocalDateTime now = LocalDateTime.now(); // 현 시각
                if (dateTime.isBefore(now)) { // 입력한 시간이 현재 시간보다 이전일 시
                    System.out.println("입력한 날짜와 시간이 현재 시간보다 이전입니다.");
                } else {
                    System.out.println("마감 날짜 : " + dateTime);
                    deadLine = dateTime;
                    break;
                }// 올바르게 입력 시 날짜 입력 종료
            } catch (DateTimeParseException e) { // 잘못된 입력일 시 예외 처리
                System.out.println("유효하지 않은 입력입니다.");
            }
        }
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
        boolean result = VoteController.getInstance().VoteWrite(vote_content,board_idx,lmiDemo,deadLine,votedtos_choices);
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
        LocalDateTime now = LocalDateTime.now(); // 현재 시간을 저장하는 객체
        // 결과 조회
        System.out.println("====================");
        System.out.println(result.get(0).getVote_content());
        System.out.println("작성자 : " + result.get(0).getMember_name());
        System.out.println("마감날짜 : " + result.get(0).getVote_deadline());
        System.out.println("====================");
        for(int i = 0; i < result.size(); i++) {
            System.out.println((i+1)+"."+result.get(i).getChoice()+"\t득표수 : "+result.get(i).getVote_count());
        } // for ed
    } // VotePage ed

    /// 투표 입장 함수
    public void VoteApproach(int board_idx) {
        // 객체 요청
        ArrayList<VoteDto> result = VoteController.getInstance().VotePage(board_idx);
        LocalDateTime now = LocalDateTime.now(); // 현재 시간을 저장하는 객체
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
                    ChoiceCheck(str);
                    if (ChoiceCheck(str)) {
                        break;
                    }
                } else if (choose == 2) {
                    break;
                } else {
                    System.out.println("유효하지 않은 입력입니다.");
                } // if else ed
            } // while ed
        } // if ed
    } // VoteApproach ed


    /// 투표 업데이트 용 함수
    public void VoteUpdate(String str) {
        VoteController.getInstance().VoteUpdate(str);
        System.out.println("투표 완료");
    } // VoteUpdate ed

    /// 선택지 검증용 함수
    public boolean ChoiceCheck(String str) {
        boolean result = VoteController.getInstance().ChoiceCheck(str);
        if (result) {
            VoteUpdate(str);
            return true;
        } else {
            System.out.println("존재하지 않는 선택지입니다.");
            return false;
        }
    } // ChoiceCheck ed

} // VoteView ed

/// 예제 확인용
        /*
        LocalDateTime dt=LocalDateTime.of(2024,1,1,1,1);
        String dtFormat1=dt.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
        System.out.println(dt);

        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue());
        */