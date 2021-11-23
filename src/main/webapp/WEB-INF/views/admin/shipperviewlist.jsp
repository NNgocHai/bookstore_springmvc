
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <button class="add-catalog"><a href="${pageContext.request.contextPath}/admin/ship/add">Thêm Shipper</a></button>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Shipper</h5>
                        <div class="table-responsive">
                            <div><b> <span style="color:red"> ${message}</span></b></div>
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Tài Khoản Shipper</th>
                                    <th scope="col">Mật Khẩu Shipper</th>
                                    <th scope="col">Họ và Tên:</th>
                                    <th scope="col">Email:</th>
                                    <th scope="col">Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${shipperList}" var="shipper">
                                    <tr>
                                        <td scope="row">${shipper.ma_Shipper}</td>
                                        <td>${shipper.taikhoan_Shipper}</td>
                                        <td>${shipper.matkhau_Shipper}</td>
                                        <td>${shipper.hoten_Shipper}</td>
                                        <td>${shipper.gmail_Shipper}</td>
                                        <td>
                                            <button type="reset" class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/ship/delete?shipper_id=${shipper.ma_Shipper}">Xóa</a></button>
                                            <button class="btn btn-success"><a href="${pageContext.request.contextPath}/admin/ship/edit?shipper_id=${shipper.ma_Shipper}">Sửa</a></button>
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
