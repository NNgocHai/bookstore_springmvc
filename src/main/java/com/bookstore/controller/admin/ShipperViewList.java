package com.bookstore.controller.admin;

import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ShipperService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/ship/list")
public class ShipperViewList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShipperService shipperService=new ShipperService_impl();
        List<ShipperEntity> shipperList = shipperService.findAll();
        request.setAttribute("shipperList", shipperList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/shipperviewlist.jsp");
        dispatcher.forward(request, response);
    }
}
