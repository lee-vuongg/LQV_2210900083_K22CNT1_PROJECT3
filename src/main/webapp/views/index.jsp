<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Trang Chủ</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <script src="<%= request.getContextPath() %>/views/scripts/main.js"></script>
    
</head>
<body>

    <!-- Header -->
    <div class="header">
        <img src="<%= request.getContextPath() %>/views/styles/images/logo.jpg" alt="Logo">
        <h1>GIỚI THIỆU CÔNG KHAI TRANG THIẾT BỊ Y TẾ</h1>
    </div>

    <!-- Navbar -->
    <div class="navbar">
        <div class="nav-left">
            <a href="#"><i class="fas fa-home"></i> Trang chủ</a>
            <a href="#"><i class="fas fa-list"></i> Danh mục</a>
            <a href="#"><i class="fas fa-envelope"></i> Liên hệ</a>
        </div>
        <div class="nav-right">
            <a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Đăng nhập</a>
            <a href="#"><i class="fas fa-user-plus"></i> Đăng ký</a>
        </div>
    </div>

    <!-- Search Bar -->
    <div class="search-container">
        <input type="text" placeholder="Tìm kiếm trang thiết bị y tế">
        <button><i class="fas fa-search"></i> Tìm kiếm nâng cao</button>
    </div>

    <!-- Danh mục thiết bị y tế -->
    <div class="category-container">
        <div class="category-card">
            <img src="<%= request.getContextPath() %>/views/styles/images/thietbi1.jpg" alt="Thiết bị 1">
            <h3>Thiết bị chẩn đoán hình ảnh</h3>
            <a href="#"><i class="fas fa-info-circle"></i> Xem chi tiết</a>
        </div>
        <div class="category-card">
            <img src="<%= request.getContextPath() %>/views/styles/images/thietbi2.jpg" alt="Thiết bị 2">
            <h3>Thiết bị hồi sức cấp cứu</h3>
            <a href="#"><i class="fas fa-info-circle"></i> Xem chi tiết</a>
        </div>
        <div class="category-card">
            <img src="<%= request.getContextPath() %>/views/styles/images/thietbi3.jpg" alt="Thiết bị 3">
            <h3>Thiết bị lọc máu</h3>
            <a href="#"><i class="fas fa-info-circle"></i> Xem chi tiết</a>
        </div>
        <div class="category-card">
            <img src="<%= request.getContextPath() %>/views/styles/images/thietbi4.jpg" alt="Thiết bị 4">
            <h3>Thiết bị phòng mổ</h3>
            <a href="#"><i class="fas fa-info-circle"></i> Xem chi tiết</a>
        </div>
    </div>

    <!-- Giới thiệu thiết bị y tế -->
    <div class="intro-section">
        <h2>Về Thiết Bị Y Tế</h2>
        <p>Trang web cung cấp thông tin chi tiết về các thiết bị y tế hiện đại nhất, phục vụ công tác khám chữa bệnh.</p>
        <div class="intro-cards">
            <div class="intro-card">
                <i class="fas fa-x-ray"></i>
                <h3>Chẩn đoán hình ảnh</h3>
                <p>Cung cấp thiết bị chẩn đoán chính xác, hỗ trợ bác sĩ trong việc xác định bệnh lý.</p>
            </div>
            <div class="intro-card">
                <i class="fas fa-procedures"></i>
                <h3>Hỗ trợ cấp cứu</h3>
                <p>Thiết bị hồi sức cấp cứu hiện đại giúp cứu sống bệnh nhân kịp thời.</p>
            </div>
            <div class="intro-card">
                <i class="fas fa-user-md"></i>
                <h3>Thiết bị chuyên khoa</h3>
                <p>Trang bị các thiết bị tiên tiến phục vụ chuyên khoa, nâng cao chất lượng điều trị.</p>
            </div>
        </div>
    </div>

   <!-- Footer -->
<footer class="footer">
    <div class="footer-container">
        <!-- Cột 1: Địa chỉ -->
        <div class="footer-info">
            <h3>Thông tin liên hệ</h3>
            <p><i class="fas fa-map-marker-alt"></i> Địa chỉ: 123 Đường ABC, Hà Nội</p>
            <p><i class="fas fa-phone"></i> Điện thoại: 0123 456 789</p>
            <p><i class="fas fa-envelope"></i> Email: contact@thietbiyte.com</p>
        </div>

        <!-- Cột 2: Form liên hệ -->
        <div class="footer-form">
            <h3>Để lại thông tin</h3>
            <form action="#">
                <input type="text" placeholder="Họ và tên" required>
                <input type="email" placeholder="Email" required>
                <textarea placeholder="Tin nhắn" required></textarea>
                <button type="submit">Gửi</button>
            </form>
        </div>
    </div>
</footer>

    <!-- Bong bóng chat động -->
    <div class="chat-bubble" onclick="openChat()">
        <i class="fas fa-comment-dots"></i>
    </div>
    <div class="chat-box" id="chatBox">
        <div class="chat-header">
            <h3>Hỗ trợ trực tuyến</h3>
            <button onclick="closeChat()"><i class="fas fa-times"></i></button>
        </div>
        <div class="chat-content">
            <p>Xin chào! Chúng tôi có thể giúp gì cho bạn?</p>
        </div>
        <div class="chat-input">
            <input type="text" placeholder="Nhập tin nhắn...">
            <button><i class="fas fa-paper-plane"></i></button>
        </div>
    </div>

    <script>
        function openChat() {
            document.getElementById("chatBox").style.display = "block";
        }
        function closeChat() {
            document.getElementById("chatBox").style.display = "none";
        }
    </script>

</body>
</html>
