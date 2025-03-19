<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.lqv.models.LQV_KhachHang, com.lqv.dao.LQV_KhachHangDAO" %>

<%
    // Lấy danh sách khách hàng từ database
    LQV_KhachHangDAO khachHangDAO = new LQV_KhachHangDAO();
    List<LQV_KhachHang> danhSachKhachHang = khachHangDAO.getAll();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh sách Khách hàng</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/list-admin.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <div class="container">
        <h2>Danh sách Khách hàng</h2>
        
        <!-- Nút thêm khách hàng -->
        <div class="actions">
            <button class="add-btn" onclick="window.location.href='<%= request.getContextPath() %>/views/quantrivien/pages/khachhang/add.jsp'">➕ Thêm Khách Hàng</button>
        </div>

        <div class="card-footer text-center">
            <a href="<%= request.getContextPath() %>/views/admin.jsp" class="btn btn-link">Quay lại trang chủ</a>
        </div>

<table border="1" cellspacing="0" cellpadding="8">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tài Khoản</th>
            <th>Họ Tên</th>
            <th>Email</th>
            <th>Địa Chỉ</th>
            <th>Số Điện Thoại</th>
            <th>Trạng Thái</th>
            <th>Ngày Sửa</th> <!-- Thêm cột Ngày Sửa -->
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <% for (LQV_KhachHang kh : danhSachKhachHang) { %>
            <tr id="row-<%= kh.getLQVid() %>">
                <td><%= kh.getLQVid() %></td>
                <td><%= kh.getLQV_tai_khoan() %></td>
                <td><%= kh.getLQV_ho_ten() %></td>
                <td><%= kh.getLQV_email() %></td>
                <td><%= kh.getLQV_dia_chi() %></td>
                <td><%= kh.getLQV_so_dien_thoai() %></td>
                <td><%= kh.isLQV_trang_thai() ? "Hoạt động" : "Bị khóa" %></td>
                <td><%= kh.getLQV_ngay_sua() %></td> <!-- Hiển thị Ngày Sửa -->
                <td>
                    <button class="edit-btn" onclick="editKhachHang(<%= kh.getLQVid() %>)">✏ Sửa</button>
                    <button class="delete-btn" onclick="deleteKhachHang(<%= kh.getLQVid() %>)">🗑 Xóa</button>
                </td>
            </tr>
        <% } %>
    </tbody>
    <a href="<%= request.getContextPath() %>/views/quantrivien/pages/khachhang/edit.jsp">Chỉnh sửa</a>
    
</table>

    </div>

    <script>
    function deleteKhachHang(id) {
        if (confirm("Bạn có chắc chắn muốn xóa khách hàng này không?")) {
            $.ajax({
                type: "GET",
                url: "khachhang?action=delete&id=" + id,
                success: function(response) {
                    if (response === "success") {
                        alert("Xóa thành công!");
                        location.reload();
                    } else {
                        alert("Lỗi khi xóa. Vui lòng thử lại.");
                    }
                },
                error: function() {
                    alert("Lỗi khi gửi request!");
                }
            });
        }
    }

    function editKhachHang(id) {
        window.location.href = "<%= request.getContextPath() %>/views/quantrivien/pages/khachhang/edit.jsp?id=" + id;
    }
    </script>

</body>
</html>
