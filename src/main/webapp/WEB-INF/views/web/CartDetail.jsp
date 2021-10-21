<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/11/2021
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/template/web" var="url"/>

<section id="aa-catg-head-banner">
<%--    <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-cart.png" alt="banner giỏ hàng">--%>
    <img src="${url}/images/new/banner-cart.png" alt="banner giỏ hàng">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Giỏ hàng</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}">Trang chủ </a></li>
                    <li style="color:#fff">Thông tin giỏ hàng</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- Cart view section -->
<section id="cart-view">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="cart-view-area">
                    <p style="color:#ff0000; display:block">
                        ${error}
                    </p>
                    <div class="cart-view-table">
                        <%--                        ${pageContext.request.contextPath}/view/client/cart-update--%>
                        <form action="${pageContext.request.contextPath}/web/product/UpdatetoCart" method="post">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>Bỏ chọn</th>
                                        <th>Mô tả</th>
                                        <th>Cuốn Sách</th>
                                        <th>Giá</th>
                                        <th>Số lượng</th>
                                        <th>Giảm giá</th>
                                        <th>Thành Tiền</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${Orders}" var="Order">
                                        <tr>
                                                <%--                                                ${pageContext.request.contextPath}/view/client/cart-delete?id=${item.product.id}--%>
                                            <td><a class="remove" href="${pageContext.request.contextPath}/web/product/DeletetoCart?index=${Orders.indexOf(Order)}&ma_CuonSach=${Order.getCuonSachEntity().getMa_CuonSach()}">
                                                <fa class="fa fa-close"></fa>
                                            </a></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/web/productDetail-detail?id=${Order.getCuonSachEntity().getMa_CuonSach()}"><img
                                                        src="${pageContext.request.contextPath}/template/web/images/products/img-test/${Order.getCuonSachEntity().getAnh_CuonSach()}"
                                                        alt="img${Order.getCuonSachEntity().getTen_CuonSach()}"></a>
                                            </td>
                                            <td><a class="aa-cart-title"
                                                   href="${pageContext.request.contextPath}/web/productDetail-detail?id=${Order.getCuonSachEntity().getMa_CuonSach()}">${Order.getCuonSachEntity().getTen_CuonSach()}</a>
                                            </td>
                                            <c:forEach items="${cuonSachEntityList}" var="product">
                                                <c:if test="${product.ma_CuonSach == Order.getCuonSachEntity().getMa_CuonSach()}">
                                                        <td><fmt:formatNumber
                                                                type="number" maxFractionDigits="3"
                                                                value="${product.getGiabia()}"/> VNĐ</td>
                                                </c:if>

                                            </c:forEach>
                                            <td><input class="aa-cart-quantity" type="number"
                                                       name="${Order.getCuonSachEntity().getMa_CuonSach()}"
                                                       value="${Order.soluong}" min=1></td>
                                            <td>${Order.getCuonSachEntity().discount} %</td>
                                            <td><fmt:formatNumber
                                                    type="number" maxFractionDigits="3"
                                                    value="${Order.getCuonSachEntity().getGiabia() * Order.getSoluong()}"/> VNĐ</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="6" class=""><strong>TỔNG TIỀN</strong></td>
                                        <td><strong><fmt:formatNumber
                                                type="number" maxFractionDigits="3"
                                                value="${tongtien}"/> VNĐ</strong></td>
                                    </tr>
                                    <tr>
                                        <td colspan="7" class="aa-cart-view-bottom">
                                            <input class="aa-cart-view-btn" type="submit" value="Cập nhật giỏ hàng">
                                        </td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </form>
                        <!-- Cart Total view -->
                        <div class="cart-view-total">
                            <h4>Thông tin giỏ hàng</h4>
                            <table class="aa-totals-table">
                                <tbody>
                                <tr>
                                    <th>Tạm tính</th>
                                    <td><fmt:formatNumber
                                            type="number" maxFractionDigits="3"
                                            value="${tongtien}"/> VNĐ</td>
                                </tr>
                                <tr>
                                    <th>VAT</th>
                                    <td>0 VNĐ</td>
                                </tr>
                                <tr>
                                    <th>Tổng cộng</th>
                                    <td><strong><fmt:formatNumber
                                            type="number" maxFractionDigits="3"
                                            value="${tongtien}"/> VNĐ</strong></td>
                                </tr>
                                </tbody>
                            </table>
                            <%--                            ${pageContext.request.contextPath}/view/client/checkout--%>
                            <a href="${pageContext.request.contextPath}/web/checkout" class="aa-cart-view-btn">Thanh toán</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

