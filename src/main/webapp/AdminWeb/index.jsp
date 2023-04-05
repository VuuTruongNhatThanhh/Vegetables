<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminWeb/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminWeb/css/home.css">
</head>
<body>
<%@include file="include/menu.jsp" %>
<section class="home-section">
    <div class="home-content">
        <div class="view-box">
            <div class="header-box">
                <div class="title">Thống kê</div>
                <div>
                    <select id="select">
                        <option value="0" ${date == 0?"selected":""}>Hôm nay</option>
                        <option value="7" ${date == 7?"selected":""}>Tuần</option>
                        <option value="30" ${date == 30?"selected":""}>Tháng</option>
                    </select>
                </div>
            </div>
            <div class="overview-boxes">
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Tổng Đơn Hàng</div>
                        <div class="number">${totalBill} đơn hàng</div>
                    </div>
                    <i class="fa-solid fa-cart-plus cart"></i>
                </div>
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Hết Hàng</div>
                        <div class="number">${sold} sản phẩm</div>
                    </div>
                    <i class="fa-solid fa-xmark cart two"></i>
                </div>
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Doanh Thu</div>
                        <div class="number">${inCome} VND</div>
                    </div>
                    <i class="fa-solid fa-sack-dollar cart three"></i>
                </div>
                <div class="box">
                    <div class="right-side">
                        <div class="box-topic">Đơn Hàng Hủy</div>
                        <div class="number">${cancel} đơn hàng</div>
                    </div>
                    <i class="fa-solid fa-scroll cart four"></i>
                </div>
            </div>
        </div>
        <div class="sales-boxes">
            <div class="recent-sales box">
                <div class="title">Đơn Hàng Gần Đây</div>
                <div class="sales-details">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã đơn hàng</th>
                            <th scope="col">Tên khách hàng</th>
                            <th scope="col">Ngày đặt</th>
                            <th scope="col">Số điện thoại</th>
                            <th scope="col">Tổng tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach begin="0" end="6" items="${bw}" var="bw">
                            <tr>
                                <th scope="row">${bw.id}</th>
                                <td>${bw.getNameReceive()}</td>
                                <td>${bw.getDate()}</td>
                                <td>${bw.getPhoneReceive()}</td>
                                <td>${bw.total} VND</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="button">
                    <a href="/BillAdmin">Xem tất cả</a>
                </div>
            </div>
            <div class="top-sales box">
                <div class="title">Sản Phẩm Bán Chạy</div>
                <ul class="top-sales-details">
                    <c:forEach items="${hot}" begin="0" end="6" var="hot">
                        <li>
                            <a href="#">
                                <img src="${hot.getPicture(0)}" alt="">
                                <span class="product">${hot.name}</span>
                            </a>
                            <span class="price">${hot.getPriceWeight(0)} VND</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script src="../AdminWeb/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../AdminWeb/js/main.js"></script>
<script>
    document.getElementById('select').addEventListener('change', function () {
        val = $("#select").val();
        url = window.location.pathname;
        window.location.href = url + "?date=" + val;

    });
</script>
</body>
</html>
