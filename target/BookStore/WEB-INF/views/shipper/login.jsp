<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 1/6/2021
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Shipper Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<c:url value='/template/loginShipper/images/icons/favicon.ico'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/vendor/bootstrap/css/bootstrap.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/fonts/iconic/css/material-design-iconic-font.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/vendor/animate/animate.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/vendor/css-hamburgers/hamburgers.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/vendor/animsition/css/animsition.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/vendor/select2/select2.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/vendor/daterangepicker/daterangepicker.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/css/util.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginShipper/css/main.css'/>">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-t-85 p-b-20">
            <form class="login100-form validate-form" action="${pageContext.request.contextPath}/shipper/login" method="post">
					<span class="login100-form-title p-b-70">
						Welcome
					</span>
                <span class="login100-form-avatar">
						<img src="<c:url value="/template/loginShipper/images/shipper.jpg"/>" alt="AVATAR">
					</span>

                <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate = "Enter username">
                    <input class="input100" type="text" name="user">
                    <span class="focus-input100" data-placeholder="Username"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-50" data-validate="Enter password">
                    <input class="input100" type="password" name="password">
                    <span class="focus-input100" data-placeholder="Password"></span>
                </div>
                <div><b> <span style="color:#00d9e8"> ${errorMessage}</span></b></div>
                <br>
                <div class="container-login100-form-btn">
                    <button type="submit" class="login100-form-btn">
                        Login
                    </button>
                </div>
                <br>
                <br>
                <br>
                <br>

                <div>
                    <center><span class="txt1">
                                @Copyright 2020 BookStore.com
                </span></center>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="<c:url value='/template/loginShipper/vendor/jquery/jquery-3.2.1.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/loginShipper/vendor/animsition/js/animsition.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/loginShipper/vendor/bootstrap/js/popper.js'/>"></script>
<script src="<c:url value='/template/loginShipper/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/loginShipper/vendor/select2/select2.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/loginShipper/vendor/daterangepicker/moment.min.js'/>"></script>
<script src="<c:url value='/template/loginShipper/vendor/daterangepicker/daterangepicker.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/loginShipper/vendor/countdowntime/countdowntime.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/loginShipper/js/main.js'/>"></script>
</body>
</html>