<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/12/2021
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value = "/template/web" var="url"/>


<!--  content -->
<!-- catg header banner section -->
<section id="aa-catg-head-banner">
    <img src="${pageContext.request.contextPath}/template/web/images/new/checkout-banner.png" alt="banner thanh toán">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Thanh toán</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                    <li style="color:#fff">Thông tin thanh toán</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- Cart view section -->
<section id="checkout">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="checkout-area">
                    <form action="${pageContext.request.contextPath}/web/payment" method="post">
<%--                        ${pageContext.request.contextPath}/view/client/transaction--%>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="checkout-left">
                                    <div class="panel-group" >
                                        <c:if test="${person == null}">
                                            <p> Bạn cần đăng nhập để thanh toán! Đăng nhập <a href="${pageContext.request.contextPath}/web/login" style="color: #754110">tại đây!</a></p>
                                        </c:if>
                                        <c:if test="${person != null}">
                                            <!-- Shipping Address -->
                                            <div class="panel panel-default aa-checkout-billaddress">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title" style="color:#754110">
                                                        Địa chỉ giao hàng
                                                    </h4>
                                                </div>
                                                <div id="collapseFour">
                                                    <div class="panel-body">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="aa-checkout-single-bill">
                                                                    <input type="text" placeholder="Họ Tên*" required="required" name="name">
                                                                </div>
                                                            </div>

                                                        </div>

                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="aa-checkout-single-bill">
                                                                    <input type="email" placeholder="Email*" required="required" name="email">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="aa-checkout-single-bill">
                                                                    <input type="tel" placeholder="Số điện thoại*" required="required" name="phone">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="aa-checkout-single-bill">
                                                                    <textarea cols="8" rows="3" required="required" placeholder="Địa chỉ*" name="address"></textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="aa-checkout-single-bill">
                                                                    <textarea cols="8" rows="3" placeholder="Ghi chú" name="transaction_mess"></textarea>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="aa-checkout-single-bill">
                                                                    <input type="date" placeholder="Password" name="transaction_created" id="the-date" style="display: none">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row" style="display: none">
                                                            <div class="col-md-12">
                                                                <div class="aa-checkout-single-bill">
                                                                    <input type="text" placeholder="userid_session" name="transaction_usersession" value="${user}">

                                                                    <input type="text" placeholder="totalmoney_session" name="transaction_amount" value="${tongtien}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="checkout-right">
                                    <h4>Thông tin đơn hàng</h4>
                                    <div class="aa-order-summary-area">
                                        <table class="table table-responsive">
                                            <thead>
                                            <tr>
                                                <th>Cuốn Sách</th>
                                                <th>Số tiền</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${Orders}" var="Order">
                                                <tr>
                                                    <td>${Order.getCuonSachEntity().getTen_CuonSach()} <strong> x  ${Order.soluong}</strong></td>
                                                    <td><fmt:formatNumber
                                                            type="number" maxFractionDigits="3"
                                                            value="${Order.getCuonSachEntity().giabia * Order.soluong}"/> VNĐ</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <th>Tạm tính</th>
                                                <td><fmt:formatNumber
                                                        type="number" maxFractionDigits="3"
                                                        value="${tongtien}"/> VNĐ</td>
                                            </tr>
                                            <tr>
                                                <th>Thuế</th>
                                                <td>0 VNĐ</td>
                                            </tr>
                                            <tr>
                                                <th>Tổng cộng</th>
                                                <td><strong><fmt:formatNumber
                                                        type="number" maxFractionDigits="3"
                                                        value="${tongtien}"/> VNĐ</strong></td>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <h4>Hình thức thanh toán</h4>
                                    <div class="aa-payment-method">
                                        <label for="cashdelivery"><input type="radio" id="cashdelivery" name="transaction_payment" checked value="0"> Thanh toán khi nhận hàng (COD) </label>
                                        <label for="paypal"><input type="radio" id="paypal" name="transaction_payment" value="1" disabled > Thanh toán bằng ATM(Sẽ hỗ trợ sớm nhất) </label>
                                        <input type="submit" value="Đặt hàng" class="aa-browse-btn">
                                    </div>
                                        <br>
                                        <p style="color: #20f108">${sucess}</p>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Cart view section -->
<!--  end content-->
<script>
    var date = new Date();

    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;

    var today = year + "-" + month + "-" + day;


    document.getElementById('the-date').value = today;
</script>
