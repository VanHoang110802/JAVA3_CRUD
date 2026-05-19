package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static final String URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=JAV20301;encrypt=true;trustServerCertificate=true";

    private static final String USER = "sa";
    private static final String PASS = "123";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Error connect DB: " + e.getMessage(), e);
        }
    }
}
