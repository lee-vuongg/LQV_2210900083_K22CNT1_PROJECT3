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
    <title>Quản trị viên</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/admin.css">
    <!-- Load jQuery từ CDN để tránh lỗi -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

    <!-- Sidebar -->
    <aside class="sidebar">
        <h2>Quản Trị Viên</h2>
        <ul>
            <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/views/quantrivien/pages/dashboard.jsp"><i class="fas fa-home"></i> Trang chủ</a></li>
            <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/views/quantrivien/pages/admin/list.jsp"><i class="fas fa-user-shield"></i> Quản lý Admin</a></li>
			 <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/views/quantrivien/pages/VatTu/list.jsp"><i class="fas fa-user-shield"></i> Quản lý vật tư</a></li>
            <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/views/quantrivien/pages/khachhang/list.jsp"><i class="fas fa-users"></i> Quản lý Khách hàng</a></li>
             <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/views/quantrivien/pages/lichsusudung/list.jsp"><i class="fas fa-shopping-cart"></i> Lịch Sử Sử Dụng</a></li>
            <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/dondathang?action=list"><i class="fas fa-shopping-cart"></i> Quản lý Đơn hàng</a></li>
            <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/kho?action=list"><i class="fas fa-warehouse"></i> Quản lý Kho</a></li>
            <li><a href="#" class="nav-link" data-page="<%= request.getContextPath() %>/views/quantrivien/pages/NhaCungCap/list.jsp"><i class="fas fa-truck"></i> Quản lý Nhà cung cấp</a></li>
            <li><a href="<%= request.getContextPath() %>/quantrivien?action=logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a></li>
        </ul>
    </aside>

    <!-- Main Content -->
    <section class="main-content">
        <header>
            <h2>Xin chào, <%= admin.getLQV_tai_khoan() %></h2>
        </header>

        <main id="mainContent">
            <h3>Chào mừng đến trang quản trị!</h3>
            <p>Chọn menu bên trái để quản lý dữ liệu.</p>
        </main>

        <footer>
            <p>Bản quyền © 2025 - Quản trị viên</p>
        </footer>
    </section>

    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script>
        $(document).ready(function () {
            $(".nav-link").click(function (e) {
                e.preventDefault(); // Ngăn load lại trang
                
                let pageUrl = $(this).data("page"); // Lấy URL trang cần load
                if (!pageUrl) return;

                // Hiện thông báo loading khi đang tải
                $("#mainContent").html("<p class='text-center'>Đang tải...</p>");

                // Load nội dung trang con vào mainContent
                $.ajax({
                    url: pageUrl,
                    type: "GET",
                    success: function (data) {
                        $("#mainContent").html(data);
                    },
                    error: function (xhr, status, error) {
                        console.error("Lỗi tải trang:", error);
                        $("#mainContent").html("<p class='text-danger'>Không thể tải trang. Kiểm tra lại đường dẫn hoặc thử lại sau.</p>");
                    }
                });
            });

            // Load trang dashboard mặc định khi mở admin.jsp
            $(".nav-link[data-page='<%= request.getContextPath() %>/views/quantrivien/pages/dashboard.jsp']").trigger("click");
        });
    </script>

</body>
</html>
