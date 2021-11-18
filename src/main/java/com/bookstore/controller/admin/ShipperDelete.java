package com.bookstore.controller.admin;

import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")

public class ShipperDelete{
    public ShipperDelete() {
        super();
    }

    @RequestMapping(value = "ship/delete", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam("shipper_id") String shipper_id) {
        int id= Integer.parseInt(shipper_id);
        ShipperService shipper = new ShipperService_impl();
        List<Integer> listId = new ArrayList<Integer>();

        listId.add(id);
        shipper.deleteList(listId);
        model.addAttribute("shipperList", shipper.findAll());
        return "redirect:/admin/ship/list";

    }
}
