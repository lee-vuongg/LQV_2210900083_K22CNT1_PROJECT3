package com.lqv.controllers;

import com.lqv.dao.QuanTriVienDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminDeleteServlet", urlPatterns = {"/admin/delete"})
public class AdminDeleteServlet extends HttpServlet {

    private QuanTriVienDAO adminDAO = new QuanTriVienDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            adminDAO.deleteAdmin(id);
            response.sendRedirect(request.getContextPath() + "/admin/admin-list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/admin-list?error=delete-failed");
        }
    }
} 