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
    <link rel="stylesheet" href="../AdminWeb/css/checkout.css">
</head>
<body>
<%@include file="include/menu.jsp" %>
<section class="home-section">
    <div class="home-content">
        <div class="manager-checkout">
            <div class="title">Quản Lý Đơn Hàng</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tab row element-button">
                        <button class="tablinks col-sm-2" id="defaultOpen" onclick="openCity(event, 'tab1')">Chờ xác
                            nhận
                        </button>
                        <button class="tablinks col-sm-2" onclick="openCity(event, 'tab2')">Đã xác nhận</button>
                        <button class="tablinks col-sm-2" onclick="openCity(event, 'tab5')">Đã gửi cho bưu cục</button>
                        <button class="tablinks col-sm-2" onclick="openCity(event, 'tab4')">Đã giao</button>
                        <button class="tablinks col-sm-2" onclick="openCity(event, 'tab3')">Đã hủy</button>
                    </div>
                    <div id="tab1" class="tabcontent">
                        <table id="table-id-1" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Mã đơn hàng</th>
                                <th scope="col">Mã tài khoản</th>
                                <th scope="col">Người nhận</th>
                                <th scope="col">Ngày đặt</th>
                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Địa chỉ</th>
                                <th scope="col">Tổng tiền</th>
                                <th>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${bw}" var="bw">
                                <tr id="${bw.id}">
                                    <th id="id" scope="row">${bw.id}</th>
                                    <td>${bw.idUser}</td>
                                    <td>${bw.getNameReceive()}</td>
                                    <td>${bw.getDate()}</td>
                                    <td>${bw.getPhoneReceive()}</td>
                                    <td><a href="/BillDetailAdmin?id=${bw.id}">Nhấp để xem</a></td>
                                    <td>${bw.getAdressReceive()}, ${bw.getWardReceive()}, ${bw.getDistrictReceive()}, ${bw.getProvinceReceive()}</td>
                                    <td>${bw.total} VND</td>
                                    <td>
                                        <button onclick="confirm('${bw.id}')" class="btn btn-primary btn-sm tick"
                                                type="button" title="check">
                                            <i class="fa-solid fa-check"></i>
                                        </button>
                                        <button onclick="remove('${bw.id}')" class="btn btn-primary btn-sm trash"
                                                type="button" title="Xóa">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <%-------------------------------------------------------------%>
                    <div id="tab2" class="tabcontent">
                        <table id="table-id-2" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Mã đơn hàng</th>
                                <th scope="col">Mã tài khoản</th>
                                <th scope="col">Người nhận</th>
                                <th scope="col">Ngày đặt</th>
                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Địa chỉ</th>
                                <th scope="col">Tổng tiền</th>
                                <th>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="confirm">
                            <c:forEach items="${bco}" var="bw">
                                <tr id="${bw.id}">
                                    <th scope="row">${bw.id}</th>
                                    <td>${bw.idUser}</td>
                                    <td>${bw.getNameReceive()}</td>
                                    <td>${bw.getDate()}</td>
                                    <td>${bw.getPhoneReceive()}</td>
                                    <td><a href="/BillDetailAdmin?id=${bw.id}">Nhấp để xem</a></td>
                                    <td>${bw.getAdressReceive()}, ${bw.getWardReceive()}, ${bw.getDistrictReceive()}, ${bw.getProvinceReceive()}</td>
                                    <td>${bw.total} VND</td>
                                    <td>
                                        <button onclick="movetoship('${bw.id}')" class="btn btn-primary btn-sm tick"
                                                type="button" title="Gửi cho bên giao hàng">
                                            <i class="fa-solid fa-truck"></i>
                                        </button>
                                        <button onclick="remove('${bw.id}')" class="btn btn-primary btn-sm trash"
                                                type="button" title="Xóa">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <%----------------------------------------%>
                    <div id="tab3" class="tabcontent">
                        <table id="table-id" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Mã đơn hàng</th>
                                <th scope="col">Mã tài khoản</th>
                                <th scope="col">Người nhận</th>
                                <th scope="col">Ngày đặt</th>
                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Địa chỉ</th>
                                <th scope="col">Tổng tiền</th>
                                <th scope="col">
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${bca}" var="bw">
                                <tr id="${bw.id}">
                                    <th scope="row">${bw.id}</th>
                                    <td>${bw.idUser}</td>
                                    <td>${bw.getNameReceive()}</td>
                                    <td>${bw.getDate()}</td>
                                    <td>${bw.getPhoneReceive()}</td>
                                    <td><a href="/BillDetailAdmin?id=${bw.id}">Nhấp để xem</a></td>
                                    <td>${bw.getAdressReceive()}, ${bw.getWardReceive()}, ${bw.getDistrictReceive()}, ${bw.getProvinceReceive()}</td>
                                    <td>${bw.total} VND</td>
                                    <td>
                                        <button onclick="remove('${bw.id}')" class="btn btn-primary btn-sm trash"
                                                type="button" title="Xóa">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
