<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.lqv.models.LQV_NhaCungCap, com.lqv.dao.LQV_NhaCungCapDAO" %>

<%
    // Lấy danh sách nhà cung cấp từ database
    LQV_NhaCungCapDAO nccDAO = new LQV_NhaCungCapDAO();
    List<LQV_NhaCungCap> danhSachNCC = nccDAO.getAll();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh Sách Nhà Cung Cấp</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/list-admin.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <div class="container">
        <h2>📋 Danh Sách Nhà Cung Cấp</h2>

        <!-- Nút thêm nhà cung cấp -->
        <div class="actions">
            <button class="add-btn" onclick="window.location.href='<%= request.getContextPath() %>/views/quantrivien/pages/NhaCungCap/add.jsp'">➕ Thêm Nhà Cung Cấp</button>
        </div>

        <div class="card-footer text-center">
            <a href="<%= request.getContextPath() %>/views/admin.jsp" class="btn btn-link">🏠 Quay lại trang chủ</a>
        </div>

        <table border="1" cellspacing="0" cellpadding="8">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Nhà Cung Cấp</th>
                    <th>Địa Chỉ</th>
                    <th>Số Điện Thoại</th>
                    <th>Email</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <% for (LQV_NhaCungCap ncc : danhSachNCC) { %>
                    <tr id="row-<%= ncc.getLQVid() %>">
                        <td><%= ncc.getLQVid() %></td>
                        <td><%= ncc.getLQV_ten_nha_cung_cap() %></td>
                        <td><%= ncc.getLQV_dia_chi() %></td>
                        <td><%= ncc.getLQV_dien_thoai() %></td>
                        <td><%= ncc.getLQV_email() %></td>
                        <td>
                            <button class="edit-btn" onclick="editNCC(<%= ncc.getLQVid() %>)">✏ Sửa</button>
                            <button class="delete-btn" onclick="deleteNCC(<%= ncc.getLQVid() %>)">🗑 Xóa</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
        function deleteNCC(id) {
            if (confirm("⚠️ Bạn có chắc muốn xóa nhà cung cấp này?")) {
                $.ajax({
                    type: "GET",
                    url: "nhacungcap?action=delete&id=" + id,
                    success: function(response) {
                        if (response === "success") {
                            alert("✅ Xóa thành công!");
                            location.reload();
                        } else {
                            alert("❌ Lỗi khi xóa. Vui lòng thử lại.");
                        }
                    },
                    error: function() {
                        alert("⚠️ Lỗi khi gửi request!");
                    }
                });
            }
        }

        function editNCC(id) {
            window.location.href = "<%= request.getContextPath() %>/views/quantrivien/pages/NhaCungCap/edit.jsp?id=" + id;
        }
    </script>

</body>
</html>
