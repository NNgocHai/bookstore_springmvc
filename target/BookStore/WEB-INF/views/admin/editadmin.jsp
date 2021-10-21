<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 1/3/2021
  Time: 11:07 AM
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

                        <form action="${pageContext.request.contextPath}/admin/admin/edit" method="post">
                            <div class="form-group">
                                <label for="input-1">ID</label>
                                <input type="text" class="form-control" readonly id="input-1" placeholder="Admin ID" name="admin-id" value="${admin.ma_Admin}" required>
                            </div>
                            <div class="form-group">
                                <label for="input-2">Username</label>
                                <input type="text" class="form-control" id="input-2" placeholder="Username" name="admin-username" value="${admin.taikhoan_Admin}" required>
                            </div>
                            <div class="form-group">
                                <label for="input-3">Password</label>
                                <input type="password" class="form-control" id="input-3" placeholder="Mật khẩu" name="admin-password" value="${admin.matkhau_Admin}" required>
                                <input type="checkbox" onclick="myFunction1()">Hiển thị mật khẩu
                                <script>function myFunction1() {
                                    var x = document.getElementById("input-3");
                                    if (x.type === "password") {
                                        x.type = "text";
                                    } else {
                                        x.type = "password";
                                    }
                                }</script>
                            </div>
                            <div class="form-group">
                                <label for="input-4">Tên Admin</label>
                                <input type="text" class="form-control" id="input-4" placeholder="Tên Admin" name="admin-name" value="${admin.hoten_Admin}" required>
                            </div>
                            <div class="form-group">
                            <label for="input-5">Email</label>
                            <input type="email" class="form-control" id="input-5" placeholder="Email" name="admin-email" value="${admin.gmail_Admin}" required>
                            </div>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i> <a href="${pageContext.request.contextPath}/admin/admin/list">Hủy</a> </button>
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
</html>
