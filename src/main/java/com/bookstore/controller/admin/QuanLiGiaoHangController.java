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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/admin/")
public class QuanLiGiaoHangController {
    @Autowired
    ShipperDao shipperDao = new ShipperDao_impl();
    @Autowired
    DonHangDao donHangDao = new DonHangDao_impl();
    @Autowired
    GiaoHangService giaoHangService= new GiaoHangService_impl();
    @Autowired
    ShipperService shipperService = new ShipperService_impl();

    @RequestMapping("giaohang/empty")
    public String GiaoHang(ModelMap model) {
        return "admin/emptyDH";

    }
    @RequestMapping(value = "giaohang/list", method = RequestMethod.GET)
    public String GiaoHangList(ModelMap model) {

        List<GiaoHangEntity> list = giaoHangService.findTT_GH();
        model.addAttribute("list", list);
        return "admin/viewlistGH";
    }
    @RequestMapping(value = "giaohang/phancong", method = RequestMethod.GET)
    public String PhanCong(ModelMap model) {
        List<ShipperEntity> listSP = shipperDao.findAll();
        model.addAttribute("listSP", listSP);
        List<DonHangEntity> listDHCG = donHangDao.Find_DHCG();
        model.addAttribute("listDHCG", listDHCG);
        if (listDHCG.size() != 0) {
            return "admin/phancongGH";

        } else {
            return "admin/emptyDH";
        }
    }
    @RequestMapping(value = "giaohang/phancong", method = RequestMethod.POST)
    public String PhanCong() {
        return "";
    }
    @RequestMapping("giaohang/phancong_2")
    public String PhanCong_2(ModelMap model, @RequestParam(value = "id", required = false) String id,
                             HttpServletRequest request) {

        if (id == null) {
            List<DonHangEntity> listDHCG = donHangDao.Find_DHCG();
            List<DonHangEntity> listDH_DaChon = new ArrayList<DonHangEntity>();
            for (DonHangEntity donHangEntity : listDHCG) {
                if (request.getParameter(String.valueOf(donHangEntity.getMa_DH())) != null) {
                    listDH_DaChon.add(donHangEntity);
                }
            }
            List<ShipperEntity> shipperEntities = new ArrayList<ShipperEntity>();
            shipperEntities = shipperService.findAll();
            HttpSession session = request.getSession();
            session.setAttribute("listDH_DaChon", listDH_DaChon);
            model.addAttribute("shipperEntities", shipperEntities);

            return "admin/phancongGH_2";

        } else {

            HttpSession session = request.getSession();
            List<DonHangEntity> listDH_DaChon = (List<DonHangEntity>) session.getAttribute("listDH_DaChon");
            for (DonHangEntity donHangEntity : listDH_DaChon) {
                GiaoHangIDKey giaoHangIDKey = new GiaoHangIDKey();
                GiaoHangEntity giaoHangEntity = new GiaoHangEntity();
                giaoHangIDKey.setMa_DH(donHangEntity.getMa_DH());
                giaoHangIDKey.setMa_Shiper(Integer.valueOf(id));
                giaoHangEntity.setId(giaoHangIDKey);
                giaoHangService.save(giaoHangEntity);
            }
            session.removeAttribute("listDH_DaChon");
            return "/admin/phancongGH";

        }
    }
}
