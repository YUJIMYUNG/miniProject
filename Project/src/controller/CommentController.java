package controller;

import model.CommentDao;
import model.CommentDto;

import java.util.ArrayList;

public class CommentController {
    private static CommentController commentController = new CommentController();
    private CommentController(){}
    public static CommentController getInstance(){return commentController;}

    //1. 댓글 조회 함수
    public ArrayList<CommentDto> commentPrint(int board_idx){
        ArrayList<CommentDto> result = CommentDao.getInstance().commentPrint(board_idx);

        return result;
    }// commentPrint end

    //2. 댓글 등록 함수
    public boolean commentWrite(CommentDto commentDto){
        return CommentDao.getInstance().commentWrite(commentDto);
    }// commentWrite end

    //3. 댓글 수정 함수
    public boolean commentUpdate(CommentDto updateCommentDto, int loginMemberIdx){
        //1.  댓글 작성자 검증
        if(!CommentDao.getInstance().comentAuthor(updateCommentDto.getComment_idx(), loginMemberIdx)){
            return false;
        }// if end

        //작성자가 맞으면 true 반환
        return CommentDao.getInstance().commentUpdate(updateCommentDto);
    }

    //4. 댓글 삭제 함수
    public boolean commentDelete(int deleteCommentNum, int loginMemberIdx){
        //1. 댓글 작성자 검증
        if(!CommentDao.getInstance().comentAuthor(deleteCommentNum, loginMemberIdx)){
            return false;
        }// if end

        //작성자가 맞으면 true 반환
        return CommentDao.getInstance().commentDelete(deleteCommentNum);
    }
} //class end
