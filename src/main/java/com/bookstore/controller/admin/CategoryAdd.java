package com.bookstore.controller.admin;
import com.bookstore.entity.CategoryEntity;
import com.bookstore.service.CategoryService;
import com.bookstore.service_impl.CategoryService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/admin/cate/add")
public class CategoryAdd extends HttpServlet {
    public CategoryAdd(){super();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/views/admin/addcategory.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String category_name = request.getParameter("category-name");
        try{
            if((!category_name.equals("")) ) {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setTen_DauSach(category_name);
                CategoryService category = new CategoryService_impl();
                category.save(categoryEntity);
                response.sendRedirect(request.getContextPath() + "/admin/cate/list");
            }
            else {
                request.setAttribute("errorMessage", "Tên đầu sách trống");
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/addcategory.jsp");
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
            out.println("alert('Trung ten dau sach')");
            out.println("location.href = \"./add\";");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
}
