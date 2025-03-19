<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.lqv.models.LQV_DonDatHang, com.lqv.dao.LQV_DonDatHangDAO" %>

<%
    // Lấy danh sách đơn đặt hàng từ database
    LQV_DonDatHangDAO donDatHangDAO = new LQV_DonDatHangDAO();
    List<LQV_DonDatHang> listDonHang = donDatHangDAO.getAll();
  

%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh sách Đơn Đặt Hàng</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/list-dondathang.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <div class="container">
        <h2>Danh sách Đơn Đặt Hàng</h2>
        
        <!-- Nút thêm đơn đặt hàng -->
        <div class="actions">
            <button class="add-btn" onclick="window.location.href='<%= request.getContextPath() %>/views/quantrivien/pages/donhang/add.jsp'">➕ Thêm Đơn Hàng</button>
        </div>

        <div class="card-footer text-center">
            <a href="<%= request.getContextPath() %>/views/admin.jsp" class="btn btn-link">Quay lại trang chủ</a>
        </div>

        <table border="1" cellspacing="0" cellpadding="8">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ngày Đặt</th>
                    <th>Nhà Cung Cấp</th>
                    <th>Tổng Tiền</th>
                    <th>Trạng Thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <% for (LQV_DonDatHang donHang : listDonHang) { %>
                    <tr id="row-<%= donHang.getLQVid() %>">
                        <td><%= donHang.getLQVid() %></td>
                        <td><%= donHang.getLQV_ngay_dat_hang() %></td>
                        <td><%= donHang.getLQVid_nha_cung_cap() %></td>
                        <td><%= donHang.getLQV_tong_tien() %> VNĐ</td>
                        <td>
                            <% if ("Đã duyệt".equals(donHang.getLQV_tinh_trang())) { %>
                                <span style="color: green;">Đã duyệt</span>
                            <% } else { %>
                                <span style="color: red;"><%= donHang.getLQV_tinh_trang() %>Đang Chờ</span>
                            <% } %>
                        </td>
                        <td>
                            <button class="edit-btn" onclick="editDonHang(<%= donHang.getLQVid() %>)">✏ Sửa</button>
                            <button class="delete-btn" onclick="deleteDonHang(<%= donHang.getLQVid() %>)">🗑 Xóa</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
    function deleteDonHang(id) {
        if (confirm("Bạn có chắc chắn muốn xóa đơn đặt hàng này không?")) {
            $.ajax({
                type: "GET",
                url: "dondathang?action=delete&id=" + id,
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

    function editDonHang(id) {
        window.location.href = "<%= request.getContextPath() %>/views/quantrivien/pages/donhang/edit.jsp?id=" + id;
    }
    </script>

</body>
</html>
