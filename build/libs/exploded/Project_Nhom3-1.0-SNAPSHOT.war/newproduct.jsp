<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cửa hàng</title>


    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link rel="stylesheet" href="css/home.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/product.css" type="text/css">
    <link rel="stylesheet" href="css/news.css" type="text/css">


</head>
<body>
<%@include file="Include/header.jsp" %>
<section class="breadcrumb-section set-bg" style="background-image: url(images/bg.png);">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb_text">
                    <h2>Cửa hàng</h2>
                    <div class="breadcrumb_option">
                        <a href="./index.jsp">Trang chủ</a>
                        <span>Cửa hàng</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="container" style="padding-top: 15px">
    <div class="row">
        <div class="col-3">
            <%@include file="Include/menu.jsp" %>


        </div>
        <div class="col-9">
            <h3>Sản phẩm mới</h3>

            <section class="ftco-section">
                <%--    <%for(Product p : (List<Product>)request.getAttribute("list"))%>--%>
                <%--        <div class="container">--%>
                <%--          &lt;%&ndash;        <%=user_name %>&ndash;%&gt;--%>
                <%--          &lt;%&ndash;        <%=user_email %>&ndash;%&gt;--%>
                <%--          <div class="row justify-content-center mb-3 pb-3">--%>
                <%--            <div class="col-md-12 heading-section text-center ftco-animate">--%>
                <%--              <h2 class="mb-4"><a style="color: black" href="HotControl">Sản phẩm hot</a></h2>--%>
                <%--            </div>--%>
                <%--          </div>--%>
                <%--        </div>--%>
                <div class="container">
                    <div class="row">
                        <c:forEach items="${listL}" var="p">
                            <div class="col-lg-3 ftco-animate">
                                <div class="product">
                                    <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"
                                       class="img-prod"><img class="img-fluid"
                                                             src="${p.getPicture(0)}"
                                                             alt="Colorlib Template">
                                        <span class="status">${p.getDiscount() == 0? "": p.getDiscountShow()}</span>
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








        </div>
    </div>
</div>
</div>


<%@include file="Include/footer.jsp" %>


<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
<script src="js/product.js"></script>

</body>
</html>
