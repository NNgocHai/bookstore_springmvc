package com.bookstore.controller.admin;

import com.bookstore.entity.ReviewEntity;
import com.bookstore.service.ReviewService;
import com.bookstore.service_impl.ReviewService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin1")
public class ReviewViewList{
    public ReviewViewList() {
        super();
    }


    @RequestMapping(value = "review/list", method = RequestMethod.GET)
    public String doGet(ModelMap model ) {
        ReviewService reviewService=new ReviewService_impl();
        List<ReviewEntity> reviewList = reviewService.findAll();
        model.addAttribute("reviewList", reviewList);
        return "/admin/reviewviewlist";

    }
}
