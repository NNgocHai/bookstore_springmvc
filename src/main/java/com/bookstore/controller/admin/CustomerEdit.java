package com.bookstore.controller.admin;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.service_impl.CustomerService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;

@Controller
@RequestMapping("/admin2/")
public class CustomerEdit  {
    public CustomerEdit() {
        super();
    }

    CustomerService_impl customer = new CustomerService_impl();

    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") String userid,
                           ModelMap model) {

        int id= Integer.parseInt(userid);
        CustomerEntity customerEntity = customer.findById(id);
        model.addAttribute("customer", customerEntity);

        return "admin/edituser";
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.POST)
    public String editUser(@RequestParam("id") String id,
                           @RequestParam("customer-username") String customer_tk,
                           @RequestParam("customer-password") String customer_password,
                           @RequestParam("customer-name") String customer_name,
                           @RequestParam("customer-email") String customer_gmail,
                           @RequestParam("customer-sdt") String customer_sdt,
                           @RequestParam("customer-vitien") String customer_vitien,
                           ModelMap model,
                           PrintWriter out) {


        try {
            if(!id.equals("") && (!customer_tk.equals("") &&
                    (!customer_password.equals("")) && (!customer_name.equals("")) &&
                    (!customer_gmail.equals("")) && (!customer_sdt.equals("")) &&
                    (!customer_vitien.equals("")))) {

                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setMa_Customer(Integer.parseInt(id));
                customerEntity.setTaikhoan_Customer(customer_tk);
                customerEntity.setGmail_Customer(customer_gmail);
                customerEntity.setHoten_Customer(customer_name);
                customerEntity.setMatkhau_Customer(customer_password);
                customerEntity.setSdt_Customer(customer_sdt);
                customerEntity.setVitien(Integer.valueOf(customer_vitien));
                customer.update(customerEntity);

                return "redirect:/admin/user/list";
            }
            else {
                model.addAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                model.addAttribute("id",id);
                model.addAttribute("customer_tk",customer_tk);
                model.addAttribute("customer_password",customer_password);
                model.addAttribute("customer_name",customer_name);
                model.addAttribute("customer_gmail",customer_gmail);
                model.addAttribute("customer_sdt",customer_sdt);
                model.addAttribute("customer_vitien",customer_vitien);

                return "admin/edituser";
            }
        }
        catch (Exception e)
        {
            out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Trung tai khoan hoac gmail hoac so dien thoai')");
            out.println("location.href = './edit?admin-id="+id+"';");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        return "redirect:/admin/user/list";
    }
}
