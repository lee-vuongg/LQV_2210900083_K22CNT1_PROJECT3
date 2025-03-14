package com.lqv.dao;

import com.lqv.models.LQV_KhachHang;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_KhachHangDAO {

    // Lấy danh sách tất cả khách hàng
    public List<LQV_KhachHang> getAll() {
        List<LQV_KhachHang> list = new ArrayList<>();
        String query = "SELECT * FROM LQV_KhachHang";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToKhachHang(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tìm khách hàng theo ID
    public LQV_KhachHang getById(int id) {
        String query = "SELECT * FROM LQV_KhachHang WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToKhachHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm khách hàng mới
    public boolean insert(LQV_KhachHang kh) {
        String query = "INSERT INTO LQV_KhachHang (LQV_tai_khoan, LQV_mat_khau, LQV_ho_ten, LQV_email, LQV_dia_chi, LQV_so_dien_thoai, LQV_trang_thai, LQV_ngay_tao) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, kh.getLQV_tai_khoan());
            stmt.setString(2, kh.getLQV_mat_khau());
            stmt.setString(3, kh.getLQV_ho_ten());
            stmt.setString(4, kh.getLQV_email());
            stmt.setString(5, kh.getLQV_dia_chi());
            stmt.setString(6, kh.getLQV_so_dien_thoai());
            stmt.setBoolean(7, kh.isLQV_trang_thai());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin khách hàng
    public boolean update(LQV_KhachHang kh) {
        String query = "UPDATE LQV_KhachHang SET LQV_tai_khoan = ?, LQV_mat_khau = ?, LQV_ho_ten = ?, LQV_email = ?, LQV_dia_chi = ?, LQV_so_dien_thoai = ?, LQV_trang_thai = ?, LQV_ngay_sua = NOW() WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, kh.getLQV_tai_khoan());
            stmt.setString(2, kh.getLQV_mat_khau());
            stmt.setString(3, kh.getLQV_ho_ten());
            stmt.setString(4, kh.getLQV_email());
            stmt.setString(5, kh.getLQV_dia_chi());
            stmt.setString(6, kh.getLQV_so_dien_thoai());
            stmt.setBoolean(7, kh.isLQV_trang_thai());
            stmt.setInt(8, kh.getLQVid());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa khách hàng theo ID
    public boolean delete(int id) {
        String query = "DELETE FROM LQV_KhachHang WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra đăng nhập khách hàng
    public LQV_KhachHang login(String username, String password) {
        String query = "SELECT * FROM LQV_KhachHang WHERE LQV_tai_khoan = ? AND LQV_mat_khau = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToKhachHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Kiểm tra email đã tồn tại chưa
    public boolean isEmailExist(String email) {
        String query = "SELECT 1 FROM LQV_KhachHang WHERE LQV_email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Ánh xạ từ ResultSet sang đối tượng LQV_KhachHang
    private LQV_KhachHang mapResultSetToKhachHang(ResultSet rs) throws SQLException {
        return new LQV_KhachHang(
                rs.getInt("LQVid"),
                rs.getString("LQV_tai_khoan"),
                rs.getString("LQV_mat_khau"),
                rs.getString("LQV_ho_ten"),
                rs.getString("LQV_email"),
                rs.getString("LQV_dia_chi"),
                rs.getString("LQV_so_dien_thoai"),
                rs.getBoolean("LQV_trang_thai"),
                rs.getTimestamp("LQV_ngay_tao"),
                rs.getTimestamp("LQV_ngay_sua")
        );
    }
}
