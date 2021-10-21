<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 11/23/2020
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Admin Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<c:url value='/template/loginAdmin/images/icons/favicon.ico'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/vendor/bootstrap/css/bootstrap.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/fonts/iconic/css/material-design-iconic-font.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/vendor/animate/animate.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/vendor/css-hamburgers/hamburgers.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/vendor/animsition/css/animsition.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/vendor/select2/select2.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/vendor/daterangepicker/daterangepicker.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/css/util.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/loginAdmin/css/main.css'/>">
    <!--===============================================================================================-->
</head>
<body>
    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">
                <form class="login100-form validate-form" action="${pageContext.request.contextPath}/admin/login" method="post">
                        <span class="login100-form-title p-b-26">
                            Welcome
                        </span>
                    <span class="login100-form-title p-b-48">
                            <i class="zmdi zmdi-font"></i>
                        </span>

                    <div class="wrap-input100 validate-input" data-validate = "Enter User">
                        <input class="input100" type="text" name="user">
                        <span class="focus-input100" data-placeholder="User"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate="Enter Password">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                        <input class="input100" type="password" name="password">
                        <span class="focus-input100" data-placeholder="Password"></span>
                    </div>
                    <div><b> <span style="color:#00d9e8"> ${errorMessage}</span></b></div>
                    <div class="container-login100-form-btn">
                        <div class="wrap-login100-form-btn">
                            <div class="login100-form-bgbtn"></div>
                            <button type="submit" class="login100-form-btn">
                                Login
                            </button>
                        </div>
                    </div>

                    <div class="text-center p-t-115">
                            <span class="txt1">
                                @Copyright 2020 BookStore.com
                            </span>

                    </div>
                </form>
            </div>
        </div>
    </div>


    <div id="dropDownSelect1"></div>

    <!--===============================================================================================-->
    <script src="<c:url value='/template/loginAdmin/vendor/jquery/jquery-3.2.1.min.js'/>"></script>
    <!--===============================================================================================-->
    <script src="<c:url value='/template/loginAdmin/vendor/animsition/js/animsition.min.js'/>"></script>
    <!--===============================================================================================-->
    <script src="<c:url value='/template/loginAdmin/vendor/bootstrap/js/popper.js'/>"></script>
    <script src="<c:url value='/template/loginAdmin/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
    <!--===============================================================================================-->
    <script src="<c:url value='/template/loginAdmin/vendor/select2/select2.min.js'/>"></script>
    <!--===============================================================================================-->
    <script src="<c:url value='/template/loginAdmin/vendor/daterangepicker/moment.min.js'/>"></script>
    <script src="<c:url value='/template/loginAdmin/vendor/daterangepicker/daterangepicker.js'/>"></script>
    <!--===============================================================================================-->
    <script src="<c:url value='/template/loginAdmin/vendor/countdowntime/countdowntime.js'/>"></script>
    <!--===============================================================================================-->
    <script src="<c:url value='/template/loginAdmin/js/main.js'/>"></script>


</body>
</html>
