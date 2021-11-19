package com.bookstore.controller.admin;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.ProductService;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ProductService_impl;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/admin1/")
public class ProductAdd {
    public ProductAdd(){super();}

    @RequestMapping(value = "product/add", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam(value = "errorMessage", required = false) String message) {
        model.addAttribute("product", new CuonSachEntity());
        model.addAttribute("errorMessage", message);
        return "admin/addcuonsach";

    }

    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("product") CuonSachEntity product,
                         ModelMap model, BindingResult errors) {
        if (product.getMa_DauSach()== null) {
            errors.rejectValue("ma_DauSach", "product", "Vui lòng nhập mã đầu sách!");
        }
        if (product.getTen_CuonSach().toString().trim().length() == 0) {
            errors.rejectValue("ten_CuonSach", "product", "Vui lòng nhập tên sách!");
        }
        if (product.getGiabia() == null) {
            errors.rejectValue("giabia", "product", "Vui lòng nhập giá!");
        }
        if (product.getSoluong()== null) {
            errors.rejectValue("soluong", "product", "Vui lòng nhập số lượng!");
        }
        if (product.getTacgia().toString().trim().length() == 0) {
            errors.rejectValue("tacgia", "product", "Vui lòng nhập tên tác giả!");
        }
        if (product.getAnh_CuonSach().toString().trim().length() == 0) {
            errors.rejectValue("anh_CuonSach", "product", "Vui lòng nhập ảnh!");
        }
        if (product.getDiscount()== null) {
            errors.rejectValue("discount", "product", "Vui lòng nhập discount!");
        }
        if (product.getMota().toString().trim().length() == 0) {
            errors.rejectValue("mota", "product", "Vui lòng nhập mô tả!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/addcuonsach";
        }

        try {
            ProductService productService = new ProductService_impl();
            model.addAttribute("message", "Thêm thành công!");
            productService.save(product);
            return "admin/addcuonsach";
        } catch (Exception e) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addcuonsach";
        }
    }
}
