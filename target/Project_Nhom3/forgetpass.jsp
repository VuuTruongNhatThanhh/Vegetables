<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Quên mật khẩu</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/userprofile.css" type="text/css">
</head>
<body>
<%@include file="Include/header.jsp" %>

<div class="container">
    <div class="row">
        <div col-12>
            <header class="entry-header"><h1 class="entry-title mb uppercase">Thông tin tài khoản</h1></header>
            <div class="woocommerce">
                <div class="woocommerce-notices-wrapper"></div>
                <form action="forgotPassword" method="post" class="woocommerce-ResetPassword lost_reset_password">
                    <p>Quên mật khẩu? Vui lòng nhập tên đăng nhập hoặc địa chỉ email. Bạn sẽ nhận được một liên kết tạo
                        mật khẩu mới qua email.</p>
                    <p class="woocommerce-form-row woocommerce-form-row--first form-row-first">
                        <label class="user-label" for="user_login">Tên đăng nhập hoặc email</label>
                        <input class="woocommerce-Input woocommerce-Input--text input-text" type="text"
                               name="email" id="user_login" autocomplete="username">
                    </p>
                    <div class="clear"></div>
                    <p class="woocommerce-form-row form-row">
                        <input type="hidden" name="wc_reset_password" value="true">
                        <button type="submit" class="woocommerce-Button button" value="Đặt lại mật khẩu">Đặt lại mật
                            khẩu
                        </button>
                    </p>
                    <input type="hidden" id="woocommerce-lost-password-nonce" name="woocommerce-lost-password-nonce"
                           value="dff97d7397">
                    <input type="hidden" name="_wp_http_referer"
                           value="/thong-tin-tai-khoan/quen-mat-khau/">
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="Include/footer.jsp" %>

<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/main.js"></script>
</body>
</html>
