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
            <div class="title">Danh sách khối lượng ${title}</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-add btn-sm" href="/AddWeightAdmin?id=${id}&url=<%=url%>" title="Thêm">
                                <i class="fas fa-plus"></i>
                                Thêm khối lượng</a>
                        </div>
                    </div>
                    <table id="table-id" class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã khối lượng</th>
                            <th scope="col">Khối lượng</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Giá tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${weights}" var="w">
                            <tr id="${w.id}">
                                <th scope="row">${w.id}</th>
                                <td>${w.weight}</td>
                                <td>${w.getCount()}</td>
                                <td>${w.price}</td>
                                <td>
                                    <button onclick="removeW('${w.id}')" class="btn btn-primary btn-sm trash"
                                            type="button" title="Xóa">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                                            data-toggle="modal" data-target="#ModalUP"><a style="color: white;"
                                                                                          href="UpdateWeightAdmin?id=${w.id}&url=<%=url%>">
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
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    function removeW(id) {
        $.ajax({
                url: "/RemoveWeightAdmin",
                type: "get",
                data: {
                    idW: id
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
