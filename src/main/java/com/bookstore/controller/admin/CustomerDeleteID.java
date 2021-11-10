package com.bookstore.controller.admin;

import com.bookstore.service_impl.CustomerService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/")
public class CustomerDeleteID {
    public CustomerDeleteID() {
        super();
    }

    @RequestMapping("user/delete")
    public String userDelete(@RequestParam("id") String userid,
                             ModelMap model) {
        int id= Integer.parseInt(userid);
        CustomerService_impl customer = new CustomerService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(id);
            customer.deleteList(listId);
            model.addAttribute("customerList", customer.findAll());

            return "redirect:/admin/user/list";

        }
        catch (Exception e)
        {
            return "";
        }
    }
}
