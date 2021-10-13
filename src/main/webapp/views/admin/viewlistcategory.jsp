<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/4/2021
  Time: 4:08 PM
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
                <button class="add-catalog"><a href="${pageContext.request.contextPath}/admin/cate/add">Thêm Đầu sách</a></button>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Đầu sách</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Tên đầu sách</th>
                                    <th scope="col">Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${categoryList}" var="category">
                                    <tr>
                                        <td scope="row">${category.ma_DauSach}</td>
                                        <td>${category.ten_DauSach}</td>
                                        <td>
                                            <button type="reset" class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/cate/delete?category-id=${category.ma_DauSach}">Xóa</a></button>
                                            <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/cate/edit?category-id=${category.ma_DauSach}">Sửa</a></button>
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