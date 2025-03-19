<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lqv.dao.LQV_DonDatHangDAO, com.lqv.models.LQV_DonDatHang, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<jsp:useBean id="donDatHangDAO" class="com.lqv.dao.LQV_DonDatHangDAO" scope="page"/>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    LQV_DonDatHang donHang = donDatHangDAO.getById(id);

    if (donHang == null) {
        response.sendRedirect(request.getContextPath() + "/views/quantrivien/pages/donhang/list.jsp");
        return;
    }

    // Định dạng datetime-local
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    String ngayDatHangFormatted = donHang.getLQV_ngay_dat_hang().toLocalDateTime().format(formatter);
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Đơn Đặt Hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/edit.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Chỉnh Sửa Đơn Đặt Hàng</h2>
        <form action="<%= request.getContextPath() %>/dondathang?action=update" method="post" class="border p-4 rounded shadow">

            <!-- ID -->
            <input type="hidden" name="id" value="<%= donHang.getLQVid() %>">

            <!-- Ngày đặt hàng -->
            <div class="mb-3">
                <label class="form-label">Ngày Đặt Hàng:</label>
                <input type="datetime-local" name="ngayDatHang" class="form-control" value="<%= ngayDatHangFormatted %>" required>
            </div>

            <!-- Nhà cung cấp -->
            <div class="mb-3">
                <label class="form-label">Nhà Cung Cấp ID:</label>
                <input type="number" name="idNhaCungCap" class="form-control" value="<%= donHang.getLQVid_nha_cung_cap() %>" required>
            </div>

            <!-- Tổng tiền -->
            <div class="mb-3">
                <label class="form-label">Tổng Tiền:</label>
                <input type="number" step="0.01" name="tongTien" class="form-control" value="<%= donHang.getLQV_tong_tien() %>" required>
            </div>

            <!-- Trạng thái -->
            <div class="mb-3">
                <label class="form-label">Trạng Thái:</label>
                <select name="tinhTrang" class="form-select">
                    <option value="Đang Chờ" <%= donHang.getLQV_tinh_trang().equals("Đang Chờ") ? "selected" : "" %>>Đang Chờ</option>
                    <option value="Đã Duyệt" <%= donHang.getLQV_tinh_trang().equals("Đã Duyệt") ? "selected" : "" %>>Đã Duyệt</option>
                    <option value="Hủy" <%= donHang.getLQV_tinh_trang().equals("Hủy") ? "selected" : "" %>>Hủy</option>
                </select>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success">✔ Cập Nhật</button>
                <a href="<%= request.getContextPath() %>/views/dondathang/list.jsp" class="btn btn-secondary">⬅ Quay lại</a>
            </div>
        </form>
    </div>
</body>
</html>
