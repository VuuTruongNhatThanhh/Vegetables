<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 11/5/2022
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Giới thiệu</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/aboutus.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
<%@include file="Include/header.jsp" %>
<div class="hero-wrap hero-bread" style="background-image: url(images/bg.png);">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <%--                <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>About us</span></p>--%>
                <h1 class="mb-0 bread">TAP ORGANIC</h1>
            </div>
        </div>
    </div>
</div>
<br>
<section class="ftco-section ftco-no-pb ftco-no-pt bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-5 p-md-5 img img-2 d-flex justify-content-center align-items-center imgabout img-fluid"
                 style="background-image: url(images/aboutus.png);">
                </a>
            </div>
            <div class="col-md-7 py-5 wrap-about pb-md-5 ftco-animate">
                <div class="heading-section-bold mb-4 mt-md-5">
                    <div class="ml-md-0">
                        <h2 class="mb-4">Chào mừng đến với TAP ORGANIC</h2>
                    </div>
                </div>
                <div class="pb-md-5">
                    <p>Chúng tôi luôn cung cấp những rau củ quả sạch, an toàn với sức khỏe người dùng</p>
                    <p>Luôn tận tâm, tận tụy. Làm người tiêu dùng hài lòng là mục tiêu mà cửa hàng luôn cố gắng</p>
                    <p>Chúng tôi hoàn toàn hiểu rằng người Việt Nam có quyền sử dụng thực phẩm sạch, dùng thực phẩm sạch
                        là quyền lợi của người dân Việt Nam, trong khi thị trường thực phẩm sạch của nước nhà đang khó
                        khăn...Và cũng từ đó TAP ORGANIC được thành lập, mang sứ mệnh là cung cấp toàn
                        bộ thực phẩm sạch đến khách hàng, một chuỗi cửa hàng ra đời với những sản phẩm chất lượng, giá
                        tốt, và an toàn.</p>
                    <strong>Cam kết với khách hàng: </strong>
                    <li>Luôn luôn đặt uy tín của công ty lên những sản phẩm bán ra.</li>
                    <li>Luôn luôn nâng cao dịch vụ, sản phẩm ngày một tốt hơn.</li>
                    <li>Luôn luôn thấu hiểu mong muốn của khách hàng để đáp ứng nhu cầu ăn sạch, giá cả cạnh tranh, chất
                        lượng sản phẩm tốt.
                    </li>
                    <p><a href="product1.jsp" class="btn btn-black">Ghé cửa hàng</a></p>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="Include/footer.jsp" %>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
