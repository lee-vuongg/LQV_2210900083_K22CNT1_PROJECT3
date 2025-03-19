<%@  page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="com.lqv.dao.LQV_KhachHangDAO, com.lqv.models.LQV_KhachHang" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%
    // Kiểm tra và lấy ID khách hàng
    int id = -1;
    try {
        id = Integer.parseInt(request.getParameter("id"));
    } catch (NumberFormatException e) {
        response.sendRedirect(request.getContextPath() + "/khachhang?action=list&message=ID khách hàng không hợp lệ!");
        return;
    }

    // Tạo DAO và tìm khách hàng
    LQV_KhachHangDAO dao = new LQV_KhachHangDAO();
    LQV_KhachHang khachHang = dao.getById(id);

    // Kiểm tra nếu không tìm thấy khách hàng
    if (khachHang == null) {
        response.sendRedirect(request.getContextPath() + "/khachhang?action=list&message=Không tìm thấy khách hàng!");
        return;
    }

    // Định dạng ngày sửa
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String ngaySua = (khachHang.getLQV_ngay_sua() != null) ? sdf.format(khachHang.getLQV_ngay_sua()) : sdf.format(new Date());
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Khách Hàng</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/styles/form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/adminlte/css/adminlte.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/adminlte/plugins/fontawesome-free/css/all.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <div class="content-wrapper">
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Chỉnh Sửa Khách Hàng</h1>
                    </div>
                </div>
            </div>
        </section>

        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Thông Tin Khách Hàng</h3>
                </div>
                <div class="card-body">
			                  <form action="${pageContext.request.contextPath}/khachhang" method="post">
			    <input type="hidden" name="action" value="update">
			
			    <label>ID Khách Hàng:</label>
			    <input type="text" name="LQVid" value="<%= khachHang.getLQVid() %>" readonly>
			
			    <div class="form-group">
			        <label for="taiKhoan">Tài Khoản:</label>
			        <input type="text" class="form-control" id="taiKhoan" name="LQV_tai_khoan"
			               value="<%= khachHang.getLQV_tai_khoan() %>" required>
			    </div>
							    <div class="form-group">
				    <label for="matKhau">Mật Khẩu:</label>
				    <input type="password" class="form-control" id="matKhau" name="LQV_mat_khau"
				           value="<%= khachHang.getLQV_mat_khau() %>" required>
				</div>
			    
			
			    <div class="form-group">
			        <label for="hoTen">Họ Tên:</label>
			        <input type="text" class="form-control" id="hoTen" name="LQV_ho_ten"
			               value="<%= khachHang.getLQV_ho_ten() %>" required>
			    </div>
			
			    <div class="form-group">
			        <label for="email">Email:</label>
			        <input type="email" class="form-control" id="email" name="LQV_email"
			               value="<%= khachHang.getLQV_email() %>" required>
			    </div>
			
			    <div class="form-group">
			        <label for="diaChi">Địa Chỉ:</label>
			        <input type="text" class="form-control" id="diaChi" name="LQV_dia_chi"
			               value="<%= khachHang.getLQV_dia_chi() %>" required>
			    </div>
			
			    <div class="form-group">
			        <label for="soDienThoai">Số Điện Thoại:</label>
			        <input type="text" class="form-control" id="soDienThoai" name="LQV_so_dien_thoai"
			               value="<%= khachHang.getLQV_so_dien_thoai() %>" required>
			    </div>

    <div class="form-group">
        <label for="ngaySua">Ngày Sửa:</label>
        <input type="text" class="form-control" id="ngaySua" name="LQV_ngay_sua"
               value="<%= ngaySua %>" readonly>
    </div>

    <div class="form-group">
        <label for="trangThai">Trạng Thái:</label>
        <select class="form-control" id="trangThai" name="LQV_trang_thai" required>
            <option value="true" <%= khachHang.isLQV_trang_thai() ? "selected" : "" %>>Hoạt động</option>
            <option value="false" <%= !khachHang.isLQV_trang_thai() ? "selected" : "" %>>Không hoạt động</option>
        </select>
    </div>

    <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Cập nhật</button>
    <a href="${pageContext.request.contextPath}/khachhang?action=list" class="btn btn-secondary">
        <i class="fas fa-arrow-left"></i> Quay lại
    </a>
</form>

                </div>
            </div>
        </section>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/adminlte/js/adminlte.min.js"></script>
</body>
</html>
