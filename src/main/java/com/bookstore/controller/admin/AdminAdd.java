package com.bookstore.controller.admin;

import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
@RequestMapping("/admin/")
public class AdminAdd {
    public AdminAdd() {
        super();
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.GET)
    public String doGet(ModelMap model,
                        @RequestParam(value = "message", required = false) String message){
        model.addAttribute("admin", new AdminsEntity());
        model.addAttribute("message", message);
        return "admin/addadmin";

    }
    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("admin") AdminsEntity admin,
                         ModelMap model, BindingResult errors) {
        if (admin.getGmail_Admin().trim().length() == 0) {
            errors.rejectValue("gmail_Admin", "admin", "Vui lòng nhập gmail!");
        }
        if (admin.getHoten_Admin().trim().length() == 0) {
            errors.rejectValue("hoten_Admin", "admin", "Vui lòng nhập tên!");
        }
        if (admin.getMatkhau_Admin().trim().length() == 0) {
            errors.rejectValue("matkhau_Admin", "admin", "Vui lòng nhập mật khẩu!");
        }
        if (admin.getTaikhoan_Admin().trim().length() == 0) {
            errors.rejectValue("taikhoan_Admin", "admin", "Vui lòng nhập tài khoàn!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("admin", admin);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/addadmin";
        }
        try {
            AdminService adminService = new AdminService_impl();
            model.addAttribute("message", "Thêm thành công!");
            adminService.save(admin);
            return "admin/addadmin";
        } catch (Exception e) {
            model.addAttribute("admin", admin);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addadmin";
        }
    }
}
