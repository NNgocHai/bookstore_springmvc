package com.bookstore.controller.admin;

import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.service.ChiTietDonHangService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class CTDHViewDetails {

    @RequestMapping("chitietdonhang/details")
    public String doGet(HttpSession session,
                        @RequestParam("DH-id") String donhang_id,
                        @RequestParam(value = "tenKH", required = false) String ten_KH,
                        ModelMap model) {

        if (session.getAttribute("user_admin") == null){
            return "redirect:/admin/login";
        }
        else {
            int DH_id = Integer.parseInt(donhang_id);

            ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
            List<ChiTietDonHangEntity> listdetails = chiTietDonHangService.FindDetails(DH_id);
            model.addAttribute("DH_id", DH_id);
            model.addAttribute("listdetails", listdetails);

            return "admin/viewlistCTDHdetails";
        }
    }

}
