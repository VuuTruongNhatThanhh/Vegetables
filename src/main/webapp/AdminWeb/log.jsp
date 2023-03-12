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
            <div class="title">Danh Sách Log</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row element-button">
<%--                        <div class="col-sm-2">--%>
<%--                            <a class="btn btn-add btn-sm" href="/AddUserAdmin" title="Thêm">--%>
<%--                                <i class="fas fa-plus"></i>--%>
<%--                                Thêm tài khoản</a>--%>
<%--                        </div>--%>
                    </div>
                    <table id="table1" class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã log</th>
                            <th scope="col">Mức độ</th>
                            <th scope="col">Mã tk</th>
                            <th scope="col">IP</th>
                            <th scope="col">Vị trí</th>
                            <th scope="col">Nội dung</th>
                            <th scope="col">Ngày</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${lg}" var="lg">
                            <tr style="${lg.level == 0 ? 'color: black': lg.level == 1 ? 'background-color: #99FF66; font-weight: bold': lg.level == 2 ? 'background-color: #33CCFF; font-weight: bold':'background-color: #CC3333 ; font-weight: bold'}" id="${lg.id}">
                                <th scope="row">${lg.id}</th>
                                <td>${lg.level}</td>
                                <td>${lg.src}</td>
                                <td>${lg.userId}</td>
                                <td>${lg.ip}</td>
                                <td>${lg.content}</td>
                                <td>${lg.creatAt}</td>
                                <td>
                                    <button onclick="remove('${lg.id}')" class="btn btn-primary btn-sm trash" type="button"
                                            title="Xóa">
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
                url: "/RemoveUserAdmin",
                type: "get",
                data: {
                    idU: id
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
