<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.lqv.dao.LQV_LichSuSuDungDAO, com.lqv.models.LQV_LichSuSuDung" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%
    String message = "";
    if (request.getMethod().equalsIgnoreCase("POST")) {
        try {
            int vatTuId = Integer.parseInt(request.getParameter("vatTuId"));
            int khachHangId = Integer.parseInt(request.getParameter("khachHangId"));
            String nguoiSuDung = request.getParameter("nguoiSuDung");
            Date ngaySuDung = Date.valueOf(request.getParameter("ngaySuDung"));
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            String ghiChu = request.getParameter("ghiChu");

            LQV_LichSuSuDung lichSu = new LQV_LichSuSuDung(0, vatTuId, khachHangId, nguoiSuDung, ngaySuDung, soLuong, ghiChu);
            LQV_LichSuSuDungDAO dao = new LQV_LichSuSuDungDAO();
            boolean result = dao.insert(lichSu);

            if (result) {
                message = "Thêm mới thành công!";
            } else {
                message = "Có lỗi xảy ra, vui lòng thử lại!";
            }
        } catch (Exception e) {
            message = "Lỗi: " + e.getMessage();
        }
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Lịch Sử Sử Dụng</title>
</head>
<body>
    <h2>Thêm Lịch Sử Sử Dụng</h2>
    <% if (!message.isEmpty()) { %>
        <p style="color: red;"><%= message %></p>
    <% } %>
    <form method="post">
        <label>Vật Tư ID:</label>
        <input type="number" name="vatTuId" required><br>
        
        <label>Khách Hàng ID:</label>
        <input type="number" name="khachHangId" required><br>
        
        <label>Người Sử Dụng:</label>
        <input type="text" name="nguoiSuDung" required><br>
        
        <label>Ngày Sử Dụng:</label>
        <input type="date" name="ngaySuDung" required><br>
        
        <label>Số Lượng:</label>
        <input type="number" name="soLuong" required><br>
        
        <label>Ghi Chú:</label>
        <textarea name="ghiChu"></textarea><br>
        
        <button type="submit">Thêm</button>
    </form>
</body>
</html>
