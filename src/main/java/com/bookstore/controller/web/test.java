package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.entity.*;
import com.bookstore.service.CustomerService;
import com.bookstore.service.GioHangService;
import com.bookstore.service.ReviewService;
import com.bookstore.service_impl.*;
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
    @RequestMapping("/web/home")
    public String WebHome(ModelMap model){
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

    @RequestMapping(value="/web/login", method =  RequestMethod.GET)
    public String WebLogin(){
        return "web/login";
    }

    @RequestMapping(value="/web/login", method =  RequestMethod.POST)
    public String WebLogin1(HttpSession session,ModelMap model,@RequestParam("user") String user,@RequestParam("password") String password){
        CustomerService customerService= new CustomerService_impl();
        GioHangService gioHangService= new GioHangService_impl();
        List<CustomerEntity> customerServices= new ArrayList<CustomerEntity>();
        List<GioHangEntity> gioHangEntities= new ArrayList<GioHangEntity>();
        GioHangEntity gioHangEntity= new GioHangEntity();
        GioHangIDKey gioHangIDKey=new GioHangIDKey();
        CustomerDao customerDao = new CustomerDao_impl();
        /*        try {*/
        boolean a = customerDao.checkCustomerLogin(user,password);
        if (a) {
            int n=0;
            int tongtien=0;
            customerServices=customerService.findByUser(user);
            int id_customer = customerServices.get(0).getMa_Customer();
            CustomerEntity person = customerService.findById(id_customer);

            session.removeAttribute("taikhoan_dk");
            session.setAttribute("user", user);
            session.setAttribute("password", password);
            session.setAttribute("person", person);
            gioHangEntities= gioHangService.FindByMaCustomer(person.getMa_Customer());
            int IsOrder=0;
            if(session.getAttribute("length_orders") != null)
                IsOrder = (int) session.getAttribute("length_orders");
            if ( IsOrder >0 && gioHangEntities.size()==0) {
                List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
                for (GioHangEntity Order : Orders) {
                    gioHangIDKey.setMa_CuonSach(Order.getCuonSachEntity().getMa_CuonSach());
                    gioHangIDKey.setMa_Customer(person.getMa_Customer());
                    gioHangEntity.setId(gioHangIDKey);
                       /* gioHangEntity.setCuonSachEntity(Order.getCuonSachEntity());
                        gioHangEntity.setCustomerEntity(person);*/
                    gioHangEntity.setSoluong(Order.getSoluong());
                    gioHangService.save(gioHangEntity);
                    tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;

                }
                n=Orders.size();
                session.setAttribute("length_orders", n);
                session.setAttribute("Orders", Orders);
                session.setAttribute("tongtien", tongtien);
            }
            else {

                for (GioHangEntity Order : gioHangEntities) {
                    double db = (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getGiabia())) * (1 - (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getDiscount())) / 100)));
                    Order.getCuonSachEntity().setGiabia((int) db);
                    tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                }
                n = gioHangEntities.size();
                session.setAttribute("length_orders", n);
                session.setAttribute("Orders", gioHangEntities);
                session.setAttribute("tongtien", tongtien);
            }
            return "web/index";
        } else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            return "web/login";
        }
    }


}
