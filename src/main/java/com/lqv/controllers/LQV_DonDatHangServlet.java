package com.lqv.controllers;

import com.lqv.dao.LQV_DonDatHangDAO;
import com.lqv.models.LQV_DonDatHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/dondathang")
public class LQV_DonDatHangServlet extends HttpServlet {
    private LQV_DonDatHangDAO donDatHangDAO;

    @Override
    public void init() throws ServletException {
        donDatHangDAO = new LQV_DonDatHangDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "insert":
                    insertDonDatHang(request, response);
                    break;
                case "update":
                    updateDonHang(request, response);
                    break;
                case "updateStatus":
                    updateStatus(request, response);
                    break;
                default:
                    listDonDatHang(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void listDonDatHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_DonDatHang> danhSachDonDatHang = donDatHangDAO.getAll();
        request.setAttribute("danhSachDonDatHang", danhSachDonDatHang);
        request.getRequestDispatcher("/views/quantrivien/pages/donhang/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_DonDatHang donDatHang = donDatHangDAO.getById(id);

        if (donDatHang == null) {
            response.sendRedirect("dondathang?action=list");
            return;
        }

        request.setAttribute("donDatHang", donDatHang);
        request.getRequestDispatcher("/views/quantrivien/pages/donhang/edit.jsp").forward(request, response);
    }

    private void insertDonDatHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Timestamp ngayDatHang = Timestamp.valueOf(request.getParameter("ngayDatHang") + " 00:00:00");
            int idNhaCungCap = Integer.parseInt(request.getParameter("idNhaCungCap"));
            double tongTien = Double.parseDouble(request.getParameter("tongTien"));
            String trangThai = request.getParameter("trangThai");

            LQV_DonDatHang donDatHang = new LQV_DonDatHang(0, ngayDatHang, idNhaCungCap, tongTien, trangThai);
            donDatHangDAO.insert(donDatHang);

            response.sendRedirect("dondathang?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void updateDonHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Kiểm tra ID
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                System.out.println("ERROR: ID bị null hoặc rỗng!");
                response.sendRedirect("dondathang?error=id-null");
                return;
            }
            int id = Integer.parseInt(idParam);

            // Kiểm tra ngày đặt hàng
            String ngayDatHangParam = request.getParameter("ngayDatHang");
            Timestamp ngayDatHang = null;
            if (ngayDatHangParam != null && !ngayDatHangParam.trim().isEmpty()) {
                ngayDatHang = Timestamp.valueOf(ngayDatHangParam + " 00:00:00");
            } else {
                ngayDatHang = new Timestamp(System.currentTimeMillis()); // Mặc định ngày hiện tại nếu không có
            }

            // Kiểm tra ID nhà cung cấp
            String idNhaCungCapParam = request.getParameter("idNhaCungCap");
            int idNhaCungCap = 0;
            if (idNhaCungCapParam != null && !idNhaCungCapParam.trim().isEmpty()) {
                idNhaCungCap = Integer.parseInt(idNhaCungCapParam);
            }
            if (idNhaCungCap == 0) {
                System.out.println("WARNING: ID nhà cung cấp = 0, có thể gây lỗi khóa ngoại!");
            }

            // Kiểm tra tổng tiền
            String tongTienParam = request.getParameter("tongTien");
            double tongTien = 0.0;
            if (tongTienParam != null && !tongTienParam.trim().isEmpty()) {
                tongTien = Double.parseDouble(tongTienParam);
            }
            if (tongTien <= 0) {
                System.out.println("WARNING: Tổng tiền <= 0, hãy kiểm tra dữ liệu!");
            }

            // Kiểm tra tình trạng đơn hàng
            String tinhTrang = request.getParameter("tinhTrang");
            if (tinhTrang == null || tinhTrang.trim().isEmpty()) {
                tinhTrang = "Chờ xử lý"; // Mặc định nếu không có tình trạng
            }

            // Debug: Kiểm tra dữ liệu trước khi cập nhật
            System.out.println("DEBUG: Chuẩn bị cập nhật đơn hàng:");
            System.out.println("  - ID: " + id);
            System.out.println("  - Ngày đặt hàng: " + ngayDatHang);
            System.out.println("  - Nhà cung cấp: " + idNhaCungCap);
            System.out.println("  - Tổng tiền: " + tongTien);
            System.out.println("  - Tình trạng: " + tinhTrang);

            // Kiểm tra xem đơn hàng có tồn tại không
            if (!donDatHangDAO.exists(id)) {
                System.out.println("ERROR: Đơn hàng ID " + id + " không tồn tại!");
                response.sendRedirect("dondathang?error=not-found");
                return;
            }

            // Cập nhật đơn hàng
            LQV_DonDatHang donHang = new LQV_DonDatHang(id, ngayDatHang, idNhaCungCap, tongTien, tinhTrang);
            boolean updateSuccess = donDatHangDAO.update(donHang);
            
            if (updateSuccess) {
                System.out.println("SUCCESS: Cập nhật đơn hàng thành công!");
                response.sendRedirect("dondathang");
            } else {
                System.out.println("ERROR: Lỗi khi cập nhật đơn hàng!");
                response.sendRedirect("dondathang?error=update-failed");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Lỗi chuyển đổi kiểu dữ liệu! " + e.getMessage());
            response.sendRedirect("dondathang?error=invalid-data");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dondathang?error=update-exception");
        }
    }

    private void deleteDonDatHang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            donDatHangDAO.delete(id);
            response.sendRedirect("dondathang?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String newStatus = request.getParameter("newStatus");

            donDatHangDAO.updateStatus(id, newStatus);
            response.sendRedirect("dondathang?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
