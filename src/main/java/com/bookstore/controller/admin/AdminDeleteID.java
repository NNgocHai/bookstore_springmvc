package com.bookstore.controller.admin;

import com.bookstore.service.AdminService;
import com.bookstore.service_impl.AdminService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/admin/delete")
public class AdminDeleteID  extends HttpServlet {
    public AdminDeleteID() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int admin_idd= Integer.parseInt(request.getParameter("admin-id"));
        AdminService admin = new AdminService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        HttpSession session = request.getSession();
        String admin_tk = (String) session.getAttribute("user_admin");
        try{
            if(!(admin.checkDelete(admin_tk, admin_idd))) {
                listId.add(admin_idd);
                admin.deleteList(listId);
                request.setAttribute("adminList", admin.findAll());
                response.sendRedirect(request.getContextPath() + "/admin/admin/list");
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/error.jsp");
                rd.forward(request, response);

            }
        }
        catch (Exception e)
        {
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/error.jsp");
            rd.forward(request, response);
        }
    }
}
