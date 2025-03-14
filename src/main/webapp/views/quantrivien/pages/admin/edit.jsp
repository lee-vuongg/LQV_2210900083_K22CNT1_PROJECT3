<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lqv.dao.LQV_QuanTriVienDAO, com.lqv.models.LQV_QuanTriVien" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    LQV_QuanTriVienDAO dao = new LQV_QuanTriVienDAO();
    LQV_QuanTriVien admin = dao.getById(id);
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/quantrivien?action=list&message=Không tìm thấy quản trị viên!");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Quản Trị Viên</title>
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
                        <h1>Chỉnh Sửa Quản Trị Viên</h1>
                    </div>
                </div>
            </div>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Thông Tin Quản Trị Viên</h3>
                </div>

                <div class="card-body">
                    <!-- Hiển thị thông báo lỗi nếu có -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/quantrivien" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="<%= admin.getLQVid() %>">

                        <div class="form-group">
                            <label for="taiKhoan">Tài Khoản:</label>
                            <input type="text" class="form-control" id="taiKhoan" name="taiKhoan"
                                   value="<%= admin.getLQV_tai_khoan() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="matKhau">Mật Khẩu (nếu muốn đổi):</label>
                            <input type="password" class="form-control" id="matKhau" name="matKhau"
                                   placeholder="Nhập mật khẩu mới (bỏ trống nếu không đổi)">
                        </div>

                        <div class="form-group">
                            <label for="trangThai">Trạng Thái:</label>
                            <select class="form-control" id="trangThai" name="trangThai" required>
                                <option value="true" <%= admin.isLQV_trang_thai() ? "selected" : "" %>>Hoạt động</option>
                                <option value="false" <%= !admin.isLQV_trang_thai() ? "selected" : "" %>>Ngừng hoạt động</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Cập nhật</button>
                        <a href="${pageContext.request.contextPath}/quantrivien?action=list" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Quay lại
                        </a>
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
