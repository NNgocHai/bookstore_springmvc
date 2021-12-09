package com.bookstore.controller.admin;


import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.entity.CategoryEntity;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/admin/")
public class QuanLiCuonSachController {
    @Autowired
    ProductService productService = new ProductService_impl();
    @Autowired
    NavigationDao_impl<CuonSachEntity> navigationDaoImpl = new NavigationDao_impl<CuonSachEntity>();
    @Autowired
    ServletContext context;
    @Autowired
    CategoryService categoryService = new CategoryService_impl();

    @RequestMapping(value = "product/add", method = RequestMethod.GET)
    public String ProductAdd(ModelMap model, @ModelAttribute(value = "message") String message) {
        model.addAttribute("product", new CuonSachEntity());
        model.addAttribute("message", message);
        return "admin/addcuonsach";

    }

    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    public String ProductAdd(@ModelAttribute("product") CuonSachEntity product,
                             @RequestParam("image") MultipartFile file,
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

        if (file.isEmpty()) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Vui lòng chọn ảnh !");
            return "admin/addcuonsach";
        } else {
            try {
                String photoPath = context.getRealPath("/")+"template/web/images/products/img-test/" + file.getOriginalFilename();
                file.transferTo(new File(photoPath));
                product.setAnh_CuonSach(file.getOriginalFilename());
            } catch (Exception e) {
                model.addAttribute("product", product);
                model.addAttribute("message", "Lỗi lưu file !");
                return "admin/addcuonsach";
            }
        }
        try {
            productService.save(product);
            model.addAttribute("message", "Thêm thành công!");
            return "redirect:/admin/product/add";
        } catch (Exception e) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addcuonsach";
        }
    }
    @RequestMapping(value = "product/delete", method = RequestMethod.GET)
    public String ProductDelete(ModelMap model, @RequestParam("cuonsach_id") String cuonsach_id) {

        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(Integer.parseInt(cuonsach_id));
            productService.deleteList(listId);
//            model.addAttribute("cuonsachList",productService.findAll());
            model.addAttribute("message", "Xóa thành công");
            return "redirect:/admin/product/list";
        }
        catch (Exception e){
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/product/list";
        }

    }

    @RequestMapping(value = "product/edit", method = RequestMethod.GET)
    public String ProductEdit(ModelMap model, @RequestParam("cuonsach_id") String id) {

        CuonSachEntity cuonSachEntity=productService.findById(Integer.parseInt(id));
        model.addAttribute("product",cuonSachEntity);
        return "admin/editcuonsach";
    }

    @RequestMapping(value = "product/edit", method = RequestMethod.POST)
    public String ProductEdit(@ModelAttribute("product") CuonSachEntity product,
                              @RequestParam("image") MultipartFile file,
                              ModelMap model, BindingResult errors,
                              @RequestParam("cuonsach_id") String cuonsach_id) {
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
        if (product.getDiscount()== null) {
            errors.rejectValue("discount", "product", "Vui lòng nhập discount!");
        }
        if (product.getMota().toString().trim().length() == 0) {
            errors.rejectValue("mota", "product", "Vui lòng nhập mô tả!");
        }
        if (errors.hasErrors()) {
            CuonSachEntity productfrDB = productService.findById(product.getMa_CuonSach());
            product.setAnh_CuonSach(productfrDB.getAnh_CuonSach());
            model.addAttribute("product", product);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/editcuonsach";
        }

        if (file.isEmpty()) {
            product = productService.findById(product.getMa_CuonSach());
            model.addAttribute("product", product);
            model.addAttribute("message", "Vui lòng chọn ảnh !");
            return "admin/editcuonsach";
        } else {
            try {
                String photoPath = context.getRealPath("/")+"template/web/images/products/img-test/" + file.getOriginalFilename();
                file.transferTo(new File(photoPath));
                product.setAnh_CuonSach(file.getOriginalFilename());
            } catch (Exception e) {
                model.addAttribute("product", product);
                model.addAttribute("message", "Lỗi lưu file !");
                return "admin/editcuonsach";
            }
        }
        try {
            productService.update(product);
            model.addAttribute("message", "Cập nhật thành công!");
            return "admin/editcuonsach";
        } catch (Exception e) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editcuonsach";
        }
    }
    @RequestMapping(value = "product/list", method = RequestMethod.GET)
    public String ProductList(ModelMap model,
                              @RequestParam(value = "page", required = false ) String page,
                              @ModelAttribute(value = "message") String message) {

        if (page == null)
            page= "1";
        int maxResult = 10;// so bang record load len 1 trang
        int maxNavigationPage = 6;// max so trang hien ra

        navigationDaoImpl.Navigation(Integer.valueOf(page),maxResult,maxNavigationPage );
        model.addAttribute("cuonsachList", navigationDaoImpl.getList());
        model.addAttribute("navigationDaoImpl", navigationDaoImpl);
        model.addAttribute("message",   message);
        return "admin/viewlistcuonsach";
    }
    @ModelAttribute("cates")
    public List<CategoryEntity> getCates(){
        List<CategoryEntity> list = categoryService.findAll();
        return list;
    }
}
