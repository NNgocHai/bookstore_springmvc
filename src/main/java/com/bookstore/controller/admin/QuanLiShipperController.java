package com.bookstore.controller.admin;

import com.bookstore.entity.ShipperEntity;
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
public class QuanLiShipperController {
    @Autowired
    ShipperService shipperService = new ShipperService_impl();

    @RequestMapping(value = "ship/add", method = RequestMethod.GET)
    public String ShipperAdd(ModelMap model,
                             @RequestParam(value = "message", required = false) String message){
        model.addAttribute("shipper", new ShipperEntity());
        model.addAttribute("message", message);
        return "admin/addshipper";
    }

    @RequestMapping(value = "ship/add", method = RequestMethod.POST)
    public String ShipperAdd(@ModelAttribute("shipper") ShipperEntity shipper,
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
            model.addAttribute("message", "Thêm thành công!");
            shipperService.save(shipper);
            return "redirect:/admin/ship/add";
        } catch (Exception e) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addshipper";
        }



    }
    @RequestMapping(value = "ship/delete", method = RequestMethod.GET)
    public String ShipperDelete(ModelMap model, @RequestParam("shipper_id") String shipper_id) {
        int id= Integer.parseInt(shipper_id);
        List<Integer> listId = new ArrayList<Integer>();

        try {
            listId.add(id);
            shipperService.deleteList(listId);
//            model.addAttribute("shipperList", shipper.findAll());
            model.addAttribute("message", "Xóa thành công");
            return "redirect:/admin/ship/list";
        }
        catch (Exception e){
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/ship/list";
        }


    }

    @RequestMapping(value = "ship/edit", method = RequestMethod.GET)
    public String ShipperEdit(ModelMap model, @RequestParam(value = "shipper_id", required = false) String shipper_id){
        int id = Integer.parseInt(shipper_id);
        ShipperEntity shipper = shipperService.findById(id);
        model.addAttribute("shipper", shipper);
        return "admin/editshipper";
    }

    @RequestMapping(value = "ship/edit", method = RequestMethod.POST)
    public String ShipperEdit(@ModelAttribute("shipper") ShipperEntity shipper,
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
            model.addAttribute("message", "Cập nhật thành công!");
            shipperService.update(shipper);
            return "admin/editshipper";
        } catch (Exception e) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editshipper";
        }
    }
    @RequestMapping(value = "ship/list", method = RequestMethod.GET)
    public String ShipperList(ModelMap model, @ModelAttribute("message") String message) {
        List<ShipperEntity> shipperList = shipperService.findAll();
        model.addAttribute("shipperList", shipperList);
        model.addAttribute("message",   message);
        return "admin/shipperviewlist";

    }
}
