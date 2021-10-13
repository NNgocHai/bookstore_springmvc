<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/4/2021
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
//    response.setHeader("Pragma" , "no-cache");
//    response.setHeader("Expires" , "0");


    if (session.getAttribute("user_admin") == null){
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="content-wrapper">
    <div class="container-fluid">
        <!--End Row-->


        <div class="row">
            <div class="col-lg-12">
<%--                <button class="add-catalog"><a href="${pageContext.request.contextPath}/admin/user/add">Thêm Customer</a></button>--%>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Customer</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Tên đăng nhập</th>
<%--                                    <th scope="col">MẬt khẩu</th>--%>
                                    <th scope="col">Họ tên</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Số điện thoại</th>
                                    <th scope="col">Ví tiền</th>
                                    <th scope="col">Hàng động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${customerList}" var="customer">
                                    <tr>
                                        <td scope="row">${customer.ma_Customer}</td>
                                        <td>${customer.taikhoan_Customer}</td>
<%--                                        <td>${customer.matkhau_Customer}</td>--%>
                                        <td>${customer.hoten_Customer}</td>
                                        <td>${customer.gmail_Customer}</td>
                                        <td>${customer.sdt_Customer}</td>
                                        <td>${customer.vitien}</td>


                                        <td>
                                            <button type="reset" class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/user/delete?id=${customer.ma_Customer}">Xóa</a></button>
<%--                                            <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/user/edit?id=${customer.ma_Customer}">Sửa</a></button>--%>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>