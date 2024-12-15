package controller;

import model.BoardDao;
import model.BoardDto;
import model.CommentDao;

import java.util.ArrayList;

public class BoardController {
    // 싱글톤
    private static BoardController boardController = new BoardController();

    private BoardController() {
    }

    public static BoardController getInstance() {
        return boardController;
    }

    // 게시물 등록 제어 함수
    public int boardWrite(int topic, String title, String content) {
        // 유효성 검사

        // 객체 생성 후 dao에 전달
        BoardDto boardDto = new BoardDto(topic, title, content);
        // 생성 성공 시 true 반환
        return BoardDao.getInstance().boardWrite(boardDto);
    } // func end

    // 게시물 목록 출력 제어 함수
    public ArrayList<BoardDto> boardList() {
        // 리스트 받아오기
        ArrayList<BoardDto> list = BoardDao.getInstance().boardList();

        // 자릿수 넘어가면 조정하기

        return list;
    } // func end

    // 공지 목록 출력 제어 함수
    public ArrayList<BoardDto> boardListNotice() {
        // 리스트 받아오기
        ArrayList<BoardDto> list = BoardDao.getInstance().boardListNotice();


        return list;
    }

    // 게시물 세부 출력 제어 함수
    public BoardDto boardPrint(int num) {
        // 게시물 객체 받아오기
        BoardDto board = BoardDao.getInstance().boardPrint(num);

        return board;
    }

    // 게시물 삭제 제어 함수
    public boolean boardDelete(int num) {
        // 유효성 검사

        // dao에 삭제할 게시물 번호 전달
        boolean result = BoardDao.getInstance().boardDelete(num);
        // 삭제 성공 시 true 반환
        return result;
    } // func end

    // 게시물 수정 제어 함수
    public boolean boardUpdate(int num, int choose, String updateData) {
        // 유효성 검사

        // 객체 생성 후 dao에 전달
        boolean result=false;
        if(choose==1){
            result=BoardDao.getInstance().boardUpdateTitle(num, updateData);
        } else if (choose==2) {
            result=BoardDao.getInstance().boardUpdateContent(num, updateData);
        } else if (choose==3) {
            result=BoardDao.getInstance().boardUpdateStatus(num);
        }

        // 수정 성공 시 true 반환
        return result;
    } // func end

//    public boolean boardCheckWriter(int boardIdx, int memberIdx){
//        // 작성자 본인의 게시글이면 true, 아니면 false 반환
//        if(BoardDao.getInstance().boardCheckWriter(boardIdx, memberIdx)){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

} // class end
