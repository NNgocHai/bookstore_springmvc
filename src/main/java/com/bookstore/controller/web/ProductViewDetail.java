package com.bookstore.controller.web;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service.ProductService;
import com.bookstore.service_impl.ProductService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web/")
public class ProductViewDetail {
    public ProductViewDetail(){
        super();
    }

    @RequestMapping("productDetail")
    public String doGet(@RequestParam("id") String id,
                           @RequestParam("Cate") String Cate,
                           ModelMap model) {
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
        model.addAttribute("Catee", Catee);
        model.addAttribute("Catee_km", Catee_km);

        model.addAttribute("product", product);
        model.addAttribute("product_km", product_km);

        return "web/ProductViewDetail";
    }

}
