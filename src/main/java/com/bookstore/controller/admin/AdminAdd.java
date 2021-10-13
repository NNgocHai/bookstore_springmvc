package com.bookstore.controller.admin;

import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/admin/add")
public class AdminAdd  extends HttpServlet {
    public AdminAdd() {
        super();
    }    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/admin/addadmin.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String admin_tk = request.getParameter("admin-username");
        String admin_password = request.getParameter("admin-password");
        String admin_name = request.getParameter("admin-name");
        String admin_gmail = request.getParameter("admin-email");
        try{
            if((!admin_tk.equals("")) && (!admin_password.equals("")) && (!admin_name.equals(""))&& (!admin_gmail.equals(""))) {
                AdminsEntity adminsEntity = new AdminsEntity();
                adminsEntity.setTaikhoan_Admin(admin_tk);
                adminsEntity.setMatkhau_Admin(admin_password);
                adminsEntity.setHoten_Admin(admin_name);
                adminsEntity.setGmail_Admin(admin_gmail);
                AdminService admin = new AdminService_impl();
                admin.save(adminsEntity);
                response.sendRedirect(request.getContextPath() + "/admin/admin/list");
            }
            else {
                request.setAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                request.setAttribute("admin_tk",admin_tk);
                request.setAttribute("admin_password",admin_password);
                request.setAttribute("admin_name",admin_name);
                request.setAttribute("admin_gmail",admin_gmail);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/addadmin.jsp");
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
            out.println("alert('Trung tai khoan hoac gmail')");
            out.println("location.href = \"./add\";");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
}
