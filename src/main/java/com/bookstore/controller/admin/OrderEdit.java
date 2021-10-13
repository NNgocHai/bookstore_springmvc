package com.bookstore.controller.admin;

import com.bookstore.entity.DonHangEntity;
import com.bookstore.service.DonHangService;
import com.bookstore.service_impl.DonHangService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/admin/order/edit")
public class OrderEdit extends HttpServlet {
    DonHangService donHangService=new DonHangService_impl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        DonHangEntity donHangEntity = donHangService.findById(id);
        request.setAttribute("donhang", donHangEntity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/editdonhang.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int id =Integer.parseInt(request.getParameter("id"));

        int maKH = Integer.parseInt(request.getParameter("maKH"));
        String diachi = request.getParameter("diachi");
        String sdt = request.getParameter("sdt");
        Timestamp ngaydat = Timestamp.valueOf(request.getParameter("ngaydat"));
        int tongtien = Integer.parseInt(request.getParameter("tongtien"));
        String tinhtrang = request.getParameter("tinhtrang");

        DonHangEntity donHangEntity = new DonHangEntity();
        donHangEntity.setMa_Customer(maKH);
        donHangEntity.setMa_DH(id);
        donHangEntity.setDiachi(diachi);
        donHangEntity.setNgaydat(ngaydat);
        donHangEntity.setSdt(sdt);
        donHangEntity.setTongtien(tongtien);
        donHangEntity.setActiveDH(tinhtrang);
        donHangService.update(donHangEntity);
        response.sendRedirect(request.getContextPath() + "/admin/order/list");

    }
}
