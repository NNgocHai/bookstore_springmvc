<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="row mt-3">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Thêm Category</div>
                        <hr>
                        <form:form action="" method="post"
                            modelAttribute="category">
                            <div><b> <span style="color:rgba(238,207,207,0.91)"> ${message}</span></b></div>
                            <div class="form-group">
                                <label>Tên đầu sách</label>
                                <form:input path="ten_DauSach" type="text" class="form-control" id="input-1"/>
<%--                                <input type="text" class="form-control" id="input-1" placeholder="Tên đầu sách"  name="category-name" required>--%>
                                <div><b> <span style="color:#f31818">
                                    <form:errors path="ten_DauSach"/>
                                </span></b></div>

                            </div>

                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/cate/list">Hủy</a></button>
                                <input type="submit"  value="Thêm" name="add" class="btn btn-success"/>
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
