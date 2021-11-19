package com.bookstore.controller.admin;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao.DonHangDao;
import com.bookstore.dao.ShipperDao;
import com.bookstore.dao_impl.AdminDao_impl;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.dao_impl.ShipperDao_impl;
import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/admin/")
public class QuanLiChiTietDonHangController {
    @Autowired
    ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
    @RequestMapping("chitietdonhang/list")
    public String detailList( ModelMap model) {
        List<ChiTietDonHangEntity> list = chiTietDonHangService.findSpec();
        model.addAttribute("list", list);

        return "admin/viewlistCTDH";
    }

    @RequestMapping("chitietdonhang/details")
    public String Detail(HttpSession session,
                         @RequestParam("DH-id") String donhang_id,
                         ModelMap model) {

        if (session.getAttribute("user_admin") == null){
            return "redirect:/admin/login";
        }
        else {
            int DH_id = Integer.parseInt(donhang_id);

            List<ChiTietDonHangEntity> listdetails = chiTietDonHangService.FindDetails(DH_id);
            model.addAttribute("DH_id", DH_id);
            model.addAttribute("listdetails", listdetails);

            return "admin/viewlistCTDHdetails";
        }
    }
}
