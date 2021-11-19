package com.bookstore.controller.admin;

import com.bookstore.entity.DonHangEntity;
import com.bookstore.service.DonHangService;
import com.bookstore.service_impl.DonHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin1/")
public class OrderViewList{
    public OrderViewList(){super();}

    @RequestMapping("order/list")
    public String doGet(ModelMap model, @ModelAttribute("message") String message) {
        DonHangService donhang = new DonHangService_impl();
        List<DonHangEntity> donhangList = donhang.findAll();
        model.addAttribute("donhangList", donhangList);
        model.addAttribute("message",   message);
        return "/admin/viewlistdonhang";
    }
}