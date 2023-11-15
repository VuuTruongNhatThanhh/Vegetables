<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 11/5/2022
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liên hệ với chúng tôi</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/contact.css">
</head>

<body>
<%@include file="Include/header.jsp" %>
<section class="breadcrumb-section set-bg" style="background-image: url(images/bg.png);">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>Liên hệ với chúng tôi</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.jsp">Trang chủ</a>
                        <span>Liên hệ với chúng tôi</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="contact spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                <div class="contact__widget">
                    <span><i class="fa fa-phone"></i></span>
                    <h4>Số điện thoại</h4>
                    <p>+84-123-4567</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                <div class="contact__widget">
                    <span><i class="fa fa-map-marker"></i></span>
                    <h4>Địa chỉ</h4>
                    <p>Đường số 6, khu phố 6, phường Linh Trung, TP. Thủ Đức</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                <div class="contact__widget">
                    <span><i class="fa fa-clock"></i></span>
                    <h4>Thời gian mở cửa</h4>
                    <p>07:00 sáng đến 22:00 tối</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                <div class="contact__widget">
                    <span><i class="fa fa-envelope"></i></span>
                    <h4>Email</h4>
                    <p>taporganic@gmail.com</p>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="map">
    <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.3761549172514!2d106.76370251458978!3d10.858968092265552!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175279c63c98c2b%3A0x6ce22fc33c34f287!2zxJDGsOG7nW5nIFPhu5EgNiwgTGluaCBUcnVuZywgVGjhu6cgxJDhu6ljLCBUaMOgbmggcGjhu5EgSOG7kyBDaMOtIE1pbmgsIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1667625189855!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"
            height="500" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
    <div class="map-inside">
        <i class="fa fa-map-pin"></i>
        <div class="inside-widget">
            <h4>Việt Nam</h4>
            <ul>
                <li>Điện thoại: +84-123-4567</li>
                <li>Địa chỉ: đường số 6, phường Linh Trung, TP. Thủ Đức</li>
            </ul>
        </div>
    </div>
</div>

<div class="contact-form spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="contact__form__title">
                    <h2>Để lại lời nhắn</h2>
                </div>
            </div>
        </div>
        <form action="#">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <input type="text" placeholder="Họ và tên">
                </div>
                <div class="col-lg-6 col-md-6">
                    <input type="text" placeholder="Email của bạn">
                </div>
                <div class="col-lg-12 text-center">
                    <textarea placeholder="Để lại lời nhắn"></textarea>
                    <button type="submit" class="btn btn-success">GỬI LỜI NHẮN</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="Include/footer.jsp" %>
</body>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="js/main.js"></script>
</html>
