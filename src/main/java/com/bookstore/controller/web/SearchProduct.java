package com.bookstore.controller.web;

import com.bookstore.entity.CategoryEntity;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service_impl.CategoryService_impl;
import com.bookstore.service_impl.ProductService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web/")

public class SearchProduct {
    @RequestMapping("product/Search")
    public String doGet(@RequestParam("TuKhoa") String TuKhoa,
                        ModelMap model){
        if (TuKhoa == null)
            TuKhoa= "";


        CategoryService_impl categoryService =new CategoryService_impl();
        ProductService_impl productService =new ProductService_impl();
        List<CategoryEntity> categoryList = categoryService.findAll();

        List<CuonSachEntity> productList = new ArrayList<CuonSachEntity>();
        productList=productService.Search(TuKhoa);

        ProductService_impl productService_impl = new ProductService_impl();
        List<CuonSachEntity> productList_km = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent_km = new ArrayList<CuonSachEntity>();

        productListCurrent= productService_impl.findAll();


        for(CuonSachEntity product: productListCurrent)
        {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService_impl.findById(product.getMa_CuonSach());
            double db =(Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount()))/100)));
            product_km.setGiabia((int)db);
            productListCurrent_km.add(product_km);

        }


        for(CuonSachEntity product: productList)
        {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService_impl.findById(product.getMa_CuonSach());
            double db =(Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount()))/100)));
            product_km.setGiabia((int)db);
            productList_km.add(product_km);

        }

        model.addAttribute("productListCurrent_km", productListCurrent_km);
        model.addAttribute("productListCurrent", productListCurrent);
        model.addAttribute("productList", productList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList_km", productList_km);

        return "web/productlist";
    }
}
