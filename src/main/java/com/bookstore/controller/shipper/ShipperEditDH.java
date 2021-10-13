package com.bookstore.controller.shipper;

import com.bookstore.entity.DonHangEntity;
import com.bookstore.service.DonHangService;
import com.bookstore.service_impl.DonHangService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shipper/editDH")
public class ShipperEditDH extends HttpServlet {
    public ShipperEditDH() {
        super();
    }

    DonHangService donHangService = new DonHangService_impl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int dh_id= Integer.parseInt(request.getParameter("DH-id"));
        DonHangEntity donHangEntity = donHangService.findById(dh_id);
        donHangEntity.setMa_DH(dh_id);
        donHangEntity.setActiveDH("Đã giao");
        donHangService.update(donHangEntity);
        response.sendRedirect(request.getContextPath() + "/shipper/home");
    }

}
