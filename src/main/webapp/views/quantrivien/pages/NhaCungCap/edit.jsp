<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lqv.models.LQV_NhaCungCap" %>
<%
    LQV_NhaCungCap ncc = (LQV_NhaCungCap) request.getAttribute("ncc");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Nhà Cung Cấp</title>
    <link rel="stylesheet" href="/assets/css/adminlte.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Chỉnh Sửa Nhà Cung Cấp</h2>
    <form action="${pageContext.request.contextPath}/nhacungcap" method="post">
        <input type="hidden" name="LQVid" value="<%= ncc.getLQVid() %>">
        
        <div class="form-group">
            <label for="ten">Tên Nhà Cung Cấp</label>
            <input type="text" id="ten" name="LQV_ten_nha_cung_cap" class="form-control" value="<%= ncc.getLQV_ten_nha_cung_cap() %>" required>
        </div>
        
        <div class="form-group">
            <label for="diaChi">Địa Chỉ</label>
            <input type="text" id="diaChi" name="LQV_dia_chi" class="form-control" value="<%= ncc.getLQV_dia_chi() %>" required>
        </div>
        
        <div class="form-group">
            <label for="soDienThoai">Số Điện Thoại</label>
            <input type="text" id="soDienThoai" name="LQV_so_dien_thoai" class="form-control" value="<%= ncc.getLQV_so_dien_thoai() %>" required>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="LQV_email" class="form-control" value="<%= ncc.getLQV_email() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
        <a href="/admin/nhacungcap-list.jsp" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>