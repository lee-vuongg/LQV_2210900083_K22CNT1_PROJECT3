<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lqv.dao.LQV_NhaCungCapDAO" %>
<%@ page import="com.lqv.models.LQV_NhaCungCap" %>
<%@ page import="java.io.IOException" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    LQV_NhaCungCapDAO dao = new LQV_NhaCungCapDAO();
    LQV_NhaCungCap ncc = dao.getById(id);
    if (ncc == null) {
        response.sendRedirect("list-nhacungcap.jsp"); // Nếu không tìm thấy, quay về danh sách
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Nhà Cung Cấp</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Chỉnh Sửa Nhà Cung Cấp</h2>
     <form action="${pageContext.request.contextPath}/nhacungcap" method="post">
      <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= ncc.getLQVid() %>">
        
        <label for="ten">Tên Nhà Cung Cấp:</label>
        <input type="text" id="ten" name="ten" value="<%= ncc.getLQV_ten_nha_cung_cap() %>" required>
        
        <label for="diachi">Địa Chỉ:</label>
        <input type="text" id="diachi" name="diachi" value="<%= ncc.getLQV_dia_chi() %>" required>
        
        <label for="dienthoai">Số Điện Thoại:</label>
        <input type="text" id="dienthoai" name="dienthoai" value="<%= ncc.getLQV_dien_thoai() %>" required>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= ncc.getLQV_email() %>" required>
        
        <button type="submit">Lưu Thay Đổi</button>
        <a href="list-nhacungcap.jsp">Hủy</a>
    </form>
</body>
</html>
