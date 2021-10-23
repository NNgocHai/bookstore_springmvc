package com.bookstore.controller.web;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.GioHangEntity;
import com.bookstore.entity.GioHangIDKey;
import com.bookstore.service.GioHangService;
import com.bookstore.service.ProductService;
import com.bookstore.service_impl.GioHangService_impl;
import com.bookstore.service_impl.ProductService_impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/web/product/AddtoCart1")
public class AddtoCart extends HttpServlet {
    public AddtoCart() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int n = 0;
        int soluong = 1;
        int tongtien = 0;

        HttpSession session = request.getSession();
        GioHangService gioHangService = new GioHangService_impl();
        ProductService productService = new ProductService_impl();
        if (session.getAttribute("person") == null) {
            if (request.getParameter("product-id") != null) {
                String id = request.getParameter("product-id");
                CuonSachEntity product = productService.findById(Integer.parseInt(id));

                if (product != null) {
                    if (request.getParameter("soluong") != null) {
                        soluong = Integer.parseInt(request.getParameter("soluong"));
                    }

                    if (session.getAttribute("Orders") == null) {
                        List<GioHangEntity> Orders = new ArrayList<GioHangEntity>();
                        GioHangEntity Order = new GioHangEntity();
                        Order = gioHangService.GetOrder(productService.findById(Integer.parseInt(id)));
                        double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                        Order.getCuonSachEntity().setGiabia((int) db);
                        Order.setSoluong(soluong);
                        tongtien = Order.getCuonSachEntity().getGiabia();
                        Orders.add(Order);
                        n = Orders.size();
                        session.setAttribute("length_orders", n);
                        session.setAttribute("Orders", Orders);
                        session.setAttribute("tongtien", tongtien);
                    } else {
                        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
                        boolean check = false;
                        for (GioHangEntity Order : Orders) {
                            if (Order.getCuonSachEntity().getMa_CuonSach() == Integer.parseInt(id)) {
                                Order.setSoluong(Order.getSoluong() + soluong);
                                double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                                Order.getCuonSachEntity().setGiabia((int) db);
                                check = true;
                            }
                            tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;

                        }
                        if (check == false) {
                            GioHangEntity Order = new GioHangEntity();
                            Order = gioHangService.GetOrder(productService.findById(Integer.parseInt(id)));
                            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                            Order.getCuonSachEntity().setGiabia((int) db);
                            Order.setSoluong(soluong);
                            tongtien = Order.getCuonSachEntity().getGiabia() + tongtien;
                            Orders.add(Order);
                        }
                        n = Orders.size();
                        session.setAttribute("length_orders", n);
                        session.setAttribute("Orders", Orders);
                        session.setAttribute("tongtien", tongtien);
                    }
                }
                response.sendRedirect(request.getHeader("Referer"));
            } else {
                response.sendRedirect(request.getHeader("Referer"));
            }
        } else {
            if (request.getParameter("product-id") != null) {

                CustomerEntity person = (CustomerEntity) session.getAttribute("person");

                String id = request.getParameter("product-id");
                CuonSachEntity product = productService.findById(Integer.parseInt(id));


                GioHangIDKey gioHangIDKey = new GioHangIDKey();
                GioHangEntity gioHangEntity= new GioHangEntity();
                List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
                boolean check = false;
                if (product != null) {
                    if (request.getParameter("soluong") != null) {
                        soluong = Integer.parseInt(request.getParameter("soluong"));
                    }
                    for (GioHangEntity Order : Orders) {
                        if (Order.getCuonSachEntity().getMa_CuonSach() == Integer.parseInt(id)) {
                            Order.setSoluong(Order.getSoluong() + soluong);
                            gioHangIDKey.setMa_Customer(person.getMa_Customer());
                            gioHangIDKey.setMa_CuonSach(Integer.valueOf(id));
                            Order.setId(gioHangIDKey);
                            gioHangService.update(Order);
                            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                            Order.getCuonSachEntity().setGiabia((int) db);
                            check = true;
                        }
                        tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                    }
                    if (check == false) {
                        gioHangEntity.setSoluong(soluong);
                        gioHangIDKey.setMa_Customer(person.getMa_Customer());
                        gioHangIDKey.setMa_CuonSach(Integer.valueOf(id));
                        gioHangEntity.setId(gioHangIDKey);
                        gioHangService.save(gioHangEntity);


                        gioHangEntity = gioHangService.GetOrder(productService.findById(Integer.parseInt(id)));
                        double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                        gioHangEntity.setCuonSachEntity(product);
                        gioHangEntity.getCuonSachEntity().setGiabia((int) db);
                        gioHangEntity.setId(gioHangIDKey);
                        gioHangEntity.setSoluong(soluong);
                        tongtien = gioHangEntity.getCuonSachEntity().getGiabia() +tongtien;
                        Orders.add(gioHangEntity);


                    }
                    n = Orders.size();
                    session.setAttribute("length_orders", n);
                    session.setAttribute("Orders", Orders);
                    session.setAttribute("tongtien", tongtien);
                }
                response.sendRedirect(request.getHeader("Referer"));
            } else {
                response.sendRedirect(request.getHeader("Referer"));
            }
        }

    }

}
