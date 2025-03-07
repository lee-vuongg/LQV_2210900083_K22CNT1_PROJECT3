package com.lqv.controllers;

import com.lqv.dao.QuanTriVienDAO;
import com.lqv.models.LQV_QuanTriVien;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAdminServlet", urlPatterns = {"/admin/add-admin"})
public class AddAdminServlet extends HttpServlet {

    private QuanTriVienDAO adminDAO = new QuanTriVienDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/add-admin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");
        boolean trangThai = "on".equals(request.getParameter("trangThai"));

        LQV_QuanTriVien admin = new LQV_QuanTriVien();
        admin.setLQV_tai_khoan(taiKhoan);
        admin.setLQV_mat_khau(matKhau);
        admin.setLQV_trang_thai(trangThai);

        adminDAO.addAdmin(admin);
        response.sendRedirect(request.getContextPath() + "/admin/admin-list");
    }
}
