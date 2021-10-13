package com.bookstore.email;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.service.CustomerService;
import com.bookstore.service_impl.CustomerService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/web/verify")
public class VerifyCode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String code_nhap=request.getParameter("ma_code").trim();
        HttpSession session=request.getSession();
        if(session.getAttribute("code_dk").equals(code_nhap))
        {
            //save user
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setTaikhoan_Customer(session.getAttribute("taikhoan_dk").toString());
            customerEntity.setGmail_Customer(session.getAttribute("gmail_dk").toString());
            customerEntity.setHoten_Customer(session.getAttribute("ten_dk").toString());
            customerEntity.setMatkhau_Customer(session.getAttribute("matkhau_dk").toString());
            customerEntity.setSdt_Customer(session.getAttribute("sdt_dk").toString());
            customerEntity.setVitien(Integer.parseInt(session.getAttribute("vitien_dk").toString()));
            CustomerService customer = new CustomerService_impl();
            customer.save(customerEntity);

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
        }
        else{
            request.setAttribute("errMessage","Vui lòng kiểm tra lại mã xác nhận.");
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/verification.jsp");
            rd.forward(request, response);
        }
    }
}
