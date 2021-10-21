<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/11/2021
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/template/web" var="url"/>
<!-- Cart view section -->
<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="row">

                        <div class="col-md-8 col-md-push-2">
                            <div class="aa-myaccount-register">
                                <h4>Đăng ký</h4>
                                <form name="formRegister" class="aa-login-form" method="post" action="${pageContext.request.contextPath}/web/register">
                                    <p style="color:#06f10a; display:block">
                                        ${success}
                                    </p>
                                    <p style="color:#ff0000; display:block">
                                        ${errMessage}
                                    </p>
                                    <label>Họ tên<span>*</span></label>
                                    <input type="text" placeholder="Name" name="name" value="${hoten}" required>
                                    <label>Email <span>*</span></label>
                                    <input style="display: block;width: 100%;height: 40px;border: 1px solid #ccc; padding: 10px" type="email"  placeholder="useremail@gmail.com" name="email" value="${gmail}" required>
                                    <p style="color:#ff0000; display:block">
                                        ${errGmail}
                                    </p>
                                    <label>Số điện thoại<span>*</span></label>
                                    <input type="text" placeholder="Số điện thoại" name="sdt" value="${sdt}" required>
                                    <p style="color:#ff0000; display:block">
                                        ${errSdt}
                                    </p>
                                    <label>Username<span>*</span></label>
                                    <input type="text" placeholder="Username" name="username" value="${taikhoan}" required>
                                    <p style="color:#ff0000; display:block">
                                        ${errTaikhoan}
                                    </p>
                                    <label>Mật khẩu<span>*</span></label>
                                    <input type="password" placeholder="Password" name="password" value="${matkhau}" required>
                                    <input type="text" style="display: none" placeholder="Password" name="vitien" id="vitien">

                                    <%--<p style="color:red; display: block;"><%=(request.getAttribute("errMessage") == null) ? ""
                                            : request.getAttribute("errMessage")%></p>
                                    <p style="color:red; display: block;"><%=(request.getAttribute("Message") == null) ? ""
                                            : request.getAttribute("Message")%></p>--%>

                                    <button type="reset" class="aa-browse-btn" value="Reset">Đặt lại</button>
                                    <button type="submit" class="aa-browse-btn">Đăng ký</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Cart view section -->
