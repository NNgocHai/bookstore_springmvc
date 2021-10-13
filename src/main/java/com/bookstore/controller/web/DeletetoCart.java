package com.bookstore.controller.web;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.GioHangEntity;
import com.bookstore.service.GioHangService;
import com.bookstore.service_impl.GioHangService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/web/product/DeletetoCart")
public class DeletetoCart extends HttpServlet {
    public DeletetoCart() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        int ma_CuonSach = Integer.parseInt(request.getParameter("ma_CuonSach"));

        int tongtien = 0;
        HttpSession session = request.getSession();
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
        GioHangService gioHangService = new GioHangService_impl();

        CustomerEntity person = (CustomerEntity) session.getAttribute("person");
        if (person != null) {
            gioHangService.DeletebyCustomer_CuonSach(person.getMa_Customer(), ma_CuonSach);
            Orders = gioHangService.FindByMaCustomer(person.getMa_Customer());
            for (GioHangEntity Order : Orders) {
                double db = (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getGiabia())) * (1 - (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getDiscount())) / 100)));
                Order.getCuonSachEntity().setGiabia((int) db);
            }
        } else
            Orders.remove(index);
        for (GioHangEntity Order : Orders) {
            tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
        }

        int n = Orders.size();
        session.setAttribute("length_orders", n);
        session.setAttribute("Orders", Orders);
        session.setAttribute("tongtien", tongtien);
        response.sendRedirect(request.getContextPath() + "/web/product/CartDetail");

    }

}
