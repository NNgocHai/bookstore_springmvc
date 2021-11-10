package com.bookstore.controller.admin;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao_impl.AdminDao_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/")
public class AdminLogin {

    public AdminLogin() {
        super();
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String doGet(){

        return "admin/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doPost(@RequestParam("user") String user,
                         @RequestParam("password") String password,
                         ModelMap model,
                         HttpSession session) {

        AdminDao adminDao = new AdminDao_impl();
        boolean a = adminDao.checkAdminLogin(user,password);
        if (a) {
            session.setAttribute("user_admin", user);
            session.setAttribute("password_admin", password);
            return "redirect:/admin/home";
        } else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            return "admin/login";
        }

    }
}