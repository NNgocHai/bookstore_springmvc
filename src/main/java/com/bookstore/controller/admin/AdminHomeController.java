package com.bookstore.controller.admin;

import com.bookstore.service.ChiTietDonHangService;
import com.bookstore.service.GioHangService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;
import com.bookstore.service_impl.GioHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin1/")
public class AdminHomeController{
    public AdminHomeController(){
        super();
    }

    @RequestMapping("home")
    public String doGet(ModelMap model) {
        GioHangService gioHangService= new GioHangService_impl();
        List<Object[]> ReportDoanhThu7Ngay= gioHangService.ReportDoanhThu7Ngay();
        List<Long> DoanhThu7NgayList= new ArrayList<Long>();
        List<String> ColorList_DoanhThu7Ngay= new ArrayList<String>();

        List<String> NgayList= new ArrayList<String>();
        for(Object[] objects: ReportDoanhThu7Ngay) {
            String pattern = "MM/dd/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            String convert = df.format(objects [0]);
            NgayList.add("'"+ convert + "'");
            DoanhThu7NgayList.add((Long) objects[1]);
            ColorList_DoanhThu7Ngay.add("`rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}`,");

        }


        ChiTietDonHangService chiTietDonHangService= new ChiTietDonHangService_impl();
        List<Object[]> ReportDoanhThu_DauSach= chiTietDonHangService.ReportDoanhThu_DauSach();
        List<Long> DoanhThu_DauSachList= new ArrayList<Long>();
        List<String> TenDauSachList= new ArrayList<String>();
        List<String> ColorList_DoanhThuDauSach= new ArrayList<String>();

        for(Object[] objects: ReportDoanhThu_DauSach) {
            TenDauSachList.add("'"+(String) objects[1] + "'");
            DoanhThu_DauSachList.add((Long) objects[2]);
            ColorList_DoanhThuDauSach.add("`rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}`,");

        }
        model.addAttribute("ColorList_DoanhThuDauSach", ColorList_DoanhThuDauSach);
        model.addAttribute("ColorList_DoanhThu7Ngay", ColorList_DoanhThu7Ngay);


        model.addAttribute("DoanhThu_DauSachList", DoanhThu_DauSachList);
        model.addAttribute("TenDauSachList", TenDauSachList);
        model.addAttribute("NgayList", NgayList);
        model.addAttribute("DoanhThu7NgayList", DoanhThu7NgayList);

        return "admin/index";
    }

}
