
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

                        <form:form action="" method="post" modelAttribute="category">
                            <div class="form-group">
                                <label for="input-1">ID</label>
                                <form:input path="ma_DauSach" type="text" class="form-control" readonly="true" name="category-id"/>
<%--                                <input type="text" class="form-control" readonly id="input-1" placeholder="ĐẦU SÁCH ID" name="category-id" value="${category.ma_DauSach}" required>--%>
                            </div>

                            <div class="form-group">
                                <label for="input-4">Tên Đầu Sách</label>
                                <form:input path="ten_DauSach" type="text" class="form-control" id="input-4"/>
<%--                                <input type="text" class="form-control" id="input-4" placeholder="Tên Đầu sách" name="category-name" value="${category.ten_DauSach} " required>--%>
                                <div><b> <span style="color:red">
                                    <form:errors path="ten_DauSach"/>
                                </span></b></div>
                            </div>

                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i> <a href="${pageContext.request.contextPath}/admin/cate/list">Hủy</a> </button>
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

