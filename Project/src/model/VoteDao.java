package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VoteDao {

    private Connection conn; // DB와 연동하는 객체

    private static VoteDao voteDao = new VoteDao();
    private VoteDao() {
        try {
            // "teamDB" 데이터베이스와 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teamDB", "team1", "1234");
            System.out.println("[ BoardDB Connection OK ]");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("[ BoardDB Connection fail ]");
        }
    } // 생성자 ed
    public static VoteDao getInstance() {
        return voteDao;
    } // 싱글턴 ed

    // 1.투표 작성 함수
    public void VoteFunction() {}

} // VoteDao ed
