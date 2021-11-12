package com.bookstore.controller.admin;

import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.service.ChiTietDonHangService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class CTDHViewList {

    @RequestMapping("chitietdonhang/list")
    public String detailList(ModelMap model) {
        ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
        List<ChiTietDonHangEntity> list = chiTietDonHangService.findSpec();
        model.addAttribute("list", list);

        return "admin/viewlistCTDH";
    }
}
