package com.lqv.dao;

import com.lqv.models.LQV_BaoTriThietBi;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_BaoTriThietBiDAO {

    public List<LQV_BaoTriThietBi> getAll() {
        List<LQV_BaoTriThietBi> list = new ArrayList<>();
        String query = "SELECT * FROM LQV_BaoTriThietBi";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToBaoTri(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LQV_BaoTriThietBi getById(int id) {
        String query = "SELECT * FROM LQV_BaoTriThietBi WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBaoTri(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(LQV_BaoTriThietBi baoTri) {
        String query = "INSERT INTO LQV_BaoTriThietBi (LQVid_vat_tu, LQV_ngay_bao_tri, LQV_mo_ta, LQV_chi_phi) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, baoTri.getLQVid_vat_tu());
            stmt.setDate(2, new java.sql.Date(baoTri.getLQV_ngay_bao_tri().getTime()));
            stmt.setString(3, baoTri.getLQV_mo_ta());
            stmt.setDouble(4, baoTri.getLQV_chi_phi());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LQV_BaoTriThietBi baoTri) {
        String query = "UPDATE LQV_BaoTriThietBi SET LQVid_vat_tu = ?, LQV_ngay_bao_tri = ?, LQV_mo_ta = ?, LQV_chi_phi = ? WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, baoTri.getLQVid_vat_tu());
            stmt.setDate(2, new java.sql.Date(baoTri.getLQV_ngay_bao_tri().getTime()));
            stmt.setString(3, baoTri.getLQV_mo_ta());
            stmt.setDouble(4, baoTri.getLQV_chi_phi());
            stmt.setInt(5, baoTri.getLQVid());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM LQV_BaoTriThietBi WHERE LQVid = ?";
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

    private LQV_BaoTriThietBi mapResultSetToBaoTri(ResultSet rs) throws SQLException {
        return new LQV_BaoTriThietBi(
            rs.getInt("LQVid"),
            rs.getInt("LQVid_vat_tu"),
            rs.getDate("LQV_ngay_bao_tri"),
            rs.getString("LQV_mo_ta"),
            rs.getDouble("LQV_chi_phi")
        );
    }
}
