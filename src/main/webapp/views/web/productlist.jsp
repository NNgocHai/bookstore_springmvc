<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/6/2021
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:url value = "/template/web" var="url"/>


<section id="aa-catg-head-banner">
    <img src="${url}/images/new/bannercs.png" alt="banner sản phẩm">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Cuốn Sách</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                    <li style="color:#ffffff">Danh sách cuốn sách</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- product category -->
<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
                <div class="aa-product-catg-content">

                    <div class="aa-product-catg-body">
                        <ul class="aa-product-catg">
                            <!-- start single product item -->
                            <c:if test="${empty productList}">
                                <p style="margin-left: 30px">Chưa có sách!</p>
                            </c:if>
                            <c:forEach items="${productList}" var="product">
                                <li>
                                    <figure>
                                        <a class="aa-product-img" href="${pageContext.request.contextPath}/web/productDetail?id=${product.ma_CuonSach}&Cate=${product.ma_DauSach}"><img src="${pageContext.request.contextPath}/template/web/images/products/img-test/${product.anh_CuonSach}" alt="${product.ten_CuonSach}"></a>
                                        <a class="aa-add-card-btn" href="${pageContext.request.contextPath}/web/product/AddtoCart?product-id=${product.ma_CuonSach}"><span class="fa fa-shopping-cart"></span>Thêm vào giỏ hàng</a>
                                        <figcaption>
                                            <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/web/productDetail?id=${product.ma_CuonSach}&Cate=${product.ma_DauSach}">${product.ten_CuonSach}</a></h4>

                                            <c:choose>
                                                <c:when test="${product.discount == 0}">
                                                    <span class="aa-product-price"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.giabia}" /> VNĐ</span><span class="aa-product-price"></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${productList_km}" var="product_km">
                                                        <c:if test="${product_km.ma_CuonSach == product.ma_CuonSach}">
                                                            <span class="aa-product-price"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product_km.giabia}" /> VNĐ</span>
                                                            <span class="aa-product-price"><del><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.giabia}" /> VNĐ</del></span>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                        </figcaption>
                                    </figure>

                                    <c:if test="${product.discount != 0}">
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sale">- ${product.discount}%</span>
                                    </c:if>

                                </li>
                            </c:forEach>
                        </ul>

                        <!-- / quick view modal -->
                    </div>

                </div>
            </div>


            <div class="col-lg-3 col-md-3 col-sm-4 col-md-pull-9">
                <aside class="aa-sidebar">
                    <!-- single sidebar -->
                    <div class="aa-sidebar-widget">
                        <h3>Danh mục</h3>
                        <ul class="aa-catg-nav">
                            <c:forEach items="${categoryList}" var="cate">
                                <li><a href="${pageContext.request.contextPath}/web/product/Cate?Cate=${cate.ma_DauSach}">${cate.ten_DauSach}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- single sidebar -->

                    <!-- single sidebar -->
                    <div class="aa-sidebar-widget">
                        <h3>Sách gần đây</h3>
                        <div class="aa-recently-views">
                            <ul>
                                <c:forEach items="${productListCurrent}" var="product" begin="${productListCurrent.size()-3}" end="${productListCurrent.size()-1}" step="1">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/web/productDetail?id=${product.ma_CuonSach}&Cate=${product.ma_DauSach}" class="aa-cartbox-img"><img alt="img" src="${pageContext.request.contextPath}/template/web/images/products/img-test/${product.anh_CuonSach}"></a>
<%--                                            ${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}--%>
                                        <div class="aa-cartbox-info">
                                            <h4><a href="${pageContext.request.contextPath}/web/productDetail?id=${product.ma_CuonSach}&Cate=${product.ma_DauSach}">${product.ten_CuonSach }</a></h4>
<%--                                                ${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}--%>
                                            <c:choose>
                                                <c:when test="${product.discount == 0}">
                                                    <p><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.giabia}" /> VNĐ</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${productListCurrent_km}" var="product_km">
                                                        <c:if test="${product_km.ma_CuonSach == product.ma_CuonSach}">
                                                            <p><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product_km.giabia}" /> VNĐ</p>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <!-- single sidebar -->

                </aside>
            </div>

        </div>
    </div>

</section>
<c:if test="${navigationDaoImpl.totalPages > 1}">
    <div class="text-center">
        <nav aria-label="Page navigation example">
            <ul class="pagination pagination-lg">
                <c:forEach items="${navigationDaoImpl.navigationPages}" var = "page">
                    <c:choose>
                        <c:when test="${page != -1 && page == navigationDaoImpl.currentPage }">
                            <li class="page-item active">
                                <a href="#" class="page-link">${page} </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item" >
                                 <a href="${pageContext.request.contextPath}/web/product?page=${page}" class="page-link">${page}</a>
                             </li>
                        </c:otherwise>
                    </c:choose>
               <%-- <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
                </c:forEach>
            </ul>
        </nav>
    </div>
</c:if>
<%--navigationDaoImpl--%>
<%--<c:if test="${paginationProducts.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationProducts.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="productList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>
    </div>
</c:if>--%>
<!-- / product category -->
<!--  end content-->

<!--  footer-->

<!-- end footer-->
