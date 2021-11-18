package com.bookstore.controller.admin;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service.ProductService;
import com.bookstore.service_impl.ProductService_impl;
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

@Controller
@RequestMapping("/admin/")
public class ProductEdit {
    public ProductEdit(){super();}
    ProductService productService=new ProductService_impl();

    @RequestMapping(value = "product/edit", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam("cuonsach_id") String id) {

        CuonSachEntity cuonSachEntity=productService.findById(Integer.parseInt(id));
        model.addAttribute("product",cuonSachEntity);
        return "admin/editcuonsach";
    }

    @RequestMapping(value = "product/edit", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("product") CuonSachEntity product,
                         ModelMap model, BindingResult errors, @RequestParam("cuonsach_id") String cuonsach_id) {
        product.setMa_CuonSach(Integer.parseInt(cuonsach_id));
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
            return "admin/editcuonsach";
        }

        try {
            ProductService productService = new ProductService_impl();
            model.addAttribute("message", "Cập nhật thành công!");
            productService.update(product);
            return "admin/editcuonsach";
        } catch (Exception e) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editcuonsach";
        }
    }
}
