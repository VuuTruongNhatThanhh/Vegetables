<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý</title>
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
            <div class="title">Danh Sách Sản Phẩm</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-add btn-sm" href="/AddProductAmin" title="Thêm">
                                <i class="fas fa-plus"></i>
                                Thêm sản phẩm</a>
                        </div>
                    </div>
                    <table id="table-id" class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã sản phẩm</th>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Khối lượng</th>
                            <th scope="col">Giảm giá</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col">Ngày thêm</th>
                            <th scope="col">Tình trạng</th>
                            <th scope="col">Loại</th>
                            <th scope="col">Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${products}" var="p">
                            <tr id="${p.id}">
                                <th scope="row">${p.id}</th>
                                <td>
                                    <a href="/PicAdmin?id=${p.id}">Hiện có ${p.pics.size()} ảnh</a>
                                </td>
                                <td>${p.name}</td>
                                <td>
                                    <a href="/WeightAdmin?id=${p.id}">Hiện có ${p.weights.size()} khối lượng</a>
                                    <c:if test="${!p.haveWeightOver()}">
                                        <span class="badge bg-success">Còn hàng</span>
                                    </c:if>
                                    <c:if test="${p.haveWeightOver()}">
                                        <span class="badge bg-danger">Hết hàng</span>
                                    </c:if>
                                </td>
                                <td>${p.discount}<span>%</span></td>
                                <td>${p.describe}</td>
                                <td>${p.getDate()}</td>
                                <c:if test="${p.isDelete()}">
                                    <td><span class="badge bg-success">Còn bán</span></td>
                                </c:if>
                                <c:if test="${!p.isDelete()}">
                                    <td><span class="badge bg-danger">Ngừng bán</span></td>
                                </c:if>
                                <td>${p.getNameType()}</td>
                                <td>
                                    <button onclick="removeP('${p.id}')" class="btn btn-primary btn-sm trash"
                                            type="button" title="Xóa">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                                            data-toggle="modal" data-target="#ModalUP"><a style="color: white;"
                                                                                          href="UpdateProductAdmin?id=${p.id}&idType=${p.idType}">
                                        <i class="fas fa-edit"></i></a>
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
<script src="${pageContext.request.contextPath}/AdminWeb/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    $("#table-id").DataTable();

    function removeP(id) {
        $.ajax({
                url: "/RemoveProductAdmin",
                type: "get",
                data: {
                    idP: id
                },
                success: function (data) {
                    if (data === "") {
                        $("tr").remove("#" + id)
                    } else {
                        $("#" + id).html(data)
                    }
                }
            }
        )
    }
</script>
</body>
</html>
