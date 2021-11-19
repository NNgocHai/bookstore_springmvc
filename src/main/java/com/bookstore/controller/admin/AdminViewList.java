package com.bookstore.controller.admin;


import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import java.util.List;

@Controller
@RequestMapping("/admin2/")
public class AdminViewList {
    public AdminViewList() {
        super();
    }

    @RequestMapping("admin/list")
    public String doGet(@ModelAttribute("message") String message, ModelMap model){

        AdminService admin = new AdminService_impl();
        List<AdminsEntity> adminList = admin.findAll();
        model.addAttribute("adminList", adminList);
        model.addAttribute("message",   message);
        return "admin/viewlistadmin";
    }
}
