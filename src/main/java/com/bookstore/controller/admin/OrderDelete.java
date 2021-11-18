package com.bookstore.controller.admin;

import com.bookstore.service_impl.DonHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class OrderDelete {
    public OrderDelete(){super();}


    @RequestMapping("order/delete")
    public String doGet(ModelMap model, @RequestParam(value = "order_id", required = false) String order_id) {

        DonHangService_impl donhang = new DonHangService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(Integer.parseInt(order_id));
            donhang.deleteList(listId);
//            model.addAttribute("donhangList", donhang.findAll());
            model.addAttribute("message", "Xóa thành công");
            return "redirect:/admin/order/list";

        }
        catch (Exception e)
        {
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/order/list";
        }
    }
}
