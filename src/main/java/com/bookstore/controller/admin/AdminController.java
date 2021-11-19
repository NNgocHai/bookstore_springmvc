package com.bookstore.controller.admin;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao.DonHangDao;
import com.bookstore.dao.ShipperDao;
import com.bookstore.dao_impl.AdminDao_impl;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.dao_impl.ShipperDao_impl;
import com.bookstore.entity.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    GioHangService gioHangService= new GioHangService_impl();
    @Autowired
    AdminDao adminDao = new AdminDao_impl();
    @Autowired
    ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();


    @RequestMapping("home")
    public String Home(ModelMap model) {
        List<Object[]> ReportDoanhThu7Ngay= gioHangService.ReportDoanhThu7Ngay();
        List<Long> DoanhThu7NgayList= new ArrayList<Long>();
        List<String> ColorList_DoanhThu7Ngay= new ArrayList<String>();

        List<String> NgayList= new ArrayList<String>();
        for(Object[] objects: ReportDoanhThu7Ngay) {
            String pattern = "MM/dd/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            String convert = df.format(objects [0]);
            NgayList.add("'"+ convert + "'");
            DoanhThu7NgayList.add((Long) objects[1]);
            ColorList_DoanhThu7Ngay.add("`rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}`,");

        }


        List<Object[]> ReportDoanhThu_DauSach= chiTietDonHangService.ReportDoanhThu_DauSach();
        List<Long> DoanhThu_DauSachList= new ArrayList<Long>();
        List<String> TenDauSachList= new ArrayList<String>();
        List<String> ColorList_DoanhThuDauSach= new ArrayList<String>();

        for(Object[] objects: ReportDoanhThu_DauSach) {
            TenDauSachList.add("'"+(String) objects[1] + "'");
            DoanhThu_DauSachList.add((Long) objects[2]);
            ColorList_DoanhThuDauSach.add("`rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}`,");

        }
        model.addAttribute("ColorList_DoanhThuDauSach", ColorList_DoanhThuDauSach);
        model.addAttribute("ColorList_DoanhThu7Ngay", ColorList_DoanhThu7Ngay);


        model.addAttribute("DoanhThu_DauSachList", DoanhThu_DauSachList);
        model.addAttribute("TenDauSachList", TenDauSachList);
        model.addAttribute("NgayList", NgayList);
        model.addAttribute("DoanhThu7NgayList", DoanhThu7NgayList);

        return "admin/index";
    }
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String Login(){

        return "admin/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String Login(@RequestParam("user") String user,
                         @RequestParam("password") String password,
                         ModelMap model,
                         HttpSession session) {

        boolean a = adminDao.checkAdminLogin(user,password);
        if (a) {
            session.setAttribute("user_admin", user);
            session.setAttribute("password_admin", password);
            return "redirect:/admin/home";
        } else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            return "admin/login";
        }

    }
    @RequestMapping("logout")
    public String Logout(HttpSession session){

        session.removeAttribute("user_admin");
        session.removeAttribute("password_admin");

        session.invalidate();

        return "admin/login";
    }

}
