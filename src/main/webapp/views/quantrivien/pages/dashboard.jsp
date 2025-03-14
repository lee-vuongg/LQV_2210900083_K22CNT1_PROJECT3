<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.lqv.models.LQV_QuanTriVien" %>
<%
    HttpSession session1 = request.getSession(false);
    LQV_QuanTriVien admin = (LQV_QuanTriVien) session1.getAttribute("admin");

    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
            line-height: 1.6;
        }

        header {
            background-color: #fff;
            color: #333;
            padding: 20px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        h2 {
            margin: 0;
            font-size: 24px;
        }

        .container {
            max-width: 900px;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .intro-text {
            font-size: 16px;
            color: #555;
            margin-bottom: 20px;
        }

        .function-list {
            margin: 0;
            padding-left: 20px;
            font-size: 16px;
            color: #555;
        }

        .function-list li {
            margin-bottom: 10px;
        }

        footer {
            background-color: #f4f4f4;
            color: #777;
            text-align: center;
            padding: 10px;
            font-size: 14px;
            margin-top: 30px;
        }

    </style>
</head>
<body>
    <header>
        <h2>Chào mừng, <%= admin.getLQV_tai_khoan() %></h2>
    </header>

    <div class="container">
        <div class="intro-text">
            <p>Quản trị viên đóng vai trò quan trọng trong việc giám sát và quản lý vật tư y tế trong hệ thống. Với nhiệm vụ quản lý thông tin vật tư, cập nhật số lượng, bảo trì thiết bị và kiểm soát kho, quản trị viên là người chịu trách nhiệm đảm bảo rằng mọi vật tư y tế luôn được duy trì và cung cấp kịp thời khi cần thiết.</p>
            <p>Dưới đây là một số chức năng chính mà quản trị viên có thể thực hiện trong hệ thống quản lý vật tư:</p>
        </div>

        <ul class="function-list">
            <li><strong>Thêm mới vật tư:</strong> Cập nhật thêm vật tư vào hệ thống khi có nhu cầu bổ sung.</li>
            <li><strong>Chỉnh sửa thông tin vật tư:</strong> Cập nhật thông tin vật tư khi có thay đổi về số lượng, nhà cung cấp, hoặc các đặc tính khác.</li>
            <li><strong>Xóa vật tư:</strong> Xóa vật tư khỏi hệ thống khi không còn sử dụng hoặc hết hạn sử dụng.</li>
            <li><strong>Xem báo cáo tình trạng vật tư:</strong> Kiểm tra tình hình kho, vật tư sắp hết hạn, và các vấn đề liên quan đến bảo trì.</li>
        </ul>

        <div class="intro-text">
            <p>Với quyền quản trị, bạn có thể quản lý tất cả các vật tư y tế, đảm bảo rằng công tác cung cấp vật tư luôn diễn ra suôn sẻ, phục vụ tốt cho nhu cầu của bệnh viện, cơ sở y tế.</p>
        </div>
    </div>

    <footer>
        <p>&copy; 2025 Quản lý vật tư y tế - All Rights Reserved</p>
    </footer>
</body>
</html>
