
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
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách Review</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Mã Customer</th>
                                    <th scope="col">Ngày Review</th>
                                    <th scope="col">Bình Luận</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${reviewList}" var="review">
                                    <tr>
                                        <td scope="row">${review.ma_Review}</td>
                                        <td>${review.ma_Customer}</td>
                                        <td>${review.ngay_Review}</td>
                                        <td>${review.binhluan}</td>

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

