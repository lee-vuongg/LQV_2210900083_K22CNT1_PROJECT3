<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Chỉnh sửa Quản trị viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f4f4f9;
        }
        form {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="checkbox"] {
            margin-bottom: 10px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2>Chỉnh sửa Quản trị viên</h2>
<form action="/admin/admin-edit" method="post">
    <input type="hidden" name="id" value="${admin.LQV_id}" />

    <label for="taiKhoan">Tài khoản:</label>
    <input type="text" id="taiKhoan" name="taiKhoan" value="${admin.LQV_tai_khoan}" required><br>

    <label for="matKhau">Mật khẩu:</label>
    <input type="password" id="matKhau" name="matKhau" value="${admin.LQV_mat_khau}" required><br>

    <label for="trangThai">Trạng thái:</label>
    <input type="checkbox" id="trangThai" name="trangThai" ${admin.LQV_trang_thai ? 'checked' : ''}><br>

    <button type="submit">Cập nhật</button>
    <a href="/admin/admin-list">Hủy</a>
</form>
</body>
</html>
