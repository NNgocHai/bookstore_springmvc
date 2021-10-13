package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.email.SendingEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/web/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/views/web/register.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String customer_tk = request.getParameter("username");
        String customer_password = request.getParameter("password");
        String customer_name = request.getParameter("name");
        String customer_gmail = request.getParameter("email");
        String customer_sdt = request.getParameter("sdt");
        int customer_vitien = 1000000;


        CustomerDao customerDao=new CustomerDao_impl();
        boolean a= customerDao.checkAddCustomer(customer_tk,customer_gmail,customer_sdt);
        boolean gmail_check=customerDao.checkGmail(customer_gmail);
        boolean sdt_check=customerDao.checkSdt(customer_sdt);
        boolean taikhoan_check=customerDao.checkUserName(customer_tk);

        if (a)
        {

            HttpSession session=request.getSession();
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
            RequestDispatcher rd=request.getRequestDispatcher("/views/web/verification.jsp");
            rd.forward(request,response);
        } else {
            request.setAttribute("errMessage", "Tạo tài khoản thất bại. Hãy thử lại !!!");
            request.setAttribute("taikhoan",customer_tk);
            request.setAttribute("matkhau",customer_password);
            request.setAttribute("hoten",customer_name);
            request.setAttribute("gmail",customer_gmail);
            request.setAttribute("sdt",customer_sdt);
            if (gmail_check==false)
            {
                request.setAttribute("errGmail","Email đã tồn tại");
            }
            if(sdt_check==false)
            {
                request.setAttribute("errSdt","Số điện thoại đã tồn tại");
            }
            if(taikhoan_check==false)
            {
                request.setAttribute("errTaikhoan","Tên tài khoản đã tồn tại");
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/register.jsp");
            rd.forward(request, response);
        }

    }
}
