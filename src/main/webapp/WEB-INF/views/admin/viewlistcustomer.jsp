
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
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Customer</h5>
                        <c:choose>
                            <c:when test="${fn:contains(message, 'thành công')}">
                                <div><b> <span style="color:green"> ${message}</span></b></div>
                            </c:when>
                            <c:otherwise>
                                <div><b> <span style="color:red"> ${message}</span></b></div>
                            </c:otherwise>
                        </c:choose>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Tên đăng nhập</th>
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
                                        <td>${customer.hoten_Customer}</td>
                                        <td>${customer.gmail_Customer}</td>
                                        <td>${customer.sdt_Customer}</td>
                                        <td>${customer.vitien}</td>


                                        <td>
                                            <button type="reset" class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/user/delete?id=${customer.ma_Customer}">Xóa</a></button>
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
