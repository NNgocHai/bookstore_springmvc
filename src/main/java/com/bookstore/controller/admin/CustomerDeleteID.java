package com.bookstore.controller.admin;

import com.bookstore.service_impl.CustomerService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/admin/user/delete")
public class CustomerDeleteID extends HttpServlet{
    public CustomerDeleteID() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        CustomerService_impl customer = new CustomerService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(id);
            customer.deleteList(listId);
            request.setAttribute("customerList", customer.findAll());
            response.sendRedirect(request.getContextPath() + "/admin/user/list");

        }
        catch (Exception e)
        {

        }
    }
}
