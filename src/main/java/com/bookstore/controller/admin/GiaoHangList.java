package com.bookstore.controller.admin;

import com.bookstore.entity.GiaoHangEntity;
import com.bookstore.service.GiaoHangService;
import com.bookstore.service_impl.GiaoHangService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/giaohang/list")
public class GiaoHangList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GiaoHangService giaoHangService = new GiaoHangService_impl();
        List<GiaoHangEntity> list = giaoHangService.findTT_GH();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistGH.jsp");
        dispatcher.forward(request, response);
    }
}
