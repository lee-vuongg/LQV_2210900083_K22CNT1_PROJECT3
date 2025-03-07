<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Quản trị viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f4f7f6;
            margin: 0;
        }
        .form-container {
            background: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }
        input[type="checkbox"] {
            margin-right: 10px;
        }
        .form-actions {
            display: flex;
            justify-content: space-between;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        a {
            text-decoration: none;
            background-color: #ccc;
            color: #333;
            padding: 10px 20px;
            border-radius: 8px;
        }
        a:hover {
            background-color: #bbb;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Thêm Quản trị viên</h2>
    <form action="/admin/add" method="post">
        <label for="taiKhoan">Tài khoản:</label>
        <input type="text" id="taiKhoan" name="taiKhoan" required>

        <label for="matKhau">Mật khẩu:</label>
        <input type="password" id="matKhau" name="matKhau" required>

        <label>
            <input type="checkbox" id="trangThai" name="trangThai">
            Trạng thái kích hoạt
        </label>

        <div class="form-actions">
            <button type="submit">Thêm</button>
            <a href="/admin/list">Hủy</a>
        </div>
    </form>
</div>
</body>
</html>
