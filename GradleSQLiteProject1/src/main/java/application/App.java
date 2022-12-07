package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int[] ids = { 1, 2, 3 };
        String[] names = { "John", "Ana", "Joe" };

        Class.forName("org.sqlite.JDBC");

        String dbUrl = "jdbc:sqlite:people.db";

        Connection conn = DriverManager.getConnection(dbUrl);

        Statement statment = conn.createStatement();
        conn.setAutoCommit(false);

        String sql = "create table if not exists user (id integer primary key, name text not null)";
        statment.execute(sql);

        sql = "insert into user (id, name) values (?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        for (int i = 0; i < ids.length; i++) {
            preparedStatement.setInt(1, ids[i]);
            preparedStatement.setString(2, names[i]);
            preparedStatement.execute();
        }

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
        
        conn.commit();

        preparedStatement.close();
        statment.close();
        conn.close();

    }

}
