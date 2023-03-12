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
<% String uri = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
    String prmt = (String) request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
    prmt = (prmt == null) ? "" : prmt.replaceAll("&", "-");
    String url = uri + "?" + prmt;
%>
<section class="home-section">
    <div class="home-content">
        <div class="manager-product">
            <div class="title">Danh Sách Ảnh</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-add btn-sm" href="/AddPicAdmin?id=${id}" title="Thêm">
                                <i class="fas fa-plus"></i>
                                Thêm ảnh</a>
                        </div>
                    </div>
                    <table id="table-id" class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã ảnh</th>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pics}" var="p">
                            <tr id="${p.id}">
                                <th scope="row">${p.id}</th>
                                <td>
                                    <img width="100px" height="100px" class="img-fluid"
                                         src="${p.url}"></li>
                                </td>
                                <td>
                                    <button onclick="removeP('${p.id}')" class="btn btn-primary btn-sm trash"
                                            type="button" title="Xóa">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script>

    function removeP(id) {
        $.ajax({
                url: "/RemovePicAdmin",
                type: "get",
                data: {
                    idP: id
                },
                success: function () {
                    $("tr").remove("#" + id)
                }
            }
        )
    }
</script>
</body>
</html>
