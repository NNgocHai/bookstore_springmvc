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


@WebServlet("/admin/cate/edit")
public class CategoryEdit extends HttpServlet {
    CategoryService categoryService=new CategoryService_impl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category_id= Integer.parseInt(request.getParameter("category-id"));
        CategoryEntity categoryEntity = categoryService.findById(category_id);
        request.setAttribute("category", categoryEntity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/editcategory.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String category_id_String=request.getParameter("category-id");
        int category_id = Integer.parseInt(request.getParameter("category-id"));
        String category_name = request.getParameter("category-name");
        if(!category_name.equals(""))
        {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setMa_DauSach(category_id);
            categoryEntity.setTen_DauSach(category_name);
            categoryService.update(categoryEntity);
            response.sendRedirect(request.getContextPath() + "/admin/cate/list");
        }
        else {

            request.setAttribute("errorMessage","Tên đầu sách trống");
            request.setAttribute("category_id",category_id_String);
            RequestDispatcher rd=request.getRequestDispatcher("/views/admin/editcategory.jsp");
            rd.forward(request,response);
        }

    }
}
