package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommentDao extends Dao{
    //싱글톤패턴
    private static CommentDao commentDao = new CommentDao();
    private CommentDao(){} // 생성자 end

    public static CommentDao getInstance(){return commentDao;}

    // 1. n번 게시물 출력 접근 함수(댓글조회)
    public ArrayList<CommentDto> commentPrint(int board_idx){

        // 댓글 담을 리스트
        ArrayList<CommentDto> commentList = new ArrayList<>();

        try{
            //1. SQL 작성
            //조회 필요한 필드 - 댓글번호, 멤버번호, 멤버이름, 댓글내용, 댓글작성시간, 댓글수정여부
            String sql = "select c.comment_idx, c.member_idx, m.member_name, " +
                    "c.comment_content, c.comment_date, c.comment_update " +
                    "from comment c join member m on c.member_idx = m.member_idx " +
                    "where c.board_idx = ? and c.comment_delete = true";


            //2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. SQL 조작, 실행
            ps.setInt(1, board_idx);
            ResultSet rs = ps.executeQuery();


            //4. SQL 결과
            while (rs.next()){
                //5.  댓글번호, 멤버번호, 멤버이름, 댓글내용, 댓글작성시간, 댓글수정여부
                int comment_idx = rs.getInt("comment_idx");
                int member_idx = rs.getInt("member_idx");
                String member_name = rs.getString("member_name");
                String comment_content = rs.getString("comment_content");
                LocalDateTime comment_date = rs.getTimestamp("comment_date").toLocalDateTime();
                boolean comment_update = rs.getBoolean("comment_update");

                //6. 레코드 호출된 필드값들 객체화(board_idx도 함께)
                CommentDto commentDto = new CommentDto(comment_idx, member_idx, member_name, board_idx,
                        comment_content, comment_date, comment_update);

                //4. 반복문 한번에 레코드 한개를 dto로 변환
                commentList.add(commentDto);

//                System.out.println("member_idx : "+ commentDto.getMember_idx());
//                System.out.println("board_idx" + commentDto.getBoard_idx());

            }// while end
        }catch (SQLException e){
            e.printStackTrace();
            e.getMessage();
            System.out.println("댓글 조회시 예외발생");
        } // try-catch end



        return commentList;
    }// commentPrint end

    //2. 댓글 등록 함수
    public boolean commentWrite(CommentDto commentDto){

        try{
            //1. SQL 작성
            String sql = "insert into comment(member_idx, board_idx, comment_content, comment_date)" +
                    "values(?, ?, ?, current_timestamp)";

            //2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. 매개변수에 값 대입
            ps.setInt(1, commentDto.getMember_idx());
            ps.setInt(2, commentDto.getBoard_idx());
            ps.setString(3, commentDto.getComment_content());

            //4. SQL 실행
            ps.executeUpdate();



            //5. 반환값
            return true;
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
            System.out.println("댓글 등록시 예외발생");
        } // try-catch end

        return false;
    }// commentWrite end

    //3. 댓글 수정 함수
    public boolean commentUpdate(CommentDto updateCommentDto){
        try{
            //1.sql 작성
            String sql = "update comment set comment_content = ?, comment_update = false, comment_date = current_timestamp where comment_idx = ?";

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
            e.printStackTrace();
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
            e.printStackTrace();
            System.out.println("댓글 삭제시 예외발생");
        } //commentDelete end
        return false;
    }// deleteComment end

    //댓글 작성자를 확인하는 함수
    public boolean comentAuthor(int comment_idx, int member_idx){
        try{
            //1. sql 작성
            String sql = "select member_idx from comment where comment_idx = ?";

            //2.
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, comment_idx);

            ResultSet rs = ps.executeQuery();

            // 댓글 작성자와 로그인한 회원이 동일한지 검증,일치하면 true 반환
            if(rs.next()){
                int authorIdx = rs.getInt("member_idx");
                return authorIdx == member_idx;
            }// if end
        } catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
            System.out.println("댓글 작성자 검증 중 예외 발생");
        }
        return false;
    }// commentAuthor end

}// class end
