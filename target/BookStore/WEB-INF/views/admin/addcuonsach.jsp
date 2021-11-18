<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/7/2021
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Thêm Cuốn sách</title>
</head>
<body>
<div class="content-wrapper">
    <div class="container-fluid">

        <div class="row mt-3">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Thêm Cuốn sách</div>
                        <hr>
                        <form:form action="" method="post" modelAttribute="product">
                            <div><b> <span style="color:red"> ${message}</span></b></div>
                            <div class="form-group">
                                <label>Mã đầu sách</label>
                                <form:input type="text" class="form-control" path="ma_DauSach"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="ma_DauSach"/></span>
                            </div>
                            <div class="form-group">
                                <label>Tên cuốn sách</label>
                                <form:input type="text" class="form-control" path="ten_CuonSach"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="ten_CuonSach"/></span>
                            </div>
                            <div class="form-group">
                                <label>Tác giả</label>
                                <form:input type="text" class="form-control" path="tacgia"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="tacgia"/></span>
                            </div>
                            <div class="form-group">
                                <label>Số lượng</label>
                                <form:input type="text" class="form-control" path="soluong"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="soluong"/></span>
                            </div>
                            <div class="form-group">
                                <label>Ảnh cuốn sách</label>
                                <form:input type="text" class="form-control" path="anh_CuonSach"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="anh_CuonSach"/></span>
                            </div>
                            <div class="form-group">
                                <label>Discount</label>
                                <form:input type="text" class="form-control" path="discount"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="discount"/></span>
                            </div>
                            <div class="form-group">
                                <label>Giá bán</label>
                                <form:input type="text" class="form-control" path="giabia"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="giabia"/></span>
                            </div>
                            <div class="form-group">
                                <label>Mô tả</label>
                                <form:textarea type="text" class="form-control" path="mota"/>
                                <span style="color:rgba(238,207,207,0.91)"><form:errors path="mota"/></span>
                            </div>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/product/list">Hủy</a></button>
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Thêm</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>

</body>
</html>
