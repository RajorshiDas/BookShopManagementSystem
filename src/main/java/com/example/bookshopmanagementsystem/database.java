package com.example.bookshopmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static Connection connectDb() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/database"; // Replace with your database URL
            String user = "root"; // Replace with your database username
            String password = "123456"; // Replace with your database password

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("database Connection Error: " + e.getMessage());
        }
        return connection;
    }
}
