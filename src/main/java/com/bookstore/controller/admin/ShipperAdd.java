package com.bookstore.controller.admin;

import com.bookstore.entity.AdminsEntity;
import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.AdminService_impl;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import java.io.PrintWriter;

@Controller
@RequestMapping("/admin1/")
public class ShipperAdd  {
    public ShipperAdd() {
        super();
    }
    @RequestMapping(value = "ship/add", method = RequestMethod.GET)
    public String doGet(ModelMap model,
                         @RequestParam(value = "message", required = false) String message){
        model.addAttribute("shipper", new ShipperEntity());
        model.addAttribute("message", message);
        return "admin/addshipper";
    }

    @RequestMapping(value = "ship/add", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("shipper") ShipperEntity shipper,
                         ModelMap model, BindingResult errors) {

        if (shipper.getGmail_Shipper().trim().length() == 0) {
            errors.rejectValue("gmail_Shipper", "shipper", "Vui lòng nhập gmail!");
        }
        if (shipper.getHoten_Shipper().trim().length() == 0) {
            errors.rejectValue("hoten_Shipper", "shipper", "Vui lòng nhập tên!");
        }
        if (shipper.getMatkhau_Shipper().trim().length() == 0) {
            errors.rejectValue("matkhau_Shipper", "shipper", "Vui lòng nhập mật khẩu!");
        }
        if (shipper.getTaikhoan_Shipper().trim().length() == 0) {
            errors.rejectValue("taikhoan_Shipper", "shipper", "Vui lòng nhập tài khoàn!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/addshipper";
        }
        try {
            ShipperService shipperService = new ShipperService_impl();
            model.addAttribute("message", "Thêm thành công!");
            shipperService.save(shipper);
            return "admin/addshipper";
        } catch (Exception e) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addshipper";
        }



    }
}
