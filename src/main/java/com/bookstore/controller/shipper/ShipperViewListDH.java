package com.bookstore.controller.shipper;


import com.bookstore.entity.GiaoHangEntity;
import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.GiaoHangService;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.GiaoHangService_impl;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class ShipperViewListDH{
    @RequestMapping(value="/shipper/home",method = RequestMethod.GET)
    public String ShipperViewListDH(ModelMap model, HttpSession session){
        try {
            String user=(String)session.getAttribute("user_shipper");
            ShipperService shipperService = new ShipperService_impl();
            ShipperEntity shipperEntity = new ShipperEntity();
            List<ShipperEntity> listShipper = shipperService.findByUser(user);
            shipperEntity = listShipper.get(0);
            int id = shipperEntity.getMa_Shipper();
            GiaoHangService giaoHangService = new GiaoHangService_impl();
            GiaoHangEntity giaoHangEntity = new GiaoHangEntity();
            List<GiaoHangEntity> list = giaoHangService.findID(id);
            if(!(list.size() == 0)){
                model.addAttribute("list", list);
                return "shipper/viewlistdonhang";
            } else {
                return "redirect:/shipper/finish";
            }
        }
        catch(Exception e)
        {
            return "redirect:/shipper/login";
        }
    }
}
//@WebServlet("/shipper/home")
//public class ShipperViewListDH extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        HttpSession session = request.getSession();
//        String user = (String) session.getAttribute("user_shipper");
//        ShipperService shipperService = new ShipperService_impl();
//        ShipperEntity shipperEntity = new ShipperEntity();
//        try {
//            List<ShipperEntity> listShipper = shipperService.findByUser(user);
//            shipperEntity = listShipper.get(0);
//            int id = shipperEntity.getMa_Shipper();
//            GiaoHangService giaoHangService = new GiaoHangService_impl();
//            GiaoHangEntity giaoHangEntity = new GiaoHangEntity();
//            List<GiaoHangEntity> list = giaoHangService.findID(id);
//            if(!(list.size() == 0)){
//                request.setAttribute("list", list);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/shipper/viewlistdonhang.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                response.sendRedirect(request.getContextPath() + "/shipper/finish");
//            }
//        }
//        catch(Exception e)
//        {
//            response.sendRedirect(request.getContextPath() + "/shipper/login");
//        }
//    }
//}
