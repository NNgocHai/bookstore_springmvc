package com.bookstore.controller.admin;

import com.bookstore.entity.DonHangEntity;
import com.bookstore.service.DonHangService;
import com.bookstore.service_impl.DonHangService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/")
public class OrderEdit {
    public OrderEdit(){super();}

    DonHangService donHangService=new DonHangService_impl();

    @RequestMapping(value = "order/edit", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam("order_id") String order_id) {

        DonHangEntity donHangEntity = donHangService.findById(Integer.parseInt(order_id));
        model.addAttribute("order", donHangEntity);
        return "admin/editdonhang";
    }
    @RequestMapping(value = "order/edit", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("order") DonHangEntity order,
                         ModelMap model, BindingResult errors, @RequestParam("order_id") String order_id) {
        if (order.getMa_Customer() == null) {
            errors.rejectValue("ma_Customer", "order", "Vui lòng nhập mã khách hàng!");
        }
        order.setMa_DH(Integer.parseInt(order_id));
        if (order.getDiachi().toString().trim().length() == 0) {
            errors.rejectValue("diachi", "order", "Vui lòng nhập địa chỉ!");
        }
        if (order.getSdt() == null) {
            errors.rejectValue("sdt", "order", "Vui lòng nhập số điện thoại!");
        }
        if (order.getNgaydat() == null) {
            errors.rejectValue("ngaydat", "order", "Vui lòng nhập ngày đặt đơn hàng!");
        }
        if (order.getTongtien() == null) {
            errors.rejectValue("tongtien", "order", "Vui lòng nhập tổng số tiền!");
        }
        if (order.getActiveDH() == null) {
            errors.rejectValue("activeDH", "order", "Vui lòng nhập tình trạng đơn hàng!");
        }

        if (errors.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/editdonhang";
        }

        try {
            DonHangService donHangService = new DonHangService_impl();
            model.addAttribute("message", "Cập nhật thành công!");
            donHangService.update(order);
            return "admin/editdonhang";
        } catch (Exception e) {
            model.addAttribute("order", order);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editdonhang";
        }
    }
//    @RequestMapping(value = "order/edit", method = RequestMethod.POST)
//    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=UTF-8");
//
//        int id =Integer.parseInt(request.getParameter("id"));
//
//        int maKH = Integer.parseInt(request.getParameter("maKH"));
//        String diachi = request.getParameter("diachi");
//        String sdt = request.getParameter("sdt");
//        Timestamp ngaydat = Timestamp.valueOf(request.getParameter("ngaydat"));
//        int tongtien = Integer.parseInt(request.getParameter("tongtien"));
//        String tinhtrang = request.getParameter("tinhtrang");
//
//        DonHangEntity donHangEntity = new DonHangEntity();
//        donHangEntity.setMa_Customer(maKH);
//        donHangEntity.setMa_DH(id);
//        donHangEntity.setDiachi(diachi);
//        donHangEntity.setNgaydat(ngaydat);
//        donHangEntity.setSdt(sdt);
//        donHangEntity.setTongtien(tongtien);
//        donHangEntity.setActiveDH(tinhtrang);
//        donHangService.update(donHangEntity);
//        response.sendRedirect(request.getContextPath() + "/admin/order/list");
//
//    }
}
