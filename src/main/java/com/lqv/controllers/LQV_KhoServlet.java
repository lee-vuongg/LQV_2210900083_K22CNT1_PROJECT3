package com.lqv.controllers;

import com.lqv.dao.LQV_KhoDAO;
import com.lqv.models.LQV_Kho;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/kho")
public class LQV_KhoServlet extends HttpServlet {
    private LQV_KhoDAO khoDAO;

    @Override
    public void init() throws ServletException {
        khoDAO = new LQV_KhoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listKho(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteKho(request, response);
                break;
            case "checkFull":
                checkKhoFull(request, response);
                break;
            default:
                listKho(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertKho(request, response);
                break;
            case "update":
                updateKho(request, response);
                break;
            default:
                listKho(request, response);
                break;
        }
    }

    private void listKho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_Kho> danhSachKho = khoDAO.getAll();
        request.setAttribute("danhSachKho", danhSachKho);
        request.getRequestDispatcher("/views/kho/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_Kho kho = khoDAO.getById(id);
        request.setAttribute("kho", kho);
        request.getRequestDispatcher("/views/kho/edit.jsp").forward(request, response);
    }

    private void insertKho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenKho = request.getParameter("tenKho");
        String diaChi = request.getParameter("diaChi");
        String nguoiQuanLy = request.getParameter("nguoiQuanLy");
        int sucChua = Integer.parseInt(request.getParameter("sucChua"));

        LQV_Kho kho = new LQV_Kho(0, tenKho, diaChi, nguoiQuanLy, sucChua);
        khoDAO.insert(kho);
        response.sendRedirect("kho?action=list");
    }

    private void updateKho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tenKho = request.getParameter("tenKho");
        String diaChi = request.getParameter("diaChi");
        String nguoiQuanLy = request.getParameter("nguoiQuanLy");
        int sucChua = Integer.parseInt(request.getParameter("sucChua"));

        LQV_Kho kho = new LQV_Kho(id, tenKho, diaChi, nguoiQuanLy, sucChua);
        khoDAO.update(kho);
        response.sendRedirect("kho?action=list");
    }

    private void deleteKho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        khoDAO.delete(id);
        response.sendRedirect("kho?action=list");
    }

    private void checkKhoFull(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int khoId = Integer.parseInt(request.getParameter("khoId"));
        boolean isFull = khoDAO.isKhoFull(khoId);
        response.getWriter().write(isFull ? "true" : "false");
    }
}
