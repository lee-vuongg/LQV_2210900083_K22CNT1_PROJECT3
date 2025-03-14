<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lqv.dao.LQV_VatTuYTeDAO, com.lqv.models.LQV_VatTuYTe" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    LQV_VatTuYTeDAO dao = new LQV_VatTuYTeDAO();
    LQV_VatTuYTe vatTu = dao.getById(id);
    if (vatTu == null) {
        response.sendRedirect(request.getContextPath() + "/vattuyte?action=list&message=Không tìm thấy vật tư!");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Chỉnh Sửa Vật Tư Y Tế</title>
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
                        <h1>Chỉnh Sửa Vật Tư Y Tế</h1>
                    </div>
                </div>
            </div>
        </section>

        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Thông Tin Vật Tư</h3>
                </div>

                <div class="card-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/vattu" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="<%= vatTu.getLQVid() %>">

                        <div class="form-group">
                            <label for="maVatTu">Mã Vật Tư:</label>
                            <input type="text" class="form-control" id="maVatTu" name="maVatTu"
                                   value="<%= vatTu.getLQV_ma_vat_tu() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="tenVatTu">Tên Vật Tư:</label>
                            <input type="text" class="form-control" id="tenVatTu" name="tenVatTu"
                                   value="<%= vatTu.getLQV_ten_vat_tu() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="soLuong">Số Lượng:</label>
                            <input type="number" class="form-control" id="soLuong" name="soLuong"
                                   value="<%= vatTu.getLQV_so_luong() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="donGia">Đơn Giá:</label>
                            <input type="number" step="0.01" class="form-control" id="donGia" name="donGia"
                                   value="<%= vatTu.getLQV_don_gia() %>" required>
                        </div>

                        <div class="form-group">
                            <label for="ngayHetHan">Ngày Hết Hạn:</label>
                            <input type="date" class="form-control" id="ngayHetHan" name="ngayHetHan"
                                   value="<%= vatTu.getLQV_ngay_het_han() %>">
                        </div>

                        <div class="form-group">
                            <label for="moTa">Mô Tả:</label>
                            <textarea class="form-control" id="moTa" name="moTa" rows="3"><%= vatTu.getLQV_mo_ta() %></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Cập nhật</button>
                        <a href="${pageContext.request.contextPath}/vattu?action=list" class="btn btn-secondary">
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
