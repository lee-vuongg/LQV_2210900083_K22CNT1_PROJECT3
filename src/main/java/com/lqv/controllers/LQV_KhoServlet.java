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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }

        try {
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
                case "insert":
                    insertKho(request, response);
                    break;
                case "update":
                    updateKho(request, response);
                    break;
                case "checkFull":
                    checkKhoFull(request, response);
                    break;
                default:
                    listKho(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Lỗi xử lý request: " + e.getMessage());
            request.getRequestDispatcher("/views/quantrivien/pages/error.jsp").forward(request, response);
        }
    }

    private void listKho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_Kho> danhSachKho = khoDAO.getAll();
        request.setAttribute("danhSachKho", danhSachKho);
        request.getRequestDispatcher("/views/quantrivien/pages/Kho/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = parseInt(request.getParameter("id"));
            LQV_Kho kho = khoDAO.getById(id);
            if (kho != null) {
                request.setAttribute("kho", kho);
                request.getRequestDispatcher("/views/quantrivien/pages/Kho/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("kho?action=list");
            }
        } catch (Exception e) {
            response.sendRedirect("/kho?action=list");
        }
    }

    private void insertKho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String tenKho = request.getParameter("LQV_ten_kho");
            String viTri = request.getParameter("LQV_vi_tri");
            String nguoiQuanLy = request.getParameter("LQV_nguoi_quan_ly");
            int sucChua = Integer.parseInt(request.getParameter("LQV_suc_chua"));

            if (tenKho == null || tenKho.trim().isEmpty()) {
                throw new Exception("Tên kho không được để trống!");
            }

            LQV_Kho kho = new LQV_Kho(sucChua, tenKho, viTri, nguoiQuanLy, sucChua);
            LQV_KhoDAO khoDAO = new LQV_KhoDAO();
            khoDAO.insert(kho);

            response.sendRedirect("kho?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi thêm kho: " + e.getMessage());
            request.getRequestDispatcher("/admin/kho/add.jsp").forward(request, response);
        }
    }

    private void updateKho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            LQV_Kho kho = extractKhoFromRequest(request);
            
            // Kiểm tra xem dữ liệu trước khi cập nhật có đúng không
            System.out.println("Updating: " + kho.toString());

            boolean isUpdated = khoDAO.update(kho);
            if (isUpdated) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Cập nhật thất bại!");
            }

            response.sendRedirect(request.getContextPath() + "/kho?action=list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Dữ liệu nhập không hợp lệ!");
            request.setAttribute("kho", extractKhoFromRequest(request));
            request.getRequestDispatcher("/views/quantrivien/pages/Kho/edit.jsp").forward(request, response);
        }
    }



    private void deleteKho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = parseInt(request.getParameter("id"));
            khoDAO.delete(id);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "ID không hợp lệ!");
        }
        response.sendRedirect("kho?action=list");
    }

    private void checkKhoFull(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int khoId = parseInt(request.getParameter("khoId"));
            boolean isFull = khoDAO.isKhoFull(khoId, khoId);
            response.getWriter().write(isFull ? "true" : "false");
        } catch (Exception e) {
            response.getWriter().write("error");
        }
    }

    private LQV_Kho extractKhoFromRequest(HttpServletRequest request) {
        LQV_Kho kho = new LQV_Kho();
        
        try {
            int id = Integer.parseInt(request.getParameter("LQVid"));
            String tenKho = request.getParameter("LQV_ten_kho");
            String viTri = request.getParameter("LQV_vi_tri");
            String nguoiQuanLy = request.getParameter("LQV_nguoi_quan_ly");
            int sucChua = Integer.parseInt(request.getParameter("LQV_suc_chua"));

            kho.setLQVid(id);
            kho.setLQV_ten_kho(tenKho);
            kho.setLQV_vi_tri(viTri);
            kho.setLQV_nguoi_quan_ly(nguoiQuanLy);
            kho.setLQV_suc_chua(sucChua);

            // 🛠 Debug: Kiểm tra dữ liệu trước khi gửi vào DAO
            System.out.println("Extracted Kho from request: " + kho.toString());

        } catch (Exception e) {
            System.out.println("❌ Lỗi lấy dữ liệu từ request: " + e.getMessage());
            e.printStackTrace();
        }
        
        return kho;
    }


    private int parseInt(String value) {
        try {
            return (value != null && !value.trim().isEmpty()) ? Integer.parseInt(value) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
}
