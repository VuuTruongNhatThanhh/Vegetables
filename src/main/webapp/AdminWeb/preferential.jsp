<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="title">Danh Sách Mã Ưu Đãi</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row element-button">
                                                <div class="col-sm-2">
                                                    <a class="btn btn-add btn-sm" href="/AddPreferentialAdmin" title="Thêm">
                                                        <i class="fas fa-plus"></i>
                                                        Thêm mã ưu đãi</a>
                                                </div>
                    </div>
                    <table id="table1" class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã ưu đãi</th>
                            <th scope="col">Tiền ưu đãi</th>
                            <th scope="col">Ngày bắt đầu</th>
                            <th scope="col">Ngày kết thúc</th>

                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pr}" var="pr">
                            <tr id="${pr.id}">
                                <th scope="row">${pr.id}</th>
                                <td>${pr.money}</td>
                                <td>${pr.nicedayStart()}</td>
                                <td>${pr.nicedayEnd()}</td>

                                <td>
                                    <button onclick="remove('${pr.id}')" class="btn btn-primary btn-sm trash" type="button"
                                            title="Xóa">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                                            data-toggle="modal" data-target="#ModalUP"><a style="color: white;"
                                                                                          href="UpdatePreferentialAdmin?id=${pr.id}">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#table1').DataTable();
    });

    function remove(id) {
        $.ajax({
                url: "/RemovePreferentialAdmin",
                type: "get",
                data: {
                    idPr: id
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
