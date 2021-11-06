package com.bookstore.controller.admin;


import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service_impl.CategoryService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/product/list")
public class ProductViewList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page= request.getParameter("page");//so trang
        if (page == null)
            page= "1";
        int maxResult = 10;// so bang record load len 1 trang
        int maxNavigationPage = 6;// max so trang hien ra

        CategoryService_impl categoryService =new CategoryService_impl();
        NavigationDao_impl<CuonSachEntity> navigationDaoImpl = new NavigationDao_impl<CuonSachEntity>();
        navigationDaoImpl.Navigation(Integer.valueOf(page),maxResult,maxNavigationPage );
        request.setAttribute("cuonsachList", navigationDaoImpl.getList());
        request.setAttribute("navigationDaoImpl", navigationDaoImpl);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistcuonsach.jsp");
        dispatcher.forward(request, response);
    }
}
