package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.email.ConfirmPayment;
import com.bookstore.email.SendingEmail;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class WebController {
    @RequestMapping("/")
    public String index() {
        return "redirect:web/home";
    }

    @Autowired
    SendingEmail sendingEmail=new SendingEmail();
    @Autowired
    ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
    @Autowired
    ReviewService reviewService = new ReviewService_impl();
    @Autowired
    GioHangService gioHangService = new GioHangService_impl();
    @Autowired
    ProductService productService = new ProductService_impl();
    @Autowired
    CustomerService customerService = new CustomerService_impl();
    @Autowired
    CustomerDao customerDao=new CustomerDao_impl();
    @RequestMapping("/web/home")
    public String WebHome(ModelMap model) {
        List<ChiTietDonHangEntity> product_hotList = new ArrayList<ChiTietDonHangEntity>();
        List<Object[]> product_hotListt = chiTietDonHangService.FindHot();
        List<ChiTietDonHangEntity> product_hotListtt = chiTietDonHangService.findAll();
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
            product_hotkm = productService.findById(product_hot.getCuonSachEntity().getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(product_hot.getCuonSachEntity().getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product_hot.getCuonSachEntity().getDiscount())) / 100)));
            product_hotkm.setGiabia((int) db);
            productList_km.add(product_hotkm);
        }
        List<CuonSachEntity> cuon = productService.FindHotDiscount();
        cuon.get(0).getDiscount();
        model.addAttribute("reviewEntities", reviewEntities);
        model.addAttribute("productList_km", productList_km);
        model.addAttribute("productListHotDiscount", productService.FindHotDiscount());
        model.addAttribute("product_hotList", product_hotList);

        return "web/index";
    }

    @RequestMapping("/web/contact")
    public String Contact() {
        return "/web/contact";
    }
    @RequestMapping("/web/error404")
    public String error404() {
        return "/web/404";
    }
    @RequestMapping("/web/introduce")
    public String Introduce() {
        return "/web/introduce";
    }
    @RequestMapping(value = "/web/register", method = RequestMethod.GET)
    public String Register() {
        return "web/register";
    }

    @RequestMapping(value = "/web/register", method = RequestMethod.POST)
    public String Register(@RequestParam("username") String customer_tk,
                           @RequestParam("password") String customer_password,
                           @RequestParam("name") String customer_name,
                           @RequestParam("email") String customer_gmail,
                           @RequestParam("sdt") String customer_sdt,
                           HttpSession session,
                           ModelMap model) {
        int customer_vitien = 1000000;


        boolean a= customerDao.checkAddCustomer(customer_tk,customer_gmail,customer_sdt);
        boolean gmail_check=customerDao.checkGmail(customer_gmail);
        boolean sdt_check=customerDao.checkSdt(customer_sdt);
        boolean taikhoan_check=customerDao.checkUserName(customer_tk);

        if (a)
        {
            session.setAttribute("taikhoan_dk",customer_tk);
            session.setAttribute("gmail_dk",customer_gmail);
            session.setAttribute("ten_dk",customer_name);
            session.setAttribute("matkhau_dk",customer_password);
            session.setAttribute("sdt_dk",customer_sdt);
            session.setAttribute("vitien_dk",customer_vitien);

            //create code
            Random random=new Random();
            int  code=random.nextInt(999999);
            String code_string=String.valueOf(code);

            session.setAttribute("code_dk",code_string);
            sendingEmail.SendingEmail1(customer_gmail,customer_name,code_string);

            return "web/verification";
        } else {
            model.addAttribute("errMessage", "Tạo tài khoản thất bại. Hãy thử lại !!!");
            model.addAttribute("taikhoan",customer_tk);
            model.addAttribute("matkhau",customer_password);
            model.addAttribute("hoten",customer_name);
            model.addAttribute("gmail",customer_gmail);
            model.addAttribute("sdt",customer_sdt);

            if (gmail_check==false)
            {
                model.addAttribute("errGmail","Email đã tồn tại");
            }
            if(sdt_check==false)
            {
                model.addAttribute("errSdt","Số điện thoại đã tồn tại");
            }
            if(taikhoan_check==false)
            {
                model.addAttribute("errTaikhoan","Tên tài khoản đã tồn tại");
            }

            return "web/register";
        }

    }

    @RequestMapping(value = "/web/login", method = RequestMethod.GET)
    public String WebLogin() {
        return "web/login";
    }

    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public String WebLogin(HttpSession session, ModelMap model, @RequestParam("user") String user, @RequestParam("password") String password) {
        List<CustomerEntity> customerServices = new ArrayList<CustomerEntity>();
        List<GioHangEntity> gioHangEntities = new ArrayList<GioHangEntity>();
        GioHangEntity gioHangEntity = new GioHangEntity();
        GioHangIDKey gioHangIDKey = new GioHangIDKey();
        boolean a = customerDao.checkCustomerLogin(user, password);
        if (a) {
            int n = 0;
            int tongtien = 0;
            customerServices = customerService.findByUser(user);
            int id_customer = customerServices.get(0).getMa_Customer();
            CustomerEntity person = customerService.findById(id_customer);

            session.removeAttribute("taikhoan_dk");
            session.setAttribute("user", user);
            session.setAttribute("password", password);
            session.setAttribute("person", person);
            gioHangEntities = gioHangService.FindByMaCustomer(person.getMa_Customer());
            int IsOrder = 0;
            if (session.getAttribute("length_orders") != null)
                IsOrder = (int) session.getAttribute("length_orders");
            if (IsOrder > 0 && gioHangEntities.size() == 0) {
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
                n = Orders.size();
                session.setAttribute("length_orders", n);
                session.setAttribute("Orders", Orders);
                session.setAttribute("tongtien", tongtien);
            } else {

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
            return "redirect:/web/home";
        } else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            return "web/login";
        }
    }

    @RequestMapping("/web/logout")
    public String Logout(HttpSession session) {
        if (session != null) {
            session.removeAttribute("user");
            session.removeAttribute("length_orders");
            session.removeAttribute("tongtien");
            session.removeAttribute("Orders");
            session.removeAttribute("person");
            session.removeAttribute("password");
        }
        return "redirect:/web/home";
    }

    @RequestMapping("/web/orderhelp")
    public String OrderHelp() {
        return "/web/orderhelp";
    }





}
