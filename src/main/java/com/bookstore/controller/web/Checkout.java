package com.bookstore.controller.web;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.GioHangEntity;
import com.bookstore.service.ProductService;
import com.bookstore.service_impl.ProductService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/web/checkout")

public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<CuonSachEntity> cuonSachEntities = new ArrayList<CuonSachEntity>();
        ProductService productService = new ProductService_impl();

        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
        if(Orders == null) {
            request.setAttribute("error", "Bạn chưa có cuốn sách nào trong giỏ hàng");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/web/CartDetail.jsp");
            dispatcher.forward(request, response);
        }
        else {
            int length_orders = (int) session.getAttribute("length_orders");
            cuonSachEntities = productService.findAll();
            int check_soluong = 1;
            String errorsoluong = null;
            for (GioHangEntity Order : Orders) {
                for (CuonSachEntity product : cuonSachEntities) {
                    if (Order.getCuonSachEntity().getMa_CuonSach() == product.getMa_CuonSach()) {
                        if (Order.getSoluong() > product.getSoluong()) {
                            check_soluong = 0;
                            errorsoluong = "Sách này:" + Order.getCuonSachEntity().getTen_CuonSach() + " Vượt quá số lương của kho:" + product.getSoluong() + "";
                            break;
                        }
                    }
                }
            }
            if (length_orders == 0) {
                request.setAttribute("error", "Bạn chưa có cuốn sách nào trong giỏ hàng");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/CartDetail.jsp");
                dispatcher.forward(request, response);

            }
            if (check_soluong == 0) {
                request.setAttribute("cuonSachEntityList", cuonSachEntities);
                request.setAttribute("error", errorsoluong);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/CartDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/web/checkout.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
