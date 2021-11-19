package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class ProductController {
    @Autowired
    GioHangService gioHangService =new GioHangService_impl();
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
    @Autowired
    CustomerDao customerDao=new CustomerDao_impl();
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
