<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.lqv.models.LQV_VatTuYTe, com.lqv.dao.LQV_VatTuYTeDAO" %>

<%
    // Lấy danh sách vật tư y tế từ database
    LQV_VatTuYTeDAO vatTuDAO = new LQV_VatTuYTeDAO();
    List<LQV_VatTuYTe> listVatTu = vatTuDAO.getAll();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh sách Vật tư Y tế</title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/list-admin.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <div class="container">
        <h2>Danh sách Vật tư Y tế</h2>
        
        <!-- Nút thêm vật tư -->
        <div class="actions">
            <button class="add-btn" onclick="window.location.href='<%= request.getContextPath() %>/views/quantrivien/pages/VatTu/add.jsp'">➕ Thêm Vật Tư</button>
        </div>

        <div class="card-footer text-center">
            <a href="<%= request.getContextPath() %>/views/admin.jsp" class="btn btn-link">Quay lại trang chủ</a>
        </div>

        <table border="1" cellspacing="0" cellpadding="8">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên vật tư</th>
                    <th>Mã vật tư</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Ngày hết hạn</th>
                    <th>Mô tả</th>
                    <th>Ngày sửa</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <% for (LQV_VatTuYTe vatTu : listVatTu) { %>
                    <tr id="row-<%= vatTu.getLQVid() %>">
                        <td><%= vatTu.getLQVid() %></td>
                        <td><%= vatTu.getLQV_ten_vat_tu() %></td>
                        <td><%= vatTu.getLQV_ma_vat_tu() %></td>
                        <td><%= vatTu.getLQV_so_luong() %></td>
                        <td><%= vatTu.getLQV_don_gia() %></td>
                        <td><%= vatTu.getLQV_ngay_het_han() %></td>
                        <td><%= vatTu.getLQV_mo_ta() != null ? vatTu.getLQV_mo_ta() : "Không có mô tả" %></td>
                        <td><%= vatTu.getLQV_ngay_sua() != null ? vatTu.getLQV_ngay_sua() : "Chưa cập nhật" %></td>
                        <td>
                            <button class="edit-btn" onclick="editVatTu(<%= vatTu.getLQVid() %>)">✏ Sửa</button>
                            <button class="delete-btn" onclick="deleteVatTu(<%= vatTu.getLQVid() %>)">🗑 Xóa</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
    function deleteVatTu(id) {
        if (confirm("Bạn có chắc chắn muốn xóa vật tư này không?")) {
            $.ajax({
                type: "GET",
                url: "vattu?action=delete&id=" + id,
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

    function editVatTu(id) {
        window.location.href = "<%= request.getContextPath() %>/views/quantrivien/pages/VatTu/edit.jsp?id=" + id;
    }
    </script>

</body>
</html>
