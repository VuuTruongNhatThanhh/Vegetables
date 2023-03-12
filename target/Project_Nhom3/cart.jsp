<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/cart.css">
</head>
<body>
<%@include file="Include/header.jsp" %>
<div id="main-cart" class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 cart-left">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 title bold"> BẠN CÓ CẦN THÊM?</div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div id="slide-show" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <%--                        element 1--%>
                            <div class="carousel-item active">
                                <div class="card-element">
                                    <div class="card-content">
                                        <div class="image">
                                            <a href="#"><img src="images/cu/carot.png"> </a>
                                        </div>
                                        <div class="info">
                                            <h4 class="heading">
                                                <a href="#">Cà rốt</a>
                                            </h4>
                                            <h5 class="price">25.000 VND</h5>
                                            <h5 class="button">
                                                <button class="btn btn-cart">Thêm</button>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="card-content">
                                        <div class="image">
                                            <a href="#">
                                                <img src="images/qua/tao.png">
                                            </a>
                                        </div>
                                        <div class="info">
                                            <h4 class="heading">
                                                <a href="#">Táo</a>
                                            </h4>
                                            <h5 class="price">25.000 VND</h5>
                                            <h5 class="button">
                                                <button class="btn btn-cart">Thêm</button>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%--                            element 2--%>
                            <div class="carousel-item">
                                <div class="card-element">
                                    <div class="card-content">
                                        <div class="image">
                                            <a href="#">
                                                <img src="images/product-4.jpg">
                                            </a>
                                        </div>
                                        <div class="info">
                                            <h4 class="heading">
                                                <a href="#">Bắp cải tím</a>
                                            </h4>
                                            <h5 class="price">25.000 VND</h5>
                                            <h5 class="button">
                                                <button class="btn btn-cart">Thêm</button>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="card-content">
                                        <div class="image">
                                            <a href="#">
                                                <img src="images/qua/cam.png">
                                            </a>
                                        </div>
                                        <div class="info">
                                            <h4 class="heading">
                                                <a href="#">Cam</a>
                                            </h4>
                                            <h5 class="price">25.000 VND</h5>
                                            <h5 class="button">
                                                <button class="btn btn-cart">Thêm</button>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%-- element 3--%>
                            <div class="carousel-item">
                                <div class="card-element">
                                    <div class="card-content">
                                        <div class="image">
                                            <a href="#"><img src="images/qua/chuoi.png"></a>
                                        </div>
                                        <div class="info">
                                            <h4 class="heading">
                                                <a href="#">Chuối</a>
                                            </h4>
                                            <h5 class="price">25.000 VND</h5>
                                            <h5 class="button">
                                                <button class="btn btn-cart">Thêm</button>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="card-content">
                                        <div class="image">
                                            <a href="#">
                                                <img src="images/cu/cucaitrang.png">
                                            </a>
                                        </div>
                                        <div class="info">
                                            <h4 class="heading">
                                                <a href="#">Củ cải trắng</a>
                                            </h4>
                                            <h5 class="price">25.000 VND</h5>
                                            <h5 class="button">
                                                <button class="btn btn-cart">Thêm</button>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#slide-show" role="button"
                           data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"><i
                                    class="fa-solid fa-angle-left"></i></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#slide-show" role="button"
                           data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"><i
                                    class="fa-solid fa-angle-right"></i></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 title-1 bold">GIỎ HÀNG</div>
                <div class="allCart">
                    <c:forEach items="${sessionScope.items}" var="i">
                        <div class="product-info row">
                            <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9 item-2">
                                <div class="product-content">
                                    <div class="image">
                                        <a href="#">
                                            <img src="${i.getUrlPic()}">
                                        </a>
                                    </div>

                                    <div class="info">
                                        <h4 class="heading">
                                            <a href="#">${i.product.name}</a>
                                        </h4>
                                        <h5 class="price">
                                        <span class="saleoff">
                                            <strong>Giá:</strong>
                                              ${i.getPricePW()} VND
                                        </span>
                                        </h5>

                                        <div class="row bottom">
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                                <h5>Khối lượng: ${i.weight.weight}G</h5>
                                            </div>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                                <h5>Số lượng</h5>
                                                <div class="buttons_added">
                                                    <a href="/UpdateCart?num=-1&idP=${i.product.id}&idW=${i.weight.id}">
                                                        <button class="minus is-form">-</button>
                                                    </a>

                                                    <input aria-label="quantity" class="input-qty" max="10"
                                                           min="1" name="" type="number" value="${i.quanity}">
                                                    <a href="/UpdateCart?num=1&idP=${i.product.id}&idW=${i.weight.id}">
                                                        <button class="plus is-form">+</button>
                                                    </a>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class=" col-xs-12 col-sm-3 col-md-3 col-lg-3 item-2-1">
                                <div class="price">${i.price} VND</div>
                                <div class="status">Còn hàng</div>
                                <a href="/RemoveItem?idP=${i.product.id}&idW=${i.weight.id}" class="button">
                                    <button class="btn btn-delete remove-product-in-cart"><i
                                            class="fa-solid fa-trash-can"></i></button>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${size != 0}">
                    <a href="/RemoveItem" class="col-xs-6 col-sm-6 col-md-6 col-lg-6 title-3">
                        <button class="btn btn-delall btn-clearAll">XÓA HẾT</button>
                    </a>
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 title-3">
                        <a href="/ListProduct" class="btn btn-cont">QUAY LẠI MUA HÀNG</a>
                    </div>
                </c:if>
                <c:if test="${size == 0}">
                    <h2 style="margin: auto">Không có gì trong giỏ hàng</h2>
                    <div style="margin: 0" class="col-xs-6 col-sm-6 col-md-6 col-lg-6 title-3">
                        <a href="/ListProduct" class="btn btn-cont">QUAY LẠI MUA HÀNG</a>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 cart-right">
            <form method="post" action="/ShoppingCart">
                <ul class="list-group">
                    <li class="list-group-item title bold">ĐƠN HÀNG</li>
                    <li class="list-group-item divider"></li>
                    <li class="list-group-item title-1 bold">NHẬP MÃ KHUYẾN MÃI</li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <input name="sale" type="text" placeholder="Mã ưu đãi"
                                   class="form-control text-uppercase">
                            <button class="input-group-btn btn">
                                <span class=" btn-apply" type="button">ÁP DỤNG</span>
                            </button>
                        </div>
                        <p STYLE="color:red;">${error==null?"":error}</p>
                    </li>
                    <li class="list-group-item divider"></li>
                    <li class="list-group-item text-1">
                        <span class="title-3 bold">Đơn hàng</span>
                        <span class="title-3-1 totalPriceOfCart bold">${sessionScope.cart.totalMoneyItem()} VND</span>
                    </li>
                    <li class="list-group-item text-2">
                        <span class="title-3 bold">Giảm</span>
                        <span class="title-4-1 totalDiscountOfCart">${sessionScope.cart.sale} VND</span>
                    </li>
                    <li class="list-group-item divider"></li>

                    <li class="list-group-item">
                        <span class="title-5 bold">TẠM TÍNH</span>
                        <span class="title-5-1 tempPrice bold">${sessionScope.cart.total < 0? 0.0:sessionScope.cart.total} VND</span>
                    </li>
                    <li class="list-group-item">
                        <a href="/CheckOut" class="btn btn-cart to-checkout"> THANH TOÁN</a>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
<%@include file="Include/footer.jsp" %>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/main.js"></script>
</body>
</html>