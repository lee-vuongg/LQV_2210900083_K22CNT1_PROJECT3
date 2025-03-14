package com.lqv.controllers;

import com.lqv.dao.LQV_NhaCungCapDAO;
import com.lqv.models.LQV_NhaCungCap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/nhacungcap")
public class LQV_NhaCungCapServlet extends HttpServlet {
    private LQV_NhaCungCapDAO nhaCungCapDAO;

    @Override
    public void init() throws ServletException {
        nhaCungCapDAO = new LQV_NhaCungCapDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listNhaCungCap(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "insert":
                    request.getRequestDispatcher("views/quantrivien/pages/NhaCungCap/add.jsp").forward(request, response);
                    break;
                case "delete":
                    deleteNhaCungCap(request, response);
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
                    insertNhaCungCap(request, response);
                    break;
                case "update":
                    updateNhaCungCap(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "⚠️ Action không hợp lệ!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ Đã xảy ra lỗi hệ thống!");
        }
    }

    private void listNhaCungCap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_NhaCungCap> danhSachNCC = nhaCungCapDAO.getAll();
        request.setAttribute("danhSachNCC", danhSachNCC);
        request.getRequestDispatcher("views/quantrivien/pages/NhaCungCap/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("ID nhận được: " + id); // Debug: Kiểm tra id

            LQV_NhaCungCapDAO nhaCungCapDAO = new LQV_NhaCungCapDAO();
            LQV_NhaCungCap ncc = nhaCungCapDAO.getById(id);

            if (ncc != null) {
                System.out.println("Thông tin nhà cung cấp: " + ncc.getLQV_ten_nha_cung_cap()); // Debug: Kiểm tra thông tin
                request.setAttribute("ncc", ncc);
                request.getRequestDispatcher("/views/quantrivien/pages/NhaCungCap/edit.jsp").forward(request, response);
            } else {
                System.out.println("❌ Không tìm thấy thông tin nhà cung cấp với id: " + id);
                request.setAttribute("errorMessage", "Không tìm thấy thông tin nhà cung cấp!");
                request.getRequestDispatcher("/views/quantrivien/pages/NhaCungCap/edit.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi định dạng ID!");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ!");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi hệ thống!");
        }
    }


    private void insertNhaCungCap(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String tenNCC = request.getParameter("tenNCC");
            String diaChi = request.getParameter("diaChi");
            String soDienThoai = request.getParameter("soDienThoai");
            String email = request.getParameter("email");

            if (tenNCC.trim().isEmpty() || diaChi.trim().isEmpty() || soDienThoai.trim().isEmpty() || email.trim().isEmpty()) {
                request.setAttribute("error", "⚠️ Vui lòng điền đầy đủ thông tin!");
                request.getRequestDispatcher("views/quantrivien/pages/NhaCungCap/add.jsp").forward(request, response);
                return;
            }

            if (!email.contains("@")) {
                request.setAttribute("error", "⚠️ Email không hợp lệ!");
                request.getRequestDispatcher("views/quantrivien/pages/NhaCungCap/add.jsp").forward(request, response);
                return;
            }

            Date ngaySua = new Date(System.currentTimeMillis()); // Lấy ngày hiện tại

            LQV_NhaCungCap nhaCungCap = new LQV_NhaCungCap(0, tenNCC, diaChi, soDienThoai, email);
            nhaCungCap.setLQV_ngay_sua(ngaySua);

            if (nhaCungCapDAO.insert(nhaCungCap)) {
                request.getSession().setAttribute("success", "✅ Thêm nhà cung cấp thành công!");
            } else {
                request.setAttribute("error", "❌ Thêm nhà cung cấp thất bại!");
                request.getRequestDispatcher("views/quantrivien/pages/NhaCungCap/add.jsp").forward(request, response);
                return;
            }

            response.sendRedirect("nhacungcap?action=list");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "⚠️ Dữ liệu không hợp lệ!");
            request.getRequestDispatcher("views/quantrivien/pages/NhaCungCap/add.jsp").forward(request, response);
        }
    }

    private void updateNhaCungCap(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String tenNCC = request.getParameter("tenNCC");
            String diaChi = request.getParameter("diaChi");
            String soDienThoai = request.getParameter("soDienThoai");
            String email = request.getParameter("email");

            LQV_NhaCungCap nhaCungCapCu = nhaCungCapDAO.getById(id);
            if (nhaCungCapCu == null) {
                request.setAttribute("error", "⚠️ Nhà cung cấp không tồn tại!");
                listNhaCungCap(request, response);
                return;
            }

            if (tenNCC.trim().isEmpty() || diaChi.trim().isEmpty() || soDienThoai.trim().isEmpty() || email.trim().isEmpty()) {
                request.setAttribute("error", "⚠️ Vui lòng điền đầy đủ thông tin!");
                showEditForm(request, response);
                return;
            }

            if (!email.contains("@")) {
                request.setAttribute("error", "⚠️ Email không hợp lệ!");
                showEditForm(request, response);
                return;
            }

            Date ngaySua = new Date(System.currentTimeMillis()); // Cập nhật ngày sửa mới

            LQV_NhaCungCap nhaCungCapMoi = new LQV_NhaCungCap(id, tenNCC, diaChi, soDienThoai, email);
            nhaCungCapMoi.setLQV_ngay_sua(ngaySua);

            if (nhaCungCapDAO.update(nhaCungCapMoi)) {
                request.getSession().setAttribute("success", "✅ Cập nhật nhà cung cấp thành công!");
            } else {
                request.setAttribute("error", "❌ Cập nhật nhà cung cấp thất bại!");
                showEditForm(request, response);
                return;
            }

            response.sendRedirect("nhacungcap?action=list");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "⚠️ Dữ liệu không hợp lệ!");
            showEditForm(request, response);
        }
    }

    private void deleteNhaCungCap(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            response.getWriter().write(nhaCungCapDAO.delete(id) ? "success" : "fail");
        } catch (NumberFormatException e) {
            response.getWriter().write("fail");
        }
    }
}
