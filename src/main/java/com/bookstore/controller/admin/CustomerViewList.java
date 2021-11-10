package com.bookstore.controller.admin;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.service.CustomerService;
import com.bookstore.service_impl.CustomerService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class CustomerViewList {
    public CustomerViewList() {
        super();
    }

    @RequestMapping("user/list")
    public String userList(ModelMap model) {
        CustomerService customer = new CustomerService_impl();
        List<CustomerEntity> customerList = customer.findAll();
        model.addAttribute("customerList", customerList);

        return "admin/viewlistcustomer";
    }
}
