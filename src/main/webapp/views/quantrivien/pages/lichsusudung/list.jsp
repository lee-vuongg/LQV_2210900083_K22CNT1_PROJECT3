<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.lqv.models.LQV_LichSuSuDung, com.lqv.dao.LQV_LichSuSuDungDAO" %>

<%
    // Lấy danh sách lịch sử sử dụng từ database
    LQV_LichSuSuDungDAO lichSuDAO = new LQV_LichSuSuDungDAO();
    List<LQV_LichSuSuDung> danhSachLichSu = lichSuDAO.getAll();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh sách Lịch Sử Sử Dụng</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/list-admin.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <div class="container">
        <h2>Danh sách Lịch Sử Sử Dụng</h2>
        
        <!-- Nút thêm lịch sử sử dụng -->
        <div class="actions">
            <button class="add-btn" onclick="window.location.href='<%= request.getContextPath() %>/views/quantrivien/pages/lichsusudung/add.jsp'">➕ Thêm Lịch Sử</button>
        </div>

        <div class="card-footer text-center">
            <a href="<%= request.getContextPath() %>/views/admin.jsp" class="btn btn-link">Quay lại trang chủ</a>
        </div>

        <table border="1" cellspacing="0" cellpadding="8">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ID Vật Tư</th>
                    <th>ID Khách Hàng</th>
                    <th>Người Sử Dụng</th>
                    <th>Ngày Sử Dụng</th>
                    <th>Số Lượng</th>
                    <th>Ghi Chú</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <% for (LQV_LichSuSuDung lichSu : danhSachLichSu) { %>
                    <tr id="row-<%= lichSu.getLQVid() %>">
                        <td><%= lichSu.getLQVid() %></td>
                        <td><%= lichSu.getLQVid_vat_tu() %></td>
                        <td><%= lichSu.getLQVid_khach_hang() %></td>
                        <td><%= lichSu.getLQV_nguoi_su_dung() %></td>
                        <td><%= lichSu.getLQV_ngay_su_dung() %></td>
                        <td><%= lichSu.getLQV_so_luong_su_dung()%></td>
                        <td><%= lichSu.getLQV_ghi_chu() %></td>
                        <td>
                            <button class="edit-btn" onclick="editLichSu(<%= lichSu.getLQVid() %>)">✏ Sửa</button>
                            <button class="delete-btn" onclick="deleteLichSu(<%= lichSu.getLQVid() %>)">🗑 Xóa</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
    function deleteLichSu(id) {
        if (confirm("Bạn có chắc chắn muốn xóa không?")) {
            $.ajax({
                type: "GET",
                url: "lichsu?action=delete&id=" + id,
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

    function editLichSu(id) {
        window.location.href = "<%= request.getContextPath() %>/views/quantrivien/pages/lichsusudung/edit.jsp?id=" + id;
    }
    </script>
</body>
</html>
