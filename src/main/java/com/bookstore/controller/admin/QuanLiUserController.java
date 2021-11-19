package com.bookstore.controller.admin;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao.DonHangDao;
import com.bookstore.dao.ShipperDao;
import com.bookstore.dao_impl.AdminDao_impl;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.dao_impl.ShipperDao_impl;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.CustomerEntity;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/admin/")
public class QuanLiUserController {

    @Autowired
    CustomerService customerService = new CustomerService_impl();

    @RequestMapping("user/list")
    public String userList(@ModelAttribute("message") String message,
                           ModelMap model) {
        List<CustomerEntity> customerList = customerService.findAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("message", message);

        return "admin/viewlistcustomer";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    public String addUserForm(ModelMap model,
                              @RequestParam(value = "message", required = false) String message){

        model.addAttribute("customer", new CustomerEntity());
        model.addAttribute("message", message);
        return "admin/adduser";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("customer") CustomerEntity customerEntity,
                          ModelMap model,
                          BindingResult errors) {

        if(customerEntity.getTaikhoan_Customer().equals("")){
            errors.rejectValue("taikhoan_Customer", "customer", "Vui lòng điền tài khoản");
        }

        if(customerEntity.getMatkhau_Customer().equals("")){
            errors.rejectValue("matkhau_Customer", "customer", "Vui lòng điền mật khẩu");
        }
        if(customerEntity.getHoten_Customer().equals("")){
            errors.rejectValue("hoten_Customer", "customer", "Vui lòng điền tên");
        }
        if(customerEntity.getGmail_Customer().equals("")){
            errors.rejectValue("gmail_Customer", "customer", "Vui lòng điền địa chỉ email");
        }
        if(customerEntity.getSdt_Customer().equals("")){
            errors.rejectValue("sdt_Customer", "customer", "Vui lòng điền số điện thoại");
        }
        if(customerEntity.getVitien().equals("")){
            errors.rejectValue("vitien", "customer", "Vui lòng điền số khởi đầu");
        }

        if (errors.hasErrors()) {
            model.addAttribute("customer", customerEntity);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/adduser";
        }

        try{
            customerService.save(customerEntity);
            model.addAttribute("message", "Thêm  thành công!");
            return "redirect:/admin/user/add";
        }
        catch (Exception e)
        {
            model.addAttribute("message", "Thêm  thất bại!");
            return "admin/adduser";
        }


    }

    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") String userid,
                           ModelMap model) {

        int id= Integer.parseInt(userid);
        CustomerEntity customerEntity = customerService.findById(id);
        model.addAttribute("customer", customerEntity);

        return "admin/edituser";
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.POST)
    public String editUser(@RequestParam("id") String id,
                           @ModelAttribute("customer") CustomerEntity customerEntity,
                           ModelMap model,BindingResult errors,
                           final RedirectAttributes redirectAttributes) {

        if(customerEntity.getTaikhoan_Customer().equals("")){
            errors.rejectValue("taikhoan_Customer", "customer", "Vui lòng điền tài khoản");
        }

        if(customerEntity.getMatkhau_Customer().equals("")){
            errors.rejectValue("matkhau_Customer", "customer", "Vui lòng điền mật khẩu");
        }
        if(customerEntity.getHoten_Customer().equals("")){
            errors.rejectValue("hoten_Customer", "customer", "Vui lòng điền tên");
        }
        if(customerEntity.getGmail_Customer().equals("")){
            errors.rejectValue("gmail_Customer", "customer", "Vui lòng điền địa chỉ email");
        }
        if(customerEntity.getSdt_Customer().equals("")){
            errors.rejectValue("sdt_Customer", "customer", "Vui lòng điền số điện thoại");
        }
        if(customerEntity.getVitien().equals("")){
            errors.rejectValue("vitien", "customer", "Vui lòng điền số khởi đầu");
        }

        if (errors.hasErrors()) {
            model.addAttribute("customer", customerEntity);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/edituser";
        }

        try {
            customerService.update(customerEntity);
            redirectAttributes.addFlashAttribute("message", "Đã chỉnh sửa id: " + id + " thành công");
            return "redirect:/admin/user/list";

        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Chỉnh sửa Customer thất bại");
            return "admin/edituser";
        }
    }

    @RequestMapping("user/delete")
    public String userDelete(@RequestParam("id") String userid,
                             ModelMap model) {
        int id= Integer.parseInt(userid);
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(id);
            customerService.deleteList(listId);
            model.addAttribute("message", "Xoá thành công");
            return "redirect:/admin/user/list";

        }
        catch (Exception e)
        {
            model.addAttribute("message", "Xoá thất bại");
            return "redirect:/admin/user/list";
        }
    }
}
