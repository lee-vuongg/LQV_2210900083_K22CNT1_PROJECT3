<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh sách Quản trị viên</title>
    <link rel="stylesheet" href="/styles/admin.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        a {
            text-decoration: none;
            color: #007bff;
            padding: 5px 10px;
            border: 1px solid #007bff;
            border-radius: 4px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        a:hover {
            background-color: #007bff;
            color: #fff;
        }
        .add-btn {
            display: block;
            width: fit-content;
            margin: 20px auto;
            background-color: #28a745;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
        }
        .add-btn:hover {
            background-color: #218838;
        }
        .message {
            text-align: center;
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h2>Danh sách Quản trị viên</h2>
<% if (request.getAttribute("message") != null) { %>
    <div class="message"><%= request.getAttribute("message") %></div>
<% } %>
<a href="/admin/add-admin" class="add-btn">Thêm Quản trị viên</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Tài khoản</th>
        <th>Trạng thái</th>
        <th>Ngày tạo</th>
        <th>Ngày sửa</th>
        <th>Hành động</th>
    </tr>
    <% if (request.getAttribute("adminList") != null) { %>
        <% for (com.lqv.models.LQV_QuanTriVien admin : (java.util.List<com.lqv.models.LQV_QuanTriVien>) request.getAttribute("adminList")) { %>
            <tr>
                <td><%= admin.getLQVid() %></td>
                <td><%= admin.getLQV_tai_khoan() %></td>
                <td><%= admin.isLQV_trang_thai() ? "Hoạt động" : "Ngưng hoạt động" %></td>
                <td><%= admin.getLQV_ngay_tao() %></td>
                <td><%= admin.getLQV_ngay_sua() %></td>
                <td>
                    <a href="/admin/edit?id=<%= admin.getLQVid() %>">Sửa</a> |
                    <a href="/admin/delete?id=<%= admin.getLQVid() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
                </td>
            </tr>
        <% } %>
    <% } else { %>
        <tr>
            <td colspan="6">Không có dữ liệu quản trị viên</td>
        </tr>
    <% } %>
</table>
</body>
</html>
