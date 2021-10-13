package com.bookstore.controller.admin;

import com.bookstore.service_impl.ShipperService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/ship/delete")
public class ShipperDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        ShipperService_impl shipper = new ShipperService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(id);
            shipper.deleteList(listId);
            request.setAttribute("shipperList", shipper.findAll());
            response.sendRedirect(request.getContextPath() + "/admin/ship/list");

        }
        catch (Exception e)
        {

        }
    }
}
