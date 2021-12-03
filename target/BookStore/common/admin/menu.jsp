<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/template/admin/" var="url"/>
<div id="sidebar-wrapper" data-simplebar="" data-simplebar-auto-hide="true">
    <div class="brand-logo">
        <a href="${pageContext.request.contextPath}/admin/home">
            <img src="${url}images/iconp2.png" class="logo-icon" alt="logo icon">
            <h5 class="logo-text">ADMIN BOOK STORE</h5>
        </a>
    </div>
    <ul class="sidebar-menu do-nicescrol">
        <li class="sidebar-header">MENU ADMIN</li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/home">
                <i class="zmdi zmdi-view-dashboard"></i> <span>Tổng quan</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/admin/list">
                <i class="zmdi zmdi-account-box"></i> <span>Quản lí Admin</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/user/list">
                <i class="zmdi zmdi-accounts"></i> <span>Quản lí User</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/ship/list">
                <i class="zmdi zmdi-accounts"></i> <span>Quản lí Shipper</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/cate/list">
                <i class="zmdi zmdi-grid"></i> <span>Đầu Sách</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/product/list">
                <i class="zmdi zmdi-widgets"></i> <span>Danh Sách Cuốn Sách</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/order/list">
                <i class="zmdi zmdi-shopping-cart"></i> <span>Đơn Hàng</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/admin/chitietdonhang/list">
                <i class="zmdi zmdi-shopping-cart"></i> <span>Chi Tiết Đơn Hàng</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/giaohang/list">
                <i class="zmdi zmdi-view-dashboard"></i> <span>Quản lí Giao Hàng</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/review/list">
                <i class="zmdi zmdi-calendar-check"></i> <span>Review</span>
            </a>
        </li>

    </ul>
</div>
