package com.lqv.controllers;

import com.lqv.dao.LQV_DonDatHangDAO;
import com.lqv.models.LQV_DonDatHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/dondathang")
public class LQV_DonDatHangServlet extends HttpServlet {
    private LQV_DonDatHangDAO donDatHangDAO;

    @Override
    public void init() throws ServletException {
        donDatHangDAO = new LQV_DonDatHangDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listDonDatHang(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteDonDatHang(request, response);
                break;
            default:
                listDonDatHang(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertDonDatHang(request, response);
                break;
            case "update":
                updateDonDatHang(request, response);
                break;
            case "updateStatus":
                updateStatus(request, response);
                break;
            default:
                listDonDatHang(request, response);
                break;
        }
    }

    private void listDonDatHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_DonDatHang> danhSachDonDatHang = donDatHangDAO.getAll();
        request.setAttribute("danhSachDonDatHang", danhSachDonDatHang);
        request.getRequestDispatcher("/views/dondathang/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_DonDatHang donDatHang = donDatHangDAO.getById(id);
        request.setAttribute("donDatHang", donDatHang);
        request.getRequestDispatcher("/views/dondathang/edit.jsp").forward(request, response);
    }

    private void insertDonDatHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Date ngayDatHang = Date.valueOf(request.getParameter("ngayDatHang"));
        int idKhachHang = Integer.parseInt(request.getParameter("idKhachHang"));
        int idNhaCungCap = Integer.parseInt(request.getParameter("idNhaCungCap"));
        double tongTien = Double.parseDouble(request.getParameter("tongTien"));
        String trangThai = request.getParameter("trangThai");

        LQV_DonDatHang donDatHang = new LQV_DonDatHang(0, ngayDatHang, idKhachHang, idNhaCungCap, tongTien, trangThai);
        donDatHangDAO.insert(donDatHang);
        response.sendRedirect("dondathang?action=list");
    }

    private void updateDonDatHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Date ngayDatHang = Date.valueOf(request.getParameter("ngayDatHang"));
        int idKhachHang = Integer.parseInt(request.getParameter("idKhachHang"));
        int idNhaCungCap = Integer.parseInt(request.getParameter("idNhaCungCap"));
        double tongTien = Double.parseDouble(request.getParameter("tongTien"));
        String trangThai = request.getParameter("trangThai");

        LQV_DonDatHang donDatHang = new LQV_DonDatHang(id, ngayDatHang, idKhachHang, idNhaCungCap, tongTien, trangThai);
        donDatHangDAO.update(donDatHang);
        response.sendRedirect("dondathang?action=list");
    }

    private void deleteDonDatHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        donDatHangDAO.delete(id);
        response.sendRedirect("dondathang?action=list");
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String newStatus = request.getParameter("newStatus");
        donDatHangDAO.updateStatus(id, newStatus);
        response.sendRedirect("dondathang?action=list");
    }
}
