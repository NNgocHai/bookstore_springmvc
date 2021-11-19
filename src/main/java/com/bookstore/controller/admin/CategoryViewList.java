package com.bookstore.controller.admin;

import com.bookstore.entity.CategoryEntity;
import com.bookstore.service.CategoryService;
import com.bookstore.service_impl.CategoryService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin2/")
public class CategoryViewList {

    @RequestMapping("cate/list")
    public String cateList(ModelMap model) {
        CategoryService categoryService=new CategoryService_impl();
        List<CategoryEntity> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        return "admin/viewlistcategory";
    }
}
