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
	            ncc.setLQV_dien_thoai(rs.getString("LQV_dien_thoai"));
	            ncc.setLQV_email(rs.getString("LQV_email"));
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
	public static void main(String[] args) {
	    LQV_NhaCungCapDAO dao = new LQV_NhaCungCapDAO();
	    LQV_NhaCungCap ncc = dao.getById(1); // Giả sử id = 1
	    if (ncc != null) {
	        System.out.println("Tên nhà cung cấp: " + ncc.getLQV_ten_nha_cung_cap());
	        System.out.println("Địa chỉ: " + ncc.getLQV_dia_chi());
	        System.out.println("Số điện thoại: " + ncc.getLQV_dien_thoai());
	        System.out.println("Email: " + ncc.getLQV_email());
	    } else {
	        System.out.println("Không tìm thấy nhà cung cấp.");
	    }
	}

	public LQV_NhaCungCap getById(int id) {
	    LQV_NhaCungCap ncc = null;
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LQV_NhaCungCap WHERE LQVid = ?")) {
	        stmt.setInt(1, id);
	        System.out.println("Truy vấn SQL: " + stmt.toString());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            ncc = new LQV_NhaCungCap();
	            ncc.setLQVid(rs.getInt("LQVid"));
	            ncc.setLQV_ten_nha_cung_cap(rs.getString("LQV_ten_nha_cung_cap"));
	            ncc.setLQV_dia_chi(rs.getString("LQV_dia_chi"));
	            ncc.setLQV_dien_thoai(rs.getString("LQV_dien_thoai"));
	            ncc.setLQV_email(rs.getString("LQV_email")); // Thêm dòng này
	        }
	        System.out.println("Kết quả truy vấn: " + (ncc != null ? "Tìm thấy" : "Không tìm thấy"));
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("DAO trả về NCC: " + (ncc != null ? ncc.getLQV_ten_nha_cung_cap() : "NULL"));

	    return ncc;
	}

    // Thêm nhà cung cấp mới
    public boolean insert(LQV_NhaCungCap ncc) {
        String query = "INSERT INTO LQV_NhaCungCap (LQV_ten_nha_cung_cap, LQV_dia_chi, LQV_so_dien_thoai, LQV_email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, ncc.getLQV_ten_nha_cung_cap());
            stmt.setString(2, ncc.getLQV_dia_chi());
            stmt.setString(3, ncc.getLQV_dien_thoai());
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
        String sql = "UPDATE LQV_nhacungcap SET LQV_ten_nha_cung_cap=?, LQV_dia_chi=?, LQV_dien_thoai=?, LQV_email=? WHERE LQVid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ncc.getLQV_ten_nha_cung_cap());
            stmt.setString(2, ncc.getLQV_dia_chi());
            stmt.setString(3, ncc.getLQV_dien_thoai());
            stmt.setString(4, ncc.getLQV_email());
            stmt.setInt(5, ncc.getLQVid());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(">>> Số dòng bị ảnh hưởng: " + rowsUpdated);

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(">>> Lỗi SQL: " + e.getMessage());
            return false;
        }
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
    		    rs.getInt("LQV_id"),
    		    rs.getString("LQV_ten"),
    		    rs.getString("LQV_diaChi"),
    		    rs.getString("LQV_soDienThoai"),
    		    rs.getString("LQV_email")
    		);

    }
} 