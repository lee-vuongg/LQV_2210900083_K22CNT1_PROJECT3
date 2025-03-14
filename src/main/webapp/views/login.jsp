<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-primary text-white text-center">
                    <h3>Đăng nhập quản trị</h3>
                </div>
                <div class="card-body">
                    <% 
                        String error = (String) request.getAttribute("error");
                        if (error != null) { 
                    %>
                        <div class="alert alert-danger text-center"><%= error %></div>
                    <% } %>
                    
                    <form action="<%= request.getContextPath() %>/quantrivien" method="post">
                        <input type="hidden" name="action" value="login">
                        
                        <div class="mb-3">
                            <label for="taiKhoan" class="form-label">Tài khoản</label>
                            <input type="text" id="taiKhoan" name="taiKhoan" class="form-control" 
                                   placeholder="Nhập tài khoản" required autofocus>
                        </div>

                        <div class="mb-3">
                            <label for="matKhau" class="form-label">Mật khẩu</label>
                            <input type="password" id="matKhau" name="matKhau" class="form-control" 
                                   placeholder="Nhập mật khẩu" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Đăng nhập</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="<%= request.getContextPath() %>/views/index.jsp" class="btn btn-link">Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
