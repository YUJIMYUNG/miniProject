package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardDao extends Dao {

    // 싱글톤
    private static BoardDao boardDao = new BoardDao();

    private BoardDao() {
    } // init end

    public static BoardDao getInstance() {
        return boardDao;
    }


    // 게시물 DB 등록 함수
    public int boardWrite(BoardDto boardDto) {
        try {
            // sql 작성
            String sql = "insert into board( board_topic, board_status, board_version, board_title, board_content, member_idx, board_date, board_update) " +
                    "values( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // 멤버 인덱스에서 이름 가져와서 writer에 넣기

            // sql의 매개변수에 값을 대입
            ps.setInt(1, boardDto.getTopic());
            ps.setInt(2, boardDto.getStatus());
            ps.setInt(3, boardDto.getVersion());
            ps.setString(4, boardDto.getTitle());
            ps.setString(5, boardDto.getContent());

            // 멤버 인덱스에 임시 값 대입 - 멤버 인덱스 받아와야함
            ps.setInt(6, 1);


            Timestamp date = Timestamp.valueOf(boardDto.getDate());
            ps.setTimestamp(7, date);
            Timestamp update = Timestamp.valueOf(boardDto.getDate());
            ps.setTimestamp(8, update);
            ps.executeUpdate();

            ResultSet rs=ps.getGeneratedKeys();
            System.out.println(ps.getGeneratedKeys());
            if(rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ 게시물 등록시 예외발생]");
        } // try end

        // 예외 발생 시 false 전달
        return -1;
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
                int writer_fk = rs.getInt("member_idx");

                // 참조키를 통해 작성자 가져올것
                String writer = "sample";

                Timestamp dateTS = rs.getTimestamp("board_date"); // 작성일
                LocalDateTime date = dateTS.toLocalDateTime();
                int status = rs.getInt("board_status"); // 완료여부
                int version = rs.getInt("board_version"); // 수정차수
                Timestamp updateTS = rs.getTimestamp("board_update"); // 수정일
                LocalDateTime update = updateTS.toLocalDateTime();
                boolean active = rs.getBoolean("in_active");

                // 객체 생성하고 리스트에 저장
                BoardDto boardDto = new BoardDto(idx, topic, title, content, writer, date, status, version, update, active);
                list.add(boardDto);
            } // while end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ 게시물 출력시 예외발생]");
        } // try end
        return list;
    } // func end

    public ArrayList<BoardDto> boardListNotice() {

        ArrayList<BoardDto> list = new ArrayList<>(); // DB 저장 후 반환할 리스트

        try {
            // sql 작성, 실행 후 결과를 rs에 저장
            String sql = "select * from board where board_topic = 3";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // 결과를 arraylist로 변환
            while (rs.next()) { // 다음 레코드가 있으면 반복

                // 필드별 데이터 호출
                int idx = rs.getInt("board_idx");
                int topic = rs.getInt("board_topic");
                String title = rs.getString("board_title");
                String content = rs.getString("board_content");
                int writer_fk = rs.getInt("member_idx");

                // 참조키를 통해 작성자 가져올것
                String writer = "sample";

                Timestamp dateTS = rs.getTimestamp("board_date"); // 작성일
                LocalDateTime date = dateTS.toLocalDateTime();
                int status = rs.getInt("board_status"); // 완료여부
                int version = rs.getInt("board_version"); // 수정차수
                Timestamp updateTS = rs.getTimestamp("board_update"); // 수정일
                LocalDateTime update = updateTS.toLocalDateTime();
                boolean active = rs.getBoolean("in_active");

                // 객체 생성하고 리스트에 저장
                BoardDto boardDto = new BoardDto(idx, topic, title, content, writer, date, status, version, update, active);
                list.add(boardDto);
            } // while end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ 게시물 출력시 예외발생]");
        } // try end
        return list;
    }

    public BoardDto boardPrint(int num) {
        BoardDto boardDto = new BoardDto();

        try {
            String sql = "select * from board where board_idx = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 필드별 데이터 호출
                int index=rs.getInt("board_idx");
                int topic = rs.getInt("board_topic");
                String title = rs.getString("board_title");
                String content = rs.getString("board_content");
                int writer_fk = rs.getInt("member_idx");

                // 참조키를 통해 작성자 가져올것
                String writer = "sample";

                Timestamp dateTS = rs.getTimestamp("board_date"); // 작성일
                LocalDateTime date = dateTS.toLocalDateTime();
                int status = rs.getInt("board_status"); // 완료여부
                int version = rs.getInt("board_version"); // 수정차수
                Timestamp updateTS = rs.getTimestamp("board_update"); // 수정일
                LocalDateTime update = updateTS.toLocalDateTime();

                // 객체 생성하고 리스트에 저장
                boardDto = new BoardDto(index, topic, title, content, writer, date, status, version, update);
            } else {
                System.out.println("[게시물이 존재하지 않습니다]");
            } // if end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[게시물 내용 출력시 예외발생]");
        } // try end
        return boardDto;
    } // func end


    // 게시물 삭제 함수
    public boolean boardDelete(int deleteNum) {
        try {
            // sql 작성
            String sql = "update board set in_active = false where board_idx = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, deleteNum);

            int result = ps.executeUpdate(); // 삭제한 레코드 개수를 반환

            if (result == 1) {
                return true; // 삭제 성공
            } // if end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[게시물 삭제 실패]");
        } // try end
        return false; // 삭제 실패
    } // func end

    public boolean boardUpdateTitle(int num, String updateData) {
        try {
            // sql 작성
            String sql = "update board set board_title = ? where board_idx = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            // sql에 매개변수 대입
            ps.setString(1, updateData);
            ps.setInt(2, num);

            int result = ps.executeUpdate(); // 수정 후 변화가 있는 레코드 개수 반환

            if (result == 1) {
                return true; // 수정 성공
            } // if end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[게시물 삭제 실패]");
        } // try end
        return false; // 수정 실패
    } // func end

    public boolean boardUpdateContent(int num, String updateData) {
        try {
            String sql_status = "select board_version from board where board_idx = ?";
            PreparedStatement ps_status = conn.prepareStatement(sql_status);
            ps_status.setInt(1, num);
            ResultSet rs = ps_status.executeQuery();

            int version=0;
            if(rs.next()){
                version=rs.getInt("board_version");
            }
            // 수정차수 증가
            version++;

            // sql 작성
            String sql = "update board set board_content = ?, board_version = ? where board_idx = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            // sql에 매개변수 대입
            ps.setString(1, updateData);
            ps.setInt(2, version);
            ps.setInt(3, num);

            int result = ps.executeUpdate(); // 수정 후 변화가 있는 레코드 개수 반환

            if (result == 1) {
                return true; // 수정 성공
            } // if end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } // try end
        return false; // 수정 실패
    } // func end

    public boolean boardUpdateStatus(int num) {

        try {
            String sql_status = "select board_status from board where board_idx = ?";
            PreparedStatement ps_status = conn.prepareStatement(sql_status);
            ps_status.setInt(1, num);
            ResultSet rs = ps_status.executeQuery();

            String sql="";
            if(rs.next()){
                int status=rs.getInt("board_status");

                if(status==0){
                    sql = "update board set board_status = 1 where board_idx = ? ";
                }
                else if (status==1){
                    sql = "update board set board_status = 0 where board_idx = ? ";
                }
            }
            PreparedStatement ps = conn.prepareStatement(sql);

            // sql에 매개변수 대입
            ps.setInt(1, num);
            int result = ps.executeUpdate(); // 수정 후 변화가 있는 레코드 개수 반환

            if (result == 1) {
                return true; // 수정 성공
            } // if end
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } // try end
        return false; // 수정 실패
    } // func end

} // class end