package com.bookstore.controller.admin;

import com.bookstore.service.CategoryService;
import com.bookstore.service_impl.CategoryService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class CategoryDelete  {

    @RequestMapping("cate/delete")
    public String deleteCate(@RequestParam("category-id") String cate_id,
                             HttpSession session,
                             ModelMap model) {
        int category_id= Integer.parseInt(cate_id);
        CategoryService category = new CategoryService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        listId.add(category_id);
        category.deleteList(listId);
        model.addAttribute("categoryList",category.findAll());

        return "redirect:/admin/cate/list";

    }
}
