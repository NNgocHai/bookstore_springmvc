<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/6/2021
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/template/web" var="url"/>
<!-- start header top  -->
<div class="aa-header-top">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-header-top-area">
                    <!-- start header top left -->
                    <div class="aa-header-top-left">
                        <div class="cellphone hidden-xs">
                            <p><span class="fas fa-home"></span>Website Chi Nhánh Của NXB Kim Đồng</p>
                        </div>
                        <!-- start language -->
                        <div class="aa-language">
                            <div class="dropdown">
                                <a class="btn dropdown-toggle" href="#" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <img src="${url}/img/flag/vietnam.png" alt="english flag">Việt Nam
                                </a>

                            </div>
                        </div>
                        <!-- / language -->

                        <!-- start cellphone -->
                        <!--                <div class="cellphone hidden-xs">
                                          <p><span class="fa fa-phone"></span>00-62-658-658</p>
                                        </div>-->
                        <!-- / cellphone -->
                    </div>
                    <!-- / header top left -->
                    <div class="aa-header-top-right">
                        <ul class="aa-head-top-nav-right">

                            <c:if test="${sessionScope.user != null}">
                                <li>
                                    <a><strong>Chào</strong> ${user }</a>
                                </li>
                                <li class="hidden-xs"><a href="${pageContext.request.contextPath}/web/logout">Đăng xuất</a></li>
                            </c:if>

                            <c:if test="${sessionScope.user == null}">
                                <li class="hidden-xs"><a href="${pageContext.request.contextPath}/web/register">Đăng ký</a></li>
                                <li><a href="${pageContext.request.contextPath}/web/login">Đăng nhập</a></li>
                            </c:if>

                            <!--  data-toggle="modal" data-target="#login-modal" -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
