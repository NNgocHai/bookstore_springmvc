package com.bookstore.controller.admin;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.service.CustomerService;
import com.bookstore.service_impl.CustomerService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user/list")
public class CustomerViewList extends HttpServlet {
    public CustomerViewList() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customer = new CustomerService_impl();
        List<CustomerEntity> customerList = customer.findAll();
        request.setAttribute("customerList", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistcustomer.jsp");
        dispatcher.forward(request, response);
    }
}
