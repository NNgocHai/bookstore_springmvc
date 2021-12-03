<%@ page contentType="text/html;charset=UTF-8"%>

<%
    if (session.getAttribute("user_admin") == null){
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<div class="content-wrapper">
    <div class="col-lg-12">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h5 class="card-title">Phân công giao hàng</h5>
                    <h1 class="add-catalog">Không còn đơn hàng nào để phân công hôm nay!</h1>
                </div>
            </div>
        </div>
    </div>
</div>

