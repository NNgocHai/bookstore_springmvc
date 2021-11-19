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
    DonHangService donHangService = new DonHangService_impl();
    @Autowired
    CategoryService categoryService = new CategoryService_impl();
    @Autowired
    NavigationDao_impl<CuonSachEntity> navigationDaoImpl = new NavigationDao_impl<CuonSachEntity>();


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

    //--------
    @RequestMapping(value = "/web/product/AddtoCart")
    public String AddtoCart(HttpSession session, HttpServletRequest request, @RequestParam(value = "product-id", required = false) String product_id, @RequestParam(value = "soluong", required = false, defaultValue = "1") Integer soluong) {
        int n = 0;
        int tongtien = 0;


        if (session.getAttribute("person") == null) {
            if (product_id != null) {
                CuonSachEntity product = productService.findById(Integer.parseInt(product_id));

                if (product != null) {
                    if (session.getAttribute("Orders") == null) {
                        List<GioHangEntity> Orders = new ArrayList<GioHangEntity>();
                        GioHangEntity Order = new GioHangEntity();
                        Order = gioHangService.GetOrder(productService.findById(Integer.parseInt(product_id)));
                        double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                        Order.getCuonSachEntity().setGiabia((int) db);
                        Order.setSoluong(soluong);
                        tongtien = Order.getCuonSachEntity().getGiabia();
                        Orders.add(Order);
                        n = Orders.size();
                        session.setAttribute("length_orders", n);
                        session.setAttribute("Orders", Orders);
                        session.setAttribute("tongtien", tongtien);
                    } else {
                        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
                        boolean check = false;
                        for (GioHangEntity Order : Orders) {
                            if (Order.getCuonSachEntity().getMa_CuonSach() == Integer.parseInt(product_id)) {
                                Order.setSoluong(Order.getSoluong() + soluong);
                                double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                                Order.getCuonSachEntity().setGiabia((int) db);
                                check = true;
                            }
                            tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;

                        }
                        if (check == false) {
                            GioHangEntity Order = new GioHangEntity();
                            Order = gioHangService.GetOrder(productService.findById(Integer.parseInt(product_id)));
                            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                            Order.getCuonSachEntity().setGiabia((int) db);
                            Order.setSoluong(soluong);
                            tongtien = Order.getCuonSachEntity().getGiabia() + tongtien;
                            Orders.add(Order);
                        }
                        n = Orders.size();
                        session.setAttribute("length_orders", n);
                        session.setAttribute("Orders", Orders);
                        session.setAttribute("tongtien", tongtien);
                    }
                }
                return "redirect:" + request.getHeader("Referer");
            } else {
                return "redirect:" + request.getHeader("Referer");
            }
        } else {
            if (product_id != null) {

                CustomerEntity person = (CustomerEntity) session.getAttribute("person");


                CuonSachEntity product = productService.findById(Integer.parseInt(product_id));


                GioHangIDKey gioHangIDKey = new GioHangIDKey();
                GioHangEntity gioHangEntity = new GioHangEntity();
                List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
                boolean check = false;
                if (product != null) {
                    for (GioHangEntity Order : Orders) {
                        if (Order.getCuonSachEntity().getMa_CuonSach() == Integer.parseInt(product_id)) {
                            Order.setSoluong(Order.getSoluong() + soluong);
                            gioHangIDKey.setMa_Customer(person.getMa_Customer());
                            gioHangIDKey.setMa_CuonSach(Integer.valueOf(product_id));
                            Order.setId(gioHangIDKey);
                            gioHangService.update(Order);
                            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                            Order.getCuonSachEntity().setGiabia((int) db);
                            check = true;
                        }
                        tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                    }
                    if (check == false) {
                        gioHangEntity.setSoluong(soluong);
                        gioHangIDKey.setMa_Customer(person.getMa_Customer());
                        gioHangIDKey.setMa_CuonSach(Integer.valueOf(product_id));
                        gioHangEntity.setId(gioHangIDKey);
                        gioHangService.save(gioHangEntity);


                        gioHangEntity = gioHangService.GetOrder(productService.findById(Integer.parseInt(product_id)));
                        double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
                        gioHangEntity.setCuonSachEntity(product);
                        gioHangEntity.getCuonSachEntity().setGiabia((int) db);
                        gioHangEntity.setId(gioHangIDKey);
                        gioHangEntity.setSoluong(soluong);
                        tongtien = gioHangEntity.getCuonSachEntity().getGiabia() + tongtien;
                        Orders.add(gioHangEntity);


                    }
                    n = Orders.size();
                    session.setAttribute("length_orders", n);
                    session.setAttribute("Orders", Orders);
                    session.setAttribute("tongtien", tongtien);
                }
                return "redirect:" + request.getHeader("Referer");
            } else {
                return "redirect:" + request.getHeader("Referer");
            }
        }

    }

    @RequestMapping("/web/product/CartDetail")
    public String CartDetail(ModelMap model) {
        List<CuonSachEntity> cuonSachEntityList = new ArrayList<CuonSachEntity>();
        cuonSachEntityList = productService.findAll();
        model.addAttribute("cuonSachEntityList", cuonSachEntityList);
        return "web/CartDetail";
    }

    @RequestMapping("/web/checkout")
    public String Checkout(HttpSession session, ModelMap model) {
        List<CuonSachEntity> cuonSachEntities = new ArrayList<CuonSachEntity>();

        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
        if (Orders == null) {
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
                            errorsoluong = "Sách này:" + Order.getCuonSachEntity().getTen_CuonSach() + " Vượt quá số lương của kho:" + product.getSoluong() + "";
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

    @RequestMapping("/web/contact")
    public String Contact() {
        return "/web/contact";
    }

    @RequestMapping("/web/product/DeletetoCart")
    public String DeletetoCart(HttpSession session, @RequestParam("index") Integer index, @RequestParam("ma_CuonSach") Integer ma_CuonSach) {


        int tongtien = 0;
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");

        CustomerEntity person = (CustomerEntity) session.getAttribute("person");
        if (person != null) {
            gioHangService.DeletebyCustomer_CuonSach(person.getMa_Customer(), ma_CuonSach);
            Orders = gioHangService.FindByMaCustomer(person.getMa_Customer());
            for (GioHangEntity Order : Orders) {
                double db = (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getGiabia())) * (1 - (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getDiscount())) / 100)));
                Order.getCuonSachEntity().setGiabia((int) db);
            }
        } else
            Orders.remove(index);
        for (
                GioHangEntity Order : Orders) {
            tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
        }

        int n = Orders.size();
        session.setAttribute("length_orders", n);
        session.setAttribute("Orders", Orders);
        session.setAttribute("tongtien", tongtien);
        return "redirect:/web/product/CartDetail";
    }

    @RequestMapping("/web/error404")
    public String error404() {
        return "/web/404";

    }

    @RequestMapping("/web/introduce")
    public String Introduce() {
        return "/web/introduce";
    }

    @RequestMapping(value = "/web/login", method = RequestMethod.GET)
    public String WebLoginGet() {
        return "web/login";
    }

    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public String WebLoginPost(HttpSession session, ModelMap model, @RequestParam("user") String user, @RequestParam("password") String password) {
        List<CustomerEntity> customerServices = new ArrayList<CustomerEntity>();
        List<GioHangEntity> gioHangEntities = new ArrayList<GioHangEntity>();
        GioHangEntity gioHangEntity = new GioHangEntity();
        GioHangIDKey gioHangIDKey = new GioHangIDKey();
        CustomerDao customerDao = new CustomerDao_impl();
        /*        try {*/
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

    @RequestMapping("/web/payhelp")
    public String PayHelp() {
        return "web/payhelp";
    }


    @RequestMapping(value = "/web/payment", method = RequestMethod.POST)
    public String Payment(@RequestParam("address") String address,
                          @RequestParam("phone") String phone,
                          @RequestParam("email") String user_email,
                          HttpSession session,
                          ModelMap model) {


        DonHangEntity donHangEntity = new DonHangEntity();
        CuonSachEntity cuonSachEntity = new CuonSachEntity();
        List<CuonSachEntity> cuonSachEntities = new ArrayList<CuonSachEntity>();
        cuonSachEntities = productService.findAll();

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

        Boolean isCheck = true;

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
                chuoi += "&&amount_" + i + "=" + (Order.getCuonSachEntity().getGiabia() / 24000);
                i++;
            }

            return "redirect:https://www.sandbox.paypal.com/cgi-bin/webscr?" + chuoi;
        }

        Orders.clear();
        session.setAttribute("Orders", Orders);

        model.addAttribute("sucess", "Đặt hàng thành công");
        int ma_dh = donHangEntity.getMa_DH();
        ConfirmPayment confirmPayment = new ConfirmPayment(ma_dh, user_email);

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


    @RequestMapping("/web/paysuccess")
    public String PayPalSuccess(HttpSession session,
                                ModelMap model) {
        List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");

        Orders.clear();
        session.setAttribute("Orders", Orders);

        model.addAttribute("sucess", "Đặt hàng và thanh toán thành công");

        return "web/checkout";

    }

    @RequestMapping("/web/product/Cate")
    public String ProductCate(@RequestParam("Cate") String Cate,
                        ModelMap model) {
        if (Cate == null)
            Cate = "";

        List<CategoryEntity> categoryList = categoryService.findAll();

        List<CuonSachEntity> productList = productService.FindByCate(Integer.parseInt(Cate));
        List<CuonSachEntity> productList_km = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent_km = new ArrayList<CuonSachEntity>();

        productListCurrent = productService.findAll();


        for (CuonSachEntity product : productListCurrent) {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService.findById(product.getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
            product_km.setGiabia((int) db);
            productListCurrent_km.add(product_km);

        }


        for (CuonSachEntity product : productList) {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService.findById(product.getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
            product_km.setGiabia((int) db);
            productList_km.add(product_km);

        }
        model.addAttribute("productListCurrent_km", productListCurrent_km);
        model.addAttribute("productListCurrent", productListCurrent);
        model.addAttribute("productList", productList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList_km", productList_km);

        return "web/productlist";

    }


    @RequestMapping("/web/productDetail")
    public String ProductDetail(@RequestParam("id") String id,
                        @RequestParam("Cate") String Cate,
                        ModelMap model) {
        if (id == null)
            id = "";
        if (Cate == null)
            Cate = "";


        CuonSachEntity product = productService.findById(Integer.parseInt(id));
        CuonSachEntity product_km = productService.findById(product.getMa_CuonSach());
        List<CuonSachEntity> Catee = new ArrayList<>();
        List<CuonSachEntity> Catee_km = new ArrayList<>();
        Catee = productService.FindByCate(Integer.parseInt(Cate));


        for (CuonSachEntity productt : Catee) {
            CuonSachEntity product_kmm = new CuonSachEntity();
            product_kmm = productService.findById(productt.getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(productt.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(productt.getDiscount())) / 100)));
            product_kmm.setGiabia((int) db);
            Catee_km.add(product_kmm);
        }

        Catee.get(0).getCategoryEntity().getTen_DauSach();
        double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
        product_km.setGiabia((int) db);
        model.addAttribute("Catee", Catee);
        model.addAttribute("Catee_km", Catee_km);

        model.addAttribute("product", product);
        model.addAttribute("product_km", product_km);

        return "web/ProductViewDetail";
    }

    @RequestMapping("/web/product")
    public String Product(@RequestParam(value = "page", required = false) String page,
                        ModelMap model) {
        if (page == null)
            page = "1";
        int maxResult = 9;// so bang record load len 1 trang
        int maxNavigationPage = 6;// max so trang hien ra

        List<CategoryEntity> categoryList = categoryService.findAll();
        navigationDaoImpl.Navigation(Integer.valueOf(page), maxResult, maxNavigationPage);

        List<CuonSachEntity> productList_km = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent = new ArrayList<CuonSachEntity>();
        List<CuonSachEntity> productListCurrent_km = new ArrayList<CuonSachEntity>();

        productListCurrent = productService.findAll();
        productListCurrent.get(productListCurrent.size() - 1);

        for (CuonSachEntity product : productListCurrent) {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService.findById(product.getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
            product_km.setGiabia((int) db);
            productListCurrent_km.add(product_km);

        }
        for (CuonSachEntity product : navigationDaoImpl.getList()) {
            CuonSachEntity product_km = new CuonSachEntity();
            product_km = productService.findById(product.getMa_CuonSach());
            double db = (Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount())) / 100)));
            product_km.setGiabia((int) db);
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


            CustomerDao customerDao=new CustomerDao_impl();
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
                SendingEmail se=new SendingEmail(customer_gmail,customer_name,code_string);

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

        @RequestMapping("/web/product/Search")
        public String Search(@RequestParam("TuKhoa") String TuKhoa,
                            ModelMap model){
            if (TuKhoa == null)
                TuKhoa= "";


            List<CategoryEntity> categoryList = categoryService.findAll();

            List<CuonSachEntity> productList = new ArrayList<CuonSachEntity>();
            productList=productService.Search(TuKhoa);

            List<CuonSachEntity> productList_km = new ArrayList<CuonSachEntity>();
            List<CuonSachEntity> productListCurrent = new ArrayList<CuonSachEntity>();
            List<CuonSachEntity> productListCurrent_km = new ArrayList<CuonSachEntity>();

            productListCurrent= productService.findAll();


            for(CuonSachEntity product: productListCurrent)
            {
                CuonSachEntity product_km = new CuonSachEntity();
                product_km = productService.findById(product.getMa_CuonSach());
                double db =(Double.parseDouble(String.valueOf(product.getGiabia())) * (1 - (Double.parseDouble(String.valueOf(product.getDiscount()))/100)));
                product_km.setGiabia((int)db);
                productListCurrent_km.add(product_km);

            }


            for(CuonSachEntity product: productList)
            {
                CuonSachEntity product_km = new CuonSachEntity();
                product_km = productService.findById(product.getMa_CuonSach());
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



        @RequestMapping("/web/product/UpdatetoCart")
        public String doGet() {
            return "web/CartDetail";
        }

        @RequestMapping(value = "/web/product/UpdatetoCart", method = RequestMethod.POST)
        public String doPost(HttpSession session,
                             ModelMap model,
                             HttpServletRequest request){
            int tongtien = 0;

            List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
            if(Orders == null) {
                model.addAttribute("error", "Bạn chưa có cuốn sách nào trong giỏ hàng");

                return "web/CartDetail";
            }
            else {


                CustomerEntity person = (CustomerEntity) session.getAttribute("person");
                if (person != null) {
                    GioHangIDKey gioHangIDKey = new GioHangIDKey();
                    for (GioHangEntity Order : Orders) {
                        int soluong = Integer.parseInt(request.getParameter(String.valueOf(Order.getCuonSachEntity().getMa_CuonSach())));
                        Order.setSoluong(soluong);
                        gioHangIDKey.setMa_Customer(person.getMa_Customer());
                        gioHangIDKey.setMa_CuonSach(Order.getCuonSachEntity().getMa_CuonSach());
                        Order.setId(gioHangIDKey);
                        gioHangService.update(Order);

                        tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                    }

                } else {
                    for (GioHangEntity Order : Orders) {
                        int soluong = Integer.parseInt(request.getParameter(String.valueOf(Order.getCuonSachEntity().getMa_CuonSach())));
                        Order.setSoluong(soluong);
                        tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                    }
                }

                int n = Orders.size();
                session.setAttribute("length_orders", n);
                session.setAttribute("Orders", Orders);
                session.setAttribute("tongtien", tongtien);

                return "redirect:/web/product/CartDetail";
            }
        }

}
