package com.lqv.dao;

import com.lqv.models.LQV_NhaCungCap;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_NhaCungCapDAO {

    // Lấy toàn bộ danh sách nhà cung cấp
	public List<LQV_NhaCungCap> getAll() {
	    List<LQV_NhaCungCap> list = new ArrayList<>();
	    String query = "SELECT * FROM LQV_NhaCungCap";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            LQV_NhaCungCap ncc = new LQV_NhaCungCap();
	            ncc.setLQVid(rs.getInt("LQVid"));
	            ncc.setLQV_ten_nha_cung_cap(rs.getString("LQV_ten_nha_cung_cap"));
	            ncc.setLQV_dia_chi(rs.getString("LQV_dia_chi"));
	            ncc.setLQV_so_dien_thoai(rs.getString("LQV_dien_thoai"));
	            list.add(ncc);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	  
        if (list.isEmpty()) {
	        System.out.println("Không có dữ liệu vật tư.");
	    } else {
	        System.out.println("Đã lấy " + list.size() + " vật tư.");
	    }

        return list;
    }

	public LQV_NhaCungCap getById(int id) {
	    LQV_NhaCungCap ncc = null;
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LQV_NhaCungCap WHERE LQVid = ?")) {
	        
	        stmt.setInt(1, id);
	        System.out.println("Thực thi query với ID: " + id); // In ra kiểm tra
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            System.out.println("Tìm thấy nhà cung cấp!");
	            ncc = new LQV_NhaCungCap(
	                rs.getInt("LQVid"),
	                rs.getString("LQV_ten_nha_cung_cap"),
	                rs.getString("LQV_dia_chi"),
	                rs.getString("LQV_so_dien_thoai"),
	                rs.getString("LQV_email")
	            );
	        } else {
	            System.out.println("Không tìm thấy nhà cung cấp!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ncc;
	}
    // Thêm nhà cung cấp mới
    public boolean insert(LQV_NhaCungCap ncc) {
        String query = "INSERT INTO LQV_NhaCungCap (LQV_ten_nha_cung_cap, LQV_dia_chi, LQV_so_dien_thoai, LQV_email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, ncc.getLQV_ten_nha_cung_cap());
            stmt.setString(2, ncc.getLQV_dia_chi());
            stmt.setString(3, ncc.getLQV_so_dien_thoai());
            stmt.setString(4, ncc.getLQV_email());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin nhà cung cấp
    public boolean update(LQV_NhaCungCap ncc) {
        String query = "UPDATE LQV_NhaCungCap SET LQV_ten_nha_cung_cap = ?, LQV_dia_chi = ?, LQV_so_dien_thoai = ?, LQV_email = ? WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ncc.getLQV_ten_nha_cung_cap());
            stmt.setString(2, ncc.getLQV_dia_chi());
            stmt.setString(3, ncc.getLQV_so_dien_thoai());
            stmt.setString(4, ncc.getLQV_email());
            stmt.setInt(5, ncc.getLQVid());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa nhà cung cấp theo ID
    public boolean delete(int id) {
        String query = "DELETE FROM LQV_NhaCungCap WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Ánh xạ ResultSet -> Đối tượng LQV_NhaCungCap
    private LQV_NhaCungCap mapResultSetToNhaCungCap(ResultSet rs) throws SQLException {
        return new LQV_NhaCungCap(
            rs.getInt("LQVid"),
            rs.getString("LQV_ten_nha_cung_cap"),
            rs.getString("LQV_dia_chi"),
            rs.getString("LQV_so_dien_thoai"),
            rs.getString("LQV_email")
        );
    }
}
