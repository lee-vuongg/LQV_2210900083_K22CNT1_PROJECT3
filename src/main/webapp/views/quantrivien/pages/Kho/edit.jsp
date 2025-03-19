<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.lqv.models.LQV_Kho, com.lqv.dao.LQV_KhoDAO" %>

<%
    // Lấy ID từ request
    String id = request.getParameter("id");
    LQV_Kho kho = null;

    if (id != null) {
        try {
            int khoId = Integer.parseInt(id);
            LQV_KhoDAO khoDAO = new LQV_KhoDAO();
            kho = khoDAO.getById(khoId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra nếu không tìm thấy kho
    if (kho == null) {
%>
        <p style="color: red;">Lỗi: Không tìm thấy kho!</p>
        <a href="<%= request.getContextPath() %>/kho?action=list">Quay lại danh sách kho</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa Kho</title>

    <!-- AdminLTE CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/adminlte/css/adminlte.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/adminlte/plugins/fontawesome-free/css/all.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <div class="content-wrapper">
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Chỉnh Sửa Kho</h1>
                    </div>
                </div>
            </div>
        </section>

        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Thông Tin Kho</h3>
                </div>
                <div class="card-body">
                    <form action="<%= request.getContextPath() %>/kho?action=update" method="post">
                        <input type="hidden" name="LQVid" value="<%= kho.getLQVid() %>">

                        <div class="form-group">
                            <label for="tenKho">Tên Kho:</label>
                            <input type="text" class="form-control" id="tenKho" name="LQV_ten_kho"
                                   value="<%= kho.getLQV_ten_kho() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="viTri">Vị trí:</label>
                            <input type="text" class="form-control" id="viTri" name="LQV_vi_tri"
                                   value="<%= kho.getLQV_vi_tri() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="nguoiQuanLy">Người quản lý:</label>
                            <input type="text" class="form-control" id="nguoiQuanLy" name="LQV_nguoi_quan_ly"
                                   value="<%= kho.getLQV_nguoi_quan_ly() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="sucChua">Sức chứa:</label>
                            <input type="number" class="form-control" id="sucChua" name="LQV_suc_chua"
                                   value="<%= kho.getLQV_suc_chua() %>" required>
                        </div>

                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Cập nhật
                        </button>
                        <a href="<%= request.getContextPath() %>/kho?action=list" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Quay lại
                        </a>
                    </form>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- AdminLTE JS -->
<script src="<%= request.getContextPath() %>/assets/adminlte/js/adminlte.min.js"></script>
</body>
</html>
