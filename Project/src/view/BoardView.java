package view;

import controller.BoardController;
import model.BoardDao;
import model.BoardDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardView {
    // 싱글톤
    public static BoardView boardView = new BoardView();
    BoardView() {}
    public static BoardView getInstance() {return boardView;}

    Scanner scan = new Scanner(System.in);

    public void mainBoard() {
        int page=1;
        while (true) {
            // 게시물 목록 출력
            boardList(page);

            System.out.println("1.게시물 작성 2.게시물 조회 3.이전 페이지 4.다음 페이지");
            System.out.print("작업 선택: ");
            int choose = scan.nextInt();
            System.out.println();

            if (choose == 1) {
                boardWrite();
            } else if (choose == 2) {
                boardPrint();
            } else if (choose==3) {
                if(page==1){
                    System.out.println("가장 앞 페이지 입니다.");
                }
                else{
                    page--;
                }
            } else if (choose==4) {
                page++;
            }

        }

    } // main end

    // 게시물 작성 함수
    void boardWrite() {
        // 값 입력
        System.out.println("-----------게시물 작성-----------");
        System.out.print("구분 선택: 1.공지 2.회의록 3.투표 4.토의 : ");
        int topic = scan.nextInt();
        scan.nextLine();
        System.out.print("제목: ");
        String title = scan.nextLine();
        System.out.print("내용: ");
        String content = scan.nextLine();
        // 진행현황은 생성자, 수정함수에서 결정
        // 수정차수는 생성자, 수정함수에서 결정
        // 작성자는 현재 로그인한 유저를 생성자에 전달
        // 작성일은 생성자에서 결정
        // 수정일은 수정함수에서 결정

        // 컨트롤러에 전달 후 이상 없을 시 true 반환
        int index = BoardController.getInstance().boardWrite(topic, title, content);

        if(topic==3){

        }

        if (index!=-1) {
            System.out.println("게시물 등록 성공");
        } else {
            System.out.println("게시물 등록 실패");
        } // if end
    } // func end

    void boardList(int page) {
        ArrayList<BoardDto> list = BoardController.getInstance().boardList();
        ArrayList<BoardDto> activeList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getActive()){
                activeList.add(list.get(i));
            }
        }

        System.out.println("--------------공지--------------");
        // 공지 3개 출력하기 만들어야 함
        System.out.println("-----------게시물 목록-----------");
        System.out.printf("%3s %-4s %-27s %-11s %-13s %-3s %-5s %-16s \n",
                "번호", "구분", "제목", "작성자", "작성일", "상태", "수정차수", "수정일");
        // 맨 뒤 인덱스부터 출력
        // 현재 10개밖에 출력 안됨 다음 페이지 만들어야 함
        for (int i = page*10 -1; i >= page*10-10; i--) {
            if(i>=activeList.size()){
                continue;
            }
            // topic 형태 변환
            String topic;
            if (activeList.get(i).getTopic() == 1) {topic = "공지";}
            else if (activeList.get(i).getTopic() == 2) {topic = "회의록";}
            else if (activeList.get(i).getTopic() == 3) {topic = "투표";}
            else if (activeList.get(i).getTopic() == 4) {topic = "토의";}
            else {topic = "기타";}

            // date, update 형태 변환
            LocalDateTime date = activeList.get(i).getDate();
            String dateFormat = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
            LocalDateTime update = activeList.get(i).getDate();
            String updateFormat = update.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));

            // status 형태 변환
            String status;
            if (activeList.get(i).getStatus() == 1) {status = "완료";}
            else {status = "미완";}

            // version 형태 변환
            String version;
            if (activeList.get(i).getVersion() == 0) {version = "new";}
            else {version = activeList.get(i).getVersion() + "차";}

            System.out.printf("%4d %-4s %-28s %-12s %-16s %-4s %-6s %-16s \n",
                    activeList.get(i).getIdx(), topic, activeList.get(i).getTitle(), activeList.get(i).getWriter(), dateFormat, status, version, updateFormat);

        } // for end
        System.out.println();
    } // func end

    // 게시물 출력 함수
    void boardPrint() {
        // 게시물 번호 입력
        System.out.print("조회할 게시물 번호: ");
        int num = scan.nextInt();
        System.out.println();

        // 컨트롤러에 게시물 번호 전달 후 받아오기
        BoardDto board = BoardController.getInstance().boardPrint(num);

        // topic 형태 변환
        String topic;
        if (board.getTopic() == 1) {topic = "공지";}
        else if (board.getTopic() == 2) {topic = "회의록";}
        else if (board.getTopic() == 3) {topic = "투표";}
        else if (board.getTopic() == 4) {topic = "토의";}
        else {topic = "기타";}

        // date, update 형태 변환
        LocalDateTime date = board.getDate();
        String dateFormat = date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
        LocalDateTime update = board.getDate();
        String updateFormat = update.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));

        // status 형태 변환
        String status;
        if (board.getStatus() == 1) {status = "완료";}
        else {status = "미완";}

        // 출력
        System.out.println("----------------------------------------------");
        System.out.print("[" + topic + "]");
        System.out.println(" 제목:" + board.getTitle());
        System.out.print("작성자: " + board.getWriter());
        System.out.println("  작성일: " + dateFormat);
        System.out.print("상태: " + status);

        if(board.getVersion()!=0){
            System.out.print("  수정 " + board.getVersion() + "차");
            System.out.println("  수정일: " + updateFormat);
        }
        System.out.println();
        System.out.println(board.getContent());
        System.out.println();

        // 추가 작업
        System.out.println("1.댓글보기 2.수정 3.삭제 4.뒤로가기");
        System.out.print("작업 선택: ");
        int choose=scan.nextInt();
        System.out.println();

        if(choose==1){
            CommentView.getInstance().mainPage(num);
        } else if (choose==2) {
            boardUpdate(num);
        } else if (choose==3) {
            boardDelete(num);
        } else if (choose==4) {
            return;
        }
        System.out.println();
    } // func end


    // 게시물 삭제 함수
    void boardDelete(int num) {
        System.out.println("정말 삭제하시겠어요? 1.예 2.아니오");
        System.out.print("선택: ");
        int choose = scan.nextInt();
        System.out.println();
        if(choose==1){
            boolean result = BoardController.getInstance().boardDelete(num);
            if (result) {
                System.out.println("게시물이 삭제되었습니다");
            } else {
                System.out.println("게시물 삭제 실패");
            } // if end
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

        if(choose==1){
            System.out.println("새로운 게시물 제목: ");
            String updateData=scan.nextLine();
            System.out.println();
            result=BoardController.getInstance().boardUpdate(num, choose, updateData);
        } else if (choose==2) {
            System.out.println("새로운 게시물 내용:");
            String updateData=scan.nextLine();
            System.out.println();
            result=BoardController.getInstance().boardUpdate(num, choose, updateData);
        } else if (choose==3) {
            String updateData="1234";
            result=BoardController.getInstance().boardUpdate(num, choose, updateData);
        }

        if(result){
            System.out.println("게시물 수정 완료");
        }else{
            System.out.println("게시물 수정 실패");
        } // if end
    } // func end
} // class end