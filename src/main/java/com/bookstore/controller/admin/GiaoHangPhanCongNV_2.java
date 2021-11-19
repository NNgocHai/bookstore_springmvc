package com.bookstore.controller.admin;

import com.bookstore.dao.DonHangDao;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.entity.*;
import com.bookstore.service.GiaoHangService;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.GiaoHangService_impl;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
//@WebServlet("/admin/giaohang/phancong_2")
@RequestMapping("/admin1/")
public class GiaoHangPhanCongNV_2 {

    @RequestMapping("giaohang/phancong_2")
    public String doGet(ModelMap model, @RequestParam(value = "id", required = false) String id,
                        HttpServletRequest request) {

        if (id ==null) {
            DonHangDao donHangDao = new DonHangDao_impl();
            List<DonHangEntity> listDHCG = donHangDao.Find_DHCG();
            List<DonHangEntity> listDH_DaChon = new ArrayList<DonHangEntity>();
            for (DonHangEntity donHangEntity : listDHCG) {
                if (request.getParameter(String.valueOf(donHangEntity.getMa_DH())) != null) {
                    listDH_DaChon.add(donHangEntity);
                }
            }
            List<ShipperEntity> shipperEntities = new ArrayList<ShipperEntity>();
            ShipperService shipperService = new ShipperService_impl();
            shipperEntities = shipperService.findAll();
            HttpSession session = request.getSession();
            session.setAttribute("listDH_DaChon", listDH_DaChon);
            model.addAttribute("shipperEntities", shipperEntities);

            return "admin/phancongGH_2";

        } else {

            HttpSession session = request.getSession();
            GiaoHangService giaoHangService = new GiaoHangService_impl();
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

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//    }

//
//    @WebServlet("/admin/giaohang/phancong_2")
//
//    public class GiaoHangPhanCongNV_2 extends HttpServlet {
//        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//            if (request.getParameter("id") ==null) {
//                DonHangDao donHangDao = new DonHangDao_impl();
//                List<DonHangEntity> listDHCG = donHangDao.Find_DHCG();
//                List<DonHangEntity> listDH_DaChon = new ArrayList<DonHangEntity>();
//                for (DonHangEntity donHangEntity : listDHCG) {
//                    if (request.getParameter(String.valueOf(donHangEntity.getMa_DH())) != null) {
//                        listDH_DaChon.add(donHangEntity);
//                    }
//                }
//                List<ShipperEntity> shipperEntities = new ArrayList<ShipperEntity>();
//                ShipperService shipperService = new ShipperService_impl();
//                shipperEntities = shipperService.findAll();
//                HttpSession session = request.getSession();
//                session.setAttribute("listDH_DaChon", listDH_DaChon);
//                request.setAttribute("shipperEntities", shipperEntities);
//
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/phancongGH_2.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                HttpSession session = request.getSession();
//                String id= request.getParameter("id");
//                GiaoHangService giaoHangService = new GiaoHangService_impl();
//                List<DonHangEntity> listDH_DaChon = (List<DonHangEntity>) session.getAttribute("listDH_DaChon");
//                for (DonHangEntity donHangEntity : listDH_DaChon) {
//                    GiaoHangIDKey giaoHangIDKey = new GiaoHangIDKey();
//                    GiaoHangEntity giaoHangEntity = new GiaoHangEntity();
//                    giaoHangIDKey.setMa_DH(donHangEntity.getMa_DH());
//                    giaoHangIDKey.setMa_Shiper(Integer.valueOf(id));
//                    giaoHangEntity.setId(giaoHangIDKey);
//                    giaoHangService.save(giaoHangEntity);
//                }
//                session.removeAttribute("listDH_DaChon");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/giaohang/phancong");
//                dispatcher.forward(request, response);
//            }
//        }
//
//        @Override
//        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//        }
}
