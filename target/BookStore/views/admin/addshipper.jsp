<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/9/2021
  Time: 1:11 PM
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
    <title>Thêm Shipper</title>
</head>
<body>
<div class="content-wrapper">
    <div class="container-fluid">

        <div class="row mt-3">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Thêm Shipper</div>
                        <hr>
                        <form action="${pageContext.request.contextPath}/admin/ship/add" method="post">
                            <div><b> <span style="color:red"> ${errorMessage}</span></b></div>
                            <div class="form-group">
                                <label for="input-1">Username</label>
                                <input type="text" class="form-control" id="input-1" placeholder="Username"  name="shipper-username" value="${shipper_tk}">
                            </div>
                            <div class="form-group">
                                <label for="input-2">Password</label>
                                <input type="password" class="form-control" id="input-2" placeholder="Password" name="shipper-password" value="${shipper_password}">
                                <input type="checkbox" onclick="myFunction1()">Hiển thị mật khẩu
                                <script>function myFunction1() {
                                    var x = document.getElementById("input-2");
                                    if (x.type === "password") {
                                        x.type = "text";
                                    } else {
                                        x.type = "password";
                                    }
                                }</script>
                            </div>
                            <div class="form-group">
                                <label for="input-3">Họ và tên</label>
                                <input type="text" class="form-control" id="input-3" placeholder="Họ và tên Shipper" name="shipper-name" value="${shipper_name}">
                            </div>
                            <div class="form-group">
                                <label for="input-4">Email</label>
                                <input type="email" class="form-control" id="input-4" placeholder="Email" name="shipper-email" value="${shipper_gmail}">
                            </div>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/ship/list">Hủy</a></button>
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Thêm</button>
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
</html>

