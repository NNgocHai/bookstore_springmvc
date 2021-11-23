<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/10/2021
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%
    if (session.getAttribute("user_admin") == null){
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<!-- Start header section -->
<div class="content-wrapper">
    <div class="container-fluid">

        <div class="row mt-3">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Chuyên mục: Sửa</div>
                        <hr>
                        <c:choose>
                            <c:when test="${fn:contains(message, 'thành công')}">
                                <div><b> <span style="color:green"> ${message}</span></b></div>
                            </c:when>
                            <c:otherwise>
                                <div><b> <span style="color:red"> ${message}</span></b></div>
                            </c:otherwise>
                        </c:choose>

                        <form:form action="" method="post" modelAttribute="order">
                            <div class="form-group">
                                <label>Mã khách hàng</label>
                                <form:input type="text" class="form-control"  path="ma_Customer" value = "${order.ma_Customer} "/>
                                <span style="color:red"><form:errors path="ma_Customer" /></span>
                            </div>
                            <div class="form-group">
                                <label>Mã đơn hàng</label>
                                <form:input type="text" class="form-control" path="ma_DH" value = "${order.ma_DH}" readonly="true"/>

                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <form:input type="text" class="form-control" path="diachi" value = "${order.diachi}"/>
                                <span style="color:red"><form:errors path="diachi"/></span>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <form:input type="text" class="form-control" path="sdt" value = "${order.sdt}"/>
                                <span style="color:red"><form:errors path="sdt"/></span>
                            </div>
                            <div class="form-group">
                                <label>Ngày đặt</label>
                                <form:input type="text" class="form-control" path="ngaydat" value = "${order.ngaydat}"/>
                                <span style="color:red"><form:errors path="ngaydat"/></span>
                            </div>
                            <div class="form-group">
                                <label>Tổng tiền</label>
                                <form:input type="text" class="form-control" path="tongtien" value = "${order.tongtien}"/>
                                <span style="color:red"><form:errors path="tongtien"/></span>
                            </div>

                            <div class="form-group">
                                <label>Tình trạng</label>
                                <form:input type="text" class="form-control" path="activeDH" value = "${order.activeDH}"/>
                                <span style="color:red"><form:errors path="activeDH"/></span>
                            </div>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/order/list">Hủy</a></button>
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Cập nhật</button>
                            </div>
                        </form:form>

                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>

