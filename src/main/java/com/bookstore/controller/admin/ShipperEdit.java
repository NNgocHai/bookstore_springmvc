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

@WebServlet("/admin/ship/edit")
public class ShipperEdit extends HttpServlet {
    ShipperService shipper = new ShipperService_impl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        ShipperEntity shipperEntity = shipper.findById(id);
        request.setAttribute("shipper", shipperEntity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/editshipper.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String id_string=request.getParameter("id");
        String shipper_tk = request.getParameter("shipper-username");
        String shipper_password = request.getParameter("shipper-password");
        String shipper_name = request.getParameter("shipper-name");
        String shipper_gmail = request.getParameter("shipper-email");
        try {
            if(!id_string.equals("")&&(!shipper_tk.equals("")&&(!shipper_tk.equals(""))&&(!shipper_name.equals(""))&&(!shipper_gmail.equals(""))))
            {
                ShipperEntity shipperEntity = new ShipperEntity();
                shipperEntity.setMa_Shipper(id);
                shipperEntity.setTaikhoan_Shipper(shipper_tk);
                shipperEntity.setGmail_Shipper(shipper_gmail);
                shipperEntity.setHoten_Shipper(shipper_name);
                shipperEntity.setMatkhau_Shipper(shipper_password);
                shipper.update(shipperEntity);
                response.sendRedirect(request.getContextPath() + "/admin/ship/list");
            }
            else {
                request.setAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                request.setAttribute("id",id_string);
                request.setAttribute("shipper_tk",shipper_tk);
                request.setAttribute("shipper_password",shipper_password);
                request.setAttribute("shipper_name",shipper_name);
                request.setAttribute("shipper_gmail",shipper_gmail);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/editshipper.jsp");
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
            out.println("location.href = './edit?id="+id+"';");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
}
