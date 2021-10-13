package com.bookstore.controller.admin;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service.ProductService;
import com.bookstore.service_impl.ProductService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/product/edit")
public class ProductEdit extends HttpServlet {
    ProductService productService=new ProductService_impl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cuonsach_id= Integer.parseInt(request.getParameter("cuonsach_id"));
        CuonSachEntity cuonSachEntity=productService.findById(cuonsach_id);
        request.setAttribute("cuonsach",cuonSachEntity);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/views/admin/editcuonsach.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int cuonsach_id = Integer.parseInt(request.getParameter("cuonsach_id"));
        String cuonsach_ten = request.getParameter("cuonsach_ten");
        String cuonsach_maDS = request.getParameter("cuonsach_maDS");
        String cuonsach_tacgia = request.getParameter("cuonsach_tacgia");
        String cuonsach_soluong = request.getParameter("cuonsach_soluong");
        String cuonsach_giaban = request.getParameter("cuonsach_giaban");
        String cuonsach_anhCS = request.getParameter("cuonsach_anhCS");
        String cuonsach_discount = request.getParameter("cuonsach_discount");
        String cuonsach_mota=request.getParameter("cuonsach_mota");
        if((!cuonsach_ten.equals("")) && (!cuonsach_maDS.equals("")) && (!cuonsach_tacgia.equals("")) && (!cuonsach_soluong.equals("")) &&(!cuonsach_giaban.equals(""))
                && (!cuonsach_discount.equals(""))&&(!cuonsach_anhCS.equals("")))
        {
            CuonSachEntity cuonSachEntity = new CuonSachEntity();
            cuonSachEntity.setMa_CuonSach(cuonsach_id);
            cuonSachEntity.setTen_CuonSach(cuonsach_ten);
            cuonSachEntity.setMa_DauSach(Integer.parseInt(cuonsach_maDS));
            cuonSachEntity.setGiabia(Integer.parseInt(cuonsach_giaban));
            cuonSachEntity.setSoluong(Integer.parseInt(cuonsach_soluong));
            cuonSachEntity.setTacgia(cuonsach_tacgia);
            cuonSachEntity.setAnh_CuonSach(cuonsach_anhCS);
            cuonSachEntity.setDiscount(Integer.parseInt(cuonsach_discount));
            cuonSachEntity.setMota(cuonsach_mota);
            ProductService productService = new ProductService_impl();
            productService.update(cuonSachEntity);
            response.sendRedirect(request.getContextPath() + "/admin/product/list");
        }
        else{
            request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin");
            request.setAttribute("macuonsach",cuonsach_id);
            request.setAttribute("madausach",cuonsach_maDS);
            request.setAttribute("tencuonsach",cuonsach_ten);
            request.setAttribute("tacgia",cuonsach_tacgia);
            request.setAttribute("soluong",cuonsach_soluong);
            request.setAttribute("anhcuonsach",cuonsach_anhCS);
            request.setAttribute("discount",cuonsach_discount);
            request.setAttribute("giaban",cuonsach_giaban);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/editcuonsach.jsp");
            rd.forward(request, response);
        }
        /*try {

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
            out.println("location.href = './edit?product-id="+cuonsach_id+"';");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }*/
    }
}
