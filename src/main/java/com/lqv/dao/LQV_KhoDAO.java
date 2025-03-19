package com.lqv.dao;

import com.lqv.models.LQV_Kho;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_KhoDAO {

	public List<LQV_Kho> getAll() {
	    List<LQV_Kho> danhSachKho = new ArrayList<>();
	    String query = "SELECT * FROM LQV_Kho";

	    try (Connection conn = DBConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        System.out.println("Executing query: " + query);

	        while (rs.next()) {
	            LQV_Kho kho = mapKho(rs);
	            danhSachKho.add(kho);
	            System.out.println("Fetched: " + kho);
	        }
	    } catch (SQLException e) {
	        System.err.println("Lỗi khi lấy danh sách kho: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return danhSachKho;
	}


    // Lấy kho theo ID
    public LQV_Kho getById(int id) {
        String query = "SELECT * FROM LQV_Kho WHERE LQVid = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapKho(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy kho theo ID", e);
        }
        return null;
    }

    // Thêm mới kho
    public boolean insert(LQV_Kho kho) {
        String query = "INSERT INTO LQV_Kho (LQV_ten_kho, LQV_vi_tri, LQV_nguoi_quan_ly, LQV_suc_chua) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // 🛠 Debug: In ra giá trị tham số trước khi thực hiện INSERT
            System.out.println("Executing Insert: " + query);
            System.out.println("Params: " +
                kho.getLQV_ten_kho() + ", " +
                kho.getLQV_vi_tri() + ", " +
                kho.getLQV_nguoi_quan_ly() + ", " +
                kho.getLQV_suc_chua());

            // Gán giá trị vào PreparedStatement
            setKhoParams(pstmt, kho);

            // Thực hiện INSERT và lấy số dòng ảnh hưởng
            int rowsInserted = pstmt.executeUpdate();

            // 🛠 Debug: Kiểm tra số dòng bị ảnh hưởng
            System.out.println("Rows inserted: " + rowsInserted);

            return rowsInserted > 0;
        } catch (SQLException e) {
            // 🛠 Debug: In lỗi SQL
            System.out.println("SQL Insert Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm kho", e);
        }
    }

    // Cập nhật kho
    public boolean update(LQV_Kho kho) {
        String sql = "UPDATE LQV_Kho SET LQV_ten_kho = ?, LQV_vi_tri = ?, LQV_nguoi_quan_ly = ?, LQV_suc_chua = ? WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, kho.getLQV_ten_kho());
            stmt.setString(2, kho.getLQV_vi_tri());
            stmt.setString(3, kho.getLQV_nguoi_quan_ly());
            stmt.setInt(4, kho.getLQV_suc_chua());
            stmt.setInt(5, kho.getLQVid());

            // 🛠 Debug: In ra giá trị của các tham số
            System.out.println("Executing Update: " + sql);
            System.out.println("Params: " +
                kho.getLQV_ten_kho() + ", " +
                kho.getLQV_vi_tri() + ", " +
                kho.getLQV_nguoi_quan_ly() + ", " +
                kho.getLQV_suc_chua() + ", ID=" + kho.getLQVid());

            int rowsUpdated = stmt.executeUpdate();

            // 🛠 Debug: Kiểm tra số dòng bị ảnh hưởng
            System.out.println("Rows updated: " + rowsUpdated);

            return rowsUpdated > 0;
        } catch (SQLException e) {
            // 🛠 Debug: In lỗi SQL
            System.out.println("SQL Update Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    // Xóa kho theo ID
    public boolean delete(int id) {
        String query = "DELETE FROM LQV_Kho WHERE LQVid = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa kho", e);
        }
    }

    // Kiểm tra xem kho có đầy không
    public boolean isKhoFull(int khoId, int soLuongHienTai) {
        String query = "SELECT LQV_suc_chua FROM LQV_Kho WHERE LQVid = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, khoId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return soLuongHienTai >= rs.getInt("LQV_suc_chua");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi kiểm tra sức chứa kho", e);
        }
        return false;
    }

    // Ánh xạ dữ liệu từ ResultSet sang đối tượng LQV_Kho
    private LQV_Kho mapKho(ResultSet rs) throws SQLException {
        return new LQV_Kho(
            rs.getInt("LQVid"),
            rs.getString("LQV_ten_kho"),
            rs.getString("LQV_vi_tri"),
            rs.getString("LQV_nguoi_quan_ly"),
            rs.getInt("LQV_suc_chua")
        );
    }

    // Thiết lập tham số cho PreparedStatement
    private void setKhoParams(PreparedStatement pstmt, LQV_Kho kho) throws SQLException {
        pstmt.setString(1, kho.getLQV_ten_kho());
        pstmt.setString(2, kho.getLQV_vi_tri());
        pstmt.setString(3, kho.getLQV_nguoi_quan_ly());
        pstmt.setInt(4, kho.getLQV_suc_chua());
    }
}
