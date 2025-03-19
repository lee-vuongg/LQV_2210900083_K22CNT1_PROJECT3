package com.lqv.controllers;

import com.lqv.dao.LQV_QuanTriVienDAO;
import com.lqv.models.LQV_QuanTriVien;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@WebServlet("/quantrivien")
public class LQV_QuanTriVienServlet extends HttpServlet {
    private LQV_QuanTriVienDAO quanTriVienDAO;

    @Override
    public void init() throws ServletException {
        quanTriVienDAO = new LQV_QuanTriVienDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "list";

        System.out.println("📝 GET Action: " + action); // Debug log

        try {
            switch (action) {
                case "list":
                    listQuanTriVien(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "insert":
                    request.getRequestDispatcher("views/quantrivien/pages/admin/add.jsp").forward(request, response);
                    break;
                case "delete":
                    deleteQuanTriVien(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                default:
                    System.out.println("⚠️ Action không hợp lệ: " + action);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action không hợp lệ!");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi hệ thống!");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            System.out.println("⚠️ Thiếu tham số action!");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số action!");
            return;
        }

        System.out.println("📝 POST Action: " + action); // Debug log

        try {
        	switch (action) {
            case "insert":
                insertQuanTriVien(request, response);
                break;
            case "update":
                updateQuanTriVien(request, response);
                break;
            case "edit":
                showEditForm(request, response); // Thêm case "edit"
                break;
            case "login":
                login(request, response);
                break;
            default:
                System.out.println("⚠️ Action không hợp lệ: " + action);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action không hợp lệ!");
                break;
        }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi hệ thống!");
        }
    }


    private void listQuanTriVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_QuanTriVien> danhSachQTV = quanTriVienDAO.getAll();
        request.setAttribute("danhSachQTV", danhSachQTV);
        request.getRequestDispatcher("views/quantrivien/pages/admin/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_QuanTriVien qtv = quanTriVienDAO.getById(id);
        request.setAttribute("quanTriVien", qtv);
        request.getRequestDispatcher("views/quantrivien/pages/admin/edit.jsp").forward(request, response);
    }

    private void insertQuanTriVien(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Lấy dữ liệu từ form
            String taiKhoan = request.getParameter("taiKhoan");
            String matKhau = request.getParameter("matKhau");
            String trangThaiStr = request.getParameter("trangThai");

            // Kiểm tra dữ liệu đầu vào
            if (taiKhoan == null || taiKhoan.trim().isEmpty() ||
                matKhau == null || matKhau.trim().isEmpty() ||
                trangThaiStr == null) {

                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
                request.getRequestDispatcher("views/quantrivien/pages/admin/add.jsp").forward(request, response);
                return;
            }

            boolean trangThai = Boolean.parseBoolean(trangThaiStr);

            // Kiểm tra tài khoản đã tồn tại chưa
            if (quanTriVienDAO.isUsernameExist(taiKhoan)) {
                request.setAttribute("error", "Tài khoản đã tồn tại!");
                request.getRequestDispatcher("views/quantrivien/pages/admin/add.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng quản trị viên
            LQV_QuanTriVien qtv = new LQV_QuanTriVien(0, taiKhoan, matKhau, trangThai, null, null);

            // Thêm vào database
            boolean result = quanTriVienDAO.insert(qtv);
            if (result) {
                request.getSession().setAttribute("success", "Thêm quản trị viên thành công!");
            } else {
                request.setAttribute("error", "Thêm quản trị viên thất bại!");
                request.getRequestDispatcher("views/quantrivien/pages/admin/add.jsp").forward(request, response);
                return;
            }

            // Chuyển hướng về danh sách
            response.sendRedirect("quantrivien?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi hệ thống!");
            request.getRequestDispatcher("views/quantrivien/pages/admin/add.jsp").forward(request, response);
        }
    }


    private void updateQuanTriVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String taiKhoan = request.getParameter("taiKhoan");
            String matKhau = request.getParameter("matKhau");
            String trangThaiStr = request.getParameter("trangThai");

            // Kiểm tra dữ liệu đầu vào
            if (taiKhoan == null || taiKhoan.trim().isEmpty() ||
                matKhau == null || matKhau.trim().isEmpty() ||
                trangThaiStr == null) {

                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
                showEditForm(request, response);
                return;
            }

            boolean trangThai = Boolean.parseBoolean(trangThaiStr);

            // Tự động cập nhật ngày sửa
            Date ngaySua = new Date();

            // Lấy thông tin quản trị viên hiện tại từ DB
            LQV_QuanTriVien qtvHienTai = quanTriVienDAO.getById(id);
            if (qtvHienTai == null) {
                request.setAttribute("error", "Không tìm thấy quản trị viên!");
                showEditForm(request, response);
                return;
            }

            // Tạo đối tượng quản trị viên với thông tin mới
            LQV_QuanTriVien qtvMoi = new LQV_QuanTriVien(id, taiKhoan, matKhau, trangThai, qtvHienTai.getLQV_ngay_tao(), ngaySua);

            // Thực hiện cập nhật
            boolean result = quanTriVienDAO.update(qtvMoi);

            if (result) {
                request.getSession().setAttribute("success", "Cập nhật quản trị viên thành công!");
            } else {
                request.setAttribute("error", "Cập nhật quản trị viên thất bại!");
                showEditForm(request, response);
                return;
            }

            // Chuyển hướng về danh sách
            response.sendRedirect("quantrivien?action=list");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "ID không hợp lệ!");
            showEditForm(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi trong quá trình cập nhật!");
            showEditForm(request, response);
        }
    }

    private void deleteQuanTriVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("🗑 Đang xóa quản trị viên ID: " + id); // Debug log

            LQV_QuanTriVienDAO dao = new LQV_QuanTriVienDAO();
            boolean success = dao.delete(id);

            if (success) {
                System.out.println("✅ Xóa thành công ID: " + id);
                response.getWriter().write("success");
            } else {
                System.out.println("❌ Xóa thất bại ID: " + id);
                response.getWriter().write("fail");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Lỗi parse ID: " + e.getMessage());
            response.getWriter().write("fail");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("fail");
        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");

        // Kiểm tra nếu tài khoản hoặc mật khẩu rỗng
        if (taiKhoan == null || matKhau == null || taiKhoan.trim().isEmpty() || matKhau.trim().isEmpty()) {
            System.out.println("⚠️ Tài khoản hoặc mật khẩu bị bỏ trống!");
            request.setAttribute("error", "Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        // Debug: In ra console để kiểm tra thông tin nhập vào
        System.out.println("📌 Đăng nhập với: " + taiKhoan + " / " + matKhau);

        LQV_QuanTriVien qtv = quanTriVienDAO.login(taiKhoan, matKhau);

        if (qtv != null) {
            System.out.println("✅ Đăng nhập thành công! Tài khoản: " + qtv.getLQV_tai_khoan());
            HttpSession session = request.getSession();
            session.setAttribute("admin", qtv);
            response.sendRedirect(request.getContextPath() + "/views/admin.jsp");
        } else {
            System.out.println("❌ Đăng nhập thất bại! Sai tài khoản hoặc mật khẩu.");
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        System.out.println("🚪 Đăng xuất thành công!");
        response.sendRedirect(request.getContextPath() + "/views/index.jsp");
    }
}
