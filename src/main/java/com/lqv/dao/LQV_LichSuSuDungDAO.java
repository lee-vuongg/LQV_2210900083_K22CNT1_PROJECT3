package com.lqv.dao;

import com.lqv.models.LQV_LichSuSuDung;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_LichSuSuDungDAO {

    public List<LQV_LichSuSuDung> getAll() {
        List<LQV_LichSuSuDung> list = new ArrayList<>();
        String query = "SELECT * FROM LQV_LichSuSuDung";
        System.out.println("[DEBUG] Bắt đầu truy vấn danh sách lịch sử sử dụng...");

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("[ERROR] Không thể kết nối đến CSDL!");
                return list;
            }
            System.out.println("[DEBUG] Kết nối CSDL thành công.");

            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LQV_LichSuSuDung lichSu = mapResultSetToLichSu(rs);
                    list.add(lichSu);
                    System.out.println("[DEBUG] Dữ liệu lấy được: " + lichSu);
                }
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Lỗi khi truy vấn dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("[DEBUG] Tổng số bản ghi lấy được: " + list.size());
        return list;
    }

    public LQV_LichSuSuDung getById(int id) {
        String query = "SELECT * FROM LQV_LichSuSuDung WHERE LQVid = ?";
        System.out.println("🔍 [DEBUG] ID được truyền vào getById: " + id);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLichSu(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Lỗi truy vấn getById: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(LQV_LichSuSuDung lichSu) {
        String query = "INSERT INTO LQV_LichSuSuDung (LQVid_vat_tu, LQVid_khach_hang, LQV_ngay_su_dung, LQV_so_luong_su_dung, LQV_ghi_chu) VALUES (?, ?, ?, ?, ?)";
        System.out.println("[DEBUG] Bắt đầu thêm lịch sử sử dụng: " + lichSu);
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            if (lichSu == null) {
                System.out.println("[ERROR] Đối tượng lichSu là null!");
                return false;
            }
            
            stmt.setInt(1, lichSu.getLQVid_vat_tu());
            stmt.setInt(2, lichSu.getLQVid_khach_hang());
            stmt.setDate(3, new java.sql.Date(lichSu.getLQV_ngay_su_dung().getTime()));
            stmt.setInt(4, lichSu.getLQV_so_luong_su_dung());
            stmt.setString(5, lichSu.getLQV_ghi_chu());
            
            int rows = stmt.executeUpdate();
            System.out.println("[DEBUG] Số bản ghi thêm vào: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("[ERROR] Lỗi khi thêm lịch sử sử dụng: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LQV_LichSuSuDung lichSu) {
        String query = "UPDATE LQV_LichSuSuDung SET LQVid_vat_tu = ?, LQVid_khach_hang = ?, LQV_ngay_su_dung = ?, LQV_so_luong_su_dung = ?, LQV_ghi_chu = ? WHERE LQVid = ?";
        System.out.println("[DEBUG] Đang cập nhật lịch sử sử dụng với ID: " + lichSu.getLQVid());

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, lichSu.getLQVid_vat_tu());
            stmt.setInt(2, lichSu.getLQVid_khach_hang());
            stmt.setDate(3, new java.sql.Date(lichSu.getLQV_ngay_su_dung().getTime()));
            stmt.setInt(4, lichSu.getLQV_so_luong_su_dung());
            stmt.setString(5, lichSu.getLQV_ghi_chu());
            stmt.setInt(6, lichSu.getLQVid());

            int rows = stmt.executeUpdate();
            System.out.println("[DEBUG] Số dòng bị ảnh hưởng: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("[ERROR] Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM LQV_LichSuSuDung WHERE LQVid = ?";
        System.out.println("[DEBUG] Xóa lịch sử sử dụng với ID: " + id);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("[DEBUG] Số bản ghi bị xóa: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("[ERROR] Lỗi khi xóa dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private LQV_LichSuSuDung mapResultSetToLichSu(ResultSet rs) throws SQLException {
        System.out.println("[DEBUG] Mapping dữ liệu từ ResultSet...");
        
        int id = rs.getInt("LQVid");
        if (rs.wasNull()) id = -1; // Đặt -1 thay vì 0 để dễ debug

        int vatTuId = rs.getInt("LQVid_vat_tu");
        int khachHangId = rs.getInt("LQVid_khach_hang");
        String nguoiSuDung = rs.getString("LQV_nguoi_su_dung");
        Date ngaySuDung = rs.getDate("LQV_ngay_su_dung");
        int soLuong = rs.getInt("LQV_so_luong_su_dung");
        String ghiChu = rs.getString("LQV_ghi_chu");
        
        return new LQV_LichSuSuDung(id, vatTuId, khachHangId, nguoiSuDung, ngaySuDung, soLuong, ghiChu);
    }
}
