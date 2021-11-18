package com.bookstore.controller.admin;

import com.bookstore.entity.GiaoHangEntity;
import com.bookstore.service.GiaoHangService;
import com.bookstore.service_impl.GiaoHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class GiaoHangList {
    public GiaoHangList(){super();}

    @RequestMapping(value = "giaohang/list", method = RequestMethod.GET)
    public String doGet(ModelMap model) {

        GiaoHangService giaoHangService = new GiaoHangService_impl();
        List<GiaoHangEntity> list = giaoHangService.findTT_GH();
        model.addAttribute("list", list);
        return "admin/viewlistGH";
    }

}
