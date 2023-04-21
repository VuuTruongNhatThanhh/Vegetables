<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý</title>
  <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../fontawesome-free-6.2.0-web/css/all.min.css">
  <link rel="stylesheet" href="../AdminWeb/css/style.css">
  <link rel="stylesheet" href="../AdminWeb/css/product.css">
</head>
<body>
<%@include file="include/menu.jsp" %>
<section class="home-section">
  <div class="home-content">
    <div class="manager-product">
      <div class="title">${title}</div>
      <form method="post" action="${action}" class="row">
        <div class="form-group col-md-4">
          <label class="control-label">Mã ưu đãi</label>
          <input name="id" value="${p == null?"":p.id}" class="form-control" type="text" placeholder="">
        </div>
        <div class="form-group col-md-4">
          <label class="control-label">Tiền ưu đãi</label>
          <input name="price" class="form-control" type="text" value="${p == null?"":p.money}">
        </div>
        <div class="form-group col-md-4">
          <label class="control-label">Ngày bắt đầu (YYYY/MM/DD)</label>
          <input name="dayStart" class="form-control" type="text" value="${p == null?"":p.dayStart}">
        </div>
        <div class="form-group col-md-4">
          <label class="control-label">Ngày kết thúc (YYYY/MM/DD)</label>
          <input name="dayEnd" class="form-control" type="text" value="${p == null?"":p.dayEnd}">
        </div>

<div style="padding-top: 32px">
        <button class="btn btn-save" type="submit" style="height: 39px">Lưu lại</button>
        <a class="btn btn-cancel" href="/PreferentialAdmin" style="height: 39px">Hủy bỏ</a></div>
      </form>
    </div>
  </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
</body>
</html>
