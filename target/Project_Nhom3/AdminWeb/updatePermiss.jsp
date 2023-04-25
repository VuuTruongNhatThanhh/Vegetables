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
      <div class="title">Chỉnh sửa quyền người dùng</div>
      <form method="post" action="UpdatePermissAdmin" class="row">
        <div class="form-group col-md-4">
          <label class="control-label">Mã</label>
          <input name="id" value="${id}" class="form-control" type="text" placeholder="" readonly>
        </div>
        <div class="form-group col-md-4">
          <label class="control-label">Mã tài khoản</label>
          <input name="u_id" class="form-control" type="text" value="${p == null?"":p.u_id}" readonly>
        </div>
    <div class="form-group col-md-4">
        <label class="control-label">Quyền</label>
        <input name="name" class="form-control" type="text" value="${p == null?"":p.getNameRole()}" readonly>
    </div>

        <div class="form-group col-md-4">
          <label class="control-label">Vị trí</label>
          <input name="name" class="form-control" type="text" value="${p == null?"":p.getNameRs()}" readonly>
        </div>
        <div class="form-group col-md-4 ">
          <label class="control-label">Quyền</label>
          <select name="per" class="form-control">
            <option>-- Chọn quyền hạng --</option>
            <option value="0">Toàn quyền</option>
            <option value="1">Xem</option>
            <option value="2">Không có quyền</option>
          </select>
        </div>

        <div style="padding-top: 25px">
        <button class="btn btn-save" type="submit" style="height: 50px">Lưu lại</button>
        <a class="btn btn-cancel" href="/LevelUpUser" style="height: 50px; padding-top: 12px">Hủy bỏ</a>
        </div>
      </form>
    </div>
  </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
</body>
</html>
