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
            <div class="title">Thêm Tài Khoản</div>
            <form method="post" action="/AddUserAdmin" class="row">
                <div class="form-group col-md-4">
                    <label class="control-label">Mã tài khoản </label>
                    <input name="id" value="${id}" class="form-control" type="text" placeholder="" readonly>
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label">Tên người dùng</label>
                    <input name="name" class="form-control" type="text">
                </div>
                <div class="form-group col-md-4 ">
                    <label class="control-label">Phân quyền</label>
                    <select name="role" class="form-control">
                        <option>-- Chọn quyền --</option>
                        <option value="0">Admin</option>
                        <option value="1">Mod</option>
                        <option value="2">User</option>
                    </select>
                </div>
                <div class="form-group col-md-6 ">
                    <label class="control-label">Email</label>
                    <input name="email" class="form-control" type="email">
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label">Mật khẩu</label>
                    <input name="pass" class="form-control" type="text">
                </div>
                <button class="btn btn-save" type="submit">Lưu lại</button>
                <a class="btn btn-cancel" href="/UserAdmin">Hủy bỏ</a>
            </form>
        </div>
    </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
</body>
</html>
