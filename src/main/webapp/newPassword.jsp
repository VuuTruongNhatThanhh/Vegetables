<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title>Đặt lại mật khẩu</title>
    <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
    <link
            href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'
            rel='stylesheet'>
    <script type='text/javascript'
            src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <style>
        .placeicon {
            font-family: fontawesome
        }

        .custom-control-label::before {
            background-color: #dee2e6;
            border: #dee2e6
        }
    </style>
</head>
<body oncontextmenu='return false' class='snippet-body bg-info'>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.css">
<div>
    <!-- Container containing all contents -->
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-9 col-lg-7 col-xl-6 mt-5">
                <!-- White Container -->
                <div class="container bg-white rounded mt-2 mb-2 px-0">
                    <!-- Main Heading -->
                    <div class="row justify-content-center align-items-center pt-3">
                        <h1>
                            <strong>Đặt lại mật khẩu</strong>
                        </h1>
                    </div>
                    <div class="pt-3 pb-3">
                        <form class="form-horizontal" action="newPassword" method="POST">
                            <!-- User Name Input -->
                            <div class="form-group row justify-content-center px-3">
                                <div class="col-9 px-0">
                                    <input type="password" name="password" placeholder="&#xf084; &nbsp; Mật khẩu mới"
                                           class="form-control border-info placeicon">
                                </div>
                            </div>
                            <!-- Password Input -->
                            <div class="form-group row justify-content-center px-3">
                                <div class="col-9 px-0">
                                    <input type="password" name="confPassword"
                                           placeholder="&#xf084; &nbsp; Nhập lại mật khẩu mới"
                                           class="form-control border-info placeicon">
                                </div>
                            </div>

                            <!-- Log in Button -->
                            <div class="form-group row justify-content-center">
                                <div class="col-3 px-3 mt-3">
                                    <input type="submit" value="Xác nhận"
                                           class="btn btn-block btn-info">
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- Alternative Login -->
                    <div class="mx-0 px-0 bg-light">

                        <!-- Horizontal Line -->
                        <div class="px-4 pt-5">
                            <hr>
                        </div>
                        <!-- Register Now -->
                        <div class="pt-2">
                            <div class="row justify-content-center">
                                <h5>
                                    Chưa có tài khoản<span><a href="signup.jsp"
                                                                   class="text-danger">Đăng kí ngay!</a></span>
                                </h5>
                            </div>
                            <div
                                    class="row justify-content-center align-items-center pt-4 pb-5">
                                <div class="col-4">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>

</body>
</html>