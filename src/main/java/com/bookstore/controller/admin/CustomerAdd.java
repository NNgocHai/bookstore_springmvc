package com.bookstore.controller.admin;

import com.bookstore.entity.CustomerEntity;
import com.bookstore.service.CustomerService;
import com.bookstore.service_impl.CustomerService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/user/add")
public class CustomerAdd  extends HttpServlet {
    public CustomerAdd() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/admin/adduser.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String customer_tk = request.getParameter("customer-username");
        String customer_password = request.getParameter("customer-password");
        String customer_name = request.getParameter("customer-name");
        String customer_gmail = request.getParameter("customer-email");
        String customer_sdt = request.getParameter("customer-sdt");
        String customer_vitien = request.getParameter("customer-vitien");

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
                response.sendRedirect(request.getContextPath() + "/admin/user/list");
            }
            else {
                request.setAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                request.setAttribute("customer_tk",customer_tk);
                request.setAttribute("customer_password",customer_password);
                request.setAttribute("customer_name",customer_name);
                request.setAttribute("customer_gmail",customer_gmail);
                request.setAttribute("customer_sdt",customer_sdt);
                request.setAttribute("customer_vitien",customer_vitien);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/adduser.jsp");
                rd.forward(request, response);
            }
        }
        catch (Exception e)
        {
            PrintWriter out = response.getWriter();
            out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
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
    }
}
