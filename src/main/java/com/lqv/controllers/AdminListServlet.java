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

@WebServlet(name = "AdminListServlet", urlPatterns = {"/admin/list"})
public class AdminListServlet extends HttpServlet {

    private QuanTriVienDAO adminDAO = new QuanTriVienDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_QuanTriVien> adminList = adminDAO.getAllAdmins();
        request.setAttribute("adminList", adminList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/admin-list.jsp");
        dispatcher.forward(request, response);
    }
} 
