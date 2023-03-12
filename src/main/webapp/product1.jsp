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
            <h3>Ưu đãi</h3>
            <div class="row">
                <c:forEach begin="1" end="3" var="i">
                    <div class="col-6">
                        <c:forEach items="${discounts}" var="p" begin="${(i == 1)?1:((i-1) * 2)+1}"
                                   end="${(i - 1) * 2 + 2}">
                            <div class="product">
                                <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"
                                   class="img-prod"><img class="img-fluid"
                                                         src="${p.getPicture(0)}"
                                                         alt="Colorlib Template">
                                    <span class="status">${p.discount} %</span>
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
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-9">
            <div class="row justify-content-center mb-3 pb-3">
                <div class="col-md-12 heading-section text-center ftco-animate">
                    <%--                <span class="subheading">Featured Products</span>--%>
                    <h2 class="title">${title}</h2>
                    <%--                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>--%>
                </div>
            </div>
            <form method="get">
                <div class="selectboot">
                    <select id="SelectOption" class="browser-default custom-select" name="sort">
                        <option selected>Sắp xếp</option>
                        <option value="a_z">A -> Z</option>
                        <option value="z_a">Z -> A</option>
                        <option value="price">Theo giá: Thấp đến cao</option>
                        <option value="price2">Theo giá: Cao đến thấp</option>
                    </select>
                </div>
            </form>
            <section>
                <div class="row justify-content-center mb-3 pb-3">
                    <div class="col-md-12 heading-section text-center ftco-animate">
                        <%--                <span class="subheading">Featured Products</span>--%>
                        <%--                        <h2 class="title">Cửa hàng</h2>--%>
                        <%--                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>--%>
                    </div>
                </div>

                <div>
                    <div class="row">
                        <%
                            for (Product o : (List<Product>) request.getAttribute("listP")) {
                        %>
                        <div class="col-md-6 col-lg-3 ftco-animate">
                            <div class="product">
                                <a href="detail?pid=<%=o.getId()%>&idW=<%=o.getWeights().get(0).getId()%>"
                                   class="img-prod"><img class="img-fluid"
                                                         src=<%=o.getPicture(0)%>>
                                    <%if (o.getDiscount() != 0) {%>
                                    <span class="status"><%=o.getDiscount()%> %</span>
                                    <%}%>
                                    <div class="overlay"></div>
                                </a>
                                <div class="text py-3 pb-4 px-3 text-center">
                                    <h3>
                                        <a href="detail?pid=<%=o.getId()%>&idW=<%=o.getWeights().get(0).getId()%>"><%=o.getName()%>
                                        </a></h3>
                                    <div class="d-flex">
                                        <div class="pricing">
                                            <p class="price">
                                                <%if (o.getDiscount() != 0) {%>
                                                <span class="mr-2 price-dc"><%=o.getPriceWeight(0)%> VND</span>
                                                <span class="price-sale"><%=o.priceDiscount(0)%> VND</span>
                                                <%} else {%>
                                                <span class="price-sale"><%=o.getPriceWeight(0)%> VND</span>
                                                <%}%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="bottom-area d-flex px-3">
                                        <div class="m-auto d-flex">
                                            <%if (o.getWeights().get(0).getCount() != 0) {%>
                                            <a href="/AddCart?url=<%=url%>&idP=<%=o.getId()%>&idW=<%=o.getWeights().get(0).getId()%>"
                                               class="buy-now d-flex justify-content-center align-items-center mx-1">
                                                <span><i class="ion-ios-cart"></i></span>
                                            </a>
                                            <%} else {%>
                                            <p style="background: #82cd47;padding: 10px;color: white;"
                                               class="buy-now d-flex justify-content-center align-items-center mx-1">
                                                <span>HẾT HÀNG</span>
                                            </p>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>
            </section>


            <div class="product__pagination container">
                <c:forEach begin="1" end="${endp}" var="i">
                    <c:if test="${type == null}">
                        <a class="${tag == i ?"active" : ""}" href="/ListProduct?page=${i}${sort}">${i}</a>
                    </c:if>
                    <c:if test="${type != null}">
                        <a class="${tag == i ?"active" : ""}"
                           href="/ListProduct?page=${i}&type=${type}${sort}">${i}</a>
                    </c:if>
                </c:forEach>
                <%--     <a href="#" tabindex="+1"><i class="fa fa-long-arrow-right"></i></a>--%>
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
