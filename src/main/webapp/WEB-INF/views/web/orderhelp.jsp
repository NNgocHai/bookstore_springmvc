<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 1/14/2021
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/template/web" var="url"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section id="aa-catg-head-banner">
    <img src="${url}/images/new/bannercs.png" alt="banner sản phẩm">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Hướng dẫn đặt hàng</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                    <li style="color:#fff">Đăt hàng</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div>
                <h1 style ="text-align: center; color: red;">Hướng dẫn đặt hàng</h1>
                <strong>Các bước mua hàng trên BOOKSTORE: </strong>
                <p>
                    - Đăng kí, đăng nhập. <br>
                    - Tìm kiếm sản phẩm. <br>
                    - Chọn sản phẩm. <br>
                    - Kiểm tra thông tin sản phẩm. <br>
                    - Xác nhận giỏ hàng. <br>
                    - Nhập thông tin giao hàng. <br>
                    - Chọn phương thức thanh toán. <br>
                    - Đặt hàng. <br>
                    - Nhận thông báo xác nhận đơn hàng.</p>

                <p>Cách thức thực hiện cực đơn giản, dễ dàng, và nhanh chóng và có đầy đủ hướng dẫn sẽ tiết kiệm thời gian mua hàng của bạn.</p>

                <p>Ngoài mua hàng thông qua website, bạn cũng có thể đặt hàng các sản phẩm của <strong>Book Store</strong> qua số điện thoại: 036.810.3208</p>
                <br><br/>
            </div>
        </div>
    </div>
</section>

</body>
</html>
