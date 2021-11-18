package com.bookstore.controller.admin;

import com.bookstore.service.ProductService;
import com.bookstore.service_impl.ProductService_impl;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class ProductDelete{
    public ProductDelete(){super();}
    @RequestMapping(value = "product/delete", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam("cuonsach_id") String cuonsach_id) {

        ProductService productService = new ProductService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        listId.add(Integer.parseInt(cuonsach_id));
        productService.deleteList(listId);
        model.addAttribute("cuonsachList",productService.findAll());
        return "redirect:/admin/product/list";
    }
}
