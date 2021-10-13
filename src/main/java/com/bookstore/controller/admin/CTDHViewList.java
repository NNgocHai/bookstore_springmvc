package com.bookstore.controller.admin;

import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.service.ChiTietDonHangService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/chitietdonhang/list")
public class CTDHViewList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
        List<ChiTietDonHangEntity> list = chiTietDonHangService.findSpec();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistCTDH.jsp");
        dispatcher.forward(request, response);
    }
}
