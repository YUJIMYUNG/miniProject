package view;

import controller.BoardController;
import controller.MemberController;
import model.BoardDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardView {
    // 싱글톤
    public static BoardView boardView = new BoardView();
    BoardView() {}
    public static BoardView getInstance() { return boardView; }

    Scanner scan = new Scanner(System.in);

    public void mainBoard() {
        int page = 1;

        while (true) {
            // 게시물 목록 출력
            boardListNotice();
            int totalPage = boardList(page); // 현재 페이지를 전달하고 총 페이지 수를 반환 받음

            // 작업 선택
            System.out.println("1.게시물 작성 2.게시물 조회 3.이전 페이지 4.다음 페이지 5.로그아웃");
            System.out.print("작업 선택: ");
            int choose = 0;
            try {
                choose = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다");
                scan.nextLine();
            } // try end
            System.out.println();

            if (choose == 1) {
                boardWrite();
            } else if (choose == 2) {
                boardPrint();
            } else if (choose == 3) {
                if (page == 1) { System.out.println("첫번째 페이지 입니다.\n"); }
                else { page--; }
            } else if (choose == 4) {
                if (page >= totalPage) { System.out.println("마지막 페이지 입니다."); }
                else { page++; }
                System.out.println();
            } else if (choose == 5) {
                MemberView.getInstance().mainPage();
            } else {
                System.out.println("잘못된 입력입니다\n");
            } // if end
        } // while end
    } // main end

    // 게시물 작성 함수
    void boardWrite() {
        // 값 입력
        System.out.println("=================게시물 작성=================");
        System.out.print("구분 선택: 1.공지 2.회의록 3.투표 4.토의 : ");
        int topic = scan.nextInt();
        scan.nextLine();
        System.out.print("제목: ");
        String title = scan.nextLine();
        System.out.print("내용: ");
        String content = scan.nextLine();
        int writerIdx = MemberController.getInstance().getLoggedInUserId();

        // 컨트롤러에 전달 후 만들어진 게시글의 인덱스 반환
        int index = BoardController.getInstance().boardWrite(topic, title, content, writerIdx);

        // 게시글 작성시 투표면 투표작성 함수 접근
        if (topic == 3) { VoteView.getInstance().VoteWrite(index); }

        // 인덱스가 -1이 왔다면 버그 발생
        if (index != -1) { System.out.println("게시물 등록 성공"); }
        else { System.out.println("게시물 등록 실패"); }
        System.out.println();
    } // func end

    int boardList(int page) {
        ArrayList<BoardDto> list = BoardController.getInstance().boardList();
        ArrayList<BoardDto> activeList = new ArrayList<>();
        // 활성화 게시글만 새로운 리스트에 담기
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getActive()) {
                activeList.add(list.get(i));
            }
        }

        System.out.println("=================게시물 목록=================");
        // 맨 뒤 인덱스부터 출력
        for (int i = page * 10 - 1; i >= page * 10 - 10; i--) {
            // 호출값이 배열의 크기를 벗어나 존재하지 않으면 continue
            if (i >= activeList.size()) { continue; }
            // topic 형태 변환
            String topic;
            if (activeList.get(i).getTopic() == 1) { topic = "공지"; }
            else if (activeList.get(i).getTopic() == 2) { topic = "회의록"; }
            else if (activeList.get(i).getTopic() == 3) { topic = "투표"; }
            else if (activeList.get(i).getTopic() == 4) { topic = "토의"; }
            else { topic = "기타"; }

            // date, update 형태 변환
            LocalDateTime date = activeList.get(i).getDate();
            String dateFormat = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
            LocalDateTime update = activeList.get(i).getDate();
            String updateFormat = update.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));

            // status 형태 변환
            String status;
            if (activeList.get(i).getStatus() == 1) { status = "완료"; }
            else { status = "미완"; }

            // version 형태 변환
            String version;
            if (activeList.get(i).getVersion() == 0) { version = "new"; }
            else { version = activeList.get(i).getVersion() + "차"; }

            // 출력
            System.out.printf("%4d %-4s %-28s %-12s %-16s %-4s %-6s %-16s \n",
                    activeList.get(i).getIdx(), topic, activeList.get(i).getTitle(), activeList.get(i).getWriter(), dateFormat, status, version, updateFormat);
        } // for end
        System.out.println();
        // 총 페이지 수를 반환
        return (activeList.size() - 1) / 10 + 1;
    } // func end

    void boardListNotice() {
        ArrayList<BoardDto> list = BoardController.getInstance().boardListNotice();
        ArrayList<BoardDto> activeList = new ArrayList<>();
        // 활성화 게시글만 새로운 리스트에 담기
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getActive()) {
                activeList.add(list.get(i));
            }
        }

        System.out.println("====================공지====================");
        System.out.printf("%3s %-4s %-27s %-11s %-13s %-3s %-5s %-16s \n",
                "번호", "구분", "제목", "작성자", "작성일", "상태", "수정차수", "수정일");
        // 맨 뒤 인덱스부터 3개만 출력
        for (int i = activeList.size() - 1; i >= activeList.size() - 3; i--) {
            if (i < 0) break;
            // topic 형태 변환
            String topic = "공지";

            // date, update 형태 변환
            LocalDateTime date = activeList.get(i).getDate();
            String dateFormat = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
            LocalDateTime update = activeList.get(i).getDate();
            String updateFormat = update.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));

            // status 형태 변환
            String status;
            if (activeList.get(i).getStatus() == 1) { status = "완료"; }
            else { status = "미완"; }

            // version 형태 변환
            String version;
            if (activeList.get(i).getVersion() == 0) { version = "new"; }
            else { version = activeList.get(i).getVersion() + "차"; }

            // 출력
            System.out.printf("%4d %-4s %-28s %-12s %-16s %-4s %-6s %-16s \n",
                    activeList.get(i).getIdx(), topic, activeList.get(i).getTitle(), activeList.get(i).getWriter(), dateFormat, status, version, updateFormat);
        } // for end
    } // func end

    // 게시물 출력 함수
    void boardPrint() {
        // 게시물 번호 입력
        System.out.print("조회할 게시물 번호: ");
        int num = 0;
        try {
            num = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다");
            scan.nextLine();
            return;
        } // try end
        System.out.println();

        // 컨트롤러에 게시물 번호 전달 후 받아오기
        BoardDto board = BoardController.getInstance().boardPrint(num);
        // 빈 게시물이 왔을 경우 리턴
        if(board.getIdx() == -1) { return; }
        if(!board.getActive()) {
            System.out.println("삭제된 게시물입니다");
            return;
        }

        // topic 형태 변환
        String topic;
        if (board.getTopic() == 1) { topic = "공지"; }
        else if (board.getTopic() == 2) { topic = "회의록"; }
        else if (board.getTopic() == 3) { topic = "투표"; }
        else if (board.getTopic() == 4) { topic = "토의"; }
        else { topic = "기타"; }

        // date, update 형태 변환
        LocalDateTime date = board.getDate();
        String dateFormat = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
        LocalDateTime update = board.getDate();
        String updateFormat = update.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));

        // status 형태 변환
        String status;
        if (board.getStatus() == 1) { status = "완료"; }
        else { status = "미완"; }

        // 출력
        System.out.println("==========================================");
        System.out.print("[" + topic + "]");
        System.out.println(" 제목:" + board.getTitle());
        System.out.print("작성자: " + board.getWriter());
        System.out.println("  작성일: " + dateFormat);
        System.out.print("상태: " + status);
        if (board.getVersion() != 0) {
            System.out.print("  수정 " + board.getVersion() + "차");
            System.out.println("  수정일: " + updateFormat);
        } else {
            System.out.println();
        } // if end
        System.out.println();
        System.out.println(board.getContent());

        // 투표 페이지 접근
        if (board.getTopic() == 3) { VoteView.getInstance().VotePage(board.getIdx()); }
        System.out.println();

        // 추가 작업
        boardPrintSelect(num, board.getTopic());
    } // func end

    // 게시물 추가 작업 함수
    void boardPrintSelect(int boardIdx, int topic) {
        // 작성자 본인인지 검증
        int memberIdx = MemberController.getInstance().getLoggedInUserId();
        boolean writerCheck = BoardController.getInstance().boardCheckWriter(boardIdx, memberIdx);

        if (topic == 3) {
            System.out.print("1.댓글보기 2.뒤로가기 3.투표하기 ");
            // 작성자 본인이면 추가 선택지 등장
            if (writerCheck) { System.out.print("4.수정 5.삭제"); }
            System.out.println();

            System.out.print("작업 선택: ");
            int choose = 0;
            try {
                choose = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다");
                scan.nextLine();
            } // try end
            System.out.println();

            if (choose == 1) {
                CommentView.getInstance().mainPage(boardIdx);
            } else if (choose == 3) {
                VoteView.getInstance().VoteApproach(boardIdx);
            } else if (choose == 4) {
                if (writerCheck) boardUpdate(boardIdx);
                else System.out.println("수정 권한이 없습니다");
            } else if (choose == 5) {
                if (writerCheck) boardDelete(boardIdx);
                else System.out.println("삭제 권한이 없습니다");
            } else {
                System.out.println("목록으로 돌아갑니다");
            } // if end
        } else {
            System.out.print("1.댓글보기 2.뒤로가기 ");
            if (writerCheck) { System.out.print("3.수정 4.삭제"); }
            System.out.println();

            System.out.print("작업 선택: ");
            int choose = 0;
            try {
                choose = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다");
                scan.nextLine();
            }
            System.out.println();

            if (choose == 1) {
                CommentView.getInstance().mainPage(boardIdx);
            } else if (choose == 3) {
                if (writerCheck) boardUpdate(boardIdx);
                else System.out.println("수정 권한이 없습니다");
            } else if (choose == 4) {
                if (writerCheck) boardDelete(boardIdx);
                else System.out.println("삭제 권한이 없습니다");
            } else {
                System.out.println("목록으로 돌아갑니다");
            } // if end
        } // if end
    } // func end

    // 게시물 삭제 함수
    void boardDelete(int num) {
        System.out.println("정말 삭제하시겠어요? 1.예 2.아니오");
        System.out.print("선택: ");
        int choose = scan.nextInt();
        System.out.println();

        if (choose == 1) {
            boolean result = BoardController.getInstance().boardDelete(num);
            if (result) { System.out.println("게시물이 삭제되었습니다"); }
            else { System.out.println("게시물 삭제 실패"); }
        } // if end
    } // func end

    // 게시물 수정 함수
    void boardUpdate(int num) {
        System.out.println("1.제목 2.내용 3.상태");
        System.out.print("수정할 항목 선택: ");
        int choose = scan.nextInt();
        scan.nextLine();
        System.out.println();

        boolean result = false;
        if (choose == 1) {
            System.out.println("새로운 게시물 제목: ");
            String updateData = scan.nextLine();
            System.out.println();
            result = BoardController.getInstance().boardUpdate(num, choose, updateData);
        } else if (choose == 2) {
            System.out.println("새로운 게시물 내용:");
            String updateData = scan.nextLine();
            System.out.println();
            result = BoardController.getInstance().boardUpdate(num, choose, updateData);
        } else if (choose == 3) {
            String updateData = "1234";
            result = BoardController.getInstance().boardUpdate(num, choose, updateData);
        } // if end

        if (result) { System.out.println("게시물 수정 완료"); }
        else { System.out.println("게시물 수정 실패"); }
    } // func end
} // class end