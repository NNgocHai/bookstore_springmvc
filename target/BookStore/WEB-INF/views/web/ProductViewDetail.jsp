<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/10/2021
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/template/web" var="url"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section id="aa-catg-head-banner">
    <img src="${pageContext.request.contextPath}/template/web/images/new/bannercs.png" alt="banner sản phẩm">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Chi tiết cuốn sách</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
                    <li><a href="${pageContext.request.contextPath}/web/product">Xem Sách</a></li>
                    <li style="color:#fff">${product.getMota() }</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- product category -->
<section id="aa-product-details">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-product-details-area">
                    <div class="aa-product-details-content">
                        <div class="row">
                            <!-- Modal view slider -->

                            <div class="col-md-5 col-sm-5 col-xs-12">
                                <div class="aa-product-view-slider">
                                    <div id="demo-1" class="simpleLens-gallery-container">
                                        <div class="simpleLens-container">
                                            <div class="simpleLens-big-image-container"><a
                                                    data-lens-image="${pageContext.request.contextPath}/template/web/images/products/img-test/${product.getAnh_CuonSach()}"
                                                    class="simpleLens-lens-image"><img
                                                    src="${pageContext.request.contextPath}/template/web/images/products/img-test/${product.getAnh_CuonSach()}"
                                                    class="simpleLens-big-image"></a></div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- Modal view content -->

                            <div class="col-md-7 col-sm-7 col-xs-12">
                                <div class="aa-product-view-content">
                                    <h3>${product.getTen_CuonSach()}</h3>
                                    <div class="aa-price-block">
                                        <c:choose>
                                            <c:when test="${product.getDiscount() == 0}">
                                                <span class="aa-product-price"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.giabia}" /> VNĐ</span><span
                                                    class="aa-product-price"></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="aa-product-price"><strong><fmt:formatNumber type="number"
                                                                                                         maxFractionDigits="3"
                                                                                                         value="${product_km.giabia}"/> VNĐ</strong></span>
                                                <span class="aa-product-price"><del><fmt:formatNumber type="number"
                                                                                                      maxFractionDigits="3"
                                                                                                      value="${product.giabia}"/> VNĐ</del></span>
                                            </c:otherwise>
                                        </c:choose>

                                        <p class="aa-product-avilability">Tình trạng: <span>
                      	<c:choose>
                            <c:when test="${product.soluong > 0}">
                                <c:out value="Còn hàng"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="Hết hàng"/>
                            </c:otherwise>
                        </c:choose>
                      </span></p>
                                    </div>

                                    <div class="aa-prod-quantity">

                                        <p class="aa-prod-category">
                                            <strong>Danh mục:</strong> <a
                                                href="${pageContext.request.contextPath}/web/product/Cate?Cate=${product.ma_DauSach}">${Catee.get(0).getCategoryEntity().getTen_DauSach()}</a>
                                        </p>
                                    </div>
                                    <div class="aa-prod-view-bottom">
                                        <a class="aa-add-to-cart-btn" href="${pageContext.request.contextPath}/web/product/AddtoCart?product-id=${product.getMa_CuonSach()}">Thêm vào giỏ hàng</a>
                                        <%--                                        ${pageContext.request.contextPath}/view/client/add-cart?product-id=${detail_product.id}--%>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="aa-product-details-bottom">
                        <ul class="nav nav-tabs aa-products-tab">
                            <li class="active"><a href="#" data-toggle="tab" class="">Mô tả</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="description">
                                <p>${product.getMota() }</p>
                            </div>

                        </div>
                    </div>
                    <!-- Related product -->
                    <div class="aa-product-related-item">
                        <h3>SÁCH LIÊN QUAN</h3>
                        <ul class="aa-product-catg aa-related-item-slider">
                            <!-- start single product item -->
                            <c:forEach items="${Catee}" var="productCate">
                                <li>
                                    <figure>
                                        <a class="aa-product-img" href="${pageContext.request.contextPath}/web/productDetail?id=${productCate.ma_CuonSach}&Cate=${productCate.ma_DauSach}"><img
                                                src="${pageContext.request.contextPath}/template/web/images/products/img-test/${productCate.anh_CuonSach}"
                                                alt=""></a>
                                        <a class="aa-add-card-btn" href="${pageContext.request.contextPath}/web/product/AddtoCart?product-id=${productCate.ma_CuonSach}"><span class="fa fa-shopping-cart"></span>Thêm
                                            vào giỏ hàng</a>
                                        <figcaption>
                                            <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/web/productDetail?id=${productCate.ma_CuonSach}&Cate=${productCate.ma_DauSach}">${productCate.ten_CuonSach}</a>
                                            </h4>

                                            <c:choose>
                                                <c:when test="${productCate.discount == 0}">
                                                    <span class="aa-product-price"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${productCate.giabia}" /> VNĐ</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${Catee_km}" var="product_Catekm">
                                                        <c:if test="${product_Catekm.ma_CuonSach  == productCate.ma_CuonSach}">
                                                            <span class="aa-product-price"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product_Catekm.giabia}" /> VNĐ</span>
                                                            <span class="aa-product-price"><del><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${productCate.giabia}" /> VNĐ</del> </span>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </figcaption>
                                    </figure>

                                    <c:if test="${productCate.discount != 0}">
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sale">- ${productCate.discount}%</span>
                                    </c:if>
                                </li>
                            </c:forEach>
                            <!-- start single product item -->
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

