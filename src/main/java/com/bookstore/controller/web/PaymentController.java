package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.email.ConfirmPayment;
import com.bookstore.email.SendingEmail;
import com.bookstore.entity.*;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class PaymentController {

    @Autowired
    ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
    @Autowired
    GioHangService gioHangService = new GioHangService_impl();
    @Autowired
    ProductService productService = new ProductService_impl();
    @Autowired
    DonHangService donHangService = new DonHangService_impl();
    @Autowired
    ConfirmPayment confirmPayment = new ConfirmPayment();

    String address,phone,user_email;


    @RequestMapping("/web/payhelp")
    public String PayHelp() {
        return "web/payhelp";
    }


    @RequestMapping(value = "/web/payment", method = RequestMethod.POST)
    public String Payment(@RequestParam("address") String address,
                          @RequestParam("phone") String phone,
                          @RequestParam("email") String user_email,
                          @RequestParam("transaction_payment") String transaction_payment,
                          HttpSession session,
                          HttpServletResponse response,
                          ModelMap model) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        this.address = address;
        this.phone = phone;
        this.user_email =user_email;
        DonHangEntity donHangEntity = new DonHangEntity();
        CuonSachEntity cuonSachEntity = new CuonSachEntity();
        List<CuonSachEntity> cuonSachEntities = new ArrayList<CuonSachEntity>();
        cuonSachEntities = productService.findAll();
        CustomerEntity person = (CustomerEntity) session.getAttribute("person");
        int tongtien = (int) session.getAttribute("tongtien");
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
        LocalDateTime now = LocalDateTime.now();
        Boolean isCheck = false;

        if(transaction_payment.equals("1") )
            isCheck = true;
        if (isCheck == true) {

            String chuoi = "";
            chuoi += "upload=1";
            chuoi += "&&return=http://localhost:8080/bookstore_springmvc_war_exploded/web/paysuccess";
            chuoi += "&&cmd=_cart";
            chuoi += "&&business=chuShop@gmail.com";

            int i = 1;

            for (GioHangEntity Order : Orders) {
                chuoi += removeAccent("&&item_name_" + i + "=" + Order.getCuonSachEntity().getTen_CuonSach());
                chuoi += "&&quantity_" + i + "=" + Order.getSoluong();
                chuoi += "&&amount_" + i + "=" + (Order.getCuonSachEntity().getGiabia() / 22000.0);
                i++;
            }
            return "redirect:https://www.sandbox.paypal.com/cgi-bin/webscr?" + chuoi;
        }
        gioHangService.DeletebyCustomer(person.getMa_Customer());
        donHangEntity.setMa_Customer(person.getMa_Customer());
        donHangEntity.setDiachi(address);
        donHangEntity.setSdt(phone);
        donHangEntity.setNgaydat(Timestamp.valueOf((now)));
        donHangEntity.setTongtien(tongtien);
        donHangEntity.setActiveDH("Chưa giao");
        donHangEntity.setGhichu("Chưa thanh toán");
        donHangEntity = donHangService.save(donHangEntity);

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
            cuonSachEntity = Order.getCuonSachEntity();
            cuonSachEntity.setSoluong(cuonSachEntity.getSoluong() - Order.getSoluong());
            for (CuonSachEntity product : cuonSachEntities) {
                if (Order.getCuonSachEntity().getMa_CuonSach() == product.getMa_CuonSach())
                    cuonSachEntity.setGiabia(product.getGiabia());

            }
            productService.update(cuonSachEntity);
        }

        session.removeAttribute("length_orders");
        session.removeAttribute("tongtien");
        Orders.clear();
        session.setAttribute("Orders", Orders);

        model.addAttribute("sucess", "Đặt hàng thành công");
        int ma_dh = donHangEntity.getMa_DH();
        confirmPayment.ConfirmPayment1(ma_dh, user_email);

        PrintWriter out = response.getWriter();
        out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Loi</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<script>");
        out.println("alert('Đặt hàng thành công')");
        out.println("location.href = \"./home\";");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        return "web/index";
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


    @RequestMapping("/web/paysuccess")
    public String PayPalSuccess(HttpSession session, HttpServletResponse response,
                                ModelMap model) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        DonHangEntity donHangEntity = new DonHangEntity();
        CuonSachEntity cuonSachEntity = new CuonSachEntity();
        List<CuonSachEntity> cuonSachEntities = new ArrayList<CuonSachEntity>();
        cuonSachEntities = productService.findAll();
        CustomerEntity person = (CustomerEntity) session.getAttribute("person");
        int tongtien = (int) session.getAttribute("tongtien");
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
        LocalDateTime now = LocalDateTime.now();
        Boolean isCheck = false;
        gioHangService.DeletebyCustomer(person.getMa_Customer());
        donHangEntity.setMa_Customer(person.getMa_Customer());
        donHangEntity.setDiachi(address);
        donHangEntity.setSdt(phone);
        donHangEntity.setNgaydat(Timestamp.valueOf((now)));
        donHangEntity.setTongtien(tongtien);
        donHangEntity.setActiveDH("Chưa giao");
        donHangEntity.setGhichu("Đã thanh toán");
        donHangEntity = donHangService.save(donHangEntity);
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
            cuonSachEntity = Order.getCuonSachEntity();
            cuonSachEntity.setSoluong(cuonSachEntity.getSoluong() - Order.getSoluong());
            for (CuonSachEntity product : cuonSachEntities) {
                if (Order.getCuonSachEntity().getMa_CuonSach() == product.getMa_CuonSach())
                    cuonSachEntity.setGiabia(product.getGiabia());
            }
            productService.update(cuonSachEntity);
        }
        session.removeAttribute("length_orders");
        session.removeAttribute("tongtien");
        Orders.clear();
        session.setAttribute("Orders", Orders);
        model.addAttribute("sucess", "Đặt hàng thành công");
        int ma_dh = donHangEntity.getMa_DH();
        confirmPayment.ConfirmPayment1(ma_dh, user_email);

        PrintWriter out = response.getWriter();
        out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Loi</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<script>");
        out.println("alert('Đặt hàng thành công')");
        out.println("location.href = \"./home\";");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        return "web/index";

    }
    @RequestMapping("/web/checkout")
    public String Checkout(HttpSession session, ModelMap model) {
        List<CuonSachEntity> cuonSachEntities = new ArrayList<CuonSachEntity>();
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
        if (Orders == null || session.getAttribute("length_orders") == null) {
            model.addAttribute("error", "Bạn chưa có cuốn sách nào trong giỏ hàng");
            return "web/CartDetail";
        } else {
            int length_orders = (int) session.getAttribute("length_orders");
            cuonSachEntities = productService.findAll();
            int check_soluong = 1;
            String errorsoluong = null;
            for (GioHangEntity Order : Orders) {
                for (CuonSachEntity product : cuonSachEntities) {
                    if (Order.getCuonSachEntity().getMa_CuonSach() == product.getMa_CuonSach()) {
                        if (Order.getSoluong() > product.getSoluong()) {
                            check_soluong = 0;
                            errorsoluong = "Sách này:" + Order.getCuonSachEntity().getTen_CuonSach()
                                    + " Vượt quá số lương của kho:" + product.getSoluong() + "";
                            break;
                        }
                    }
                }
            }
            if (length_orders == 0) {
                model.addAttribute("error", "Bạn chưa có cuốn sách nào trong giỏ hàng");
                return "web/CartDetail";
            }
            if (check_soluong == 0) {
                model.addAttribute("cuonSachEntityList", cuonSachEntities);
                model.addAttribute("error", errorsoluong);
                return "web/CartDetail";
            } else {
                return "web/checkout";

            }
        }
    }
}
