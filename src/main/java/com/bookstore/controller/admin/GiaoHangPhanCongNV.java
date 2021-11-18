package com.bookstore.controller.admin;

import com.bookstore.dao.DonHangDao;
import com.bookstore.dao.ShipperDao;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.dao_impl.ShipperDao_impl;
import com.bookstore.entity.DonHangEntity;
import com.bookstore.entity.ShipperEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller

@RequestMapping("/admin/")
public class GiaoHangPhanCongNV {
    public GiaoHangPhanCongNV(){super();}
    @RequestMapping(value = "giaohang/phancong", method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        ShipperDao shipperDao = new ShipperDao_impl();
        List<ShipperEntity> listSP = shipperDao.findAll();
        model.addAttribute("listSP", listSP);
        DonHangDao donHangDao = new DonHangDao_impl();
        List<DonHangEntity> listDHCG = donHangDao.Find_DHCG();
        model.addAttribute("listDHCG", listDHCG);
        if (listDHCG.size() != 0) {
            return "admin/phancongGH";

        } else {
            return "admin/emptyDH";
        }
    }
    @RequestMapping(value = "giaohang/phancong", method = RequestMethod.POST)
    public String doPost() {
        return "";
    }
}
