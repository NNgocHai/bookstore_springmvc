package com.bookstore.controller.admin;

import com.bookstore.service_impl.DonHangService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/order/delete")
public class OrderDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        DonHangService_impl donhang = new DonHangService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(id);
            donhang.deleteList(listId);
            request.setAttribute("donhangList", donhang.findAll());
            response.sendRedirect(request.getContextPath() + "/admin/order/list");

        }
        catch (Exception e)
        {

        }
    }
}
