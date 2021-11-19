package com.bookstore.controller.admin;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.service.CustomerService;
import com.bookstore.service_impl.CustomerService_impl;
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
@RequestMapping("/admin2/")
public class CustomerAdd  {
    public CustomerAdd() {
        super();
    }

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    public String addUserForm(){

        return "admin/adduser";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public String addUser(@RequestParam("customer-username") String customer_tk,
                          @RequestParam("customer-password") String customer_password,
                          @RequestParam("customer-name") String customer_name,
                          @RequestParam("customer-email") String customer_gmail,
                          @RequestParam("customer-sdt") String customer_sdt,
                          @RequestParam("customer-vitien") String customer_vitien,
                          ModelMap model,
                          PrintWriter out) {

        try{
            if((!customer_tk.equals("")) && (!customer_password.equals("")) && (!customer_name.equals(""))&& (!customer_gmail.equals("")) && (!customer_sdt.equals(""))&& (!customer_vitien.equals(""))) {
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setTaikhoan_Customer(customer_tk);
                customerEntity.setGmail_Customer(customer_gmail);
                customerEntity.setHoten_Customer(customer_name);
                customerEntity.setMatkhau_Customer(customer_password);
                customerEntity.setSdt_Customer(customer_sdt);
                customerEntity.setVitien(Integer.valueOf(customer_vitien));
                CustomerService customer = new CustomerService_impl();
                customer.save(customerEntity);

                return "redirect:/admin/user/list";
            }
            else {
                model.addAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                model.addAttribute("customer_tk",customer_tk);
                model.addAttribute("customer_password",customer_password);
                model.addAttribute("customer_name",customer_name);
                model.addAttribute("customer_gmail",customer_gmail);
                model.addAttribute("customer_sdt",customer_sdt);
                model.addAttribute("customer_vitien",customer_vitien);

                return "admin/adduser";
            }
        }
        catch (Exception e)
        {
            out.println("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Trung tai khoan hoac gmail hoac so dien thoai')");
            out.println("location.href = \"./add\";");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

        return "redirect:/admin/user/list";
    }
}
