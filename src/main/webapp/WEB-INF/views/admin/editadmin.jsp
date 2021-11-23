
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <form:form action="" method="post" modelAttribute="admin">
                            <div class="form-group">
                                <label for="input-1">ID</label>
                                <form:input readonly="true" type="text" class="form-control" path="ma_Admin"/>
<%--                                <input type="text" class="form-control" readonly id="input-1" placeholder="Admin ID" name="admin-id" value="${admin.ma_Admin}" required>--%>
                            </div>
                            <div class="form-group">
                                <label >Username</label>
                                <form:input path="taikhoan_Admin" type="text" class="form-control"/>
                                <span style="color:red"><form:errors path="taikhoan_Admin"/></span>
<%--                                <input type="text" class="form-control" id="input-2" placeholder="Username" name="admin-username" value="${admin.taikhoan_Admin}" required>--%>
                            </div>
                            <div class="form-group">
                                <label for="input-3">Password</label>
                                <form:input path="matkhau_Admin" type="password" class="form-control" id="input-3"/>
                                <span style="color:red"><form:errors path="matkhau_Admin"/></span>
<%--                                <input type="password" class="form-control" id="input-3" placeholder="Mật khẩu" name="admin-password" value="${admin.matkhau_Admin}" required>--%>
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
                                <form:input path="hoten_Admin" type="text" class="form-control" id="input-4"/>
                                <span style="color:red"><form:errors path="hoten_Admin"/></span>
<%--                                <input type="text" class="form-control" id="input-4" placeholder="Tên Admin" name="admin-name" value="${admin.hoten_Admin}" required>--%>
                            </div>
                            <div class="form-group">
                                <label for="input-5">Email</label>
                                <form:input path="gmail_Admin" type="email" class="form-control" id="input-5"/>
                                <span style="color:red"><form:errors path="gmail_Admin"/></span>
<%--                                <input type="email" class="form-control" id="input-5" placeholder="Email" name="admin-email" value="${admin.gmail_Admin}" required>--%>
                            </div>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i> <a href="${pageContext.request.contextPath}/admin/admin/list">Hủy</a> </button>
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

