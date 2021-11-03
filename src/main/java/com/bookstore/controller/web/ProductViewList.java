package com.bookstore.controller.web;

import com.bookstore.dao_impl.NavigationDao_impl;
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
public class ProductViewList {
    public ProductViewList(){
        super();
    }

    @RequestMapping("product")
    public String doGet(@RequestParam(value = "page", required = false) String page,
                           ModelMap model){
        if (page == null)
            page= "1";
        int maxResult = 9;// so bang record load len 1 trang
        int maxNavigationPage = 6;// max so trang hien ra

        CategoryService_impl categoryService =new CategoryService_impl();
        List<CategoryEntity> categoryList = categoryService.findAll();
        NavigationDao_impl<CuonSachEntity> navigationDaoImpl = new NavigationDao_impl<CuonSachEntity>(Integer.valueOf(page),maxResult,maxNavigationPage );
        List<CuonSachEntity> productList_km = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent_km = new ArrayList<CuonSachEntity>();

        ProductService_impl productService_impl = new ProductService_impl();
        productListCurrent= productService_impl.findAll();
        productListCurrent.get(productListCurrent.size()-1);

        for(CuonSachEntity product: productListCurrent)
        {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService_impl.findById(product.getMa_CuonSach());
            double db =(Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount()))/100)));
            product_km.setGiabia((int)db);
            productListCurrent_km.add(product_km);

        }
        for(CuonSachEntity product: navigationDaoImpl.getList())
        {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService_impl.findById(product.getMa_CuonSach());
            double db =(Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount()))/100)));
            product_km.setGiabia((int)db);
            productList_km.add(product_km);

        }
        model.addAttribute("productListCurrent", productListCurrent);
        model.addAttribute("productListCurrent_km", productListCurrent_km);

        model.addAttribute("productList_km", productList_km);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList", navigationDaoImpl.getList());
        model.addAttribute("navigationDaoImpl", navigationDaoImpl);

        return "web/productlist";
    }
}
