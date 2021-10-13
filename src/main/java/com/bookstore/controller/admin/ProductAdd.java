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
import java.io.PrintWriter;

@WebServlet("/admin/product/add")
public class ProductAdd extends HttpServlet {
    public ProductAdd(){super();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/views/admin/addcuonsach.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String cuonsach_ten = request.getParameter("cuonsach-ten");
        String cuonsach_maDS = request.getParameter("cuonsach-maDS");
        String cuonsach_tacgia = request.getParameter("cuonsach-tacgia");
        String cuonsach_soluong = request.getParameter("cuonsach-soluong");
        String cuonsach_giaban = request.getParameter("cuonsach-giaban");
        String cuonsach_anhCS = request.getParameter("cuonsach-anhCS");
        String cuonsach_discount = request.getParameter("cuonsach-discount");
        String cuonsach_mota=request.getParameter("cuonsach-mota");
        try{
            if((!cuonsach_ten.equals("")) && (!cuonsach_maDS.equals("")) && (!cuonsach_tacgia.equals("")) && (!cuonsach_soluong.equals("")) &&(!cuonsach_giaban.equals(""))&&
                    (!cuonsach_discount.equals(""))) {
                CuonSachEntity cuonSachEntity = new CuonSachEntity();
                cuonSachEntity.setTen_CuonSach(cuonsach_ten);
                cuonSachEntity.setMa_DauSach(Integer.parseInt(cuonsach_maDS));
                cuonSachEntity.setGiabia(Integer.parseInt(cuonsach_giaban));
                cuonSachEntity.setSoluong(Integer.parseInt(cuonsach_soluong));
                cuonSachEntity.setTacgia(cuonsach_tacgia);
                cuonSachEntity.setAnh_CuonSach(cuonsach_anhCS);
                cuonSachEntity.setDiscount(Integer.parseInt(cuonsach_discount));
                cuonSachEntity.setMota(cuonsach_mota);
                ProductService cuonsach = new ProductService_impl();
                cuonsach.save(cuonSachEntity);
                response.sendRedirect(request.getContextPath() + "/admin/product/list");
            }
            else {
                request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin");
                request.setAttribute("madausach",cuonsach_maDS);
                request.setAttribute("tencuonsach",cuonsach_ten);
                request.setAttribute("tacgia",cuonsach_tacgia);
                request.setAttribute("soluong",cuonsach_soluong);
                request.setAttribute("anhcuonsach",cuonsach_anhCS);
                request.setAttribute("discount",cuonsach_discount);
                request.setAttribute("giaban",cuonsach_giaban);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/addcuonsach.jsp");
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
            out.println("alert('Không có mã đầu sách này')");
            out.println("location.href = \"./add\";");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
}
