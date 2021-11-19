package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.email.SendingEmail;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.GioHangEntity;
import com.bookstore.entity.GioHangIDKey;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    GioHangService gioHangService = new GioHangService_impl();
    @Autowired
    ProductService productService = new ProductService_impl();


    @RequestMapping(value = "/web/product/AddtoCart")
    public String AddtoCart(HttpSession session, HttpServletRequest request, @RequestParam(value = "product-id", required = false) String product_id, @RequestParam(value = "soluong", required = false, defaultValue = "1") Integer soluong) {
        int n = 0;
        int tongtien = 0;


        if (session.getAttribute("person") == null) {
            if (product_id != null) {
                CuonSachEntity product = productService.findById(Integer.parseInt(product_id));

                if (product != null) {
                    if (session.getAttribute("Orders") == null) {
                        List<GioHangEntity> Orders = new ArrayList<GioHangEntity>();
                        GioHangEntity Order = new GioHangEntity();
                        Order = gioHangService.GetOrder(productService.findById(Integer.parseInt(product_id)));
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
                            if (Order.getCuonSachEntity().getMa_CuonSach() == Integer.parseInt(product_id)) {
                                Order.setSoluong(Order.getSoluong() + soluong);
                                double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                                Order.getCuonSachEntity().setGiabia((int) db);
                                check = true;
                            }
                            tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;

                        }
                        if (check == false) {
                            GioHangEntity Order = new GioHangEntity();
                            Order = gioHangService.GetOrder(productService.findById(Integer.parseInt(product_id)));
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
                return "redirect:" + request.getHeader("Referer");
            } else {
                return "redirect:" + request.getHeader("Referer");
            }
        } else {
            if (product_id != null) {

                CustomerEntity person = (CustomerEntity) session.getAttribute("person");


                CuonSachEntity product = productService.findById(Integer.parseInt(product_id));


                GioHangIDKey gioHangIDKey = new GioHangIDKey();
                GioHangEntity gioHangEntity = new GioHangEntity();
                List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
                boolean check = false;
                if (product != null) {
                    for (GioHangEntity Order : Orders) {
                        if (Order.getCuonSachEntity().getMa_CuonSach() == Integer.parseInt(product_id)) {
                            Order.setSoluong(Order.getSoluong() + soluong);
                            gioHangIDKey.setMa_Customer(person.getMa_Customer());
                            gioHangIDKey.setMa_CuonSach(Integer.valueOf(product_id));
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
                        gioHangIDKey.setMa_CuonSach(Integer.valueOf(product_id));
                        gioHangEntity.setId(gioHangIDKey);
                        gioHangService.save(gioHangEntity);


                        gioHangEntity = gioHangService.GetOrder(productService.findById(Integer.parseInt(product_id)));
                        double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                        gioHangEntity.setCuonSachEntity(product);
                        gioHangEntity.getCuonSachEntity().setGiabia((int) db);
                        gioHangEntity.setId(gioHangIDKey);
                        gioHangEntity.setSoluong(soluong);
                        tongtien = gioHangEntity.getCuonSachEntity().getGiabia() + tongtien;
                        Orders.add(gioHangEntity);


                    }
                    n = Orders.size();
                    session.setAttribute("length_orders", n);
                    session.setAttribute("Orders", Orders);
                    session.setAttribute("tongtien", tongtien);
                }
                return "redirect:" + request.getHeader("Referer");
            } else {
                return "redirect:" + request.getHeader("Referer");
            }
        }

    }

    @RequestMapping("/web/product/CartDetail")
    public String CartDetail(ModelMap model) {
        List<CuonSachEntity> cuonSachEntityList = new ArrayList<CuonSachEntity>();
        cuonSachEntityList = productService.findAll();
        model.addAttribute("cuonSachEntityList", cuonSachEntityList);
        return "web/CartDetail";
    }
    @RequestMapping("/web/product/DeletetoCart")
    public String DeletetoCart(HttpSession session, @RequestParam("index") Integer index, @RequestParam("ma_CuonSach") Integer ma_CuonSach) {


        int tongtien = 0;
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");

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
        for (
                GioHangEntity Order : Orders) {
            tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
        }

        int n = Orders.size();
        session.setAttribute("length_orders", n);
        session.setAttribute("Orders", Orders);
        session.setAttribute("tongtien", tongtien);
        return "redirect:/web/product/CartDetail";
    }
}
