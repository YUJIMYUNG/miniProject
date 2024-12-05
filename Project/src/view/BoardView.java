package view;

import controller.BoardController;
import model.BoardDto;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardView {
    // 싱글톤
    public static BoardView boardView=new BoardView();
    BoardView(){}
    public static BoardView getInstance() {return boardView;}

    Scanner scan;

    void mainBoard(){

        while(true){

        }

    } // main end

    // 게시물 작성 함수
    void boardWrite() {
        // 값 입력
        scan.nextLine();
        System.out.println("-----------게시물 작성-----------");
        System.out.println("구분 선택: 1.공지 2.회의록 3.투표 4.토의 : ");
        int topic = scan.nextInt();
        System.out.println("제목: ");
        String title = scan.next();
        System.out.print("내용: ");
        String content = scan.next();
        // 진행현황은 생성자, 수정함수에서 결정
        // 수정차수는 생성자, 수정함수에서 결정
        // 작성자는 현재 로그인한 유저를 생성자에 전달
        // 작성일은 생성자에서 결정
        // 수정일은 수정함수에서 결정

        // 컨트롤러에 전달 후 이상 없을 시 true 반환
        boolean result = BoardController.getInstance().boardWrite(topic, title, content);

        if (result) {
            System.out.println("게시물 등록 성공");
        } else {
            System.out.println("게시물 등록 실패");
        } // if end
    } // func end

    /*
    // 게시물 출력 함수
    void boardPrint() {

        ArrayList<BoardDto> result = BoardController.getInstance().boardPrint();
        // 출력
        for (int index = 0; index <= result.size() - 1; index++) {
            System.out.print("게시물번호: " + result.get(index).getNum());
            System.out.print(" 게시물내용: " + result.get(index).getContent());
            System.out.println(" 작성자: " + result.get(index).getWriter());
        } // for end
    } // func end

    // 게시물 삭제 함수
    void boardDelete() {
        System.out.println("삭제할 게시물 번호: ");
        int deleteNum = scan.nextInt();
        boolean result = BoardController.getInstance().boardDelete(deleteNum);
        if (result) {
            System.out.println("게시물 삭제 완료");
        } else {
            System.out.println("게시물 삭제 실패");
        } // if end
    } // func end

    // 게시물 수정 함수
    void boardUpdate() {
        System.out.println("수정할 게시물 번호: ");
        int updateNum=scan.nextInt();
        System.out.println("새로운 게시물 내용:");
        String updateContent=scan.next();
        boolean result=BoardController.getInstance().boardUpdate(updateNum, updateContent);
        if(result){
            System.out.println("게시물 수정 완료");
        }else{
            System.out.println("게시물 수정 실패");
        } // if end
    } // func end
*/

} // class end
