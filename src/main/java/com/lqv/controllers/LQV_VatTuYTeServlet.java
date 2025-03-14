package com.lqv.controllers;

import com.lqv.dao.LQV_VatTuYTeDAO;
import com.lqv.models.LQV_VatTuYTe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/vattu")
public class LQV_VatTuYTeServlet extends HttpServlet {
    private LQV_VatTuYTeDAO vatTuYTeDAO;

    @Override
    public void init() throws ServletException {
        vatTuYTeDAO = new LQV_VatTuYTeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listVatTu(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "insert":
                    request.getRequestDispatcher("views/quantrivien/pages/VatTu/add.jsp").forward(request, response);
                    break;
                case "delete":
                    deleteVatTu(request, response);
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
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "⚠️ Thiếu tham số action!");
            return;
        }

        try {
            switch (action) {
                case "insert":
                    insertVatTu(request, response);
                    break;
                case "update":
                    updateVatTu(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "⚠️ Action không hợp lệ!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ Đã xảy ra lỗi hệ thống!");
        }
    }

    private void listVatTu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_VatTuYTe> danhSachVatTu = vatTuYTeDAO.getAll();
        request.setAttribute("danhSachVatTu", danhSachVatTu);
        request.getRequestDispatcher("views/quantrivien/pages/VatTu/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_VatTuYTe vatTu = vatTuYTeDAO.getById(id);
        request.setAttribute("vatTu", vatTu);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/quantrivien/pages/VatTu/edit.jsp");
        dispatcher.forward(request, response);

    }

    private void insertVatTu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String tenVatTu = request.getParameter("tenVatTu");
            String maVatTu = request.getParameter("maVatTu");
            String moTa = request.getParameter("moTa");
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            double donGia = Double.parseDouble(request.getParameter("donGia"));
            Date ngayHetHan = getDateParameter(request.getParameter("ngayHetHan"));

            if (tenVatTu.trim().isEmpty() || maVatTu.trim().isEmpty() || soLuong <= 0 || donGia <= 0) {
                request.setAttribute("error", "⚠️ Vui lòng điền đầy đủ thông tin!");
                request.getRequestDispatcher("views/quantrivien/pages/VatTu/add.jsp").forward(request, response);
                return;
            }

            LQV_VatTuYTe vatTu = new LQV_VatTuYTe(0, tenVatTu, maVatTu, soLuong, donGia, ngayHetHan, moTa, new Date(System.currentTimeMillis()));
            if (vatTuYTeDAO.insert(vatTu)) {
                request.getSession().setAttribute("success", "✅ Thêm vật tư thành công!");
            } else {
                request.setAttribute("error", "❌ Thêm vật tư thất bại!");
                request.getRequestDispatcher("views/quantrivien/pages/VatTu/add.jsp").forward(request, response);
                return;
            }

            response.sendRedirect("vattu?action=list");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "⚠️ Dữ liệu không hợp lệ!");
            request.getRequestDispatcher("views/quantrivien/pages/VatTu/add.jsp").forward(request, response);
        }
    }

    private void updateVatTu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String tenVatTu = request.getParameter("tenVatTu");
            String maVatTu = request.getParameter("maVatTu");
            String moTa = request.getParameter("moTa");
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            double donGia = Double.parseDouble(request.getParameter("donGia"));
            Date ngayHetHan = getDateParameter(request.getParameter("ngayHetHan"));

            LQV_VatTuYTe vatTuCu = vatTuYTeDAO.getById(id);
            if (vatTuCu == null) {
                request.setAttribute("error", "⚠️ Vật tư không tồn tại!");
                showEditForm(request, response);
                return;
            }

            LQV_VatTuYTe vatTuMoi = new LQV_VatTuYTe(id, tenVatTu, maVatTu, soLuong, donGia, ngayHetHan, moTa, vatTuCu.getLQV_ngay_tao());
            vatTuMoi.updateNgaySua();

            if (vatTuYTeDAO.update(vatTuMoi)) {
                request.getSession().setAttribute("success", "✅ Cập nhật vật tư thành công!");
            } else {
                request.setAttribute("error", "❌ Cập nhật vật tư thất bại!");
                showEditForm(request, response);
                return;
            }

            response.sendRedirect("vattu?action=list");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "⚠️ Dữ liệu không hợp lệ!");
            showEditForm(request, response);
        }
    }

    private void deleteVatTu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            response.getWriter().write(vatTuYTeDAO.delete(id) ? "success" : "fail");
        } catch (NumberFormatException e) {
            response.getWriter().write("fail");
        }
    }

    private Date getDateParameter(String dateStr) {
        return (dateStr != null && !dateStr.isEmpty()) ? Date.valueOf(dateStr) : null;
    }
}
