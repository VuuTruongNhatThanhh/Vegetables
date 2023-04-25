<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 12/4/2023
  Time: 8:42 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Thống kê log</title>
  <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../AdminWeb/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="../fontawesome-free-6.2.0-web/css/all.min.css">
  <link rel="stylesheet" href="../AdminWeb/css/style.css">
  <link rel="stylesheet" href="../AdminWeb/css/product.css">
</head>
<body>
<%@include file="include/menu.jsp" %>
<section class="home-section">
  <div class="home-content">
    <div class="manager-product">
      <div class="title">Thống kê log</div>
      <div><a href="/StatisticalLog">Thống kê tài khoản không nằm trong hệ thống</a></div>

      <div><a href="/LoginFailedLog">Thống kê tài khoản đăng nhập sai</a></div>

<%--      <div><a href="/NumberAccessMod">Thống kê số lần truy cập của nhân viên</a></div>--%>



    </div>
  </div>
</section>
</body>
</html>
