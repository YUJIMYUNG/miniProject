package controller;

import model.CommentDao;
import model.CommentDto;

import java.util.ArrayList;

public class CommentController {
    private static CommentController commentController = new CommentController();
    private CommentController(){}
    public static CommentController getInstance(){return commentController;}

    //1. 댓글 조회 함수
    public ArrayList<CommentDto> commentPrint(){
        ArrayList<CommentDto> result = CommentDao.getInstance().commentPrint();

        return result;
    }// commentPrint end

    //2. 댓글 등록 함수
    public boolean commentWrite(String comment_content){
        CommentDto commentDto = new CommentDto(comment_content);

        return CommentDao.getInstance().commentWrite(commentDto);
    }// commentWrite end

    //3. 댓글 수정 함수
    public boolean commentUpdate(CommentDto updateCommentDto){
        boolean result = CommentDao.getInstance().commentUpdate(updateCommentDto);
        return result;
    }

    //4. 댓글 삭제 함수
    public boolean commentDelete(int deleteCommentNum){
        //여기서 로그인 회원번호 = 댓글 회원번호 맞는지 검사해

        boolean result = CommentDao.getInstance().commentDelete(deleteCommentNum);

        return result;
    }
} //class end
