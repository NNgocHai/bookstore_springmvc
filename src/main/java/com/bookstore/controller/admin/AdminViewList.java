package com.bookstore.controller.admin;


import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminViewList {
    public AdminViewList() {
        super();
    }

    @RequestMapping("admin/list")
    public String doGet(ModelMap model){

        AdminService admin = new AdminService_impl();
        List<AdminsEntity> adminList = admin.findAll();
        model.addAttribute("adminList", adminList);

        return "admin/viewlistadmin";
    }
}
