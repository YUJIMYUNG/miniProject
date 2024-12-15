package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class MemberDao extends Dao {

    public static MemberDao projectManagerDao = new MemberDao();
    //DB 연동
    private MemberDao(){}
    public static MemberDao getInstance(){return projectManagerDao;}

    // 멤버 로그인 접근 함수
    public boolean memberLogin(MemberDto loginDto){
        try {
            // sql 작성
            String sql = "select* from member where member_email = ? and member_pwd = ? ";
            // sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 매개변수 값 대입
            ps.setString(1, loginDto.getMember_email());
            ps.setString(2, loginDto.getMember_pwd());

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    loginDto.setMember_idx(rs.getInt("member_idx"));
                    return true;
                }
            }
        } catch (SQLException e){
            System.out.println("[로그인 예외 발생]" + e.getMessage());
        }
        return false;
    }

    // 로그인된 회원번호 반환
//    public int getLoggedInUserId(MemberDto loginDto){
//        return loginDto.getMember_idx();
//    }

    // 멤버 등록 접근 함수
    public boolean memberWrite(MemberDto memberDto){
        try {
            //이메일 중복확인 sql
            String EmailSql = "select count(*) from member where member_email = ?";
            PreparedStatement EmailPs = conn.prepareStatement(EmailSql);
            EmailPs.setString(1, memberDto.getMember_email());
            ResultSet rs = EmailPs.executeQuery();

            //이메일 중복확인
            if (rs.next() && rs.getInt(1) > 0){
                System.out.println("[멤버 등록 예외 발생 : 중복되는 이메일입니다.]");
                return false;
            } // 중복되지 않으면 진행

            // sql 작성
            String sql = "insert into member(member_name, member_email, member_pwd, birthdate, member_phone, member_date, in_active)" +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            // sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 매개변수 값 대입
            ps.setString(1, memberDto.getMember_name());
            ps.setString(2, memberDto.getMember_email());
            ps.setString(3, memberDto.getMember_pwd());

            // birthdate 입력받고 변환
            String birthdateInput = String.valueOf(memberDto.getBirthdate());
            LocalDate birthdate = LocalDate.parse(birthdateInput);
            ps.setDate(4, java.sql.Date.valueOf(birthdate));

            ps.setString(5, memberDto.getMember_phone());

            // member_date가 null일 때 현시각 반환
            if (memberDto.getMember_date() != null) {
                ps.setTimestamp(6, Timestamp.valueOf(memberDto.getMember_date()));
            } else {
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            }

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
            String sql = "select* from member";
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
                LocalDateTime member_date = rs.getTimestamp("member_date").toLocalDateTime();
                boolean in_active = rs.getBoolean("in_active");
                MemberDto memberDto = new MemberDto(member_idx, member_name, member_email, birthdate, member_phone, member_date, in_active);
                memberList.add(memberDto);
            }
        } catch (SQLException e){
            System.out.println("[멤버 출력시 예외 발생]" + e.getMessage());
        }
        // 멤버리스트 객체 반환
        return memberList;
    }

    // 멤버 삭제 접근 함수
    public boolean memberDelete(int deleteNum){
        try {
            //sql 작성
            String sql = "delete from member where member_idx = ?";
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
            System.out.println("[멤버 삭제 예외 발생]" + e.getMessage());
        }
        return false;
    }

    //멤버 수정 접근 함수
   public boolean memberUpdate(MemberDto updateDto){
       try {
            //sql 작성
            String sql = "update member set member_pwd = ?, member_phone = ?, in_active = ? where member_idx = ?";
            //sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //sql 조작
            ps.setString(1, updateDto.getMember_pwd());
            ps.setString(2, updateDto.getMember_phone());
            ps.setBoolean(3, updateDto.isIn_active());
            ps.setInt(4, updateDto.getMember_idx());
            //sql 실행
            int result = ps.executeUpdate();
            if (result == 1){
                return true;
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
    }
}
