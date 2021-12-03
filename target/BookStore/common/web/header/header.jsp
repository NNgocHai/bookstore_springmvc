<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/template/web" var="url"/>

<header id="aa-header">
    <jsp:include page = "topheader.jsp" flush = "true" />
    <!-- start header bottom  -->
    <jsp:include page = "bottomheader.jsp" flush = "true" />
    <!-- / header bottom  -->
</header>
<!-- menu -->
<jsp:include page = "menu.jsp" flush = "true" />
<!-- / menu -->
<!-- wpf loader Two -->
<div id="wpf-loader-two">
    <div class="wpf-loader-two-inner">
        <span>Loading...</span>
    </div>
</div>
<!-- / wpf loader Two -->
<!-- SCROLL TOP BUTTON -->
<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
<!-- END SCROLL TOP BUTTON -->
