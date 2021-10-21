<%--
  Created by IntelliJ IDEA.
  User: HIEU
  Date: 1/2/2021
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lỗi</title>
</head>
<body>
    <script type="text/javascript">
        alert('Loi khong the xoa tai khoan dang dang nhap!');
        location.href = "${pageContext.request.contextPath}/admin/admin/list";
    </script>

</body>
</html>
