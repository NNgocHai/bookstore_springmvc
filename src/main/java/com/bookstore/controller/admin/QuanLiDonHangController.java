package com.bookstore.controller.admin;

import com.bookstore.entity.DonHangEntity;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class QuanLiDonHangController {
    @Autowired
    DonHangService donHangService=new DonHangService_impl();
    @RequestMapping("order/delete")
    public String OrderDelete(ModelMap model, @RequestParam(value = "order_id", required = false) String order_id) {

        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(Integer.parseInt(order_id));
            donHangService.deleteList(listId);
            model.addAttribute("message", "Xóa thành công");
            return "redirect:/admin/order/list";
        }
        catch (Exception e)
        {
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/order/list";
        }
    }

    @RequestMapping(value = "order/edit", method = RequestMethod.GET)
    public String OrderEdit(ModelMap model, @RequestParam("order_id") String order_id) {

        DonHangEntity donHangEntity = donHangService.findById(Integer.parseInt(order_id));
        model.addAttribute("order", donHangEntity);
        return "admin/editdonhang";
    }
    @RequestMapping(value = "order/edit", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("order") DonHangEntity order,
                         ModelMap model, BindingResult errors, @RequestParam("order_id") String order_id) {
        if (order.getMa_Customer() == null) {
            errors.rejectValue("ma_Customer", "order", "Vui lòng nhập mã khách hàng!");
        }
        order.setMa_DH(Integer.parseInt(order_id));
        if (order.getDiachi().toString().trim().length() == 0) {
            errors.rejectValue("diachi", "order", "Vui lòng nhập địa chỉ!");
        }
        if (order.getSdt() == null) {
            errors.rejectValue("sdt", "order", "Vui lòng nhập số điện thoại!");
        }
        if (order.getTongtien() == null) {
            errors.rejectValue("tongtien", "order", "Vui lòng nhập tổng số tiền!");
        }
        if (order.getActiveDH() == null) {
            errors.rejectValue("activeDH", "order", "Vui lòng nhập tình trạng đơn hàng!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/editdonhang";
        }
        try {
            model.addAttribute("message", "Cập nhật thành công!");
            donHangService.update(order);
            return "admin/editdonhang";
        } catch (Exception e) {
            model.addAttribute("order", order);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editdonhang";
        }
    }
    @RequestMapping("order/list")
    public String OrderList(ModelMap model, @ModelAttribute("message") String message) {
        List<DonHangEntity> donhangList = donHangService.findAll();
        model.addAttribute("donhangList", donhangList);
        model.addAttribute("message",   message);
        return "/admin/viewlistdonhang";
    }
}
