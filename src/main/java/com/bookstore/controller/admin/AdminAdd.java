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
import java.io.IOException;
import java.io.PrintWriter;


@Controller
@RequestMapping("/admin/")
public class AdminAdd {
    public AdminAdd() {
        super();
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.GET)
    public String doGet() {
        return "admin/addadmin";

    }

    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public String doPost(@RequestParam("admin-username") String admin_tk,
                         @RequestParam("admin-password") String admin_password,
                         @RequestParam("admin-name") String admin_name,
                         @RequestParam("admin-email") String admin_gmail,
                         ModelMap model,
                         PrintWriter out) {

        try{
            if((!admin_tk.equals("")) && (!admin_password.equals("")) && (!admin_name.equals(""))&& (!admin_gmail.equals(""))) {
                AdminsEntity adminsEntity = new AdminsEntity();
                adminsEntity.setTaikhoan_Admin(admin_tk);
                adminsEntity.setMatkhau_Admin(admin_password);
                adminsEntity.setHoten_Admin(admin_name);
                adminsEntity.setGmail_Admin(admin_gmail);
                AdminService admin = new AdminService_impl();
                admin.save(adminsEntity);

                return "redirect: /admin/admin/list";
            }
            else {
                model.addAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                model.addAttribute("admin_tk",admin_tk);
                model.addAttribute("admin_password",admin_password);
                model.addAttribute("admin_name",admin_name);
                model.addAttribute("admin_gmail",admin_gmail);

                return "admin/addadmin";
            }
        }
        catch (Exception e)
        {
            out.flush();
            out.println("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Trung tai khoan hoac gmail')");
            out.println("location.href = \"./add\";");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

        return "redirect:/admin/admin/list";
    }
}
