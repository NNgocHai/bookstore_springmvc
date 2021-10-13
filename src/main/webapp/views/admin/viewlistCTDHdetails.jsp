<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%


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
                <button class="add-catalog"><a href="${pageContext.request.contextPath}/admin/chitietdonhang/list">Quay lại</a></button>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Mã đơn: ${listdetails[0][0]}</h5>
                        <h5 class="card-title">Tên khách: ${listdetails[0][1]}</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">Tên Sách</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Giá</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listdetails}" var="list">
                                    <tr>
                                        <td scope="row">${list[2]}</td>
                                        <td>${list[3]}</td>
                                        <td>${list[4]}</td>
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
