package model;

import java.sql.*;
import java.util.ArrayList;

public class BoardDao {

    private Connection conn; // DB와 연동하는 객체

    // 싱글톤
    private static BoardDao boardDao = new BoardDao();

    private BoardDao() {
        try {
            // "teamDB" 데이터베이스와 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teamDB", "root", "1234");
            System.out.println("[ BoardDB Connection OK ]");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ BoardDB Connection fail ]");
        }
    } // init end

    public static BoardDao getInstance() {
        return boardDao;
    }

    /*
    // 게시물 DB 등록 함수
    public boolean boardWrite(BoardDto boardDto) {
        try {
            // sql 작성
            String sql = "insert into board( content, writer, pwd) values( ? , ? , ? )";
            PreparedStatement ps = conn.prepareStatement(sql);

            // sql의 매개변수에 값을 대입
            ps.setString(1, boardDto.getContent());
            ps.setString(2, boardDto.getWriter());
            ps.setInt(3, boardDto.getPwd());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ 게시물 등록시 예외발생]");
        } // try end

        return false;
    } // func end

    // 게시물 DB 불러오기 함수
    public ArrayList<BoardDto> boardPrint() {

        ArrayList<BoardDto> list = new ArrayList<>(); // DB 저장 후 반환할 리스트

        try {
            // sql 작성, 실행 후 결과를 rs에 저장
            String sql = "select * from board";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // 결과를 arraylist로 변환
            while (rs.next()) { // 다음 레코드가 있으면 반복

                // 필드별 데이터 호출
                int num = rs.getInt("num");
                String content = rs.getString("content");
                String writer = rs.getString("writer");

                // 객체 생성하고 리스트에 저장
                BoardDto boardDto = new BoardDto();
                list.add(boardDto);

            } // while end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ 게시물 출력시 예외발생]");
        }

        return list;
    } // func end

    // 게시물 삭제 함수
    public boolean boardDelete(int deleteNum) {
        try {
            // sql 작성
            String sql = "delete from board where num = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            // sql의 매개변수에 값을 대입
            ps.setInt(1, deleteNum);

            int result = ps.executeUpdate(); // 삭제한 레코드 개수를 반환

            if (result == 1) {
                return true; // 삭제 성공
            } // if end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } // try end
        return false; // 삭제 실패
    } // func end

    // 게시물 수정 함수
    public boolean boardUpdate(BoardDto updateDto) {
        try {
            // sql 작성
            String sql = "update board set content = ? where num = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            // sql에 매개변수 대입
            ps.setString(1, updateDto.getContent());
            ps.setInt(2, updateDto.getIdx());

            int result = ps.executeUpdate(); // 수정 후 변화가 있는 레코드 개수 반환

            if (result == 1) {
                return true; // 삭제 성공
            } // if end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } // try end
        return false; // 삭제 실패
    } // func end

    */
} // class end