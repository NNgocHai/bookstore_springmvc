<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 11/24/2020
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<c:url value ="/template/web" var="url"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BOOK STORE</title>

    <!-- Font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
          integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
    <link href="${url}/css/font-awesome.css" rel="stylesheet">

    <!-- Bootstrap -->
    <link href="${url}/css/bootstrap.css" rel="stylesheet">
    <link rel="icon" href="${url}/images/new/favicon.png" type="image/x-icon">
    <!-- SmartMenus jQuery Bootstrap Addon CSS -->
    <link href="${url}/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
    <!-- Product view slider -->
    <link rel="stylesheet" type="text/css" href="${url}/css/jquery.simpleLens.css">
    <!-- slick slider -->
    <link rel="stylesheet" type="text/css" href="${url}/css/slick.css">
    <!-- price picker slider -->
    <link rel="stylesheet" type="text/css" href="${url}/css/nouislider.css">
    <!-- Theme color -->
    <link id="switcher" href="${url}/css/theme-color/default-theme.css" rel="stylesheet">

    <!-- Top Slider CSS -->
    <link href="${url}/css/sequence-theme.modern-slide-in.css" rel="stylesheet" media="all">

    <!-- Main style sheet -->
    <link href="${url}/css/style.css" rel="stylesheet">

    <!-- Google Font -->
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <![endif]-->
</head>
<style>
  #aa-footer{
      background-color:#413F3F !important;
  }
</style>
<body class="bg-theme bg-theme1">
<jsp:include page = "/common/web/header/header.jsp" flush = "true" />
<dec:body/>
<jsp:include page = "/common/web/footer/footer.jsp" flush = "true" />
<!-- jQuery library -->

<%--<!--Start of Tawk.to Script-->
<script type="text/javascript">
    var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
    (function(){
        var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
        s1.async=true;
        s1.src='https://embed.tawk.to/5eedbd889e5f69442290f4d1/default';
        s1.charset='UTF-8';
        s1.setAttribute('crossorigin','*');
        s0.parentNode.insertBefore(s1,s0);
    })();
</script>
<!--End of Tawk.to Script-->--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${url}/js/bootstrap.js"></script>
<!-- SmartMenus jQuery plugin -->
<!-- SmartMenus jQuery Bootstrap Addon -->
<!-- To Slider JS -->
<script src="${url}/js/sequence.js"></script>
<script src="${url}/js/sequence-theme.modern-slide-in.js"></script>
<!-- Product view slider -->
<script type="text/javascript" src="${url}/js/jquery.simpleGallery.js"></script>
<script type="text/javascript" src="${url}/js/jquery.simpleLens.js"></script>
<!-- slick slider -->
<script type="text/javascript" src="${url}/js/slick.js"></script>
<!-- Price picker slider -->
<script type="text/javascript" src="${url}/js/nouislider.js"></script>
<script type="text/javascript" src="${url}/js/validate.js"></script>
<!-- Custom js -->
<script type="text/javascript" src="${url}/js/custom.js"></script>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</body>
</html>
