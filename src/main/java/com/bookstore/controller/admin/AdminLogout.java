package com.bookstore.controller.admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/logout")
public class AdminLogout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminLogout() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user_admin");
        session.removeAttribute("password_admin");

        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/login.jsp");
        dispatcher.forward(request, response);
    }
}
