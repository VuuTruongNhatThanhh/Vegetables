<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 11/9/2022
  Time: 5:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang cá nhân</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/userprofile.css" type="text/css">
</head>
<body>
<%@include file="Include/header.jsp" %>
<br>
<div class="tabtab">
    <div class="tab">
        <a href="/UserProfile">
            <button class="button">Thông tin cá nhân</button>
        </a>
        <a href="/OderStatus">
            <button class="button">Tình trạng đơn hàng</button>
        </a>
        <a href="changepassword.jsp">
            <button class="button">Đổi mật khẩu</button>
        </a>
    </div>

    <div class="tabcontent">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <h4>Thông tin cá nhân</h4>
                        <hr>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <form>
                            <div class="form-group row">
                                <p class="col-4">Tên người dùng</p>
                                <div class="col-8">
                                    <c:set var="auth" value="${sessionScope.auth}"/>
                                    <p>${auth.name}</p>
                                </div>
                            </div>
                            <div class="form-group row">
                                <p class="col-4">Số điện thoại</p>
                                <div class="col-8">
                                    <p>${ship.phoneNumber}</p>
                                </div>
                            </div>
                            <div class="form-group row">
                                <p class="col-4">Địa chỉ</p>
                                <div class="col-8">
                                    <p>${ship.getFullAddress()}</p>
                                </div>
                            </div>

                            <div class="form-group row">
                                <p class="col-4">Email</p>
                                <div class="col-8">
                                    <p>${auth.email}</p>
                                </div>
                            </div>
                        </form>
                        <div class="form-group row">
                            <a href="updateuser.jsp">
                                <button class="btn-black"
                                        style="background-color: var(--main-color); color: white">Cập nhật thông
                                    tin cá nhân
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="Include/footer.jsp" %>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/userprofile.js"></script>
</body>
</html>