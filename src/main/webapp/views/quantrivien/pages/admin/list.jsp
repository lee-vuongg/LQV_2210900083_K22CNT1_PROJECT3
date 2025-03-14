<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.lqv.models.LQV_QuanTriVien, com.lqv.dao.LQV_QuanTriVienDAO" %>

<%
    // Lấy danh sách quản trị viên từ database
    LQV_QuanTriVienDAO qtvDAO = new LQV_QuanTriVienDAO();
    List<LQV_QuanTriVien> listQuanTriVien = qtvDAO.getAll();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh sách Quản trị viên</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/list-admin.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <div class="container">
        <h2>Danh sách Quản trị viên</h2>
        
        <!-- Nút thêm quản trị viên -->
        <div class="actions">
            <button class="add-btn" onclick="window.location.href='<%= request.getContextPath() %>/views/quantrivien/pages/admin/add.jsp'">➕ Thêm Quản Trị Viên</button>
        
        </div>
         <div class="card-footer text-center">
                    <a href="<%= request.getContextPath() %>/views/admin.jsp" class="btn btn-link">Quay lại trang chủ</a>
                </div>

        <table border="1" cellspacing="0" cellpadding="8">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tài khoản</th>
                    <th>Trạng thái</th>
                    <th>Ngày tạo</th>
                    <th>Ngày sửa</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <% for (LQV_QuanTriVien qtv : listQuanTriVien) { %>
                    <tr id="row-<%= qtv.getLQVid() %>">
                        <td><%= qtv.getLQVid() %></td>
                        <td><%= qtv.getLQV_tai_khoan() %></td>
                        <td>
                            <% if (qtv.isLQV_trang_thai()) { %>
                                <span style="color: green;">Hoạt động</span>
                            <% } else { %>
                                <span style="color: red;">Bị khóa</span>
                            <% } %>
                        </td>
                        <td><%= qtv.getLQV_ngay_tao() %></td>
                        <td><%= qtv.getLQV_ngay_sua() != null ? qtv.getLQV_ngay_sua() : "Chưa cập nhật" %></td>
                        <td>
                            <button class="edit-btn" onclick="editAdmin(<%= qtv.getLQVid() %>)">✏ Sửa</button>
                            <button class="delete-btn" onclick="deleteAdmin(<%= qtv.getLQVid() %>)">🗑 Xóa</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
    function deleteAdmin(id) {
        if (confirm("Bạn có chắc chắn muốn xóa không?")) {
            $.ajax({
                type: "GET",
                url: "quantrivien?action=delete&id=" + id,
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

        function editAdmin(id) {
            window.location.href = "<%= request.getContextPath() %>/views/quantrivien/pages/admin/edit.jsp?id=" + id;
        }
    </script>

</body>
</html>
