package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/salary";
    private static final String username = "root";
    private static final String password = "Rohan@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
