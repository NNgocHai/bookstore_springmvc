package com.bookstore.controller.admin;

import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/admin/")
public class AdminEdit {
    public AdminEdit() {
        super();
    }

    AdminService adminService = new AdminService_impl();
    //AdminDao adminDao = new AdminDao_impl();

    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String doGet(@RequestParam("admin-id") String adminId,
                        ModelMap model) {

        int admin_id= Integer.parseInt(adminId);
        AdminsEntity adminsEntity = adminService.findById(admin_id);
        model.addAttribute("admin", adminsEntity);

        return "admin/editadmin";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public String doPost(@RequestParam("admin-id") String admin_id,
                         @RequestParam(value = "admin-username", required = false) String admin_tk,
                         @RequestParam(value = "admin-password", required = false) String admin_password,
                         @RequestParam(value = "admin-name", required = false) String admin_name,
                         @RequestParam(value = "admin-email", required = false) String admin_email,
                         ModelMap model,
                         PrintWriter out) {

        try {
            if((!admin_tk.equals("")) && (!admin_password.equals("")) &&
                    (!admin_name.equals(""))&& (!admin_email.equals(""))){

                AdminsEntity adminsEntity = new AdminsEntity();
                adminsEntity.setMa_Admin(Integer.parseInt(admin_id));
                adminsEntity.setTaikhoan_Admin(admin_tk);
                adminsEntity.setMatkhau_Admin(admin_password);
                adminsEntity.setHoten_Admin(admin_name);
                adminsEntity.setGmail_Admin(admin_email);
                adminService.update(adminsEntity);

                return "redirect:/admin/admin/list";
            }
            else {
                model.addAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                model.addAttribute("id",admin_id);
                model.addAttribute("admin_tk",admin_tk);
                model.addAttribute("admin_password",admin_password);
                model.addAttribute("admin_name",admin_name);
                model.addAttribute("admin_gmail",admin_email);

                return "admin/addadmin";
            }
        }
        catch (Exception e)
        {
            out.flush();
            out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Trung tai khoan hoac gmail')");
            out.println("location.href = './edit?admin-id="+admin_id+"';");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        return "redirect:/admin/admin/list";
    }
}

