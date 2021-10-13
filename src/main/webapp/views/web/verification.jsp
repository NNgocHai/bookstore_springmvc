<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 1/13/2021
  Time: 5:58 PM
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
                                <h4>Xác nhận đăng ký</h4>
                                <form name="formRegister" class="aa-login-form" method="post" action="${pageContext.request.contextPath}/web/verify">
                                    <label>Mã<span>*</span></label>
                                    <input type="text" placeholder="Mã code" name="ma_code" required>
                                    <p style="color:#ff0000; display:block">
                                        ${errMessage}
                                    </p>
                                    <button type="reset" class="aa-browse-btn" value="Reset">Đặt lại</button>
                                    <button type="submit" class="aa-browse-btn">Xác nhân</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

