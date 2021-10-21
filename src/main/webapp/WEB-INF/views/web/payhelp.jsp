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
                <h2>Hình thức thanh toán</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                    <li style="color:#fff">Hình thức thanh toán</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div>
                <h1 style ="text-align: center; color: red;">Các hình thức thanh toán của BOOKSTORE</h1>
                <br>
                <strong>* Thanh toán tiền mặt: </strong>
                <p>Sau khi nhận được đơn hàng trên website, chúng tôi sẽ lên đơn cho shipper giao hàng, bạn sẽ nhận được hàng trong vòng 2-3 ngày kể từ khi đặt hàng.
                    Bạn có quyền kiểm tra hàng trước khi nhận. Bạn sẽ thanh toán bằng tiền mặt khi nhận được hàng và thanh toán trực tiếp với shipper giao hàng.
                    Hình thức thanh toán tiền mặt sẽ được áp dụng với tất cả các đơn hàng trên toàn quốc. Xin cám ơn!</p>
                <strong>* Thanh toán chuyển khoản ngân hàng và ví điện tử: </strong><span>sẽ hỗ trợ sớm nhất.</span>
            </div>
        </div>
    </div>
</section>

</body>
</html>
