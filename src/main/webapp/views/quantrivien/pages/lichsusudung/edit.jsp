<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lqv.models.LQV_LichSuSuDung, com.lqv.dao.LQV_LichSuSuDungDAO" %>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
    String idParam = request.getParameter("id");
    int id = 0;
    if (idParam != null && !idParam.isEmpty()) {
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    
    LQV_LichSuSuDungDAO lichSuDAO = new LQV_LichSuSuDungDAO();
    LQV_LichSuSuDung lichSu = lichSuDAO.getById(id);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa Lịch sử Sử Dụng</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: auto;
            padding: 20px;
            background-color: #f9f9f9;
        }
        h2 { text-align: center; }
        form {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 15px;
        }
        button:hover { background: #218838; }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h2>Chỉnh sửa Lịch sử Sử Dụng</h2>
    <% if (lichSu != null) { %>
        <form action="<%= request.getContextPath() %>/lichsu" method="post">
            <input type="hidden" name="action" value="update">
              <label>ID:</label>
			    <input type="text" name="LQVid" value="<%= lichSu.getLQVid() %>" readonly>
            <label for="LQVid_vat_tu">Mã Vật Tư:</label>
            <input type="number" id="LQVid_vat_tu" name="LQVid_vat_tu" value="<%= lichSu.getLQVid_vat_tu() %>" required>
            
            <label for="LQVid_khach_hang">Mã Khách Hàng:</label>
            <input type="number" id="LQVid_khach_hang" name="LQVid_khach_hang" value="<%= lichSu.getLQVid_khach_hang() %>" required>
            
            <label for="LQV_nguoi_su_dung">Người Sử Dụng:</label>
            <input type="text" id="LQV_nguoi_su_dung" name="LQV_nguoi_su_dung" value="<%= lichSu.getLQV_nguoi_su_dung() %>" required>
            
            <label for="LQV_ngay_su_dung">Ngày Sử Dụng:</label>
            <input type="date" id="LQV_ngay_su_dung" name="LQV_ngay_su_dung" value="<%= (lichSu.getLQV_ngay_su_dung() != null) ? sdf.format(lichSu.getLQV_ngay_su_dung()) : "" %>" required>
            
            <label for="LQV_so_luong_su_dung">Số Lượng Sử Dụng:</label>
            <input type="number" id="LQV_so_luong_su_dung" name="LQV_so_luong_su_dung" value="<%= lichSu.getLQV_so_luong_su_dung() %>" required>
            
            <label for="LQV_ghi_chu">Ghi Chú:</label>
            <textarea id="LQV_ghi_chu" name="LQV_ghi_chu"><%= (lichSu.getLQV_ghi_chu() != null) ? lichSu.getLQV_ghi_chu() : "" %></textarea>
            
            <button type="submit">Lưu Thay Đổi</button>
        </form>
    <% } else { %>
        <p style="color: red; text-align: center;">Lỗi: Không tìm thấy dữ liệu lịch sử sử dụng.</p>
    <% } %>
    <a href="list-lichsu.jsp" class="back-link">Quay lại danh sách</a>
</body>
</html>
