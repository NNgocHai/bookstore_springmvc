package com.bookstore.controller.admin;

import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminDeleteID {
    public AdminDeleteID() {
        super();
    }

    @RequestMapping("admin/delete")
    public String doGet(@RequestParam("admin-id") int admin_idd,
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
