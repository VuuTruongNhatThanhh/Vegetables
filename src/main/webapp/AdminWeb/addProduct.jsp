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
                    <label class="control-label">Mã sản phẩm </label>
                    <input name="id" class="form-control" type="text" placeholder="SP00001" value="${id}" readonly>
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Tên sản phẩm</label>
                    <input name="name" class="form-control" type="text" value="${p == null?"":p.name}">
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Danh mục</label>
                    <select name="type" class="form-control">
                        <option>-- Chọn danh mục --</option>
                        <c:forEach items="${types}" var="t">
                            <c:if test="${t.id == idType}">
                                <option value="${t.id}" selected>${t.name}</option>
                            </c:if>
                            <c:if test="${t.id != idType}">
                                <option value="${t.id}">${t.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label class="control-label">Giảm giá (%)</label>
                    <input type="number" name="dis" class="form-control" value="${p == null?0:p.getDis()}" min="0" max="100">
                </div>
                <div class="form-group col-md-12">
                    <label class="control-label">Mô tả sản phẩm</label>
                    <textarea class="form-control" name="dicription_product" id="mota">${p == null?"":p.describe}</textarea>
                </div>
                <button class="btn btn-save" type="submit"> Lưu lại</button>
                <a class="btn btn-cancel" href="/ProductAdmin">Hủy bỏ</a>
            </form>
        </div>
    </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
</body>
</html>
