<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/10/2021
  Time: 5:58 PM
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
<!-- Start header section -->
<div class="content-wrapper">
    <div class="container-fluid">

        <div class="row mt-3">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Chuyên mục: Sửa</div>
                        <hr>

                        <form action="${pageContext.request.contextPath}/admin/order/edit" method="post">
                            <div><b> <span style="color:red"> ${errorMessage}</span></b></div>
                            <div class="form-group">
                                <label for="input-1">Mã đơn hàng</label>
                                <input type="text" class="form-control" readonly id="input-1" placeholder="Mã đơn hàng" name="id" value="${donhang.ma_DH}">
                            </div>
                            <div class="form-group">
                                <label for="input-2">Mã khách hàng</label>
                                <input type="text" class="form-control" readonly id="input-2" placeholder="Mã khách hàng" name="maKH" value="${donhang.ma_Customer}">
                            </div>
                            <div class="form-group">
                                <label for="input-3">Địa chỉ</label>
                                <input type="text" class="form-control" id="input-3" placeholder="Địa chỉ" name="diachi" value="${donhang.diachi}"required>
                            </div>
                            <div class="form-group">
                                <label for="input-4">Số điện thoại</label>
                                <input type="text" class="form-control" id="input-4" placeholder="Số điện thoại" name="sdt" value="${donhang.sdt}" required>
                            </div>
                            <div class="form-group">
                                <label for="input-5">Ngày đặt</label>
                                <input type="text" class="form-control" readonly id="input-5" placeholder="Ngày đặt" name="ngaydat" value="${donhang.ngaydat}">
                            </div>
                            <div class="form-group">
                                <label for="input-6">Tổng tiền</label>
                                <input type="text" class="form-control" readonly id="input-6" placeholder="Tổng tiền" name="tongtien" value="${donhang.tongtien}">
                            </div>
                            <div class="form-group">
                                <label for="input-7">Tình trạng</label>
                                <input type="text" class="form-control" readonly id="input-7" placeholder="Tình trạng" name="tinhtrang" value="${donhang.activeDH}">
                            </div>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i> <a href="${pageContext.request.contextPath}/admin/order/list">Hủy</a> </button>
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Sửa </button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>

</body>

