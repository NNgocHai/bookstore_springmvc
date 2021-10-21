<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%--%>
<%--    //    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");--%>
<%--//    response.setHeader("Pragma" , "no-cache");--%>
<%--//    response.setHeader("Expires" , "0");--%>
<%

    if (session.getAttribute("user_admin") == null){
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<style>
    select option{
        background-color: white !important;
    }
</style>
<body>
<div class="content-wrapper">
    <div class="container-fluid">
        <!--End Row-->


        <div class="row">
            <div class="col-lg-12">
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách shipper </h5>
                        <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Họ tên shipper</th>
                                        <%--                                    <th scope="col">MẬt khẩu</th>--%>
                                        <th scope="col">Gmail shipper</th>
                                        <th scope="col">Hàng động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${shipperEntities}" var="shipper">
                                        <tr>
                                            <td scope="row">${shipper.ma_Shipper}</td>
                                            <td>${shipper.hoten_Shipper}</td>
                                            <td>${shipper.gmail_Shipper}</td>
                                            <td>
                                                <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/giaohang/phancong_2?id=${shipper.ma_Shipper}">Phân công giao hàng</a></button>
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