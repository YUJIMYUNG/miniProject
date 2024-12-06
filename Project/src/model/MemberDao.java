package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberDao {

    private Connection conn;

    private static MemberDao projectManagerDao = new MemberDao();
    //DB 연동
    private MemberDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teamDB:", "team1", "1234");
            System.out.println("[MemberDao Connection Success]");
        }catch (ClassNotFoundException e){
            e.getMessage();
            System.out.println("[MemberDao Connection Fail]");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("[MemberDao Connection Fail]");
        }
    }
    public static MemberDao getInstance(){return projectManagerDao;}

    // 멤버 등록 접근 함수
    public boolean memberWrite(MemberDto memberDto){
        try {
            // sql 작성
            String sql = "insert into members(member_name, member_email, pwd, birthdate, member_phone, member_date, in_active)" +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            // sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 매개변수 값 대입
            ps.setString(1, memberDto.getMember_name());
            ps.setString(2, memberDto.getMember_email());
            ps.setString(3, memberDto.getPwd());
            ps.setDate(4, java.sql.Date.valueOf(memberDto.getBirthdate()));
            ps.setString(5, memberDto.getMember_phone());
            ps.setTimestamp(6, java.sql.Timestamp.valueOf(memberDto.getMember_date()));
            ps.setBoolean(7, memberDto.isIn_active());
            // sql 실행
            ps.executeUpdate();
            // 반환값
            return true;
        }catch (SQLException e){
            e.getMessage();
            System.out.println("[멤버 등록 예외 발생]");
        }
        return false;
    }

    // 멤버 출력 접근 함수
    public ArrayList<MemberDto> memberPrint(){
        // 멤버 리스트
        ArrayList<MemberDto> memberList = new ArrayList<>();
        try {
            // sql 작성
            String sql = "select* from members";
            // sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // sql 실행
            ResultSet rs = ps.executeQuery();
            // 결과
            while (rs.next()){
                int member_idx = rs.getInt("member_idx");
                String member_name = rs.getString("member_name");
                String member_email = rs.getString("member_email");
                LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
                String member_phone = rs.getString("member_phone");
                Date member_date = rs.getDate("member_date");
                boolean in_active = rs.getBoolean("in_active");
            }
        } catch (SQLException e){
            e.getMessage();
            System.out.println("[멤버 출력시 예외 발생]");
        }
        // 멤버리스트 객체 반환
        return memberList;
    }

    // 멤버 삭제 접근 함수
    public boolean memberDelete(int deleteNum){
        try {
            //sql 작성
            String sql = "delete from members where num = ?";
            //sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //sql 조작
            ps.setInt(1,deleteNum);
            // sql 실행
            int result = ps.executeUpdate();
            // 반환
            if (result == 1){
                return true;
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
    }

    //멤버 수정 접근 함수
    public boolean memberUpdate(MemberDto memberDto){
        try {
            //sql 작성
            String sql = "update member set member_phone = ? where num = ?";
            //sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //sql 조작
            ps.setString(1, updateDto.get);
            //sql 실행
        }
    }
}
