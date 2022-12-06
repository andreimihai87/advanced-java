package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

        sql = "insert into user (id, name) values (1, 'Andrei')";
        statment.execute(sql);

        sql = "insert into user (id, name) values (2, 'Mike')";
        statment.execute(sql);
        
        sql = "insert into user (id, name) values (3, 'Anna')";
        statment.execute(sql);

        sql = "select * from user";
        ResultSet resultSet = statment.executeQuery(sql);

        System.out.println("id | name");
        System.out.println("---------");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + "  | " + name);
        }

        sql = "drop table user";
        statment.execute(sql);

        statment.close();
        conn.close();

    }

}
