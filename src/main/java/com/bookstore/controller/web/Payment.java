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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

        Boolean isCheck = true;

        if (isCheck == true)
        {

            String chuoi="";
            chuoi+="upload=1";
            chuoi+="&&return=http://localhost:8080/bookstore_springmvc_war_exploded/web/paysuccess";
            chuoi+="&&cmd=_cart";
            chuoi+="&&business=chuShop@gmail.com";

            int i=1;

            for(GioHangEntity Order : Orders){
                chuoi+=removeAccent("&&item_name_" + i + "=" + Order.getCuonSachEntity().getTen_CuonSach());
                chuoi+="&&quantity_" + i + "=" + Order.getSoluong();
                chuoi+="&&amount_" + i + "=" + (Order.getCuonSachEntity().getGiabia() / 24000);
                i++;
            }

            return "redirect:https://www.sandbox.paypal.com/cgi-bin/webscr?"+chuoi;
        }

        Orders.clear();
        session.setAttribute("Orders", Orders);

        model.addAttribute("sucess", "Đặt hàng thành công");
        int ma_dh=donHangEntity.getMa_DH();
        ConfirmPayment confirmPayment=new ConfirmPayment(ma_dh,user_email);

        return "web/checkout";
    }

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
}
