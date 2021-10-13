package com.bookstore.controller.admin;

import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ShipperService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/ship/add")
public class ShipperAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/views/admin/addshipper.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String shipper_tk = request.getParameter("shipper-username");
        String shipper_password = request.getParameter("shipper-password");
        String shipper_name = request.getParameter("shipper-name");
        String shipper_gmail = request.getParameter("shipper-email");

        try{
            if((!shipper_tk.equals("")) && (!shipper_password.equals("")) && (!shipper_name.equals(""))&& (!shipper_gmail.equals(""))) {
                ShipperEntity shipperEntity = new ShipperEntity();
                shipperEntity.setTaikhoan_Shipper(shipper_tk);
                shipperEntity.setGmail_Shipper(shipper_gmail);
                shipperEntity.setHoten_Shipper(shipper_name);
                shipperEntity.setMatkhau_Shipper(shipper_password);
                ShipperService shipper = new ShipperService_impl();
                shipper.save(shipperEntity);
                response.sendRedirect(request.getContextPath() + "/admin/ship/list");
            }
            else {
                request.setAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                request.setAttribute("shipper_tk",shipper_tk);
                request.setAttribute("shipper_password",shipper_password);
                request.setAttribute("shipper_name",shipper_name);
                request.setAttribute("shipper_gmail",shipper_gmail);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/addshipper.jsp");
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
