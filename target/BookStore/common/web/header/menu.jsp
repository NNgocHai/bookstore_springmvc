<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 11/24/2020
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/template/web" var="url"/>
<section id="menu">
    <div class="container">
        <div class="menu-area">
            <!-- Navbar -->
            <div class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse">
                    <!-- Left nav -->
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/web/home">TRANG CHỦ</a></li>
                        <li><a href="${pageContext.request.contextPath}/web/introduce">GIỚI THIỆU</a></li>
                        <li><a href="${pageContext.request.contextPath}/web/product">XEM SÁCH</a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/web/contact">LIÊN HỆ</a></li>
                        <li class="aa-search"><!-- search box -->
                            <a class="aa-search-box">
                                <form action="${pageContext.request.contextPath}/web/product/Search" method="Get">
                                    <input type="text" name="TuKhoa" id="" placeholder="Tìm kiếm sách..">
                                    <button class="serach-box"><span class="fa fa-search"></span></button>
                                </form>
                            </a>
                            <!-- / search box -->
                        </li>
                    </ul>
                </div><!--/.nav-collapse -->

            </div>
        </div>
    </div>
</section>