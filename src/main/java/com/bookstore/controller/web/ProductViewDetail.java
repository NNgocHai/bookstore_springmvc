package com.bookstore.controller.web;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/web/productDetail")
public class ProductViewDetail extends HttpServlet {
    public ProductViewDetail(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");
        String Cate= request.getParameter("Cate");

        if (id == null)
            id= "";
        if (Cate == null)
            Cate= "";


        ProductService productService = new ProductService_impl();
        CuonSachEntity product = productService.findById(Integer.parseInt(id));
        CuonSachEntity product_km= productService.findById(product.getMa_CuonSach());
        List<CuonSachEntity> Catee =new ArrayList<>();
        List<CuonSachEntity> Catee_km =new ArrayList<>();
        Catee = productService.FindByCate(Integer.parseInt(Cate));


        for(CuonSachEntity productt: Catee)
        {
            CuonSachEntity product_kmm = new CuonSachEntity();
            product_kmm = productService.findById(productt.getMa_CuonSach());
            double db =(Double.parseDouble(String.valueOf(productt.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(productt.getDiscount()))/100)));
            product_kmm.setGiabia((int)db);
            Catee_km.add(product_kmm);
        }

        Catee.get(0).getCategoryEntity().getTen_DauSach();
        double db =(Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount()))/100)));
        product_km.setGiabia((int)db);
        request.setAttribute("Catee", Catee);
        request.setAttribute("Catee_km", Catee_km);

        request.setAttribute("product", product);
        request.setAttribute("product_km", product_km);

        RequestDispatcher rd = request.getRequestDispatcher("/views/web/ProductViewDetail.jsp");
        rd.forward(request, response);


    }

}
