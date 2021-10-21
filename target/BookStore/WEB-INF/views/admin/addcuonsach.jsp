<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/7/2021
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Thêm Cuốn sách</title>
</head>
<body>
<div class="content-wrapper">
    <div class="container-fluid">

        <div class="row mt-3">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Thêm Cuốn sách</div>
                        <hr>
                        <form action="${pageContext.request.contextPath}/admin/product/add" method="post">
                            <div><b> <span style="color:red"> ${errorMessage}</span></b></div>
                            <div class="form-group">
                                <label for="input-1">Mã đầu sách</label>
                                <input type="text" class="form-control" id="input-1" placeholder="Mã đầu sách"  name="cuonsach-maDS" required>
                            </div>
                            <div class="form-group">
                                <label for="input-2">Tên cuốn sách</label>
                                <input type="text" class="form-control" id="input-2" placeholder="Tên cuốn sách" name="cuonsach-ten" required >
                            </div>
                            <div class="form-group">
                                <label for="input-3">Tác giả</label>
                                <input type="text" class="form-control" id="input-3" placeholder="Tác giả" name="cuonsach-tacgia" required>
                            </div>
                            <div class="form-group">
                                <label for="input-4">Số lượng</label>
                                <input type="text" class="form-control" id="input-4" placeholder="Số lượng" name="cuonsach-soluong" required>
                            </div>
                            <div class="form-group">
                                <label for="input-6">Ảnh cuốn sách</label>
                                <input type="text" class="form-control" id="input-6" placeholder="Ảnh cuốn sách" name="cuonsach-anhCS" required>
                            </div>
                            <div class="form-group">
                                <label for="input-7">Discount</label>
                                <input type="text" class="form-control" id="input-7" placeholder="Discount" name="cuonsach-discount" required>
                            </div>
                            <div class="form-group">
                                <label for="input-8">Giá bán</label>
                                <input type="text" class="form-control" id="input-8" placeholder="Giá bán" name="cuonsach-giaban" required>
                            </div>
                            <div class="form-group">
                                <label for="input-9">Mô tả</label>
                                <textarea type="text" class="form-control" id="input-9" placeholder="Mô tả" name="cuonsach-mota" required></textarea>
                            </div>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/product/list">Hủy</a></button>
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Thêm</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>

</body>
</html>
