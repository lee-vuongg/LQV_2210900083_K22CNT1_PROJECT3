package com.lqv.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lqv.dao.LQV_KhachHangDAO;
import com.lqv.models.LQV_KhachHang;

@WebServlet("/khachhang")
public class LQV_KhachHangServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LQV_KhachHangDAO khachHangDAO;
    
    @Override
    public void init() throws ServletException {
        khachHangDAO = new LQV_KhachHangDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteKhachHang(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                default:
                    listKhachHang(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý yêu cầu");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertKhachHang(request, response);
                    break;
                case "update":
                    updateKhachHang(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                case "register":
                    register(request, response);
                    break;
                default:
                    response.sendRedirect("khachhang");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý yêu cầu");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin tài khoản và mật khẩu từ form
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");

        // Kiểm tra nếu tài khoản hoặc mật khẩu rỗng
        if (taiKhoan == null || matKhau == null || taiKhoan.trim().isEmpty() || matKhau.trim().isEmpty()) {
            System.out.println("⚠️ Tài khoản hoặc mật khẩu bị bỏ trống!");
            request.setAttribute("error", "Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
            request.getRequestDispatcher("views/login-user.jsp").forward(request, response);
            return;
        }

        // Debug: In ra console để kiểm tra thông tin nhập vào
        System.out.println("📌 Đang đăng nhập với: Tài khoản = " + taiKhoan + " | Mật khẩu = " + matKhau);

        // Sử dụng DAO cho khách hàng thay vì quản trị viên
        LQV_KhachHang khachHang = khachHangDAO.login(taiKhoan, matKhau);
        
        // Debug: Kiểm tra xem khách hàng có tồn tại không
        if (khachHang != null) {
            System.out.println("✅ Đăng nhập thành công! Tài khoản: " + khachHang.getLQV_tai_khoan());
            HttpSession session = request.getSession();
            session.setAttribute("user", khachHang); // Lưu thông tin khách hàng vào session

            // Debug: Kiểm tra xem session có lưu đúng thông tin không
            System.out.println("📌 Đã lưu thông tin vào session: " + session.getAttribute("user"));

            // Chuyển hướng đến trang của khách hàng
            System.out.println("📌 Trước khi chuyển hướng...");
            response.sendRedirect(request.getContextPath() + "/views/user.jsp");
            System.out.println("📌 Sau khi chuyển hướng...");



        } else {
            System.out.println("❌ Đăng nhập thất bại! Sai tài khoản hoặc mật khẩu.");
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            
            // Debug: Hiển thị lại thông báo lỗi trên trang login
            System.out.println("⚠️ Lỗi: Sai tài khoản hoặc mật khẩu!");
            
            request.getRequestDispatcher("views/login-user.jsp").forward(request, response);
        }
        return;
    }



    // ✅ Đăng ký (MỚI)
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String taiKhoan = request.getParameter("tai_khoan");
        String matKhau = request.getParameter("mat_khau");
        String hoTen = request.getParameter("ho_ten");
        String email = request.getParameter("email");
        String diaChi = request.getParameter("dia_chi");
        String soDienThoai = request.getParameter("so_dien_thoai");

        // Kiểm tra tài khoản hoặc email đã tồn tại
        if (khachHangDAO.isEmailExist(email)) {
            request.setAttribute("error", "Email đã tồn tại.");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }

        // Tạo đối tượng khách hàng
        LQV_KhachHang khachHang = new LQV_KhachHang(0, taiKhoan, matKhau, hoTen, email, diaChi, soDienThoai, true, new Date(), null);

        // Gọi DAO để đăng ký
        if (khachHangDAO.register(khachHang)) {
            System.out.println("✅ Đăng ký thành công!");
            response.sendRedirect("views/auth/login.jsp"); // Chuyển hướng đến trang đăng nhập
        } else {
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            request.getRequestDispatcher("views/auth/register.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("✅ Đã đăng xuất!");
        response.sendRedirect("views/auth/login.jsp");
    }

    private void listKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_KhachHang> list = khachHangDAO.getAll();
        request.setAttribute("listKhachHang", list);
        request.getRequestDispatcher("views/quantrivien/pages/khachhang/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/quantrivien/pages/khachhang/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_KhachHang khachHang = khachHangDAO.getById(id);
        
        if (khachHang == null) {
            response.sendRedirect("khachhang");
            return;
        }
        
        request.setAttribute("khachHang", khachHang);
        request.getRequestDispatcher("views/quantrivien/pages/khachhang/edit.jsp").forward(request, response);
    }


    private void insertKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("🔹 Bắt đầu thêm khách hàng...");

            // Nhận dữ liệu từ request
            String taiKhoan = request.getParameter("tai_khoan");
            String matKhau = request.getParameter("mat_khau");
            String hoTen = request.getParameter("ho_ten");
            String email = request.getParameter("email");
            String diaChi = request.getParameter("dia_chi");
            String soDienThoai = request.getParameter("so_dien_thoai");
            String trangThaiStr = request.getParameter("trang_thai");

            // Kiểm tra null để tránh lỗi
            boolean trangThai = (trangThaiStr != null) && trangThaiStr.equals("true");

            // Debug thông tin nhận được
            System.out.println("🔹 Nhận được từ request:");
            System.out.println("- Tài khoản: " + taiKhoan);
            System.out.println("- Mật khẩu: " + matKhau);
            System.out.println("- Họ tên: " + hoTen);
            System.out.println("- Email: " + email);
            System.out.println("- Địa chỉ: " + diaChi);
            System.out.println("- Số điện thoại: " + soDienThoai);
            System.out.println("- Trạng thái: " + trangThai);

            // Kiểm tra dữ liệu đầu vào
            if (taiKhoan == null || taiKhoan.trim().isEmpty()) {
                request.setAttribute("error", "Tài khoản không được để trống.");
                request.getRequestDispatcher("them_khachhang.jsp").forward(request, response);
                return;
            }
            if (matKhau == null || matKhau.trim().isEmpty()) {
                request.setAttribute("error", "Mật khẩu không được để trống.");
                request.getRequestDispatcher("them_khachhang.jsp").forward(request, response);
                return;
            }
            if (hoTen == null || hoTen.trim().isEmpty()) {
                request.setAttribute("error", "Họ tên không được để trống.");
                request.getRequestDispatcher("them_khachhang.jsp").forward(request, response);
                return;
            }
            if (email == null || email.trim().isEmpty() || !email.contains("@")) {
                request.setAttribute("error", "Email không hợp lệ.");
                request.getRequestDispatcher("them_khachhang.jsp").forward(request, response);
                return;
            }
            if (soDienThoai == null || soDienThoai.trim().isEmpty()) {
                request.setAttribute("error", "Số điện thoại không được để trống.");
                request.getRequestDispatcher("them_khachhang.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng khách hàng
            LQV_KhachHang kh = new LQV_KhachHang(0, taiKhoan, matKhau, hoTen, email, diaChi, soDienThoai, trangThai, new Date(System.currentTimeMillis()), null);

            // Debug trước khi insert
            System.out.println("=== Debug đối tượng khách hàng trước khi insert ===");
            System.out.println(kh);

            // Gọi DAO để thêm khách hàng vào cơ sở dữ liệu
            boolean isInserted = khachHangDAO.insertKhachHang(kh);
            if (isInserted) {
                System.out.println("✅ Thêm khách hàng thành công!");
                response.sendRedirect("khachhang");
            } else {
                request.setAttribute("error", "Có lỗi xảy ra khi thêm khách hàng.");
                request.getRequestDispatcher("them_khachhang.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Lỗi khi thêm khách hàng: " + e.getMessage());
            request.setAttribute("error", "Lỗi khi thêm khách hàng: " + e.getMessage());
            request.getRequestDispatcher("them_khachhang.jsp").forward(request, response);
        }
    }



    private void updateKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy ID từ request
            String idStr = request.getParameter("LQVid");
            if (idStr == null || idStr.trim().isEmpty()) {
                System.out.println("❌ Lỗi: ID khách hàng không hợp lệ! (null hoặc rỗng)");
                response.getWriter().println("Lỗi: ID khách hàng không hợp lệ!");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID khách hàng không phải số hợp lệ!");
                response.getWriter().println("Lỗi: ID khách hàng không hợp lệ!");
                return;
            }

            // Debug ID
            System.out.println("🔹 ID khách hàng: " + id);

            // Lấy thông tin khách hàng từ DB
            LQV_KhachHang khachHang = khachHangDAO.getById(id);
            if (khachHang == null) {
                System.out.println("❌ Không tìm thấy khách hàng có ID: " + id);
                response.getWriter().println("Không tìm thấy khách hàng!");
                return;
            }

            // Nhận thông tin từ request
            String taiKhoan = request.getParameter("LQV_tai_khoan");
            String matKhau = request.getParameter("LQV_mat_khau");
            String hoTen = request.getParameter("LQV_ho_ten");
            String email = request.getParameter("LQV_email");
            String diaChi = request.getParameter("LQV_dia_chi");
            String soDienThoai = request.getParameter("LQV_so_dien_thoai");
            boolean trangThai = Boolean.parseBoolean(request.getParameter("LQV_trang_thai"));
            String ngaySuaStr = request.getParameter("LQV_ngay_sua");

            // Debug thông tin nhận được
            System.out.println("🔹 Nhận được từ request:");
            System.out.println("- Tài khoản: " + taiKhoan);
            System.out.println("- Họ tên: " + hoTen);
            System.out.println("- Email: " + email);
            System.out.println("- Địa chỉ: " + diaChi);
            System.out.println("- Số điện thoại: " + soDienThoai);
            System.out.println("- Trạng thái: " + trangThai);
            System.out.println("- Ngày sửa: " + ngaySuaStr);

            // Cập nhật thông tin
            khachHang.setLQV_tai_khoan(taiKhoan);
            khachHang.setLQV_mat_khau(matKhau);
            khachHang.setLQV_ho_ten(hoTen);
            khachHang.setLQV_email(email);
            khachHang.setLQV_dia_chi(diaChi);
            khachHang.setLQV_so_dien_thoai(soDienThoai);
            khachHang.setLQV_trang_thai(trangThai);

            // Xử lý ngày sửa
            Date ngaySua;
            if (ngaySuaStr == null || ngaySuaStr.trim().isEmpty()) {
                ngaySua = new Date();
                System.out.println("⚠ Ngày sửa trống, sử dụng ngày hiện tại: " + ngaySua);
            } else {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    ngaySua = sdf.parse(ngaySuaStr);
                    System.out.println("✅ Ngày sửa sau khi parse: " + ngaySua);
                } catch (ParseException e) {
                    System.out.println("⚠ Lỗi khi parse ngày sửa! Gán ngày hiện tại.");
                    ngaySua = new Date();
                }
            }

            // Cập nhật ngày sửa
            khachHang.setLQV_ngay_sua(ngaySua);

            // Debug trước khi cập nhật
            System.out.println("=== Debug thông tin trước khi cập nhật ===");
            System.out.println(khachHang);

            // Cập nhật vào DB
            boolean isUpdated = khachHangDAO.update(khachHang);
            if (isUpdated) {
                System.out.println("✅ Cập nhật thành công!");
                response.sendRedirect(request.getContextPath() + "/khachhang");
            } else {
                System.out.println("❌ Cập nhật không thành công!");
                response.getWriter().println("Cập nhật không thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Lỗi khi cập nhật khách hàng: " + e.getMessage());
            response.getWriter().println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
        }
    }

    private void deleteKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        khachHangDAO.delete(id);
        response.sendRedirect("khachhang");
    }
} 