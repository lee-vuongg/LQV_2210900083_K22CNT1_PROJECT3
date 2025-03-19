package com.lqv.controllers;

import com.lqv.dao.LQV_LichSuSuDungDAO;
import com.lqv.models.LQV_LichSuSuDung;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/lichsu")
public class LQV_LichSuSuDungServlet extends HttpServlet {
    private LQV_LichSuSuDungDAO lichSuSuDungDAO;

    @Override
    public void init() throws ServletException {
        lichSuSuDungDAO = new LQV_LichSuSuDungDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "list";

        System.out.println("[DEBUG] GET action: " + action);

        try {
            switch (action) {
                case "list":
                    listLichSu(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "insert":
                    request.getRequestDispatcher("views/quantrivien/pages/lichsusudung/add.jsp").forward(request, response);
                    break;
                case "delete":
                    deleteLichSu(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "⚠️ Action không hợp lệ!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ Đã xảy ra lỗi hệ thống!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        System.out.println("[DEBUG] POST action: " + action);

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "⚠️ Thiếu tham số action!");
            return;
        }

        try {
            switch (action) {
                case "insert":
                    insertLichSu(request, response);
                    break;
                case "update":
                    updateLichSu(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "⚠️ Action không hợp lệ!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ Đã xảy ra lỗi hệ thống!");
        }
    }

    private void listLichSu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_LichSuSuDung> danhSachLichSu = lichSuSuDungDAO.getAll();
        request.setAttribute("danhSachLichSu", danhSachLichSu);
        request.getRequestDispatcher("views/quantrivien/pages/lichsusudung/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("[DEBUG] ID edit: " + id);
        LQV_LichSuSuDung lichSu = lichSuSuDungDAO.getById(id);
        request.setAttribute("lichSu", lichSu);
        request.getRequestDispatcher("/views/quantrivien/pages/lichsusudung/edit.jsp").forward(request, response);
    }

    private void insertLichSu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            LQV_LichSuSuDung lichSu = extractLichSuFromRequest(request);
            if (lichSuSuDungDAO.insert(lichSu)) {
                response.sendRedirect(request.getContextPath() + "/lichsu?action=list");
            } else {
                request.setAttribute("error", "⚠️ Thêm lịch sử sử dụng thất bại!");
                request.getRequestDispatcher("views/quantrivien/pages/lichsusudung/add.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ Đã xảy ra lỗi khi thêm dữ liệu!");
        }
    }

    private void updateLichSu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            LQV_LichSuSuDung lichSu = extractLichSuFromRequest(request);
            if (lichSuSuDungDAO.update(lichSu)) {
                response.sendRedirect(request.getContextPath() + "/lichsu?action=list");
            } else {
                request.setAttribute("error", "⚠️ Cập nhật lịch sử sử dụng thất bại!");
                request.setAttribute("lichSu", lichSu);
                request.getRequestDispatcher("/views/quantrivien/pages/lichsusudung/edit.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Đã xảy ra lỗi khi cập nhật dữ liệu!");
            request.getRequestDispatcher("/views/quantrivien/pages/lichsusudung/edit.jsp").forward(request, response);
        }
    }

    private void deleteLichSu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("[DEBUG] ID delete: " + id);

        if (lichSuSuDungDAO.delete(id)) {
            response.sendRedirect(request.getContextPath() + "/lichsu?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ Xóa lịch sử sử dụng thất bại!");
        }
    }

    private LQV_LichSuSuDung extractLichSuFromRequest(HttpServletRequest request) {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
        int vatTuId = Integer.parseInt(request.getParameter("vatTuId"));
        int khachHangId = Integer.parseInt(request.getParameter("khachHangId"));
        String nguoiSuDung = request.getParameter("nguoiSuDung");
        Date ngaySuDung = Date.valueOf(request.getParameter("ngaySuDung"));
        int soLuong = Integer.parseInt(request.getParameter("soLuongSuDung"));
        String ghiChu = request.getParameter("ghiChu");

        return new LQV_LichSuSuDung(id, vatTuId, khachHangId, nguoiSuDung, ngaySuDung, soLuong, ghiChu);
    }
}
