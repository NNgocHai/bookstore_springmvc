package com.bookstore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin/")
public class AdminLogout {
    private static final long serialVersionUID = 1L;

    public AdminLogout() {
        super();
    }

    @RequestMapping("logout")
    public String doGet(HttpSession session){

        session.removeAttribute("user_admin");
        session.removeAttribute("password_admin");

        session.invalidate();

        return "admin/login";
    }
}
