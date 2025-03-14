package com.lqv.controllers;

import com.lqv.dao.LQV_LichSuSuDungDAO;
import com.lqv.models.LQV_LichSuSuDung;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/lichsusudung")
public class LQV_LichSuSuDungServlet extends HttpServlet {
    private LQV_LichSuSuDungDAO lichSuSuDungDAO;

    @Override
    public void init() throws ServletException {
        lichSuSuDungDAO = new LQV_LichSuSuDungDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listLichSuSuDung(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteLichSuSuDung(request, response);
                break;
            default:
                listLichSuSuDung(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertLichSuSuDung(request, response);
                break;
            case "update":
                updateLichSuSuDung(request, response);
                break;
            default:
                listLichSuSuDung(request, response);
                break;
        }
    }

    private void listLichSuSuDung(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_LichSuSuDung> danhSachLichSu = lichSuSuDungDAO.getAll();
        request.setAttribute("danhSachLichSu", danhSachLichSu);
        request.getRequestDispatcher("/views/lichsusudung/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_LichSuSuDung lichSu = lichSuSuDungDAO.getById(id);
        request.setAttribute("lichSuSuDung", lichSu);
        request.getRequestDispatcher("/views/lichsusudung/edit.jsp").forward(request, response);
    }

    private void insertLichSuSuDung(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idVatTu = Integer.parseInt(request.getParameter("idVatTu"));
        int idKhachHang = Integer.parseInt(request.getParameter("idKhachHang"));
        Date ngaySuDung = Date.valueOf(request.getParameter("ngaySuDung"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));

        LQV_LichSuSuDung lichSu = new LQV_LichSuSuDung(0, idVatTu, idKhachHang, ngaySuDung, soLuong);
        lichSuSuDungDAO.insert(lichSu);
        response.sendRedirect("lichsusudung?action=list");
    }

    private void updateLichSuSuDung(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idVatTu = Integer.parseInt(request.getParameter("idVatTu"));
        int idKhachHang = Integer.parseInt(request.getParameter("idKhachHang"));
        Date ngaySuDung = Date.valueOf(request.getParameter("ngaySuDung"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));

        LQV_LichSuSuDung lichSu = new LQV_LichSuSuDung(id, idVatTu, idKhachHang, ngaySuDung, soLuong);
        lichSuSuDungDAO.update(lichSu);
        response.sendRedirect("lichsusudung?action=list");
    }

    private void deleteLichSuSuDung(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        lichSuSuDungDAO.delete(id);
        response.sendRedirect("lichsusudung?action=list");
    }
}
