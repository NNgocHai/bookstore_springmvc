package com.bookstore.controller.admin;

import com.bookstore.service.ProductService;
import com.bookstore.service_impl.ProductService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/product/delete")
public class ProductDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cuonsach_id= Integer.parseInt(request.getParameter("cuonsach_id"));
        ProductService productService = new ProductService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        HttpSession session = request.getSession();
        //String cuonsach_ten = (String) session.getAttribute("cuonsach");
        listId.add(cuonsach_id);
        productService.deleteList(listId);
        request.setAttribute("cuonsachList",productService.findAll());
        response.sendRedirect(request.getContextPath()+"/admin/product/list");
    }
}
