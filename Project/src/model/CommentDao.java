package model;

import java.sql.*;
import java.util.ArrayList;

public class CommentDao {
    //JDBC 인터페이스
    private Connection conn; //연동된 결과의 객체를 조작할 인터페이스

    //싱글톤패턴
    private static CommentDao commentDao = new CommentDao();
    private CommentDao(){
        try{
            //1.
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teamDB", "team1", "1234");

            //3. test
            System.out.println("[ teamDB Connection OK ]");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("teamDB Connection Fail");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("teamDB Connection Fail");
        }
    } // 생성자 end

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
    }




}// class end
