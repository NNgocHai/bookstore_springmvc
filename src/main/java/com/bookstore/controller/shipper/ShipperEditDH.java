package com.bookstore.controller.shipper;

import com.bookstore.entity.DonHangEntity;
import com.bookstore.service.DonHangService;
import com.bookstore.service_impl.DonHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;

@Controller
public class ShipperEditDH{
    @RequestMapping(value = "/shipper1/editDH",method = RequestMethod.GET)
    public String ShipperEdit(HttpSession session,@RequestParam(value = "DH-id",required = false) String id)
    {
        try{
            String user=(String) session.getAttribute("user_shipper");
            DonHangService donHangService=new DonHangService_impl();
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
}

