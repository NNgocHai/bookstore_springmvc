package com.bookstore.controller.web;

import com.bookstore.email.ConfirmPayment;
import com.bookstore.entity.GioHangEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/web1/")
public class PayPalSuccess {
    @RequestMapping("paysuccess")
    public String success(HttpSession session,
                          ModelMap model){
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");

        Orders.clear();
        session.setAttribute("Orders", Orders);

        model.addAttribute("sucess", "Đặt hàng và thanh toán thành công");

        return "web/checkout";
    }
}
