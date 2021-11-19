package com.bookstore.controller.admin;

import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    AdminService adminService = new AdminService_impl();

    @RequestMapping("admin/list")
    public String index(@ModelAttribute("message") String message, ModelMap model){

        AdminService admin = new AdminService_impl();
        List<AdminsEntity> adminList = admin.findAll();
        model.addAttribute("adminList", adminList);
        model.addAttribute("message",   message);
        return "admin/viewlistadmin";
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.GET)
    public String showform(ModelMap model,
                        @RequestParam(value = "message", required = false) String message){
        model.addAttribute("admin", new AdminsEntity());
        model.addAttribute("message", message);
        return "admin/addadmin";

    }
    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public String addAdmin(@ModelAttribute("admin") AdminsEntity admin,
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

    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String showEditForm(@RequestParam("admin-id") String adminId,
                               @RequestParam(value = "message", required = false) String message,
                               ModelMap model) {

        int admin_id= Integer.parseInt(adminId);
        AdminsEntity adminsEntity = adminService.findById(admin_id);
        model.addAttribute("admin", adminsEntity);

        return "admin/editadmin";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public String editAdmin(@RequestParam("admin-id") String ma_admin,
                            @ModelAttribute("admin") AdminsEntity adminsEntity,
                            ModelMap model,
                            BindingResult errors,
                            final RedirectAttributes redirectAttributes) {

        try {
            if (adminsEntity.getGmail_Admin().trim().length() == 0) {
                errors.rejectValue("gmail_Admin", "admin", "Vui lòng nhập gmail!");
            }
            if (adminsEntity.getHoten_Admin().trim().length() == 0) {
                errors.rejectValue("hoten_Admin", "admin", "Vui lòng nhập tên!");
            }
            if (adminsEntity.getMatkhau_Admin().trim().length() == 0) {
                errors.rejectValue("matkhau_Admin", "admin", "Vui lòng nhập mật khẩu!");
            }
            if (adminsEntity.getTaikhoan_Admin().trim().length() == 0) {
                errors.rejectValue("taikhoan_Admin", "admin", "Vui lòng nhập tài khoàn!");
            }
            if (errors.hasErrors()) {
                model.addAttribute("admin", adminsEntity);
                model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
                return "admin/editadmin";
            }

            if(!ma_admin.equals("") || !ma_admin.equals("0")){

                //adminsEntity.setMa_Admin(Integer.parseInt(ma_admin));
                adminService.update(adminsEntity);
                redirectAttributes.addFlashAttribute("message", "Đã chỉnh sửa id: " + ma_admin + " thành công");
                return "redirect:/admin/admin/list";
            }
            else {
                model.addAttribute("message", "Vui lòng điền đầy đủ các thông tin");
                model.addAttribute("admin", adminsEntity);

                return "admin/editadmin";
            }
        }
        catch (Exception e)
        {
            model.addAttribute("admin", adminsEntity);
            model.addAttribute("message", "Chỉnh sửa thất bại");
            return "admin/editadmin";
        }
    }

    @RequestMapping("admin/delete")
    public String delete(@RequestParam("admin-id") int admin_idd,
                        HttpSession session,
                        final RedirectAttributes redirectAttributes,
                        ModelMap model) {

        AdminService admin = new AdminService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        String admin_tk = (String) session.getAttribute("user_admin");
        try{
            if(!(admin.checkDelete(admin_tk, admin_idd))) {
                listId.add(admin_idd);
                admin.deleteList(listId);
                model.addAttribute("adminList", admin.findAll());
                String message= "Xóa thành công";
                redirectAttributes.addFlashAttribute("message", message);
                return "redirect:/admin/admin/list";
            }
            else
            {
                return "admin/error";
            }
        }
        catch (Exception e)
        {
            return "admin/error";
        }
    }
}
