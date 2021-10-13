package com.bookstore.controller.shipper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/shipper/logout")
public class ShipperLogout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("password_shipper");
        session.removeAttribute("user_shipper");

        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/shipper/login.jsp");
        dispatcher.forward(request, response);
    }
}
