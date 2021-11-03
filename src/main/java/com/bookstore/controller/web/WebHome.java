package com.bookstore.controller.web;

import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.ReviewEntity;
import com.bookstore.service.ReviewService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;
import com.bookstore.service_impl.ProductService_impl;
import com.bookstore.service_impl.ReviewService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class WebHome {
    public WebHome(){
        super();
    }

    @RequestMapping("home1")
    public String doGet(ModelMap model) {


        ProductService_impl productService_impl=new ProductService_impl();
        ChiTietDonHangService_impl chiTietDonHangService_impl=new ChiTietDonHangService_impl();
        ReviewService reviewService= new ReviewService_impl();
        List<ChiTietDonHangEntity> product_hotList = new ArrayList<ChiTietDonHangEntity>();
        List<Object[]> product_hotListt = chiTietDonHangService_impl.FindHot();
        List<ChiTietDonHangEntity> product_hotListtt = chiTietDonHangService_impl.findAll();

        for(Object[] product_hott: product_hotListt) {
            int check =0;
            for(ChiTietDonHangEntity product_hottt: product_hotListtt) {
                if (product_hottt.getCuonSachEntity().getMa_CuonSach() == (int) product_hott[0]) {
                    product_hotList.add(product_hottt);
                    break;
                }
            }


        }
            List<CuonSachEntity> productList_km = new ArrayList<CuonSachEntity>();
        List<ReviewEntity> reviewEntities = new ArrayList<ReviewEntity>();
       // List<CuonSachEntity> product_hotdiscount = new ArrayList<CuonSachEntity>();
        reviewEntities= reviewService.findAll();
        for(ChiTietDonHangEntity product_hot: product_hotList) {
            product_hot.getCuonSachEntity().getMa_CuonSach();
            CuonSachEntity product_hotkm = new CuonSachEntity();
            product_hotkm = productService_impl.findById(product_hot.getCuonSachEntity().getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(product_hot.getCuonSachEntity().getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product_hot.getCuonSachEntity().getDiscount())) / 100)));
            product_hotkm.setGiabia((int) db);
            productList_km.add(product_hotkm);
        }

        List<CuonSachEntity> cuon= productService_impl.FindHotDiscount();
        cuon.get(0).getDiscount();

        model.addAttribute("reviewEntities", reviewEntities);
        model.addAttribute("productList_km", productList_km);
        model.addAttribute("productListHotDiscount", productService_impl.FindHotDiscount());
        model.addAttribute("product_hotList", product_hotList);

        return "web/index";
    }
}