<%--                    -----------------------------------%>
                    <div id="tab4" class="tabcontent">
                        <table id="table-id-3" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Mã đơn hàng</th>
                                <th scope="col">Mã tài khoản</th>
                                <th scope="col">Người nhận</th>
                                <th scope="col">Ngày đặt</th>
                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Địa chỉ</th>
                                <th scope="col">Tổng tiền</th>
                                <th>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${bd}" var="bw">
                                <tr id="${bw.id}">
                                    <th scope="row">${bw.id}</th>
                                    <td>${bw.idUser}</td>
                                    <td>${bw.getNameReceive()}</td>
                                    <td>${bw.getDate()}</td>
                                    <td>${bw.getPhoneReceive()}</td>
                                    <td><a href="/BillDetailAdmin?id=${bw.id}">Nhấp để xem</a></td>
                                    <td>${bw.getAdressReceive()}, ${bw.getWardReceive()}, ${bw.getDistrictReceive()}, ${bw.getProvinceReceive()}</td>
                                    <td>${bw.total} VND</td>
                                    <td>

                                        <button onclick="remove('${bw.id}')" class="btn btn-primary btn-sm trash"
                                                type="button" title="Xóa">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
<%--                    --------------------------------------------%>
                    <div id="tab5" class="tabcontent">
                        <table id="table-id-5" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Mã đơn hàng</th>
                                <th scope="col">Mã tài khoản</th>
                                <th scope="col">Người nhận</th>
                                <th scope="col">Ngày đặt</th>
                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Địa chỉ</th>
                                <th scope="col">Tổng tiền</th>
                                <th>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="movetoship">
                            <c:forEach items="${bs}" var="bw">
                                <tr id="${bw.id}">
                                    <th scope="row">${bw.id}</th>
                                    <td>${bw.idUser}</td>
                                    <td>${bw.getNameReceive()}</td>
                                    <td>${bw.getDate()}</td>
                                    <td>${bw.getPhoneReceive()}</td>
                                    <td><a href="/BillDetailAdmin?id=${bw.id}">Nhấp để xem</a></td>
                                    <td>${bw.getAdressReceive()}, ${bw.getWardReceive()}, ${bw.getDistrictReceive()}, ${bw.getProvinceReceive()}</td>
                                    <td>${bw.total} VND</td>
                                    <td>

                                        <button onclick="remove('${bw.id}')" class="btn btn-primary btn-sm trash"
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
    </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script src="../AdminWeb/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
<script>
    $("#table-id").DataTable();
    $("#table-id-1").DataTable();
    $("#table-id-2").DataTable();
    $("#table-id-3").DataTable();
    $("#table-id-5").DataTable();

    function confirm(id) {
        $.ajax({
                url: "/ConfirmBill",
                type: "get",
                data: {
                    id: id
                },
                success: function (data) {
                    $("tr").remove("#" + id)
                    $("#confirm").html(data);
                }
            }
        )
    }

    function remove(id) {
        $.ajax({
                url: "/RemoveBill",
                type: "get",
                data: {
                    id: id
                },
                success: function () {
                    $("tr").remove("#" + id)
                }
            }
        )
    }
    function movetoship(id) {
        $.ajax({
                url: "/ShipBill",
                type: "get",
                data: {
                    id: id
                },
                success: function (data) {
                    $("tr").remove("#" + id)
                    $("#movetoship").html(data);
                }
            }
        )
    }
</script>
</body>
</html>
