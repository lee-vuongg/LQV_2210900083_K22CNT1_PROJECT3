<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Đơn Đặt Hàng</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Thêm Đơn Đặt Hàng</h2>
    <form action="<%= request.getContextPath() %>/dondathang?action=add" method="post" class="border p-4 rounded shadow">
        <label>Ngày Đặt Hàng:</label>
        <input type="datetime-local" name="ngayDatHang" required><br>

        <label>Nhà Cung Cấp ID:</label>
        <input type="number" name="idNhaCungCap" required><br>

        <label>Tổng Tiền:</label>
        <input type="number" step="0.01" name="tongTien" required><br>

        <label>Trạng Thái:</label>
        <select name="trangThai">
            <option value="Đang xử lý">Đang xử lý</option>
            <option value="Hoàn thành">Hoàn thành</option>
            <option value="Hủy">Hủy</option>
        </select><br>

        <button type="submit">Thêm</button>
        <a href="list.jsp">Quay lại</a>
    </form>
</body>
</html>
