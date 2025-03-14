<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Thêm Vật Tư Y Tế</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/form.css">
    <!-- AdminLTE CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/adminlte/css/adminlte.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/adminlte/plugins/fontawesome-free/css/all.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

    <!-- Content Wrapper -->
    <div class="content-wrapper">
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Thêm Vật Tư Y Tế</h1>
                    </div>
                </div>
            </div>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Thông Tin Vật Tư Y Tế</h3>
                </div>

                <div class="card-body">
                    <!-- Hiển thị thông báo lỗi nếu có -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/vattu" method="post">
                        <input type="hidden" name="action" value="insert">

                        <div class="form-group">
                            <label for="tenVatTu">Tên Vật Tư:</label>
                            <input type="text" class="form-control" id="tenVatTu" name="tenVatTu" placeholder="Nhập tên vật tư" required>
                        </div>

                        <div class="form-group">
                            <label for="maVatTu">Mã Vật Tư:</label>
                            <input type="text" class="form-control" id="maVatTu" name="maVatTu" placeholder="Nhập mã vật tư" required>
                        </div>

                        <div class="form-group">
                            <label for="soLuong">Số Lượng:</label>
                            <input type="number" class="form-control" id="soLuong" name="soLuong" placeholder="Nhập số lượng" required min="1">
                        </div>

                        <div class="form-group">
                            <label for="donGia">Đơn Giá:</label>
                            <input type="number" class="form-control" id="donGia" name="donGia" placeholder="Nhập đơn giá" required step="0.01">
                        </div>

                        <div class="form-group">
                            <label for="ngayHetHan">Ngày Hết Hạn:</label>
                            <input type="date" class="form-control" id="ngayHetHan" name="ngayHetHan" required>
                        </div>

                        <div class="form-group">
                            <label for="moTa">Mô Tả:</label>
                            <textarea class="form-control" id="moTa" name="moTa" rows="3" placeholder="Nhập mô tả"></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Lưu</button>
                        <a href="${pageContext.request.contextPath}/vattu?action=list" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Quay lại</a>
                    </form>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- AdminLTE JS -->
<script src="${pageContext.request.contextPath}/assets/adminlte/js/adminlte.min.js"></script>
</body>
</html>
