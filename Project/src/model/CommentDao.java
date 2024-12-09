package model;

import java.sql.*;
import java.util.ArrayList;

public class CommentDao extends Dao{
    //싱글톤패턴
    private static CommentDao commentDao = new CommentDao();
    private CommentDao(){} // 생성자 end

    public static CommentDao getInstance(){return commentDao;}

    // 1. n번 게시물 출력 접근 함수(댓글조회)
    public ArrayList<CommentDto> commentPrint(){

        // 댓글 담을 리스트
        ArrayList<CommentDto> commentList = new ArrayList<>();

        try{
            //1. SQL 작성
            String sql = "select * from comment";

            //2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. SQL 조작, 실행
            ResultSet rs = ps.executeQuery();

            //4. SQL 결과
            while (rs.next()){
                //5.  댓글번호, 작성자, 내용, 시간 넣어야함
                int comment_idx = rs.getInt("comment_idx");
                String comment_content = rs.getString("comment_content");

                //6. 레코드 호출된 필드값들 객체화 - 수정중
                CommentDto commentDto = new CommentDto(comment_idx, comment_content);

                //4. 반복문 한번에 레코드 한개를 dto로 변환
                commentList.add(commentDto);
            }// while end
        }catch (SQLException e){
            e.getMessage();
            System.out.println("댓글 조회시 예외발생");
        } // try-catch end


        return commentList;
    }// commentPrint end

    //2. 댓글 등록 함수
    public boolean commentWrite(CommentDto commentDto){

        try{
            //1. SQL 작성
            //로그인 한 유저 만 댓글 작성한다고 가정.
            //직접 콘솔에서 입력할 내용은 comment_content만 있음
            String sql = "insert into comment(comment_content)values(?)";

            //2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. 매개변수에 값 대입
            ps.setString(1, commentDto.getComment_content());

            //4. SQL 실행
            ps.executeUpdate();

            //5. 반환값
            return true;
        }catch (SQLException e){
            e.getMessage();
            System.out.println("댓글 등록시 예외발생");
        } // try-catch end

        return false;
    }// commentWrite end

    //3. 댓글 수정 함수
    public boolean commentUpdate(CommentDto updateCommentDto){
        try{
            //1.sql 작성
            String sql = "update comment set content = ? where comment_idx = ?";

            //2. sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            //3.sql 조작
            ps.setString(1, updateCommentDto.getComment_content());
            ps.setInt(2, updateCommentDto.getComment_idx());

            //4. sql실행, 결과
            int result = ps.executeUpdate();

            //4. 반환
            if(result ==1){
                return true;
            }// if end
        }catch (SQLException e){
            e.getMessage();
            System.out.println("댓글 수정시 예외발생");
        } // try-catch end
        return false;
    }// commentUpdate end

    //4. 댓글 삭제 함수(사실은 삭제로 보이게 하기 위한 DB update임)
    public boolean commentDelete(int deleteCommentNum){
        try{
            //1. sql작성
            String sql = "update comment set comment_delete=false where comment_idx= ?";

            //2. sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. sql조작
            ps.setInt(1, deleteCommentNum);

            //4. sql실행, 결과
            int result = ps.executeUpdate();

            //5. 반환
            if(result == 1){
                return true;
            }// if end
        }catch (SQLException e){
            e.getMessage();
            System.out.println("댓글 삭제시 예외발생");
        } //commentDelete end
        return false;
    }





}// class end
