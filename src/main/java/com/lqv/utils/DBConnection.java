package com.lqv.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    // Phương thức đóng PreparedStatement và Connection
    public static void close(PreparedStatement ps, Connection conn) {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức đóng ResultSet, PreparedStatement và Connection
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}