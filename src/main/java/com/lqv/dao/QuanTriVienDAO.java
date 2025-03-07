package com.lqv.dao;

import com.lqv.models.LQV_QuanTriVien;
import com.lqv.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuanTriVienDAO {

    public List<LQV_QuanTriVien> getAllAdmins() {
        List<LQV_QuanTriVien> admins = new ArrayList<>();
        String query = "SELECT * FROM LQV_QuanTriVien";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LQV_QuanTriVien admin = new LQV_QuanTriVien(
                        rs.getInt("LQVid"),
                        rs.getString("LQV_tai_khoan"),
                        rs.getString("LQV_mat_khau"),
                        rs.getBoolean("LQV_trang_thai"),
                        rs.getTimestamp("LQV_ngay_tao"),
                        rs.getTimestamp("LQV_ngay_sua")
                );
                admins.add(admin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }

    public void addAdmin(LQV_QuanTriVien admin) {
        String query = "INSERT INTO LQV_QuanTriVien (LQV_tai_khoan, LQV_mat_khau, LQV_trang_thai) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, admin.getLQV_tai_khoan());
            ps.setString(2, admin.getLQV_mat_khau());
            ps.setBoolean(3, admin.isLQV_trang_thai());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAdmin(LQV_QuanTriVien admin) {
        String query = "UPDATE LQV_QuanTriVien SET LQV_tai_khoan = ?, LQV_mat_khau = ?, LQV_trang_thai = ?, LQV_ngay_sua = CURRENT_TIMESTAMP WHERE LQVid = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, admin.getLQV_tai_khoan());
            ps.setString(2, admin.getLQV_mat_khau());
            ps.setBoolean(3, admin.isLQV_trang_thai());
            ps.setInt(4, admin.getLQVid());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmin(int id) {
        String query = "DELETE FROM LQV_QuanTriVien WHERE LQVid = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LQV_QuanTriVien getAdminById(int id) {
        String query = "SELECT * FROM LQV_QuanTriVien WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}