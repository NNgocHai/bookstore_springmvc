<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("user_admin") == null){
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<html>
<head>
    <title>cccc</title>
</head>
<body>
    <div class="content-wrapper">
        <div class="container-fluid">

            <div class="row mt-3">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title">Thêm Admin</div>
                            <hr>
                            <form:form action="" method="post" modelAttribute="admin">
                                <c:choose>
                                    <c:when test="${fn:contains(message, 'thành công')}">
                                        <div><b> <span style="color:green"> ${message}</span></b></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div><b> <span style="color:red"> ${message}</span></b></div>
                                    </c:otherwise>
                                </c:choose>
                                <div class="form-group">
                                    <label>Username</label>
                                    <form:input type="text" class="form-control" path="taikhoan_Admin"/>
                                    <span style="color:red"><form:errors path="taikhoan_Admin"/></span>
                                </div>
                                <div class="form-group">
                                    <label >Password</label>
                                    <form:input type="password" id="input-2" class="form-control" path="matkhau_Admin"/>
                                    <input type="checkbox" onclick="myFunction1()">Hiển thị mật khẩu
                                    <script>function myFunction1() {
                                        var x = document.getElementById("input-2");
                                        if (x.type === "password") {
                                            x.type = "text";
                                        } else {
                                            x.type = "password";
                                        }
                                    }</script>
                                    <span style="color:red"><form:errors path="matkhau_Admin"/></span>
                                </div>
                                <div class="form-group">
                                    <label>Tên Admin</label>
                                    <form:input type="text" class="form-control" path="hoten_Admin"/>
                                    <form:errors path="hoten_Admin"/>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <form:input type="email" class="form-control" path="gmail_Admin"/>
                                    <span style="color:red"><form:errors path="gmail_Admin"/></span>
                                </div>
                                <div class="form-footer">
                                    <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/admin/list">Hủy</a></button>
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
