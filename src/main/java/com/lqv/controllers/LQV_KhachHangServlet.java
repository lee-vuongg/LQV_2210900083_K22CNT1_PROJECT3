package com.lqv.controllers;

import com.lqv.dao.LQV_KhachHangDAO;
import com.lqv.models.LQV_KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/khachhang")
public class LQV_KhachHangServlet extends HttpServlet {
    private LQV_KhachHangDAO khachHangDAO;

    @Override
    public void init() throws ServletException {
        khachHangDAO = new LQV_KhachHangDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listKhachHang(request, response);
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
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

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
            default:
                listKhachHang(request, response);
                break;
        }
    }

    private void listKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_KhachHang> danhSachKhachHang = khachHangDAO.getAll();
        request.setAttribute("danhSachKhachHang", danhSachKhachHang);
        request.getRequestDispatcher("/views/khachhang/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_KhachHang khachHang = khachHangDAO.getById(id);
        request.setAttribute("khachHang", khachHang);
        request.getRequestDispatcher("/views/khachhang/edit.jsp").forward(request, response);
    }

    private void insertKhachHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String diaChi = request.getParameter("diaChi");
        String soDienThoai = request.getParameter("soDienThoai");
        boolean trangThai = Boolean.parseBoolean(request.getParameter("trangThai"));

        if (khachHangDAO.isEmailExist(email)) {
            response.sendRedirect("register.jsp?error=Email đã tồn tại!");
            return;
        }

        LQV_KhachHang khachHang = new LQV_KhachHang(0, taiKhoan, matKhau, hoTen, email, diaChi, soDienThoai, trangThai, null, null);
        khachHangDAO.insert(khachHang);
        response.sendRedirect("khachhang?action=list");
    }

    private void updateKhachHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String diaChi = request.getParameter("diaChi");
        String soDienThoai = request.getParameter("soDienThoai");
        boolean trangThai = Boolean.parseBoolean(request.getParameter("trangThai"));

        LQV_KhachHang khachHang = new LQV_KhachHang(id, taiKhoan, matKhau, hoTen, email, diaChi, soDienThoai, trangThai, null, null);
        khachHangDAO.update(khachHang);
        response.sendRedirect("khachhang?action=list");
    }

    private void deleteKhachHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        khachHangDAO.delete(id);
        response.sendRedirect("khachhang?action=list");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");

        LQV_KhachHang khachHang = khachHangDAO.login(taiKhoan, matKhau);
        if (khachHang != null) {
            HttpSession session = request.getSession();
            session.setAttribute("khachHang", khachHang);
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}
