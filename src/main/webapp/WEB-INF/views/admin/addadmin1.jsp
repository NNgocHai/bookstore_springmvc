
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  //    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
//    response.setHeader("Pragma" , "no-cache");
//    response.setHeader("Expires" , "0");


  if (session.getAttribute("user_admin") == null){
    response.sendRedirect(request.getContextPath() + "/admin/login");
  }
%>
<html>
<head>
  <title>Thêm Admin</title>
</head>
<body>
<div class="content-wrapper">
  <div class="container-fluid">

    <div class="row mt-3">
      <div class="col-lg-8">
        <div class="card">
          <div class="card-body">
            <div class="card-title">Thêm Admin</div>
            <hr>
            <form action="${pageContext.request.contextPath}/admin/admin/add" method="post">
              <div><b> <span style="color:red"> ${errorMessage}</span></b></div>
              <div class="form-group">
                <label for="input-1">Username</label>
                <input type="text" class="form-control" id="input-1" placeholder="Username"  name="admin-username" required>
              </div>
              <div class="form-group">
                <label for="input-2">Password</label>
                <input type="password" class="form-control" id="input-2" placeholder="Password" name="admin-password" required>
                <input type="checkbox" onclick="myFunction1()">Hiển thị mật khẩu
                <script>function myFunction1() {
                  var x = document.getElementById("input-2");
                  if (x.type === "password") {
                    x.type = "text";
                  } else {
                    x.type = "password";
                  }
                }</script>
              </div>
              <div class="form-group">
                <label for="input-3">Tên Admin</label>
                <input type="text" class="form-control" id="input-3" placeholder="Tên Admin" name="admin-name" required>
              </div>
              <div class="form-group">
                <label for="input-4">Email</label>
                <input type="email" class="form-control" id="input-4" placeholder="Email" name="admin-email" required>
              </div>
              <div class="form-footer">
                <button type="reset" class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/admin/list">Hủy</a></button>
                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Thêm</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="overlay toggle-menu"></div>
  </div>
</div>

</body>
</html>

