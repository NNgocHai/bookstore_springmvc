package com.bookstore.controller.admin;

import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.service.ChiTietDonHangService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/chitietdonhang/details")
public class CTDHViewDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user_admin") == null){
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
        else {
            int DH_id = Integer.parseInt(request.getParameter("DH-id"));
            String ten_KH = request.getParameter("tenKH");
            ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
            List<ChiTietDonHangEntity> listdetails = chiTietDonHangService.FindDetails(DH_id);
            request.setAttribute("DH_id", DH_id);
            request.setAttribute("listdetails", listdetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/viewlistCTDHdetails.jsp");
            dispatcher.forward(request, response);
        }
    }

}
