package com.bookstore.controller.admin;

import com.bookstore.service.CategoryService;
import com.bookstore.service_impl.CategoryService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/cate/delete")
public class CategoryDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category_id= Integer.parseInt(request.getParameter("category-id"));
        CategoryService category = new CategoryService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        HttpSession session = request.getSession();
        //dString category_tk = (String) session.getAttribute("cate");
        listId.add(category_id);
        category.deleteList(listId);
        request.setAttribute("categoryList",category.findAll());
        response.sendRedirect(request.getContextPath()+"/admin/cate/list");

    }
}
