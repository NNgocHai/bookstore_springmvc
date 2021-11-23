package com.bookstore.controller.admin;

import com.bookstore.entity.ReviewEntity;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
@Controller
@RequestMapping("/admin/")
public class QuanLiReviewController {
    @Autowired
    ReviewService reviewService=new ReviewService_impl();

    @RequestMapping(value = "review/list", method = RequestMethod.GET)
    public String ReviewList(ModelMap model ) {
        List<ReviewEntity> reviewList = reviewService.findAll();
        model.addAttribute("reviewList", reviewList);
        return "/admin/reviewviewlist";
    }
}
