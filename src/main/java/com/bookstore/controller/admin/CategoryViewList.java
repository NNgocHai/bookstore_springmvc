package com.bookstore.controller.admin;

import com.bookstore.entity.CategoryEntity;
import com.bookstore.service.CategoryService;
import com.bookstore.service_impl.CategoryService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/cate/list")
public class CategoryViewList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService=new CategoryService_impl();
        List<CategoryEntity> categoryList = categoryService.findAll();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistcategory.jsp");
        dispatcher.forward(request, response);
    }
}
