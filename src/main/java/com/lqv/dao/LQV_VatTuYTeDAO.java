package com.lqv.dao;

import com.lqv.models.LQV_VatTuYTe;
import com.lqv.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LQV_VatTuYTeDAO {

	public List<LQV_VatTuYTe> getAll() {
	    List<LQV_VatTuYTe> list = new ArrayList<>();
	    String query = "SELECT * FROM LQV_VatTuYTe";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        // Kiểm tra kết quả truy vấn
	        if (rs != null) {
	            System.out.println("Dữ liệu đã được truy vấn thành công.");
	        }

	        while (rs.next()) {
	            LQV_VatTuYTe vatTu = mapResultSetToVatTu(rs);
	            list.add(vatTu);
	            // In thông tin từng vật tư để kiểm tra
	            System.out.println(vatTu);  // Hoặc in các thuộc tính của vật tư
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    // Kiểm tra danh sách kết quả
	    if (list.isEmpty()) {
	        System.out.println("Không có dữ liệu vật tư.");
	    } else {
	        System.out.println("Đã lấy " + list.size() + " vật tư.");
	    }

	    return list;
	}


    // Tìm vật tư y tế theo ID
    public LQV_VatTuYTe getById(int id) {
        String query = "SELECT * FROM LQV_VatTuYTe WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToVatTu(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm mới vật tư y tế
    public boolean insert(LQV_VatTuYTe vatTu) {
        String query = "INSERT INTO LQV_VatTuYTe (LQV_ten_vat_tu, LQV_ma_vat_tu, LQV_so_luong, LQV_don_gia, LQV_ngay_het_han, LQV_mo_ta, LQV_ngay_sua) " +
                       "VALUES (?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, vatTu.getLQV_ten_vat_tu());
            stmt.setString(2, vatTu.getLQV_ma_vat_tu());
            stmt.setInt(3, vatTu.getLQV_so_luong());
            stmt.setDouble(4, vatTu.getLQV_don_gia());
            stmt.setDate(5, new java.sql.Date(vatTu.getLQV_ngay_het_han().getTime()));
            stmt.setString(6, vatTu.getLQV_mo_ta());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin vật tư y tế
    public boolean update(LQV_VatTuYTe vatTu) {
        String query = "UPDATE LQV_VatTuYTe SET LQV_ten_vat_tu = ?, LQV_ma_vat_tu = ?, LQV_so_luong = ?, LQV_don_gia = ?, LQV_ngay_het_han = ?, LQV_mo_ta = ?, LQV_ngay_sua = NOW() " +
                       "WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, vatTu.getLQV_ten_vat_tu());
            stmt.setString(2, vatTu.getLQV_ma_vat_tu());
            stmt.setInt(3, vatTu.getLQV_so_luong());
            stmt.setDouble(4, vatTu.getLQV_don_gia());
            stmt.setDate(5, new java.sql.Date(vatTu.getLQV_ngay_het_han().getTime()));
            stmt.setString(6, vatTu.getLQV_mo_ta());
            stmt.setInt(7, vatTu.getLQVid());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa vật tư y tế theo ID
    public boolean delete(int id) {
        String query = "DELETE FROM LQV_VatTuYTe WHERE LQVid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra vật tư theo mã đã tồn tại chưa
    public boolean isMaVatTuExist(String maVatTu) {
        String query = "SELECT 1 FROM LQV_VatTuYTe WHERE LQV_ma_vat_tu = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, maVatTu);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Trả về true nếu có kết quả
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm hỗ trợ ánh xạ từ ResultSet sang đối tượng LQV_VatTuYTe// Hàm hỗ trợ ánh xạ từ ResultSet sang đối tượng LQV_VatTuYTe
    private LQV_VatTuYTe mapResultSetToVatTu(ResultSet rs) throws SQLException {
        return new LQV_VatTuYTe(
            rs.getInt("LQVid"),
            rs.getString("LQV_ten_vat_tu"),
            rs.getString("LQV_ma_vat_tu"),
            rs.getInt("LQV_so_luong"),
            rs.getDouble("LQV_don_gia"),
            rs.getDate("LQV_ngay_het_han"),
            rs.getString("LQV_mo_ta"),
            rs.getDate("LQV_ngay_tao") // Thêm ngày tạo
        );
    }

}
