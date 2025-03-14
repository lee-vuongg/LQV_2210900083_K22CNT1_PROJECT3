package com.lqv.controllers;

import com.lqv.dao.LQV_BaoTriThietBiDAO;
import com.lqv.models.LQV_BaoTriThietBi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/baotri")
public class LQV_BaoTriThietBiServlet extends HttpServlet {
    private LQV_BaoTriThietBiDAO baoTriThietBiDAO;

    @Override
    public void init() throws ServletException {
        baoTriThietBiDAO = new LQV_BaoTriThietBiDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteBaoTri(request, response);
                break;
            default:
                listBaoTri(request, response);
                break;
        }
    }

    private void listBaoTri(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LQV_BaoTriThietBi> danhSachBaoTri = baoTriThietBiDAO.getAll();
        request.setAttribute("danhSachBaoTri", danhSachBaoTri);
        request.getRequestDispatcher("/views/baotri/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LQV_BaoTriThietBi baoTri = baoTriThietBiDAO.getById(id);
        if (baoTri == null) {
            response.sendRedirect("baotri");
            return;
        }
        request.setAttribute("baoTri", baoTri);
        request.getRequestDispatcher("/views/baotri/edit.jsp").forward(request, response);
    }

    private void deleteBaoTri(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        baoTriThietBiDAO.delete(id);
        response.sendRedirect("baotri");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "save";

        switch (action) {
            case "update":
                updateBaoTri(request, response);
                break;
            case "insert":
                insertBaoTri(request, response);
                break;
            default:
                response.sendRedirect("baotri");
                break;
        }
    }

    private void insertBaoTri(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int vatTuId = Integer.parseInt(request.getParameter("vatTuId"));
            Date ngayBaoTri = Date.valueOf(request.getParameter("ngayBaoTri"));
            String moTa = request.getParameter("moTa");
            double chiPhi = Double.parseDouble(request.getParameter("chiPhi"));

            LQV_BaoTriThietBi baoTri = new LQV_BaoTriThietBi(0, vatTuId, ngayBaoTri, moTa, chiPhi);
            baoTriThietBiDAO.insert(baoTri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("baotri");
    }

    private void updateBaoTri(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int vatTuId = Integer.parseInt(request.getParameter("vatTuId"));
            Date ngayBaoTri = Date.valueOf(request.getParameter("ngayBaoTri"));
            String moTa = request.getParameter("moTa");
            double chiPhi = Double.parseDouble(request.getParameter("chiPhi"));

            LQV_BaoTriThietBi baoTri = new LQV_BaoTriThietBi(id, vatTuId, ngayBaoTri, moTa, chiPhi);
            baoTriThietBiDAO.update(baoTri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("baotri");
    }
}
