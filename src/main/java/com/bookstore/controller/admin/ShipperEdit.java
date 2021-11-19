package com.bookstore.controller.admin;

import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.PrintWriter;


@Controller
@RequestMapping("/admin1/")
public class ShipperEdit {
    public ShipperEdit() {
        super();
    }

    ShipperService shipperService = new ShipperService_impl();

    @RequestMapping(value = "ship/edit", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam(value = "shipper_id", required = false) String shipper_id){
        int id = Integer.parseInt(shipper_id);
        ShipperEntity shipper = shipperService.findById(id);
        model.addAttribute("shipper", shipper);
        return "admin/editshipper";
    }

    @RequestMapping(value = "ship/edit", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("shipper") ShipperEntity shipper,
                         ModelMap model, BindingResult errors, @RequestParam("shipper_id") String ma_Shipper)  {
        shipper.setMa_Shipper(Integer.parseInt(ma_Shipper));
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
            return "admin/editshipper";
        }
        try {
            ShipperService shipperService = new ShipperService_impl();
            model.addAttribute("message", "Cập nhật thành công!");
            shipperService.update(shipper);
            return "admin/editshipper";
        } catch (Exception e) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editshipper";
        }



    }
}
