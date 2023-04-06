<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link rel="stylesheet" href="css/home.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">

</head>
<body>
<%@include file="Include/header.jsp" %>
<section id="home-section" class="hero pt-5">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <%@include file="Include/menu.jsp" %>
            </div>
            <div class="col-9">
                <div id="banner" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="d-block w-100" src="images/bg-1.png">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="images/bg-2.png">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="images/bg-3.png">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="images/bg-4.png">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#banner" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#banner" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<br>
<section class="ftco-section">
    <%--    <%for(Product p : (List<Product>)request.getAttribute("list"))%>--%>
    <div class="container">
        <%--        <%=user_name %>--%>
        <%--        <%=user_email %>--%>
        <div class="row justify-content-center mb-3 pb-3">
            <div class="col-md-12 heading-section text-center ftco-animate">
                <h2 class="mb-4">Sản phẩm hot</h2>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <c:forEach items="${listH}" var="p">
                <div class="col-lg-3 ftco-animate">
                    <div class="product">
                        <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"
                           class="img-prod"><img class="img-fluid"
                                                 src="${p.getPicture(0)}"
                                                 alt="Colorlib Template">
                            <span class="status">${p.getDiscount() == 0? "": p.getDiscount()}</span>
                            <div class="overlay"></div>
                        </a>
                        <div class="text py-3 pb-4 px-3 text-center">
                            <h3>
                                <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"></a>${p.name}
                            </h3>
                            <div class="d-flex">
                                <div class="pricing">
                                    <p class="price">
                                        <c:if test="${p.getDiscount() != 0}">
                                            <span class="mr-2 price-dc">${p.getPriceWeight(0)} VND</span>
                                            <span class="price-sale">${p.priceDiscount(0)} VND</span>
                                        </c:if>
                                        <c:if test="${p.getDiscount() == 0}">
                                            <span class="price-sale">${p.getPriceWeight(0)} VND</span>
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                            <div class="bottom-area d-flex px-3">
                                <div class="m-auto d-flex">
                                    <c:if test="${p.getAmountWeight(0) != 0}">
                                        <a href="/AddCart?url=<%=url%>&idP=${p.id}&idW=${p.getIdWeight(0)}"
                                           class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span><i class="ion-ios-cart"></i></span>
                                        </a>
                                    </c:if>
                                    <c:if test="${p.getAmountWeight(0) == 0}">
                                        <p style="background: #82cd47;padding: 10px;color: white;"
                                           class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span>HẾT HÀNG</span>
                                        </p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</section>


<%--------------------------------------------------------------%>
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center mb-3 pb-3">
            <div class="col-md-12 heading-section text-center ftco-animate">
                <%--                <span class="subheading">Featured Products</span>--%>
                <h2 class="mb-4">Ưu đãi</h2>
                <%--                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>--%>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <c:forEach items="${listD}" var="p">
                <div class="col-lg-3 ftco-animate">
                    <div class="product">
                        <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"
                           class="img-prod"><img class="img-fluid"
                                                 src="${p.getPicture(0)}"
                                                 alt="Colorlib Template">
                            <span class="status">${p.getDiscount() == 0? "": p.getDiscount()}</span>
                            <div class="overlay"></div>
                        </a>
                        <div class="text py-3 pb-4 px-3 text-center">
                            <h3>
                                <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"></a>${p.name}
                            </h3>
                            <div class="d-flex">
                                <div class="pricing">
                                    <p class="price">
                                        <c:if test="${p.getDiscount() != 0}">
                                            <span class="mr-2 price-dc">${p.getPriceWeight(0)} VND</span>
                                            <span class="price-sale">${p.priceDiscount(0)} VND</span>
                                        </c:if>
                                        <c:if test="${p.getDiscount() == 0}">
                                            <span class="price-sale">${p.getPriceWeight(0)} VND</span>
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                            <div class="bottom-area d-flex px-3">
                                <div class="m-auto d-flex">
                                    <c:if test="${p.getAmountWeight(0) != 0}">
                                        <a href="/AddCart?url=<%=url%>&idP=${p.id}&idW=${p.getIdWeight(0)}"
                                           class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span><i class="ion-ios-cart"></i></span>
                                        </a>
                                    </c:if>
                                    <c:if test="${p.getAmountWeight(0) == 0}">
                                        <p style="background: #82cd47;padding: 10px;color: white;"
                                           class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span>HẾT HÀNG</span>
                                        </p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>


<%--------------------------------------------------------%>


<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center mb-3 pb-3">
            <div class="col-md-12 heading-section text-center ftco-animate">
                <%--                <span class="subheading">Featured Products</span>--%>
                <h2 class="mb-4">Sản phẩm mới</h2>
                <%--                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>--%>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <c:forEach items="${listL}" var="p">
                <div class="col-lg-3 ftco-animate">
                    <div class="product">
                        <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"
                           class="img-prod"><img class="img-fluid"
                                                 src="${p.getPicture(0)}"
                                                 alt="Colorlib Template">
                            <span class="status">${p.getDiscount() == 0? "": p.getDiscount()}</span>
                            <div class="overlay"></div>
                        </a>
                        <div class="text py-3 pb-4 px-3 text-center">
                            <h3>
                                <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"></a>${p.name}
                            </h3>
                            <div class="d-flex">
                                <div class="pricing">
                                    <p class="price">
                                        <c:if test="${p.getDiscount() != 0}">
                                            <span class="mr-2 price-dc">${p.getPriceWeight(0)} VND</span>
                                            <span class="price-sale">${p.priceDiscount(0)} VND</span>
                                        </c:if>
                                        <c:if test="${p.getDiscount() == 0}">
                                            <span class="price-sale">${p.getPriceWeight(0)} VND</span>
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                            <div class="bottom-area d-flex px-3">
                                <div class="m-auto d-flex">
                                    <c:if test="${p.getAmountWeight(0) != 0}">
                                        <a href="/AddCart?url=<%=url%>&idP=${p.id}&idW=${p.getIdWeight(0)}"
                                           class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span><i class="ion-ios-cart"></i></span>
                                        </a>
                                    </c:if>
                                    <c:if test="${p.getAmountWeight(0) == 0}">
                                        <p style="background: #82cd47;padding: 10px;color: white;"
                                           class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span>HẾT HÀNG</span>
                                        </p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


</section>


<%@include file="Include/footer.jsp" %>


<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>