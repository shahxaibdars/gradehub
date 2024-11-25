package com.gradehub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/gradehub"; // Change to your database URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "Taha13070"; // Your MySQL password

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
