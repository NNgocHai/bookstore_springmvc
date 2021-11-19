package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.GioHangEntity;
import com.bookstore.entity.GioHangIDKey;
import com.bookstore.service.CustomerService;
import com.bookstore.service.GioHangService;
import com.bookstore.service.ProductService;
import com.bookstore.service_impl.CustomerService_impl;
import com.bookstore.service_impl.GioHangService_impl;
import com.bookstore.service_impl.ProductService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Web1 {
    @RequestMapping(value = "/web/product/AddtoCart")
    public String AddtoCart(HttpSession session, HttpServletRequest request, @RequestParam(value = "product-id", required = false) String product_id, @RequestParam(value = "soluong", required = false, defaultValue = "1") Integer soluong) {
        int n = 0;
        int tongtien = 0;

        GioHangService gioHangService = new GioHangService_impl();
        ProductService productService = new ProductService_impl();
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
        ProductService productService = new ProductService_impl();
        List<CuonSachEntity> cuonSachEntityList = new ArrayList<CuonSachEntity>();
        cuonSachEntityList = productService.findAll();
        model.addAttribute("cuonSachEntityList", cuonSachEntityList);
        return "web/CartDetail";
    }

    @RequestMapping("/web/checkout")
    public String Checkout(HttpSession session, ModelMap model) {
        List<CuonSachEntity> cuonSachEntities = new ArrayList<CuonSachEntity>();
        ProductService productService = new ProductService_impl();

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
        GioHangService gioHangService = new GioHangService_impl();

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
        CustomerService customerService = new CustomerService_impl();
        GioHangService gioHangService = new GioHangService_impl();
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
}

