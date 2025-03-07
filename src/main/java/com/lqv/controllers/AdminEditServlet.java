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
import java.util.List;

@WebServlet(name = "AdminEditServlet", urlPatterns = {"/admin/edit"})
public class AdminEditServlet extends HttpServlet {

    private QuanTriVienDAO adminDAO = new QuanTriVienDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<LQV_QuanTriVien> adminList = adminDAO.getAllAdmins();
        request.setAttribute("adminList", adminList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/admin-edit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");
        boolean trangThai = "on".equals(request.getParameter("trangThai"));

        LQV_QuanTriVien admin = new LQV_QuanTriVien(id, taiKhoan, matKhau, trangThai, null, null);
        adminDAO.updateAdmin(admin);
        response.sendRedirect(request.getContextPath() + "/admin/list");
    }
} 
