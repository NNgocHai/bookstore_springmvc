<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<c:url value ="/template/admin" var="url"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Admin BOOK STORE</title>


    <link href="${url}/css/pace.min.css" rel="stylesheet" />
    <script src="${url}/js/pace.min.js"></script>
    <link rel="stylesheet" href="${url}/plugins/summernote/dist/summernote-bs4.css"/>
    <link rel="icon" href="${url}/images/favicon.ico" type="image/x-icon">
    <link href="${url}/plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
    <link href="${url}/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
    <link href="${url}/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${url}/css/animate.css" rel="stylesheet" type="text/css" />
    <link href="${url}/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${url}/css/sidebar-menu.css" rel="stylesheet" />
    <link href="${url}/css/app-style.css" rel="stylesheet" />
    <style>
        .tablee td, .tablee th{
            white-space: initial !important;
            text-align: center;
        }
        .tablee tr td:first-child, .tablee tr th:first-child, .tablee tr td:nth-child(2), .tablee tr th:nth-child(2) {
            padding: 2%;
        }
        .limit-width {
            width: 15%;
        }
        select option{
            background-color: white !important;
        }
        input[type="checkbox"]{
            width: 30px; /*Desired width*/
            height: 30px; /*Desired height*/
        }
    </style>
</head>
<body class="bg-theme bg-theme1">
        <jsp:include page = "/common/admin/header.jsp" flush = "true" />
        <dec:body/>
        <jsp:include page = "/common/admin/footer.jsp" flush = "true" />
        <script src="${url}/js/jquery.min.js"></script>
        <script src="${url}/js/popper.min.js"></script>
        <script src="${url}/js/bootstrap.min.js"></script>
        <script src="${url}/plugins/simplebar/js/simplebar.js"></script>
        <script src="${url}/js/sidebar-menu.js"></script>
        <script src="${url}/js/jquery.loading-indicator.js"></script>
        <script src="${url}/js/app-script.js"></script>
        <script src="${url}/plugins/Chart.js/Chart.min.js"></script>
        <script src="${url}/js/index.js"></script>
        <script src="${url}/plugins/summernote/dist/summernote-bs4.min.js"></script>
</body>
</html>