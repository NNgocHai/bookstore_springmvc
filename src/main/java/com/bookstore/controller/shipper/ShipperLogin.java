package com.bookstore.controller.shipper;

import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.ShipperService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
public class ShipperLogin{
    @RequestMapping(value = "/shipper1/login",method=RequestMethod.GET)
    public String ShipperLogin()
    {
        return "/shipper/login";
    }
    @RequestMapping(value = "/shipper1/login",method = RequestMethod.POST)
    public String ShipperLoginPost(ModelMap model,HttpSession session,@RequestParam("user") String user,@RequestParam("password") String password)
    {
        ShipperService shipper = new ShipperService_impl();
        if (shipper.checkShipperLogin(user,password)) {
            model.addAttribute("user_shipper",user);
            session.setAttribute("user_shipper", user);
            session.setAttribute("password_shipper", password);
            return "redirect:/shipper/home";
        } else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            return "shipper/login";
        }
    }
}
//@WebServlet("/shipper/login")
//public class ShipperLogin extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/shipper/login.jsp");
//        dispatcher.forward(request, response);
//    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=UTF-8");
//        String user = request.getParameter("user");
//        String password = request.getParameter("password");
//        ShipperService shipper = new ShipperService_impl();
//        if (shipper.checkShipperLogin(user,password)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user_shipper", user);
//            session.setAttribute("password_shipper", password);
//            response.sendRedirect(request.getContextPath() + "/shipper/home");
//        } else {
//            request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
//            RequestDispatcher rd = request.getRequestDispatcher("/views/shipper/login.jsp");
//            rd.forward(request, response);
//        }
//    }
//}
