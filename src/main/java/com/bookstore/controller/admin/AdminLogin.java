package com.bookstore.controller.admin;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao_impl.AdminDao_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/admin/login")
public class AdminLogin extends HttpServlet {

    public AdminLogin() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/admin/login.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        AdminDao adminDao = new AdminDao_impl();
        boolean a = adminDao.checkAdminLogin(user,password);
        if (a) {
            HttpSession session = request.getSession();
            session.setAttribute("user_admin", user);
            session.setAttribute("password_admin", password);
            response.sendRedirect(request.getContextPath() + "/admin/home");
        } else {
            request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/login.jsp");
            rd.forward(request, response);
        }

    }
}