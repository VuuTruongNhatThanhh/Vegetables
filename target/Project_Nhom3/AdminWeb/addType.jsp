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
          <label class="control-label">Mã</label>
          <input name="id" value="${id}" class="form-control" type="text" placeholder="" readonly>
        </div>
        <div class="form-group col-md-4">
          <label class="control-label">Tên loại</label>
          <input name="name" class="form-control" type="text">
        </div>
        <div class="form-group col-md-4 ">
          <label class="control-label">Phân loại cha</label>
          <select name="typeFather" class="form-control">
            <option>-- Chọn loại sản phẩm --</option>
            <option value="LSP00001">Rau</option>
            <option value="LSP00005">Củ</option>
            <option value="LSP00006">Quả</option>
          </select>
        </div>


        <button class="btn btn-save" type="submit">Lưu lại</button>
        <a class="btn btn-cancel" href="/TypeAdmin">Hủy bỏ</a>
      </form>
    </div>
  </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
</body>
</html>
