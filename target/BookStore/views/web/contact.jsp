<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 1/14/2021
  Time: 1:57 PM
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
                <h2>Liên hệ</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                    <li style="color:#fff">Liên hệ</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div>
                <b><h1 style ="text-align: center; color: red;">BOOK STORE</h1></b>
                <p><b> Hãy liên hệ qua: </b></p>
                <ul>
                    <li>- Địa chỉ: IT18 - Đại học Sư phạm Kĩ Thuật Hồ Chí Minh - Q.Thủ Đức - TP.HCM </li>
                    <li>- Số điện thoại: 036.810.3208 </li>
                </ul>
                <br>
                <br>
                <br>
            </div>
        </div>
    </div>
</section>
</body>
</html>
