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

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToLichSuSuDung(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LQV_LichSuSuDung getById(int id) {
        String query = "SELECT * FROM LQV_LichSuSuDung WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLichSuSuDung(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(LQV_LichSuSuDung lichSu) {
        String query = "INSERT INTO LQV_LichSuSuDung (LQVid_vat_tu, LQVid_khach_hang, LQV_ngay_su_dung, LQV_so_luong) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, lichSu.getLQVid_vat_tu());
            stmt.setInt(2, lichSu.getLQVid_khach_hang());
            stmt.setDate(3, new java.sql.Date(lichSu.getLQV_ngay_su_dung().getTime()));
            stmt.setInt(4, lichSu.getLQV_so_luong());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LQV_LichSuSuDung lichSu) {
        String query = "UPDATE LQV_LichSuSuDung SET LQVid_vat_tu = ?, LQVid_khach_hang = ?, LQV_ngay_su_dung = ?, LQV_so_luong = ? WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, lichSu.getLQVid_vat_tu());
            stmt.setInt(2, lichSu.getLQVid_khach_hang());
            stmt.setDate(3, new java.sql.Date(lichSu.getLQV_ngay_su_dung().getTime()));
            stmt.setInt(4, lichSu.getLQV_so_luong());
            stmt.setInt(5, lichSu.getLQVid());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM LQV_LichSuSuDung WHERE LQVid = ?";
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

    private LQV_LichSuSuDung mapResultSetToLichSuSuDung(ResultSet rs) throws SQLException {
        return new LQV_LichSuSuDung(
            rs.getInt("LQVid"),
            rs.getInt("LQVid_vat_tu"),
            rs.getInt("LQVid_khach_hang"),
            rs.getDate("LQV_ngay_su_dung"),
            rs.getInt("LQV_so_luong")
        );
    }
}
