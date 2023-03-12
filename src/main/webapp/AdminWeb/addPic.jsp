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
            <div class="title">Thêm ảnh</div>
            <form class="row" action="AddPicAdmin" method="post" enctype="multipart/form-data">
                <input class="form-control" type="file" name="fileName" multiple>
                <button class="btn btn-save" type="submit"> Lưu lại</button>
                <a class="btn btn-cancel" href="/PicAdmin?id=${id}">Hủy bỏ</a>
            </form>
        </div>
    </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
</body>
</html>
