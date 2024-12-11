package controller;

import model.BoardDao;
import model.BoardDto;

import java.util.ArrayList;

public class BoardController {
    // 싱글톤
    private static BoardController boardController = new BoardController();
    private BoardController(){}
    public static BoardController getInstance(){
        return boardController;
    }

    // 게시물 등록 제어 함수
    public boolean boardWrite( int topic, String title,String content ){
        // 유효성 검사

        // 객체 생성 후 dao에 전달
        BoardDto boardDto = new BoardDto(topic, title, content);
        // 생성 성공 시 true 반환
        return BoardDao.getInstance().boardWrite( boardDto );
    } // func end

    // 게시물 출력 제어 함수
    public ArrayList<BoardDto> boardList( ){
        // 리스트 받아오기
        ArrayList<BoardDto> list = BoardDao.getInstance().boardList();

        // 자릿수 넘어가면 조정하기

        return list;
    } // func end

    public BoardDto boardPrint() {
        BoardDto list = BoardDao.getInstance().boardPrint();

        return list;
    }

    /*
    // 게시물 삭제 제어 함수
    public boolean boardDelete( int deleteNum ){
        // 유효성 검사

        // dao에 삭제할 게시물 번호 전달
        boolean result  = BoardDao.getInstance().boardDelete( deleteNum );
        // 삭제 성공 시 true 반환
        return result;
    } // func end

    // 게시물 수정 제어 함수
    public boolean boardUpdate( BoardDto updateDto ){
        // 유효성 검사

        // 객체 생성 후 dao에 전달
        boolean result = BoardDao.getInstance().boardUpdate( updateDto );
        // 수정 성공 시 true 반환
        return result;
    } // func end
*/
} // class end
