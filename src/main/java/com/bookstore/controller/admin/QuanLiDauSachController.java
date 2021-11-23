package com.bookstore.controller.admin;


import com.bookstore.entity.CategoryEntity;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class QuanLiDauSachController {
    @Autowired
    CategoryService categoryService =new CategoryService_impl();

    @RequestMapping("cate/list")
    public String cateList(@ModelAttribute("message") String message,
                           ModelMap model) {
        List<CategoryEntity> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("message", message);

        return "admin/viewlistcategory";
    }

    @RequestMapping(value = "cate/add", method = RequestMethod.GET)
    public String addCateForm(@RequestParam(value = "message", required = false) String message,
                              ModelMap model) {

        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("message", message);
        return "admin/addcategory";
    }

    @RequestMapping(value = "cate/add", method = RequestMethod.POST)
    public String addCate(@ModelAttribute("category") CategoryEntity cate,
                          ModelMap model, BindingResult errors) {

            if(cate.getTen_DauSach().equals("") ||
                    cate.getTen_DauSach().trim().length() == 0){
                errors.rejectValue("ten_DauSach", "cate", "Vui lòng điền tên đầu sách");
            }
        if (errors.hasErrors()) {
            model.addAttribute("category", cate);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/addcategory";
        }
        try {
            model.addAttribute("message", "Thêm thành công!");
            categoryService.save(cate);
            return "redirect:/admin/cate/add";
        } catch (Exception e) {
            model.addAttribute("category", cate);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addcategory";
        }
        //return "redirect:/admin/cate/list";
    }

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
                           @ModelAttribute("category") CategoryEntity categoryEntity,
                           ModelMap model, BindingResult errors) {

        if(categoryEntity.getTen_DauSach().equals("") ||
                categoryEntity.getTen_DauSach().trim().length() == 0){
            errors.rejectValue("ten_DauSach", "cate", "Vui lòng điền tên đầu sách");
        }
        if (errors.hasErrors()) {
            model.addAttribute("category", categoryEntity);
            model.addAttribute("message", "Vui lòng sửa lỗi sau đây!");
            return "admin/editcategory";
        }

        try {
            model.addAttribute("message", "Cập nhật thành công!");
            categoryService.update(categoryEntity);
            return "admin/editcategory";
        } catch (Exception e) {
            model.addAttribute("category", categoryEntity);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editcategory";
        }
    }

    @RequestMapping("cate/delete")
    public String deleteCate(@RequestParam("category-id") String cate_id,
                             ModelMap model
    ) {
        int category_id= Integer.parseInt(cate_id);

        List<Integer> listId = new ArrayList<Integer>();
        try {
            listId.add(category_id);
            categoryService.deleteList(listId);
            model.addAttribute("message",  "Xoá thành công");
            return "redirect:/admin/cate/list";
        }
        catch(Exception e){
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/cate/list";
        }

    }
}
