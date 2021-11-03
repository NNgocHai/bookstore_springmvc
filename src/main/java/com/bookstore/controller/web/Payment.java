package com.bookstore.controller.web;

import com.bookstore.email.ConfirmPayment;
import com.bookstore.entity.*;
import com.bookstore.service.ChiTietDonHangService;
import com.bookstore.service.DonHangService;
import com.bookstore.service.GioHangService;
import com.bookstore.service.ProductService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;
import com.bookstore.service_impl.DonHangService_impl;
import com.bookstore.service_impl.GioHangService_impl;
import com.bookstore.service_impl.ProductService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web/")
public class Payment {
    @RequestMapping(value = "payment", method = RequestMethod.POST)
    public String doPost(@RequestParam("address") String address,
                            @RequestParam("phone") String phone,
                            @RequestParam("email") String user_email,
                            HttpSession session,
                            ModelMap model) {

        GioHangService gioHangService = new GioHangService_impl();
        DonHangService donHangService = new DonHangService_impl();
        ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
        DonHangEntity donHangEntity = new DonHangEntity();
        ProductService productService= new ProductService_impl();
        CuonSachEntity cuonSachEntity = new CuonSachEntity();
        List<CuonSachEntity>cuonSachEntities= new ArrayList<CuonSachEntity>();
        cuonSachEntities= productService.findAll();

        CustomerEntity person = (CustomerEntity) session.getAttribute("person");
        int tongtien = (int) session.getAttribute("tongtien");
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");

        LocalDateTime now = LocalDateTime.now();

        gioHangService.DeletebyCustomer(person.getMa_Customer());
        donHangEntity.setMa_Customer(person.getMa_Customer());
        donHangEntity.setDiachi(address);
        donHangEntity.setSdt(phone);
        donHangEntity.setNgaydat(Timestamp.valueOf((now)));
        donHangEntity.setTongtien(tongtien);
        donHangEntity.setActiveDH("Chưa giao");
        donHangEntity=donHangService.save(donHangEntity);


        for (GioHangEntity Order : Orders) {
            ChiTietDonHangEntity chiTietDonHangEntity = new ChiTietDonHangEntity();
            ChiTietDonHangIDKey chiTietDonHangIDKey = new ChiTietDonHangIDKey();
            chiTietDonHangIDKey.setMa_CuonSach(Order.getCuonSachEntity().getMa_CuonSach());
            chiTietDonHangIDKey.setMa_DH(donHangEntity.getMa_DH());
            chiTietDonHangEntity.setId(chiTietDonHangIDKey);
            chiTietDonHangEntity.setSoluong(Order.getSoluong());
            chiTietDonHangEntity.setGia(Order.getCuonSachEntity().getGiabia());
            chiTietDonHangService.save(chiTietDonHangEntity);
            //Trừ số lượng trong kho
            cuonSachEntity= Order.getCuonSachEntity();
            cuonSachEntity.setSoluong(cuonSachEntity.getSoluong()-Order.getSoluong());
            for(CuonSachEntity product : cuonSachEntities) {
                if(Order.getCuonSachEntity().getMa_CuonSach()== product.getMa_CuonSach())
                cuonSachEntity.setGiabia(product.getGiabia());

            }
            productService.update(cuonSachEntity);
        }
        session.removeAttribute("length_orders");
        session.removeAttribute("tongtien");
        Orders.clear();
        session.setAttribute("Orders", Orders);
        model.addAttribute("sucess", "Đặt hàng thành công");
        int ma_dh=donHangEntity.getMa_DH();
        ConfirmPayment confirmPayment=new ConfirmPayment(ma_dh,user_email);

        return "web/checkout";
    }
}
