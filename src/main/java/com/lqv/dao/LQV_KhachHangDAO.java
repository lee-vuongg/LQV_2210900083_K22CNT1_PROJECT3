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
    public boolean insertKhachHang(LQV_KhachHang kh) {
        String sql = "INSERT INTO LQV_KhachHang (LQV_tai_khoan, LQV_mat_khau, LQV_ho_ten, LQV_email, LQV_dia_chi, LQV_so_dien_thoai, LQV_trang_thai) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, kh.getLQV_tai_khoan());
            ps.setString(2, kh.getLQV_mat_khau());
            ps.setString(3, kh.getLQV_ho_ten());
            ps.setString(4, kh.getLQV_email());
            ps.setString(5, kh.getLQV_dia_chi());
            ps.setString(6, kh.getLQV_so_dien_thoai());
            ps.setBoolean(7, kh.isLQV_trang_thai());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;  // Trả về true nếu thêm thành công
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Trả về false nếu có lỗi
    }

    // Cập nhật thông tin khách hàng
    public boolean update(LQV_KhachHang khachHang) {
        String sql = "UPDATE LQV_KhachHang SET LQV_tai_khoan=?, LQV_mat_khau=?, LQV_ho_ten=?, LQV_email=?, LQV_dia_chi=?, LQV_so_dien_thoai=?, LQV_trang_thai=?, LQV_ngay_sua=? WHERE LQVid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, khachHang.getLQV_tai_khoan());
            stmt.setString(2, khachHang.getLQV_mat_khau());
            stmt.setString(3, khachHang.getLQV_ho_ten());
            stmt.setString(4, khachHang.getLQV_email());
            stmt.setString(5, khachHang.getLQV_dia_chi());
            stmt.setString(6, khachHang.getLQV_so_dien_thoai());
            stmt.setBoolean(7, khachHang.isLQV_trang_thai());

            // Cập nhật ngày sửa với thời gian hiện tại
            Timestamp ngaySua = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(8, ngaySua);

            stmt.setInt(9, khachHang.getLQVid()); // Đặt ID vào vị trí thứ 9

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

    // Đăng ký khách hàng mới
    public boolean register(LQV_KhachHang kh) {
        // Kiểm tra email đã tồn tại chưa
        if (isEmailExist(kh.getLQV_email())) {
            return false; // Email đã tồn tại, không thể đăng ký
        }

        String sql = "INSERT INTO LQV_KhachHang (LQV_tai_khoan, LQV_mat_khau, LQV_ho_ten, LQV_email, LQV_dia_chi, LQV_so_dien_thoai, LQV_trang_thai, LQV_ngay_tao) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, kh.getLQV_tai_khoan());
            ps.setString(2, kh.getLQV_mat_khau());
            ps.setString(3, kh.getLQV_ho_ten());
            ps.setString(4, kh.getLQV_email());
            ps.setString(5, kh.getLQV_dia_chi());
            ps.setString(6, kh.getLQV_so_dien_thoai());
            ps.setBoolean(7, kh.isLQV_trang_thai());

            // Gán ngày tạo là thời gian hiện tại
            Timestamp ngayTao = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(8, ngayTao);

            return ps.executeUpdate() > 0; // Trả về true nếu đăng ký thành công
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Trả về false nếu có lỗi
    }

    // Kiểm tra email đã tồn tại chưa
    public boolean isEmailExist(String email) {
        String query = "SELECT 1 FROM LQV_KhachHang WHERE LQV_email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Trả về true nếu có kết quả
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm hỗ trợ ánh xạ từ ResultSet sang đối tượng LQV_KhachHang
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
