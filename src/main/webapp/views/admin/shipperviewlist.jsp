<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/9/2021
  Time: 12:44 PM
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
                <button class="add-catalog"><a href="${pageContext.request.contextPath}/admin/ship/add">Thêm Shipper</a></button>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Shipper</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Tài Khoản Shipper</th>
                                    <th scope="col">Mật Khẩu Shipper</th>
                                    <th scope="col">Họ và Tên:</th>
                                    <th scope="col">Email:</th>
                                    <th scope="col">Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${shipperList}" var="shipper">
                                    <tr>
                                        <td scope="row">${shipper.ma_Shipper}</td>
                                        <td>${shipper.taikhoan_Shipper}</td>
                                        <td>${shipper.matkhau_Shipper}</td>
                                        <td>${shipper.hoten_Shipper}</td>
                                        <td>${shipper.gmail_Shipper}</td>
                                        <td>
                                            <button type="reset" class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/ship/delete?id=${shipper.ma_Shipper}">Xóa</a></button>
                                            <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/ship/edit?id=${shipper.ma_Shipper}">Sửa</a></button>
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
