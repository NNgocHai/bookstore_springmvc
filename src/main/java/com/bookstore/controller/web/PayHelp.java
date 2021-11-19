package com.bookstore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web1/")
public class PayHelp {
    @RequestMapping("payhelp")
    public String doGet() {
        return "web/payhelp";
    }
}
