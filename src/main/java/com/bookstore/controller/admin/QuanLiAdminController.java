package com.bookstore.controller.admin;


import com.bookstore.entity.AdminsEntity;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class QuanLiAdminController {
    @Autowired
    AdminService adminService = new AdminService_impl();


    @RequestMapping("admin/list")
    public String index(@ModelAttribute("message") String message, ModelMap model) {

        List<AdminsEntity> adminList = adminService.findAll();
        model.addAttribute("adminList", adminList);
        model.addAttribute("message", message);
        return "admin/viewlistadmin";
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.GET)
    public String showform(ModelMap model,
                           @RequestParam(value = "message", required = false) String message) {
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
            model.addAttribute("message", "Thêm thành công!");
            adminService.save(admin);
            return "redirect:/admin/admin/add";

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

        int admin_id = Integer.parseInt(adminId);
        AdminsEntity adminsEntity = adminService.findById(admin_id);
        model.addAttribute("admin", adminsEntity);

        return "admin/editadmin";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public String editAdmin(@RequestParam("admin-id") String ma_admin,
                            @ModelAttribute("admin") AdminsEntity adminsEntity,
                            ModelMap model,
                            BindingResult errors) {

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

        try {
            model.addAttribute("message", "Cập nhật thành công!");
            adminService.update(adminsEntity);
            return "admin/editadmin";
        } catch (Exception e) {
            model.addAttribute("admin", adminsEntity);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editadmin";
        }
    }

    @RequestMapping("admin/delete")
    public String delete(@RequestParam("admin-id") int admin_idd,
                         HttpSession session,
                         ModelMap model) {

        List<Integer> listId = new ArrayList<Integer>();
        String admin_tk = (String) session.getAttribute("user_admin");
        try {
            if (!(adminService.checkDelete(admin_tk, admin_idd))) {
                listId.add(admin_idd);
                adminService.deleteList(listId);
                String message = "Xóa thành công";
                model.addAttribute("message", message);
                return "redirect:/admin/admin/list";
            } else {
                model.addAttribute("message", "Xóa thất bại");
                return "redirect:/admin/admin/list";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/admin/list";
        }
    }
}
