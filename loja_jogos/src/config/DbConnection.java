package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/loja_jogos";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Driver do MySQL não encontrado.");
                System.err.println(e.getMessage());
            }
        }
        return connection;
    }

    public static void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error disconnecting the database", e);
        }
    }
}