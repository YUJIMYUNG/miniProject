package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    }
    public static CommentDao getInstance(){return commentDao;}
}// class end
