<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng Ký</title>
</head>
<body>
    <h2>Đăng Ký</h2>
    <form action="register" method="post">
        <label for="username">Tài khoản:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required><br>
        <label for="fullName">Họ tên:</label>
        <input type="text" id="fullName" name="fullName" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <label for="address">Địa chỉ:</label>
        <input type="text" id="address" name="address" required><br>
        <label for="phone">Số điện thoại:</label>
        <input type="text" id="phone" name="phone" required><br>
        <button type="submit">Đăng ký</button>
    </form>
    <p>Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>

    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
</body>
</html>
