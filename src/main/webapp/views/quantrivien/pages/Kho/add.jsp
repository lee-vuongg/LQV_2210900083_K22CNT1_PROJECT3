<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Kho Mới</title>

    <!-- AdminLTE CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/adminlte/css/adminlte.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/adminlte/plugins/fontawesome-free/css/all.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <div class="content-wrapper">
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Thêm Kho Mới</h1>
                    </div>
                </div>
            </div>
        </section>

        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Nhập Thông Tin Kho</h3>
                </div>
                <div class="card-body">
                   <form action="<%= request.getContextPath() %>/kho?action=insert" method="post">
			    <div class="form-group">
			        <label for="tenKho">Tên Kho:</label>
			        <input type="text" class="form-control" id="tenKho" name="LQV_ten_kho" required>
			    </div>
			
			    <div class="form-group">
			        <label for="viTri">Vị trí:</label>
			        <input type="text" class="form-control" id="viTri" name="LQV_vi_tri" required>
			    </div>
			
			    <div class="form-group">
			        <label for="nguoiQuanLy">Người quản lý:</label>
			        <input type="text" class="form-control" id="nguoiQuanLy" name="LQV_nguoi_quan_ly" required>
			    </div>
			
			    <div class="form-group">
			        <label for="sucChua">Sức chứa:</label>
			        <input type="number" class="form-control" id="sucChua" name="LQV_suc_chua" required>
			    </div>
			
			    <button type="submit" class="btn btn-success">
			        <i class="fas fa-plus"></i> Thêm mới
			    </button>
			</form>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- AdminLTE JS -->
<script src="<%= request.getContextPath() %>/assets/adminlte/js/adminlte.min.js"></script>
</body>
</html>
