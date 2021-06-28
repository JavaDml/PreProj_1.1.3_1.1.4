package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String url = "jdbc:mysql://localhost:3306/test";
    private final String user = "root";
    private final String pass = "root";

    private Connection connection =null;

    public Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection connectDB() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            //statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
