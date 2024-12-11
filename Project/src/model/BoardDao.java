package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardDao extends Dao{

    // 싱글톤
    private static BoardDao boardDao = new BoardDao();

    private BoardDao() {} // init end

    public static BoardDao getInstance() {
        return boardDao;
    }


    // 게시물 DB 등록 함수
    public boolean boardWrite(BoardDto boardDto) {
        try {
            // sql 작성
            String sql = "insert into board( board_topic, board_status, board_version, board_title, board_content, board_writer, board_date, board_update) " +
                    "values( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            // 멤버 인덱스에서 이름 가져와서 writer에 넣기

            // sql의 매개변수에 값을 대입
            ps.setInt(1, boardDto.getTopic());
            ps.setInt(2, boardDto.getStatus());
            ps.setInt(3, boardDto.getVersion());
            ps.setString(4, boardDto.getTitle());
            ps.setString(5, boardDto.getContent());
            ps.setString(6, boardDto.getWriter());
            Timestamp date=Timestamp.valueOf(boardDto.getDate());
            ps.setTimestamp(7, date);
            Timestamp update=Timestamp.valueOf(boardDto.getDate());
            ps.setTimestamp(8, update);
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ 게시물 등록시 예외발생]");
        } // try end

        return false;
    } // func end

    // 게시물 DB 불러오기 함수
    public ArrayList<BoardDto> boardList() {

        ArrayList<BoardDto> list = new ArrayList<>(); // DB 저장 후 반환할 리스트

        try {
            // sql 작성, 실행 후 결과를 rs에 저장
            String sql = "select * from board";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // 결과를 arraylist로 변환
            while (rs.next()) { // 다음 레코드가 있으면 반복

                // 필드별 데이터 호출
                int idx = rs.getInt("board_idx");
                int topic = rs.getInt("board_topic");
                String title = rs.getString("board_title");
                String content = rs.getString("board_content");
                int writer_fk=rs.getInt("member_idx");
                // 참조키를 통해 작성자 가져올것
                String writer = "sample";
                Timestamp dateTS = rs.getTimestamp("board_date"); // 작성일
                LocalDateTime date = dateTS.toLocalDateTime();
                int status = rs.getInt("board_status"); // 완료여부
                int version = rs.getInt("board_version"); // 수정차수
                Timestamp updateTS = rs.getTimestamp("board_update"); // 수정일
                LocalDateTime update = updateTS.toLocalDateTime();
                boolean active=rs.getBoolean("in_active");

                // 객체 생성하고 리스트에 저장
                BoardDto boardDto = new BoardDto(idx, topic, title, content, writer, date, status, version, update, active);
                list.add(boardDto);

            } // while end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ 게시물 출력시 예외발생]");
        }

        return list;
    } // func end

    public BoardDto boardPrint() {
        BoardDto boardDto=new BoardDto();
        return boardDto;
    }

    /*
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