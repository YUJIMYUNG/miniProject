package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class VoteDao extends Dao{

    private static VoteDao voteDao = new VoteDao();
    private VoteDao() {}
    public static VoteDao getInstance() {
        return voteDao;
    } // 싱글턴 ed

    /// 1.투표 작성 함수
    public boolean VoteWrite(VoteDto voteDto) {
        try {
            String sql = "insert into vote(vote_content,board_idx,member_idx,vote_deadline) values(?,?,?,?)"; // SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); // SQL 기재 준비 및 ps 에 자동생성된 키 반환
            ps.setString(1,voteDto.getVote_content());
            ps.setInt(2,voteDto.getBoard_idx());
            ps.setInt(3,voteDto.getMember_idx());
            ps.setTimestamp(4,java.sql.Timestamp.valueOf(voteDto.getVote_deadline()));
            ps.executeUpdate(); // SQL 실행

            // 생성된 vote_idx 가져오기
            ResultSet rs = ps.getGeneratedKeys();
            int voteIdx = 0;
            if (rs.next()) {
                voteIdx = rs.getInt(1); // 자동 생성된 키 값
            }

            String sql2 = "insert into votecount(vote_idx, vote_choice, vote_count) values(?, ?, 0)"; // SQL2 작성
            PreparedStatement ps2 = conn.prepareStatement(sql2); // SQL2 기재 준비
            for (String choice : voteDto.getVote_choice()) { // vote_choice에 저장된 ArrayList 의 수만큼 DB에 저장
                ps2.setInt(1, voteIdx);
                ps2.setString(2,choice);
            ps2.executeUpdate(); // SQL 실행
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } // VoteWrite ed

    /// 2.투표 조회 및 투표 함수
    public ArrayList<VoteDto> VotePage(int board_idx) {
        ArrayList<VoteDto> voteList = new ArrayList<>(); // 투표 내용을 담을 리스트

        try {
            String sql = "SELECT v.vote_content,m.member_name,v.vote_deadline,vc.vote_choice,vc.vote_count " +
                    "FROM vote v JOIN member m ON v.member_idx = m.member_idx " +
                    "JOIN votecount vc ON v.vote_idx = vc.vote_idx " +
                    "WHERE v.board_idx = ?;";


            PreparedStatement ps = conn.prepareStatement(sql); // sql 기재

            // SQL 조작, 실행
            ps.setInt(1, board_idx);
            ResultSet rs = ps.executeQuery();

            // SQL 결과 변수에 초기화
            while (rs.next()) {
                String vote_content = rs.getString("vote_content"); // 투표 내용
                String member_name = rs.getString("member_name"); // 투표 작성자
                LocalDateTime vote_deadline = rs.getTimestamp("vote_deadline").toLocalDateTime();
                String vote_choice = rs.getString("vote_choice"); // 투표 선택지
                int vote_count = rs.getInt("vote_count"); // 득표 수

                // 호출된 값들 객체화
                VoteDto voteDto = new VoteDto(vote_content,board_idx,member_name,vote_deadline,
                                                                    vote_choice,vote_count);
                // System.out.println(voteDto.getVote_content());
                // System.out.println(voteDto.getVote_deadline());
                voteList.add(voteDto); // 반복문 1번당 레코드 1개 저장
            } // while ed
        } catch (SQLException e) {
            e.printStackTrace();
        } // try catch ed
        return voteList;
    } // VotePage ed

    /// 3. 투표 업데이트 함수
    public boolean VoteUpdate(String str) {
        try {
            String sql = "UPDATE votecount SET vote_count =  vote_count + 1 " +
                    "WHERE vote_choice = ?;"; // SQL 준비

            PreparedStatement ps = conn.prepareStatement(sql); // SQL 기재 준비
            ps.setString(1,str);
            ps.executeUpdate(); // SQL 실행
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } // VoteUpdate ed

    /// 4.투표 선택지가 존재하는지 확인하는 함수
    public boolean ChoiceCheck(String str) {
        try {
            //1. sql 작성
            String sql = "select vote_choice from votecount where vote_choice = ?";

            //2.
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, str);
            ResultSet rs = ps.executeQuery();

            //3. 번호 있으면 true반환
            return rs.next();

        } catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
            System.out.println("예외 발생");
        }
        return false;
        } // ChoiceCheck ed

} // VoteDao ed
