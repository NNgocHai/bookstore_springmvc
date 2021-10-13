package com.bookstore.controller.admin;

import com.bookstore.entity.ReviewEntity;
import com.bookstore.service.ReviewService;
import com.bookstore.service_impl.ReviewService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/review/list")
public class ReviewViewList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService reviewService=new ReviewService_impl();
        List<ReviewEntity> reviewList = reviewService.findAll();
        request.setAttribute("reviewList", reviewList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/reviewviewlist.jsp");
        dispatcher.forward(request, response);
    }
}
