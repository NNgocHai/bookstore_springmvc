<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/10/2021
  Time: 5:36 PM
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

<div class="content-wrapper">
    <div class="container-fluid">
        <!--End Row-->
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Đơn hàng</h5>
                        <div class="table-responsive">
                            <c:choose>
                                <c:when test="${fn:contains(message, 'thành công')}">
                                    <div><b> <span style="color:green"> ${message}</span></b></div>
                                </c:when>
                                <c:otherwise>
                                    <div><b> <span style="color:red"> ${message}</span></b></div>
                                </c:otherwise>
                            </c:choose>
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Mã Đơn</th>
                                    <th scope="col">Mã Khách hàng</th>
                                    <th scope="col">Địa chỉ</th>
                                    <th scope="col">Số điện thoại</th>
                                    <th scope="col">Tổng tiền</th>
                                    <th scope="col">Tình trạng</th>
                                    <th scope="col">Ghi chú</th>
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
                                        <td>${donhang.ghichu}</td>

                                        <td>
                                            <button type="reset" class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/order/delete?order_id=${donhang.ma_DH}">Xóa</a></button>
                                            <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/order/edit?order_id=${donhang.ma_DH}">Sửa</a></button>
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

