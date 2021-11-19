package com.bookstore.controller.admin;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao.DonHangDao;
import com.bookstore.dao.ShipperDao;
import com.bookstore.dao_impl.AdminDao_impl;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.dao_impl.ShipperDao_impl;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.ReviewEntity;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
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
