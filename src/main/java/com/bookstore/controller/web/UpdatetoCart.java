package com.bookstore.controller.web;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.GioHangEntity;
import com.bookstore.entity.GioHangIDKey;
import com.bookstore.service.GioHangService;
import com.bookstore.service_impl.GioHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/web/")
public class UpdatetoCart {
    public UpdatetoCart() {
        super();
    }

    @RequestMapping("product/UpdatetoCart")
    public String doGet() {
        return "web/CartDetail";
    }

    @RequestMapping(value = "product/UpdatetoCart", method = RequestMethod.POST)
    public String doPost(HttpSession session,
                         ModelMap model,
                         HttpServletRequest request){
        int tongtien = 0;

        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
        if(Orders == null) {
            model.addAttribute("error", "Bạn chưa có cuốn sách nào trong giỏ hàng");

            return "web/CartDetail";
        }
        else {

            GioHangService gioHangService = new GioHangService_impl();

            CustomerEntity person = (CustomerEntity) session.getAttribute("person");
            if (person != null) {
                GioHangIDKey gioHangIDKey = new GioHangIDKey();
                for (GioHangEntity Order : Orders) {
                    int soluong = Integer.parseInt(request.getParameter(String.valueOf(Order.getCuonSachEntity().getMa_CuonSach())));
                    Order.setSoluong(soluong);
                    gioHangIDKey.setMa_Customer(person.getMa_Customer());
                    gioHangIDKey.setMa_CuonSach(Order.getCuonSachEntity().getMa_CuonSach());
                    Order.setId(gioHangIDKey);
                    gioHangService.update(Order);

                    tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                }

            } else {
                for (GioHangEntity Order : Orders) {
                    int soluong = Integer.parseInt(request.getParameter(String.valueOf(Order.getCuonSachEntity().getMa_CuonSach())));
                    Order.setSoluong(soluong);
                    tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                }
            }

            int n = Orders.size();
            session.setAttribute("length_orders", n);
            session.setAttribute("Orders", Orders);
            session.setAttribute("tongtien", tongtien);

            return "redirect:/web/product/CartDetail";
        }
    }


}
