package com.bookstore.controller.admin;

import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class ShipperViewList{
    public ShipperViewList() {
        super();
    }
    @RequestMapping(value = "ship/list", method = RequestMethod.GET)
    public String doGet(ModelMap model, @ModelAttribute("message") String message) {
        ShipperService shipperService=new ShipperService_impl();
        List<ShipperEntity> shipperList = shipperService.findAll();
        model.addAttribute("shipperList", shipperList);
        model.addAttribute("message",   message);
        return "admin/shipperviewlist";

    }
}
