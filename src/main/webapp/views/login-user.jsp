<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding-top: 50px;
        }

        .login-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 30px;
            background-color: white;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-control {
            height: 45px;
            font-size: 16px;
        }

        .btn-primary {
            width: 100%;
            padding: 10px;
        }

        .error-message {
            color: red;
            font-size: 14px;
            text-align: center;
        }

        .text-center a {
            color: #007bff;
            text-decoration: none;
        }

        .text-center a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="login-container">
            <h2>Đăng Nhập</h2>
              <form action="<%= request.getContextPath() %>/khachhang" method="post">
                        <input type="hidden" name="action" value="login">
                <div class="form-group">
                    <label for="taiKhoan">Tài Khoản</label>
                    <input type="text" class="form-control" id="taiKhoan" name="taiKhoan" placeholder="Nhập tài khoản" required>
                </div>
                <div class="form-group">
                    <label for="matKhau">Mật Khẩu</label>
                    <input type="password" class="form-control" id="matKhau" name="matKhau" placeholder="Nhập mật khẩu" required>
                </div>

                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary">Đăng Nhập</button>
                </div>

                <div class="text-center">
                    <a href="views/auth/register.jsp">Chưa có tài khoản? Đăng ký ngay!</a>
                </div>

                <%-- Hiển thị lỗi nếu có --%>
                <div class="error-message">
                    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
                </div>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
