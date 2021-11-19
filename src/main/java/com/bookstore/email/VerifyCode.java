package com.bookstore.email;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.service.CustomerService;
import com.bookstore.service_impl.CustomerService_impl;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
public class VerifyCode {
    @Autowired
    CustomerService customerService = new CustomerService_impl();

    @RequestMapping(value="/web/verify", method = RequestMethod.POST)
    public String Verify(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "ma_code", required = false) String code_nhap,
                            ModelMap model) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("code_dk").equals(code_nhap)) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setTaikhoan_Customer(session.getAttribute("taikhoan_dk").toString());
            customerEntity.setGmail_Customer(session.getAttribute("gmail_dk").toString());
            customerEntity.setHoten_Customer(session.getAttribute("ten_dk").toString());
            customerEntity.setMatkhau_Customer(session.getAttribute("matkhau_dk").toString());
            customerEntity.setSdt_Customer(session.getAttribute("sdt_dk").toString());
            customerEntity.setVitien(Integer.parseInt(session.getAttribute("vitien_dk").toString()));

            customerService.save(customerEntity);

            //remove session regist
            //session.removeAttribute("taikhoan_dk");
            session.removeAttribute("gmail_dk");
            session.removeAttribute("ten_dk");
            session.removeAttribute("matkhau_dk");
            session.removeAttribute("sdt_dk");
            session.removeAttribute("vitien_dk");
            session.removeAttribute("code_dk");

            //announce success
            PrintWriter out = response.getWriter();
            out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Hoàn tất đăng kí')");
            out.println("location.href = \"./login\";");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        } else {
            model.addAttribute("errMessage", "Vui lòng kiểm tra lại mã xác nhận.");
            return "web/verification";
        }
        return "web/verification";
    }
}
