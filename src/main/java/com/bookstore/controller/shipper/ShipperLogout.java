package com.bookstore.controller.shipper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
@Controller
public class ShipperLogout {
    @RequestMapping("/shipper/logout")
    public String ShipperLogout(HttpSession session){
        session.removeAttribute("password_shipper");
        session.removeAttribute("user_shipper");

        session.invalidate();
        return "shipper/login";
    }
}

