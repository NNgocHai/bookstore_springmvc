package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.entity.*;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class test {
    @RequestMapping("/")
    public String index() {
        return "redirect:web/home";
    }
    @Autowired
    ProductService productService_impl = new ProductService_impl();
    @Autowired
    ChiTietDonHangService chiTietDonHangService_impl = new ChiTietDonHangService_impl();
    @Autowired
    ReviewService reviewService = new ReviewService_impl();

    @RequestMapping("/web/home")
    public String WebHome(ModelMap model) {
        List<ChiTietDonHangEntity> product_hotList = new ArrayList<ChiTietDonHangEntity>();
        List<Object[]> product_hotListt = chiTietDonHangService_impl.FindHot();
        List<ChiTietDonHangEntity> product_hotListtt = chiTietDonHangService_impl.findAll();
        for (Object[] product_hott : product_hotListt) {
            int check = 0;
            for (ChiTietDonHangEntity product_hottt : product_hotListtt) {
                if (product_hottt.getCuonSachEntity().getMa_CuonSach() == (int) product_hott[0]) {
                    product_hotList.add(product_hottt);
                    break;
                }
            }
        }
        List<CuonSachEntity> productList_km = new ArrayList<CuonSachEntity>();
        List<ReviewEntity> reviewEntities = new ArrayList<ReviewEntity>();
        // List<CuonSachEntity> product_hotdiscount = new ArrayList<CuonSachEntity>();
        reviewEntities = reviewService.findAll();
        for (ChiTietDonHangEntity product_hot : product_hotList) {
            product_hot.getCuonSachEntity().getMa_CuonSach();
            CuonSachEntity product_hotkm = new CuonSachEntity();
            product_hotkm = productService_impl.findById(product_hot.getCuonSachEntity().getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(product_hot.getCuonSachEntity().getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product_hot.getCuonSachEntity().getDiscount())) / 100)));
            product_hotkm.setGiabia((int) db);
            productList_km.add(product_hotkm);
        }
        List<CuonSachEntity> cuon = productService_impl.FindHotDiscount();
        cuon.get(0).getDiscount();
        model.addAttribute("reviewEntities", reviewEntities);
        model.addAttribute("productList_km", productList_km);
        model.addAttribute("productListHotDiscount", productService_impl.FindHotDiscount());
        model.addAttribute("product_hotList", product_hotList);

        return "web/index";
    }


}
