<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/6/2021
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value = "/template/web" var="url"/>
<div class="aa-header-bottom">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-header-bottom-area">
                    <!-- logo  -->
                    <div class="aa-logo">
                        <!-- Text based logo -->
                        <!--                <a href="index.jsp">
                                          <span class="fa fa-shopping-cart"></span>
                                          <p>daily<strong>Shop</strong> <span>Your Shopping Partner</span></p>
                                        </a>-->
                        <!-- img based logo -->
                        <a href="${pageContext.request.contextPath}/web/home"><img src="${url}/images/new/logo.png" alt="logo img" width="100%"></a>
                    </div>
                    <!-- / logo  -->
                    <!-- Shipping service -->
                    <div class="aa-shipping-box">
                        <a class="aa-shipping-boxsmall">
                            <span class="aa-shipping-boxsmall-icon fas fa-shipping-fast"></span>
                            <span class="aa-shipping-box-text">
                          <div class="aa-shipping-title">Miễn phí vận chuyển</div>
                      <div class="">An toàn và nhanh chóng</div>
                      </span>

                        </a>
                        <a class="aa-shipping-boxsmall">
                            <span class="aa-shipping-boxsmall-icon fas fa-phone"></span>
                            <span class="aa-shipping-box-text">
                          <div class="aa-shipping-title">Hỗ trợ: 036.810.3208</div>
                          <div class="">Tư vấn 24/7 miễn phí</div>
                      </span>

                        </a>
                        <a class="aa-shipping-boxsmall">
                            <span class="aa-shipping-boxsmall-icon fas fa-shipping-fast"></span>
                            <span class="aa-shipping-box-text">
                          <div class="aa-shipping-title">Giao hàng toàn quốc</div>
                      <div class="">Đảm bảo uy tín, chất lượng</div>
                      </span>

                        </a>
                    </div>




                    <!-- cart box -->
                    <div class="aa-cartbox">
                        <a class="aa-cart-link" href="${pageContext.request.contextPath}/web/product/CartDetail">
                            <span class="fas fa-cart-arrow-down"></span>
                            <span class="aa-cart-title">GIỎ HÀNG</span>

                            <c:if test="${length_orders != NULL}">
                                <span class="aa-cart-notify">${length_orders}</span>
                            </c:if>


                        </a>
                        <div class="aa-cartbox-summary">
                            <ul class="scroll-product">
                                <c:forEach items="${Orders}" var="Order" >
                                    <li>
                                        <a class="aa-cartbox-img" href="#"><img src="${pageContext.request.contextPath}/template/web/images/products/img-test/${Order.getCuonSachEntity().anh_CuonSach}" alt="img"></a>
<%--                                            ${pageContext.request.contextPath}/view/client/cart--%>
                                        <div class="aa-cartbox-info">
                                            <h4><a href="#">${Order.getCuonSachEntity().ten_CuonSach}</a></h4>
                                            <p>${Order.soluong} x <fmt:formatNumber
                                                    type="number" maxFractionDigits="3"
                                                    value="${Order.getCuonSachEntity().giabia}"/> VNĐ</p>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="total-detailproduct">
                  		<span class="aa-cartbox-total-title">
                        <b>Tổng:</b>
                      </span>
                                <span class="aa-cartbox-total-price">
                        <fmt:formatNumber
                                type="number" maxFractionDigits="3"
                                value="${tongtien}"/> VNĐ
                      </span>
                            </div>
                            <a class="aa-cartbox-checkout aa-primary-btn" href="${pageContext.request.contextPath}/web/product/CartDetail">Chi tiết</a>
                            <a class="aa-cartbox-checkout aa-primary-btn" href="${pageContext.request.contextPath}/web/checkout">Thanh toán</a>
                        </div>
                    </div>
                    <!-- / cart box -->

                </div>
            </div>
        </div>
    </div>
</div>
