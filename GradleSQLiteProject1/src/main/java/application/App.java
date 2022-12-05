package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        
        String dbUrl = "jdbc:sqlite:people.db";
        
        Connection conn = DriverManager.getConnection(dbUrl);
        
        Statement statment = conn.createStatement();
        
        String sql = "create table if not exists user (id integer primary key, name text not null)";
        statment.execute(sql);
        
//        sql = "drop table user";
//        statment.execute(sql);
        
        System.out.println(conn);
        
        statment.close();
        conn.close();
        
    }

}
