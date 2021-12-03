
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <form:form action="" method="post" enctype="multipart/form-data" modelAttribute="product">
                            <div class="form-group">
                                <label>Mã đầu sách</label>
                                <form:select class="form-control"
                                             path="ma_DauSach"
                                             items="${cates}" itemValue="ma_DauSach" itemLabel="ten_DauSach"/>                                <span style="color:red"><form:errors path="ma_DauSach" /></span>
                            </div>
                            <div class="form-group">
                                <label>Tên cuốn sách</label>
                                <form:input type="text" class="form-control" path="ten_CuonSach" value = "${product.ten_CuonSach}"/>
                                <span style="color:red"><form:errors path="ten_CuonSach"/></span>
                            </div>
                            <div class="form-group">
                                <label>Tác giả</label>
                                <form:input type="text" class="form-control" path="tacgia" value = "${product.tacgia}"/>
                                <span style="color:red"><form:errors path="tacgia"/></span>
                            </div>
                            <div class="form-group">
                                <label>Số lượng</label>
                                <form:input type="number" min="0" class="form-control" path="soluong" value = "${product.soluong}"/>
                                <span style="color:red"><form:errors path="soluong"/></span>
                            </div>
                            <div class="form-group">
                                <label>Discount</label>
                                <form:input type="number" min="0"  class="form-control" path="discount" value = "${product.discount}"/>
                                <span style="color:red"><form:errors path="discount"/></span>
                            </div>
                            <div class="form-group">
                                <label>Giá bán</label>
                                <form:input type="number" min="0"  class="form-control" path="giabia" value = "${product.giabia}"/>
                                <span style="color:red"><form:errors path="giabia"/></span>
                            </div>
                            <div class="form-group">
                                <label>Mô tả</label>
                                <form:textarea type="text" class="form-control" path="mota" value = "${product.mota}"/>
                                <span style="color:red"><form:errors path="mota"/></span>
                            </div>
                            <div>Image:</div>
                            <input name="image" type="file" accept="image/png, image/gif, image/jpeg">
                            <hr>
                            <div class="form-footer">
                                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/product/list">Hủy</a></button>
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Cập nhật</button>
                            </div>
                        </form:form>

                    </div>
                </div>
            </div>
            <div height="100%">
                <img width="400" height="500" src="${pageContext.request.contextPath}/template/web/images/products/img-test/${product.anh_CuonSach}"/>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>

