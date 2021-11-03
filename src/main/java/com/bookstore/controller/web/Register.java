package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.email.SendingEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequestMapping("/web/")
public class Register {
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String doGet() {
        return "web/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doPost(@RequestParam("username") String customer_tk,
                         @RequestParam("password") String customer_password,
                         @RequestParam("name") String customer_name,
                         @RequestParam("email") String customer_gmail,
                         @RequestParam("sdt") String customer_sdt,
                         HttpSession session,
                         ModelMap model) {
        int customer_vitien = 1000000;


        CustomerDao customerDao=new CustomerDao_impl();
        boolean a= customerDao.checkAddCustomer(customer_tk,customer_gmail,customer_sdt);
        boolean gmail_check=customerDao.checkGmail(customer_gmail);
        boolean sdt_check=customerDao.checkSdt(customer_sdt);
        boolean taikhoan_check=customerDao.checkUserName(customer_tk);

        if (a)
        {
            session.setAttribute("taikhoan_dk",customer_tk);
            session.setAttribute("gmail_dk",customer_gmail);
            session.setAttribute("ten_dk",customer_name);
            session.setAttribute("matkhau_dk",customer_password);
            session.setAttribute("sdt_dk",customer_sdt);
            session.setAttribute("vitien_dk",customer_vitien);

            //create code
            Random random=new Random();
            int  code=random.nextInt(999999);
            String code_string=String.valueOf(code);

            session.setAttribute("code_dk",code_string);
            SendingEmail se=new SendingEmail(customer_gmail,customer_name,code_string);

            return "web/verification";
        } else {
            model.addAttribute("errMessage", "Tạo tài khoản thất bại. Hãy thử lại !!!");
            model.addAttribute("taikhoan",customer_tk);
            model.addAttribute("matkhau",customer_password);
            model.addAttribute("hoten",customer_name);
            model.addAttribute("gmail",customer_gmail);
            model.addAttribute("sdt",customer_sdt);

            if (gmail_check==false)
            {
                model.addAttribute("errGmail","Email đã tồn tại");
            }
            if(sdt_check==false)
            {
                model.addAttribute("errSdt","Số điện thoại đã tồn tại");
            }
            if(taikhoan_check==false)
            {
                model.addAttribute("errTaikhoan","Tên tài khoản đã tồn tại");
            }

            return "web/register";
        }

    }
}
