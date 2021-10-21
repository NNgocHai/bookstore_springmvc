<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/10/2021
  Time: 5:36 PM
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
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Đơn hàng</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Mã Đơn</th>
                                    <th scope="col">Mã Khách hàng</th>
                                    <th scope="col">Địa chỉ</th>
                                    <th scope="col">Số điện thoại</th>
<%--                                    <th scope="col">Ngày đặt</th>--%>
                                    <th scope="col">Tổng tiền</th>
                                    <th scope="col">Tình trạng</th>
                                    <th scope="col">Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${donhangList}" var="donhang">
                                    <tr>
                                        <td scope="row">${donhang.ma_DH}</td>
                                        <td>${donhang.ma_Customer}</td>
                                        <td>${donhang.diachi}</td>
                                        <td>${donhang.sdt}</td>
<%--                                        <td>${donhang.ngaydat}</td>--%>
                                        <td>${donhang.tongtien}</td>
                                        <td>${donhang.activeDH}</td>
                                        <td>
                                            <button type="reset" class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/order/delete?id=${donhang.ma_DH}">Xóa</a></button>
                                            <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/order/edit?id=${donhang.ma_DH}">Sửa</a></button>
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
