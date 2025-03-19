<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.lqv.models.LQV_Kho, com.lqv.dao.LQV_KhoDAO" %>

<%
    // Lấy danh sách kho từ database
    LQV_KhoDAO khoDAO = new LQV_KhoDAO();
    List<LQV_Kho> danhSachKho = khoDAO.getAll();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Kho</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/form.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <div class="container">
        <h2>Danh Sách Kho</h2>

        <!-- Nút thêm kho -->
        <div class="actions">
            <button class="add-btn" onclick="window.location.href='<%= request.getContextPath() %>/views/quantrivien/pages/Kho/add.jsp'">➕ Thêm Kho Mới</button>
        </div>

        <div class="card-footer text-center">
            <a href="<%= request.getContextPath() %>/views/admin.jsp" class="btn btn-link">Quay lại trang chủ</a>
        </div>

        <table border="1" cellspacing="0" cellpadding="8">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Kho</th>
                    <th>Vị Trí</th>
                    <th>Người Quản Lý</th>
                    <th>Sức Chứa</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <% for (LQV_Kho kho : danhSachKho) { %>
                    <tr id="row-<%= kho.getLQVid() %>">
                        <td><%= kho.getLQVid() %></td>
                        <td><%= kho.getLQV_ten_kho() %></td>
                        <td><%= kho.getLQV_vi_tri() %></td>
                        <td><%= kho.getLQV_nguoi_quan_ly() %></td>
                        <td><%= kho.getLQV_suc_chua() %></td>
                        <td>
                            <button class="edit-btn" onclick="editKho(<%= kho.getLQVid() %>)">✏ Sửa</button>
                            <button class="delete-btn" onclick="deleteKho(<%= kho.getLQVid() %>)">🗑 Xóa</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
    function deleteKho(id) {
        if (confirm("Bạn có chắc chắn muốn xóa không?")) {
            $.ajax({
                type: "GET",
                url: "kho?action=delete&id=" + id,
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

    function editKho(id) {
        window.location.href = "<%= request.getContextPath() %>/views/quantrivien/pages/Kho/edit.jsp?id=" + id;
    }
    </script>

</body>
</html>
