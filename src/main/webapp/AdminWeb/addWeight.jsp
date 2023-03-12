<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <form class="row" action="${action}" method="post">
                <div class="form-group col-md-3">
                    <label class="control-label">Mã khối lượng </label>
                    <input name="id" class="form-control" type="text" value="${id}" readonly>
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Khối lượng</label>
                    <input name="weight" class="form-control" type="number" value="${w == null?"":w.weight}">
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Số lượng</label>
                    <input type="number" name="amount" class="form-control" value="${w == null?0:w.getCount()}" min="0">
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Giá tiền</label>
                    <input type="number" name="price" class="form-control" value="${w == null?0:w.price}">
                </div>
                <button class="btn btn-save" type="submit"> Lưu lại</button>
                <a class="btn btn-cancel" href="${url}">Hủy bỏ</a>
            </form>
        </div>
    </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
</body>
</html>
