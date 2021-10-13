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
import java.util.List;

@WebServlet("/admin/order/list")
public class OrderViewList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DonHangService donhang = new DonHangService_impl();
        List<DonHangEntity> donhangList = donhang.findAll();
        request.setAttribute("donhangList", donhangList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistdonhang.jsp");
        dispatcher.forward(request, response);
    }
}