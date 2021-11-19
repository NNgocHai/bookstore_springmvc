package com.bookstore.controller.shipper;

import com.bookstore.entity.DonHangEntity;
import com.bookstore.entity.GiaoHangEntity;
import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.DonHangService;
import com.bookstore.service.GiaoHangService;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.DonHangService_impl;
import com.bookstore.service_impl.GiaoHangService_impl;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShipperController {
    @Autowired
    ShipperService shipperService = new ShipperService_impl();
    @Autowired
    GiaoHangService giaoHangService = new GiaoHangService_impl();
    @Autowired
    DonHangService donHangService=new DonHangService_impl();

    @RequestMapping(value="/shipper/home",method = RequestMethod.GET)
    public String ShipperViewListDH(ModelMap model, HttpSession session){
        try {
            String user=(String)session.getAttribute("user_shipper");
            ShipperEntity shipperEntity = new ShipperEntity();
            List<ShipperEntity> listShipper = shipperService.findByUser(user);
            shipperEntity = listShipper.get(0);
            int id = shipperEntity.getMa_Shipper();
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
    @RequestMapping(value = "/shipper/editDH",method = RequestMethod.GET)
    public String ShipperEdit(HttpSession session, @RequestParam(value = "DH-id",required = false) String id)
    {
        try{
            String user=(String) session.getAttribute("user_shipper");
            int dh_id= Integer.parseInt(id);
            DonHangEntity donHangEntity = donHangService.findById(dh_id);
            donHangEntity.setMa_DH(dh_id);
            donHangEntity.setActiveDH("Đã giao");
            donHangService.update(donHangEntity);
            return "redirect:/shipper/home";
        }
        catch(Exception e){
            return "redirect:/shipper/login";
        }

    }
    @RequestMapping("/shipper/finish")
    public String ShipperFinish1(HttpSession session){
        try{
            String user = (String) session.getAttribute("user_shipper");
            ShipperEntity shipperEntity = new ShipperEntity();
            List<ShipperEntity> listShipper = shipperService.findByUser(user);
            shipperEntity = listShipper.get(0);
            int id = shipperEntity.getMa_Shipper();
            List<GiaoHangEntity> list = giaoHangService.findID(id);
            if (user != null && list.size() != 0) {
                return "redirect:/shipper/home";
            } else {
                return "shipper/finished";
            }
        }
        catch(Exception e)
        {
            return "redirect:/shipper/login";
        }
    }
    @RequestMapping("/shipper/emptyhistory")
    public String ShipperHistoryEmpty(HttpSession session){
        try{
            String user = (String) session.getAttribute("user_shipper");
            ShipperEntity shipperEntity = new ShipperEntity();
            List<ShipperEntity> listShipper = shipperService.findByUser(user);
            shipperEntity = listShipper.get(0);
            int id = shipperEntity.getMa_Shipper();
            List<GiaoHangEntity> list = giaoHangService.findID_DG(id);
            if (user != null && list.size() != 0) {
                return "redirect:/shipper/history";
            } else {
                return "shipper/empty";
            }
        }
        catch(Exception e)
        {
            return "redirect:/shipper/login";
        }
    }
    @RequestMapping("/shipper/history")
    public String ShipperHistoryGH(HttpSession session, ModelMap model){
        try {
            String user = (String)session.getAttribute("user_shipper");
            ShipperEntity shipperEntity = new ShipperEntity();
            List<ShipperEntity> listShipper = shipperService.findByUser(user);
            shipperEntity = listShipper.get(0);
            int id = shipperEntity.getMa_Shipper();
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
    @RequestMapping(value = "/shipper/login",method=RequestMethod.GET)
    public String ShipperLogin()
    {
        return "/shipper/login";
    }
    @RequestMapping(value = "/shipper/login",method = RequestMethod.POST)
    public String ShipperLoginPost(ModelMap model,HttpSession session,@RequestParam("user") String user,@RequestParam("password") String password)
    {
        if (shipperService.checkShipperLogin(user,password)) {
            model.addAttribute("user_shipper",user);
            session.setAttribute("user_shipper", user);
            session.setAttribute("password_shipper", password);
            return "redirect:/shipper/home";
        } else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            return "shipper/login";
        }
    }
    @RequestMapping("/shipper/logout")
    public String ShipperLogout(HttpSession session){
        session.removeAttribute("password_shipper");
        session.removeAttribute("user_shipper");

        session.invalidate();
        return "shipper/login";
    }

}
