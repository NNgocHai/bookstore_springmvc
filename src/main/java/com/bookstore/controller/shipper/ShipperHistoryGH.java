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
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class ShipperHistoryGH {
    @RequestMapping("/shipper/history")
    public String ShipperHistoryGH(HttpSession session, ModelMap model){
        try {
            String user = (String)session.getAttribute("user_shipper");
            ShipperService shipperService = new ShipperService_impl();
            ShipperEntity shipperEntity = new ShipperEntity();
            List<ShipperEntity> listShipper = shipperService.findByUser(user);
            shipperEntity = listShipper.get(0);
            int id = shipperEntity.getMa_Shipper();
            GiaoHangService giaoHangService = new GiaoHangService_impl();
            GiaoHangEntity giaoHangEntity = new GiaoHangEntity();
            List<GiaoHangEntity> list = giaoHangService.findID_DG(id);
            if(!(list.size() == 0)){
                model.addAttribute("list", list);
                return "shipper/viewhistoryDH";
            } else {
                return "redirect:/shipper/emptyhistory";
            }
        }
        catch(Exception e)
        {
            return "redirect:/shipper/login";
        }
    }
}
