package com.lqv.dao;

import com.lqv.models.LQV_QuanTriVien;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_QuanTriVienDAO {

    // Lấy danh sách tất cả quản trị viên
    public List<LQV_QuanTriVien> getAll() {
        List<LQV_QuanTriVien> list = new ArrayList<>();
        String query = "SELECT * FROM LQV_QuanTriVien";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToQTV(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    // Tìm quản trị viên theo ID
    public LQV_QuanTriVien getById(int id) {
        String query = "SELECT * FROM LQV_QuanTriVien WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToQTV(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm quản trị viên mới
    public boolean insert(LQV_QuanTriVien qtv) {
        String query = "INSERT INTO LQV_QuanTriVien (LQV_tai_khoan, LQV_mat_khau, LQV_trang_thai, LQV_ngay_tao) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, qtv.getLQV_tai_khoan());
            stmt.setString(2, qtv.getLQV_mat_khau());
            stmt.setBoolean(3, qtv.isLQV_trang_thai());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin quản trị viên
    public boolean update(LQV_QuanTriVien qtv) {
        String query = "UPDATE LQV_QuanTriVien SET LQV_tai_khoan = ?, LQV_mat_khau = ?, LQV_trang_thai = ?, LQV_ngay_sua = NOW() WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, qtv.getLQV_tai_khoan());
            stmt.setString(2, qtv.getLQV_mat_khau());
            stmt.setBoolean(3, qtv.isLQV_trang_thai());
            stmt.setInt(4, qtv.getLQVid());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa quản trị viên theo ID
    public boolean delete(int id) {
        String query = "DELETE FROM LQV_QuanTriVien WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra đăng nhập quản trị viên
    public LQV_QuanTriVien login(String taiKhoan, String matKhau) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Không thể kết nối database!");
            return null;
        }

        String sql = "SELECT * FROM LQV_QuanTriVien WHERE LQV_tai_khoan = ? AND LQV_mat_khau = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ps.setString(2, matKhau);
            
            System.out.println("📌 SQL: " + ps.toString()); // Debug SQL

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LQV_QuanTriVien qtv = new LQV_QuanTriVien();
                qtv.setLQV_tai_khoan(rs.getString("LQV_tai_khoan"));
                qtv.setLQV_mat_khau(rs.getString("LQV_mat_khau"));
                return qtv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Kiểm tra tài khoản đã tồn tại chưa
    public boolean isUsernameExist(String username) {
        String query = "SELECT 1 FROM LQV_QuanTriVien WHERE LQV_tai_khoan = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Trả về true nếu có kết quả
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm hỗ trợ ánh xạ từ ResultSet sang đối tượng LQV_QuanTriVien
    private LQV_QuanTriVien mapResultSetToQTV(ResultSet rs) throws SQLException {
        return new LQV_QuanTriVien(
                rs.getInt("LQVid"),
                rs.getString("LQV_tai_khoan"),
                rs.getString("LQV_mat_khau"),
                rs.getBoolean("LQV_trang_thai"),
                rs.getTimestamp("LQV_ngay_tao"),
                rs.getTimestamp("LQV_ngay_sua")
        );
    }
}
