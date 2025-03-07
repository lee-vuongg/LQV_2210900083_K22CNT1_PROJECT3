package com.lqv.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/qlvtyt_lqv";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[INFO] Kết nối CSDL thành công!");
        } catch (Exception e) {
            System.err.println("[ERROR] Kết nối CSDL thất bại!");
            e.printStackTrace();
        }
        return connection;
    }
}