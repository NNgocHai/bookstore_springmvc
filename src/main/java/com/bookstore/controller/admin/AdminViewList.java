package com.bookstore.controller.admin;


import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/admin/list")
public class AdminViewList extends HttpServlet {
    public AdminViewList() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminService admin = new AdminService_impl();
        List<AdminsEntity> adminList = admin.findAll();
        request.setAttribute("adminList", adminList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistadmin.jsp");
        dispatcher.forward(request, response);
    }
}
