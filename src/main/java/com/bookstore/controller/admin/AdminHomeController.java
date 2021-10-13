package com.bookstore.controller.admin;

import com.bookstore.service.ChiTietDonHangService;
import com.bookstore.service.GioHangService;
import com.bookstore.service_impl.ChiTietDonHangService_impl;
import com.bookstore.service_impl.GioHangService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/home")
public class AdminHomeController extends HttpServlet {
    public AdminHomeController(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        request.setAttribute("ColorList_DoanhThuDauSach", ColorList_DoanhThuDauSach);
        request.setAttribute("ColorList_DoanhThu7Ngay", ColorList_DoanhThu7Ngay);


        request.setAttribute("DoanhThu_DauSachList", DoanhThu_DauSachList);
        request.setAttribute("TenDauSachList", TenDauSachList);
        request.setAttribute("NgayList", NgayList);
        request.setAttribute("DoanhThu7NgayList", DoanhThu7NgayList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/index.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
