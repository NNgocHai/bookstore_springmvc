package com.bookstore.controller.shipper;

import com.bookstore.entity.GiaoHangEntity;
import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.GiaoHangService;
import com.bookstore.service.ShipperService;
import com.bookstore.service_impl.GiaoHangService_impl;
import com.bookstore.service_impl.ShipperService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/shipper/history")
public class ShipperHistoryGH extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user_shipper");
        ShipperService shipperService = new ShipperService_impl();
        ShipperEntity shipperEntity = new ShipperEntity();
        try {
            List<ShipperEntity> listShipper = shipperService.findByUser(user);
            shipperEntity = listShipper.get(0);
            int id = shipperEntity.getMa_Shipper();
            GiaoHangService giaoHangService = new GiaoHangService_impl();
            GiaoHangEntity giaoHangEntity = new GiaoHangEntity();
            List<GiaoHangEntity> list = giaoHangService.findID_DG(id);
            if(!(list.size() == 0)){
                request.setAttribute("list", list);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/shipper/viewhistoryDH.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/shipper/emptyhistory");
            }
        }
        catch(Exception e)
        {
            response.sendRedirect(request.getContextPath() + "/shipper/login");
        }
    }
}
