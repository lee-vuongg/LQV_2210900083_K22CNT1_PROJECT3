<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Khách Hàng</title>
    <link rel="stylesheet" href="assets/css/style.css"> <!-- Thêm file CSS nếu có -->
</head>
<body>

    <h2>Thêm Khách Hàng</h2>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
       <p style="color: red;"><%= error %></p>
    <% } %>
	<form action="${pageContext.request.contextPath}/khachhang?action=insert"method="post">
        <label for="tai_khoan">Tài khoản:</label>
        <input type="text" id="tai_khoan" name="tai_khoan" required>

        <label for="mat_khau">Mật khẩu:</label>
        <input type="password" id="mat_khau" name="mat_khau" required>

        <label for="ho_ten">Họ và tên:</label>
        <input type="text" id="ho_ten" name="ho_ten" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="dia_chi">Địa chỉ:</label>
        <input type="text" id="dia_chi" name="dia_chi">

        <label for="so_dien_thoai">Số điện thoại:</label>
        <input type="text" id="so_dien_thoai" name="so_dien_thoai" required>

        <label for="trang_thai">Trạng thái:</label>
        <select id="trang_thai" name="trang_thai">
            <option value="true">Hoạt động</option>
            <option value="false">Không hoạt động</option>
        </select>

        <button type="submit">Thêm khách hàng</button>
    </form>

    <br>
    <a href="khachhang">Quay lại danh sách</a>

</body>
</html>
