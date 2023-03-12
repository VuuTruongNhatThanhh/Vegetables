<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 12/7/2022
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/userprofile.css" type="text/css">
</head>
<body>
<%
    String error = (String) request.getAttribute("ms");

    error = (error == null) ? "" : error;

%>
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
        <a href="#">
            <button class="button">Đổi mật khẩu</button>
        </a>
    </div>
    <div class="tabcontent">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <h4>Đổi mật khẩu</h4>
                        <hr>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <form method="post" action="/ChangePass">
                            <div class="form-group row">
                                <label class="col-4 col-form-label">Mật khẩu hiện tại</label>
                                <div class="col-8">
                                    <input name="current_pass"
                                           class="form-control here" required="required" type="password">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-4 col-form-label">Mật khẩu mới</label>
                                <div class="col-8">
                                    <input name="new_pass" class="form-control here"
                                           type="password">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-4 col-form-label">Nhập lại mật khẩu mới</label>
                                <div class="col-8">
                                    <input name="conf_pass"
                                           class="form-control here" type="password">
                                </div>
                            </div>
                            <p style="color: red;font-size: 18px"><%=error%></p>
                            <div class="form-group row">
                                <div class="offset-4 col-8">
                                    <button name="submit" type="submit" class="btn btn-black"
                                            style="background-color: var(--main-color); color: white">
                                        Hoàn tất
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="Include/footer.jsp" %>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/main.js"></script>
<script src="js/userprofile.js"></script>
</body>
</html>
