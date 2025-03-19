package com.lqv.controllers;

import com.lqv.dao.LQV_NhaCungCapDAO;
import com.lqv.models.LQV_NhaCungCap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

@WebServlet("/nhacungcap")
public class LQV_NhaCungCapServlet extends HttpServlet {
    private LQV_NhaCungCapDAO nhaCungCapDAO;

    @Override
    public void init() throws ServletException {
        nhaCungCapDAO = new LQV_NhaCungCapDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) action = "list";

        try {
            switch (action) {
                case "list":
                    listNhaCungCap(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "insert":
                    forwardToPage(request, response, "/views/quantrivien/pages/NhaCungCap/add.jsp");
                    break;
                case "delete":
                    deleteNhaCungCap(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "⚠️ Action không hợp lệ!");
            }
        } catch (Exception e) {
            log("❌ Lỗi trong doGet: " + e.getMessage(), e);
            response.sendRedirect(request.getContextPath() + "/nhacungcap?action=list&error=" + URLEncoder.encode("❌ Đã xảy ra lỗi hệ thống!", "UTF-8"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/nhacungcap?action=list&error=" + URLEncoder.encode("⚠️ Action không hợp lệ!", "UTF-8"));
            return;
        }

        try {
            switch (action) {
                case "insert":
                    insertNhaCungCap(request, response);
                    break;
                case "update":
                    update(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/nhacungcap?action=list&error=" + URLEncoder.encode("⚠️ Action không hợp lệ!", "UTF-8"));
            }
        } catch (Exception e) {
            log("❌ Lỗi trong doPost: " + e.getMessage(), e);
            response.sendRedirect(request.getContextPath() + "/nhacungcap?action=list&error=" + URLEncoder.encode("❌ Đã xảy ra lỗi hệ thống!", "UTF-8"));
        }
    }
    private void listNhaCungCap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_NhaCungCap> danhSachNCC = nhaCungCapDAO.getAll();
        request.setAttribute("danhSachNCC", danhSachNCC);
        forwardToPage(request, response, "views/quantrivien/pages/NhaCungCap/list.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_NhaCungCap ncc = nhaCungCapDAO.getById(id);

        if (ncc != null) {
            request.setAttribute("ncc", ncc);
            forwardToPage(request, response, "/views/quantrivien/pages/NhaCungCap/edit.jsp");
        } else {
            if (!response.isCommitted()) {
                response.sendRedirect(request.getContextPath() + "/nhacungcap?action=list&error=notfound");
            }
        }
    }

    private void insertNhaCungCap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String tenNCC = request.getParameter("tenNCC");
            String diaChi = request.getParameter("diaChi");
            String soDienThoai = request.getParameter("soDienThoai");
            String email = request.getParameter("email");

            // Kiểm tra dữ liệu đầu vào
            if (tenNCC == null || tenNCC.trim().isEmpty() ||
                diaChi == null || diaChi.trim().isEmpty() ||
                soDienThoai == null || soDienThoai.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                request.setAttribute("error", "⚠️ Vui lòng điền đầy đủ thông tin!");
                forwardToPage(request, response, "/views/quantrivien/pages/NhaCungCap/add.jsp");
                return;
            }

            // Kiểm tra định dạng email
            if (!email.contains("@")) {
                request.setAttribute("error", "⚠️ Email không hợp lệ!");
                forwardToPage(request, response, "/views/quantrivien/pages/NhaCungCap/add.jsp");
                return;
            }

            // Tạo đối tượng nhà cung cấp
            LQV_NhaCungCap nhaCungCap = new LQV_NhaCungCap(0, tenNCC, diaChi, soDienThoai, email);
            nhaCungCap.setLQV_ngay_sua(new Date(System.currentTimeMillis()));

            // Chèn vào CSDL
            if (nhaCungCapDAO.insert(nhaCungCap)) {
                request.getSession().setAttribute("success", "✅ Thêm nhà cung cấp thành công!");
                response.sendRedirect(request.getContextPath() + "/nhacungcap?action=list");
            } else {
                request.setAttribute("error", "❌ Thêm nhà cung cấp thất bại!");
                forwardToPage(request, response, "/views/quantrivien/pages/NhaCungCap/add.jsp");
            }
        } catch (Exception e) {
            log("❌ Lỗi trong insertNhaCungCap: " + e.getMessage(), e);
            request.setAttribute("error", "❌ Đã xảy ra lỗi hệ thống!");
            forwardToPage(request, response, "/views/quantrivien/pages/NhaCungCap/add.jsp");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Update function called");

        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String dienThoai = request.getParameter("dienThoai");
        String email = request.getParameter("email");

        LQV_NhaCungCap ncc = new LQV_NhaCungCap(id, ten, diaChi, dienThoai, email);
        boolean isUpdated = nhaCungCapDAO.update(ncc);

        if (isUpdated) {
            System.out.println("✅ Update thành công! Chuyển hướng...");
            response.sendRedirect(request.getContextPath() + "/nhacungcap?action=list&success=✔️ Cập nhật thành công!");
        } else {
            System.out.println("❌ Update thất bại! Trả về trang sửa.");
            request.setAttribute("errorMessage", "❌ Cập nhật thất bại!");
            request.getRequestDispatcher("pages/NhacCungCap/edit.jsp").forward(request, response);
        }
    }

    private void deleteNhaCungCap(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            response.getWriter().write(nhaCungCapDAO.delete(id) ? "success" : "fail");
        } catch (NumberFormatException e) {
            response.getWriter().write("fail");
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        if (!response.isCommitted()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}