package com.bookstore.controller.web;

import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.GioHangEntity;
import com.bookstore.entity.GioHangIDKey;
import com.bookstore.service.CustomerService;
import com.bookstore.service.GioHangService;
import com.bookstore.service_impl.CustomerService_impl;
import com.bookstore.service_impl.GioHangService_impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/web/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/web/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        CustomerService customerService= new CustomerService_impl();
        GioHangService gioHangService= new GioHangService_impl();
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        List<CustomerEntity> customerServices= new ArrayList<CustomerEntity>();
        List<GioHangEntity> gioHangEntities= new ArrayList<GioHangEntity>();
        GioHangEntity gioHangEntity= new GioHangEntity();
        GioHangIDKey gioHangIDKey=new GioHangIDKey();
        CustomerDao customerDao = new CustomerDao_impl();
/*        try {*/
            boolean a = customerDao.checkCustomerLogin(user,password);
            if (a) {
                int n=0;
                int tongtien=0;
                customerServices=customerService.findByUser(user);
                int id_customer = customerServices.get(0).getMa_Customer();
                CustomerEntity person = customerService.findById(id_customer);

                HttpSession session = request.getSession();
                session.removeAttribute("taikhoan_dk");
                session.setAttribute("user", user);
                session.setAttribute("password", password);
                session.setAttribute("person", person);
                gioHangEntities= gioHangService.FindByMaCustomer(person.getMa_Customer());
                int IsOrder=0;
                if(session.getAttribute("length_orders") != null)
                    IsOrder = (int) session.getAttribute("length_orders");
                if ( IsOrder >0 && gioHangEntities.size()==0) {
                    List<GioHangEntity> Orders = (List<GioHangEntity>) session.getAttribute("Orders");
                    for (GioHangEntity Order : Orders) {
                        gioHangIDKey.setMa_CuonSach(Order.getCuonSachEntity().getMa_CuonSach());
                        gioHangIDKey.setMa_Customer(person.getMa_Customer());
                        gioHangEntity.setId(gioHangIDKey);
                       /* gioHangEntity.setCuonSachEntity(Order.getCuonSachEntity());
                        gioHangEntity.setCustomerEntity(person);*/
                        gioHangEntity.setSoluong(Order.getSoluong());
                        gioHangService.save(gioHangEntity);
                        tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;

                    }
                     n=Orders.size();
                    session.setAttribute("length_orders", n);
                    session.setAttribute("Orders", Orders);
                    session.setAttribute("tongtien", tongtien);
                }
                else {

                    for (GioHangEntity Order : gioHangEntities) {
                        double db = (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getGiabia())) * (1 - (Double.parseDouble(String.valueOf(Order.getCuonSachEntity().getDiscount())) / 100)));
                        Order.getCuonSachEntity().setGiabia((int) db);
                        tongtien = Order.getCuonSachEntity().getGiabia() * Order.getSoluong() + tongtien;
                    }
                    n = gioHangEntities.size();
                    session.setAttribute("length_orders", n);
                    session.setAttribute("Orders", gioHangEntities);
                    session.setAttribute("tongtien", tongtien);
                }
                response.sendRedirect(request.getContextPath() + "/web/home");
            } else {
                request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
                RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
                rd.forward(request, response);
            }
/*        }*/
 /*       catch (Exception e)
        {e.printStackTrace();}
*/
    }
}
