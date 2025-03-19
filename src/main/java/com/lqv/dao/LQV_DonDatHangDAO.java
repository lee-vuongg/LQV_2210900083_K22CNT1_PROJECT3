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
	    
	    System.out.println("DEBUG: Chuẩn bị thực thi truy vấn: " + query);  // Debug truy vấn SQL

	    try (Connection conn = DBConnection.getConnection()) {
	        if (conn == null) {
	            System.out.println("DEBUG: Lỗi kết nối đến database!");
	            return list;
	        } else {
	            System.out.println("DEBUG: Kết nối database thành công.");
	        }

	        try (PreparedStatement stmt = conn.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                LQV_DonDatHang donHang = mapResultSetToDonDatHang(rs);
	                list.add(donHang);

	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("DEBUG: Lỗi SQL - " + e.getMessage());
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
        String query = "INSERT INTO LQV_DonDatHang (LQV_ngay_dat_hang, LQVid_nha_cung_cap, LQV_tong_tien, LQV_tinh_trang) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Debug: In ra các giá trị trước khi set vào query
            System.out.println("DEBUG: Chuẩn bị INSERT đơn đặt hàng");
            System.out.println("DEBUG: Ngày đặt hàng = " + donDatHang.getLQV_ngay_dat_hang());
            System.out.println("DEBUG: ID Nhà Cung Cấp = " + donDatHang.getLQVid_nha_cung_cap());
            System.out.println("DEBUG: Tổng tiền = " + donDatHang.getLQV_tong_tien());
            System.out.println("DEBUG: Tình trạng = " + donDatHang.getLQV_tinh_trang());

            stmt.setTimestamp(1, donDatHang.getLQV_ngay_dat_hang()); // Sử dụng Timestamp
            stmt.setInt(2, donDatHang.getLQVid_nha_cung_cap());
            stmt.setDouble(3, donDatHang.getLQV_tong_tien());
            stmt.setString(4, donDatHang.getLQV_tinh_trang());

            int affectedRows = stmt.executeUpdate();

            // Debug: Kiểm tra số dòng bị ảnh hưởng
            System.out.println("DEBUG: Số dòng bị ảnh hưởng = " + affectedRows);

            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("LỖI: Không thể thêm đơn đặt hàng!");
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean exists(int id) {
        String sql = "SELECT COUNT(*) FROM LQV_DonDatHang WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu tìm thấy ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(LQV_DonDatHang donDatHang) {
        System.out.println("DEBUG: Hàm update() được gọi với ID = " + donDatHang.getLQVid());

        String query = "UPDATE LQV_DonDatHang SET LQV_ngay_dat_hang = ?, LQVid_nha_cung_cap = ?, LQV_tong_tien = ?, LQV_tinh_trang = ? WHERE LQVid = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setTimestamp(1, donDatHang.getLQV_ngay_dat_hang());
            stmt.setInt(2, donDatHang.getLQVid_nha_cung_cap());
            stmt.setDouble(3, donDatHang.getLQV_tong_tien());
            stmt.setString(4, donDatHang.getLQV_tinh_trang());
            stmt.setInt(5, donDatHang.getLQVid());

            // 🔥 Debug dữ liệu trước khi thực hiện UPDATE
            System.out.println("DEBUG: SQL UPDATE: " + query);
            System.out.println("DEBUG: Giá trị tham số:");
            System.out.println("  - Ngày đặt hàng: " + donDatHang.getLQV_ngay_dat_hang());
            System.out.println("  - Nhà cung cấp: " + donDatHang.getLQVid_nha_cung_cap());
            System.out.println("  - Tổng tiền: " + donDatHang.getLQV_tong_tien());
            System.out.println("  - Tình trạng: " + donDatHang.getLQV_tinh_trang());
            System.out.println("  - ID: " + donDatHang.getLQVid());

            int affectedRows = stmt.executeUpdate();

            // 🔥 Kiểm tra số dòng bị ảnh hưởng
            System.out.println("DEBUG: affectedRows = " + affectedRows);

            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("ERROR: Lỗi khi cập nhật đơn đặt hàng!");
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
        String query = "UPDATE LQV_DonDatHang SET LQV_tinh_trang = ? WHERE LQVid = ?";
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
            rs.getTimestamp("LQV_ngay_dat_hang"), // Sửa từ getDate() -> getTimestamp()
            rs.getInt("LQVid_nha_cung_cap"),
            rs.getDouble("LQV_tong_tien"),
            rs.getString("LQV_tinh_trang")
        );
    }
}
