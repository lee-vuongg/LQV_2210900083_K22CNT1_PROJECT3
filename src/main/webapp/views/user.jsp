<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lqv.models.LQV_KhachHang" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessionKH = request.getSession();
    LQV_KhachHang khachHang = (LQV_KhachHang) sessionKH.getAttribute("khachHang");

    if (khachHang == null) {
        response.sendRedirect(request.getContextPath() + "/views/login-user.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang Khách Hàng</title>
</head>
<body>
    <h2>Chào mừng, <%= khachHang.getLQV_ho_ten() %>!</h2>
    <p>Email: <%= khachHang.getLQV_email() %></p>
    <p>Địa chỉ: <%= khachHang.getLQV_dia_chi() %></p>

    <a href="<%= request.getContextPath() %>/LogoutServlet">Đăng xuất</a>
</body>
</html>
