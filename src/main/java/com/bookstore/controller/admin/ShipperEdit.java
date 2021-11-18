package com.bookstore.controller.admin;

import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.PrintWriter;


@Controller
@RequestMapping("/admin/")
public class ShipperEdit {
    public ShipperEdit() {
        super();
    }

    ShipperService shipperService = new ShipperService_impl();

    @RequestMapping(value = "ship/edit", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam(value = "shipper_id", required = false) String shipper_id) {
        int id = Integer.parseInt(shipper_id);
        ShipperEntity shipper = shipperService.findById(id);
        model.addAttribute("shipper", shipper);
        return "admin/editshipper";
    }

    @RequestMapping(value = "ship/edit", method = RequestMethod.POST)
    public String doPost(ModelMap model, @RequestParam(value = "id", required = false) String shipper_id,
                         @RequestParam(value = "shipper_username", required = false) String shipper_username,
                         @RequestParam(value = "shipper_password", required = false) String shipper_password,
                         @RequestParam(value = "shipper_name", required = false) String shipper_name,
                         @RequestParam(value = "shipper_gmail", required = false) String shipper_gmail,
                         PrintWriter out) {


        try {
            if (!shipper_id.equals("") && (!shipper_username.equals("") && (!shipper_password.equals("")) && (!shipper_name.equals("")) && (!shipper_gmail.equals("")))) {
                ShipperEntity shipperEntity = new ShipperEntity();
                shipperEntity.setMa_Shipper(Integer.parseInt(shipper_id));
                shipperEntity.setTaikhoan_Shipper(shipper_username);
                shipperEntity.setGmail_Shipper(shipper_gmail);
                shipperEntity.setHoten_Shipper(shipper_name);
                shipperEntity.setMatkhau_Shipper(shipper_password);
                shipperService.update(shipperEntity);
                return "redirect:/admin/ship/list";
            } else {
                model.addAttribute("errorMessage", "Vui lòng điền đầy đủ các thông tin");
                model.addAttribute("id", shipper_id);
                model.addAttribute("shipper_username", shipper_username);
                model.addAttribute("shipper_password", shipper_password);
                model.addAttribute("shipper_name", shipper_name);
                model.addAttribute("shipper_gmail", shipper_gmail);
                return "admin/editshipper";

            }
        } catch (Exception e) {
            out.flush();
            out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Trung tai khoan hoac gmail hoac so dien thoai')");
            out.println("location.href = './edit?id=" + shipper_id + "';");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        return "redirect:/admin/ship/list";
    }
}
