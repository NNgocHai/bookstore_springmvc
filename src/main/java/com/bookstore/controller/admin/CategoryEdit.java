package com.bookstore.controller.admin;

import com.bookstore.entity.CategoryEntity;
import com.bookstore.service.CategoryService;
import com.bookstore.service_impl.CategoryService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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



@Controller
@RequestMapping("/admin/")
public class CategoryEdit {
    CategoryService categoryService=new CategoryService_impl();

    @RequestMapping(value = "cate/edit", method = RequestMethod.GET)
    public String editCateForm(@RequestParam("category-id") String cate_id,
                        ModelMap model){
        int category_id= Integer.parseInt(cate_id);
        CategoryEntity categoryEntity = categoryService.findById(category_id);
        model.addAttribute("category", categoryEntity);

        return "admin/editcategory";
    }

    @RequestMapping(value = "cate/edit", method = RequestMethod.POST)
    public String editCate(@RequestParam("category-id") String cate_id,
                           @RequestParam(value = "category-name", required = false) String category_name,
                           ModelMap model) {

        int category_id = Integer.parseInt(cate_id);
        if(!category_name.equals(""))
        {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setMa_DauSach(category_id);
            categoryEntity.setTen_DauSach(category_name);
            categoryService.update(categoryEntity);

            return "redirect:/admin/cate/list";
        }
        else {

            model.addAttribute("errorMessage","Tên đầu sách trống");
            model.addAttribute("category_id",cate_id);

            return "admin/editcategory";
        }

    }
}
