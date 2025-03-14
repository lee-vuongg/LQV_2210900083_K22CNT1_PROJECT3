package com.lqv.dao;

import com.lqv.models.LQV_DonDatHang;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_DonDatHangDAO {

    public List<LQV_DonDatHang> getAll() {
        List<LQV_DonDatHang> list = new ArrayList<>();
        String query = "SELECT * FROM LQV_DonDatHang";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDonDatHang(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LQV_DonDatHang getById(int id) {
        String query = "SELECT * FROM LQV_DonDatHang WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDonDatHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(LQV_DonDatHang donDatHang) {
        String query = "INSERT INTO LQV_DonDatHang (LQV_ngay_dat_hang, LQVid_khach_hang, LQVid_nha_cung_cap, LQV_tong_tien, LQV_trang_thai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, new java.sql.Date(donDatHang.getLQV_ngay_dat_hang().getTime()));
            stmt.setInt(2, donDatHang.getLQVid_khach_hang());
            stmt.setInt(3, donDatHang.getLQVid_nha_cung_cap());
            stmt.setDouble(4, donDatHang.getLQV_tong_tien());
            stmt.setString(5, donDatHang.getLQV_trang_thai());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LQV_DonDatHang donDatHang) {
        String query = "UPDATE LQV_DonDatHang SET LQV_ngay_dat_hang = ?, LQVid_khach_hang = ?, LQVid_nha_cung_cap = ?, LQV_tong_tien = ?, LQV_trang_thai = ? WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, new java.sql.Date(donDatHang.getLQV_ngay_dat_hang().getTime()));
            stmt.setInt(2, donDatHang.getLQVid_khach_hang());
            stmt.setInt(3, donDatHang.getLQVid_nha_cung_cap());
            stmt.setDouble(4, donDatHang.getLQV_tong_tien());
            stmt.setString(5, donDatHang.getLQV_trang_thai());
            stmt.setInt(6, donDatHang.getLQVid());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM LQV_DonDatHang WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatus(int id, String newStatus) {
        String query = "UPDATE LQV_DonDatHang SET LQV_trang_thai = ? WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private LQV_DonDatHang mapResultSetToDonDatHang(ResultSet rs) throws SQLException {
        return new LQV_DonDatHang(
            rs.getInt("LQVid"),
            rs.getDate("LQV_ngay_dat_hang"),
            rs.getInt("LQVid_khach_hang"),
            rs.getInt("LQVid_nha_cung_cap"),
            rs.getDouble("LQV_tong_tien"),
            rs.getString("LQV_trang_thai")
        );
    }
}
