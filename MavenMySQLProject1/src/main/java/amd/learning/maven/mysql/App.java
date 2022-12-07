package amd.learning.maven.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String dbUrl = "jdbc:mysql://localhost:3306/whist";
        Connection conn = DriverManager.getConnection(dbUrl, "root", "root");

        Statement statment = conn.createStatement();
        conn.setAutoCommit(false);

        String sql = "select * from gametable";
        ResultSet resultSet = statment.executeQuery(sql);

        System.out.println("idGame | idRound | nrCards | idPlayer | said | made | score");
        System.out.println("-----------------------------------------------------------");
        while (resultSet.next()) {
            int idGame = resultSet.getInt("idGame");
            int idRound = resultSet.getInt("idRound");
            int nrCards = resultSet.getInt("nrCards");
            int idPlayer = resultSet.getInt("idPlayer");
            int said = resultSet.getInt("said");
            int made = resultSet.getInt("made");
            int score = resultSet.getInt("score");
            System.out.printf("%-6s | %-7s | %-7s | %-8s | %-5s| %-5s| %-5s", idGame, idRound, nrCards, idPlayer, said, made,
                    score);
            System.out.println();
        }

        conn.commit();
        statment.close();
        conn.close();

    }
}
