<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Thêm Nhà Cung Cấp</title>
</head>
<body>
    <div class="container">
        <h2>➕ Thêm Nhà Cung Cấp</h2>

        <%-- Thông báo lỗi --%>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>

        <form action="nhacungcap?action=insert" method="post">
            <div class="form-group">
                <label for="tenNCC">Tên nhà cung cấp:</label>
                <input type="text" class="form-control" id="tenNCC" name="tenNCC" placeholder="Nhập tên nhà cung cấp" required>
            </div>

            <div class="form-group">
                <label for="diaChi">Địa chỉ:</label>
                <input type="text" class="form-control" id="diaChi" name="diaChi" placeholder="Nhập địa chỉ" required>
            </div>

            <div class="form-group">
                <label for="soDienThoai">Số điện thoại:</label>
                <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" placeholder="Nhập số điện thoại" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
            </div>

            <button type="submit" class="btn btn-primary">➕ Thêm mới</button>
            <a href="nhacungcap?action=list" class="btn btn-secondary">⬅️ Quay lại</a>
        </form>
    </div>
</body>
</html>
