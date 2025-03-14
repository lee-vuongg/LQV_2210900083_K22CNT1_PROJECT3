package com.lqv.dao;

import com.lqv.models.LQV_Kho;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_KhoDAO {

    public List<LQV_Kho> getAll() {
        List<LQV_Kho> list = new ArrayList<>();
        String query = "SELECT * FROM LQV_Kho";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToKho(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LQV_Kho getById(int id) {
        String query = "SELECT * FROM LQV_Kho WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToKho(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(LQV_Kho kho) {
        String query = "INSERT INTO LQV_Kho (LQV_ten_kho, LQV_dia_chi, LQV_nguoi_quan_ly, LQV_suc_chua) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, kho.getLQV_ten_kho());
            stmt.setString(2, kho.getLQV_dia_chi());
            stmt.setString(3, kho.getLQV_nguoi_quan_ly());
            stmt.setInt(4, kho.getLQV_suc_chua());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LQV_Kho kho) {
        String query = "UPDATE LQV_Kho SET LQV_ten_kho = ?, LQV_dia_chi = ?, LQV_nguoi_quan_ly = ?, LQV_suc_chua = ? WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, kho.getLQV_ten_kho());
            stmt.setString(2, kho.getLQV_dia_chi());
            stmt.setString(3, kho.getLQV_nguoi_quan_ly());
            stmt.setInt(4, kho.getLQV_suc_chua());
            stmt.setInt(5, kho.getLQVid());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM LQV_Kho WHERE LQVid = ?";
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

    public boolean isKhoFull(int khoId) {
        String query = "SELECT LQV_suc_chua, (SELECT SUM(LQV_so_luong) FROM LQV_VatTuYTe WHERE LQV_kho_id = ?) AS total FROM LQV_Kho WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, khoId);
            stmt.setInt(2, khoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int sucChua = rs.getInt("LQV_suc_chua");
                    int totalStock = rs.getInt("total");
                    return totalStock >= sucChua;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private LQV_Kho mapResultSetToKho(ResultSet rs) throws SQLException {
        return new LQV_Kho(
            rs.getInt("LQVid"),
            rs.getString("LQV_ten_kho"),
            rs.getString("LQV_dia_chi"),
            rs.getString("LQV_nguoi_quan_ly"),
            rs.getInt("LQV_suc_chua")
        );
    }
}
