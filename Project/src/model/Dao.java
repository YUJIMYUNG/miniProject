package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    protected Connection conn;

    protected Dao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teamDB", "team1", "1234");
            System.out.println("[ Connection OK ]");
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("[ Connection fail ]");
        }
    }
}
