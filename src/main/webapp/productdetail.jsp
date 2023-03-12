<%@ page import="vn.edu.hcmuaf.fit.model.Weight" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> Chi tiết</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link rel="stylesheet" href="css/home.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">

    <link rel="stylesheet" href="css/product.css" type="text/css">
    <link rel="stylesheet" href="css/cart.css">


</head>
<body>
<%@include file="Include/header.jsp" %>

<section>
    <div class="container pt-5">
        <div class="row">
            <div class="col-lg-6 mb-5 ftco-animate">
                <%--                <a href="images/product-2.jpg" class="image-popup"><img src="images/product-2.jpg" class="img-fluid"--%>
                <%--                                                                        alt="Colorlib Template"></a>--%>
                <div class="image-slider">
                    <ul>
                        <%
                            Product p = (Product) request.getAttribute("detail");
                            for (int i = 0; i < p.getPics().size(); i++) {
                        %>
                        <li><img src=<%=p.getPicture(i)%> onclick="changeImage('<%=i%>')" id=<%=i%>></li>
                        <%}%>
                    </ul>
                    <div id="main-image">
                        <img src="${detail.getPicture(0)}" id="img-main">
                    </div>
                </div>

            </div>
            <div class="col-lg-6 product-details pl-md-5 ftco-animate">
                <h3>${detail.name}</h3>
                <div class="rating d-flex">
                    <div class="stars text-left mr-4">
                        <form style="margin: 0" action="">
                            <c:forEach var="i" begin="1" end="5">
                                <c:if test="${6-i == star}">
                                    <input class="star star-${6 - i} check" type="radio" name="star"/>
                                    <label class="star star-${6 - i}" for="star-${6 - i}"></label>
                                </c:if>
                                <c:if test="${6-i != star}">
                                    <input class="star star-${6- i}" type="radio" name="star"/>
                                    <label class="star star-${6 - i}" for="star-${6 - i}"></label>
                                </c:if>
                            </c:forEach>
                        </form>
                    </div>
                    <p class="text-left mr-4">
                        <a href="#" class="mr-2" style="color: #000;">${reviews.size()} <span style="color: #bbb;">Đánh giá</span></a>
                    </p>
                    <p class="text-left">
                        <a href="#" class="mr-2" style="color: #000;">${amount} <span style="color: #bbb;">Đã bán</span></a>
                    </p>
                </div>
                <p class="price">
                    <c:if test="${detail.getDiscount() != 0}">
                        <span style="text-decoration: line-through;color: #b3b3b3;"
                              class="mr-2 price-dc">${detail.getPriceWeight(0)} VND</span>
                        <span class="price-sale">${detail.priceDiscount(0)} VND</span>
                    </c:if>
                    <c:if test="${detail.getDiscount() == 0}">
                        <span class="price-sale">${detail.getPriceWeight(0)} VND</span>
                    </c:if>
                </p>
                <p>${detail.describe}</p>
                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="form-group d-flex">
                            <div class="select-wrap">
                                <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                <select id="selectWeight" name="weight" class="form-control">
                                    <%
                                        for (Weight i : p.getWeights()) {
                                            if (i.getId().equals(request.getAttribute("idW"))) {
                                    %>
                                    <option selected value=<%=i.getId()%>><%=i.getWeight()%>G
                                    </option>
                                    <%} else {%>
                                    <option value=<%=i.getId()%>><%=i.getWeight()%>G</option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="minusplus">
                        <div class="buttons_added">
                            <input class="minus is-form" type="button" value="-">
                            <input aria-label="quantity" class="input-qty" max="10"
                                   min="1" name="num" type="number" value="1">
                            <input class="plus is-form" type="button" value="+">
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col-md-12">
                        <%--                        <p style="color: #000;">600 kg available</p>--%>
                    </div>
                </div>
                <c:if test="${weight.getCount() != 0}">
                    <p>
                        <button onclick="addCart('${idP}', '${idW}', '<%=url%>')" class="btn btn-black py-1 px-5">Thêm
                            vào
                            giỏ
                            hàng
                        </button>
                    </p>
                </c:if>
                <c:if test="${weight.getCount() == 0}">
                    <p style="font-size: 20px;margin-top: 10px;">
                        HẾT HÀNG
                    </p>
                </c:if>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 title bold"> Sản Phẩm Tương Tư</div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div id="slide-show" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="i" begin="1" end="3">
                            <div class="carousel-item ${i==1?"active":""}">
                                <div class="row">
                                    <c:forEach items="${lists}" var="p" begin="${(i == 1)?1:((i-1) * 4)+1}"
                                               end="${(i - 1) * 4 + 4}">
                                        <div class="col-lg-3 ftco-animate">
                                            <div class="product">
                                                <a href="/detail?pid=${p.id}&idW=${p.getIdWeight(0)}"
                                                   class="img-prod"><img class="img-fluid"
                                                                         src="${p.getPicture(0)}"
                                                                         alt="Colorlib Template">
                                                    <span class="status">${p.discount == 0? "" : p.discount}</span>
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
                        </c:forEach>
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
    </div>
</section>


<%--comment--%>
<div class="comment">
    <div class="comment-form-wrap pt-5">

        <c:if test="${auth == null}">
            <h3 class="mb-5">Để lại đánh giá của bạn <br> <strong>Bạn cần <a style="color: #82cd47"
                                                                             href="Login?url=<%=url%>">đăng
                nhập</a> để có thể đánh
                giá</strong></h3>
        </c:if>
        <c:if test="${auth != null}">
            <h3 class="mb-5">Để lại đánh giá của bạn</h3>
            <form method="post" action="/ReviewControl?url=<%=url%>&idP=${idP}" class="p-5 bg-light boxcomment">
                <div class="vcard bio">
                    <i class="fa-solid fa-user"></i>
                </div>
                <div class="comment-body">
                    <h4>${auth.name}</h4>
                    <div class="rating d-flex">
                        <div class="stars text-left mr-4">
                            <form style="margin: 0" action="">
                                <input class="star star-5 star-check" id="star-5" value="5" type="radio" name="star"/>
                                <label class="star star-5" for="star-5"></label>
                                <input class="star star-4 star-check" id="star-4" value="4" type="radio" name="star"/>
                                <label class="star star-4" for="star-4"></label>
                                <input class="star star-3 star-check" id="star-3" value="3" type="radio" name="star"/>
                                <label class="star star-3" for="star-3"></label>
                                <input class="star star-2 star-check" id="star-2" value="2" type="radio" name="star"/>
                                <label class="star star-2" for="star-2"></label>
                                <input class="star star-1 star-check" id="star-1" value="1" type="radio" name="star"/>
                                <label class="star star-1" for="star-1"></label>
                            </form>
                        </div>
                    </div>
                        <%--            <div class="form-group">--%>
                        <%--                <label for="name">Tên *</label>--%>
                        <%--                <input type="text" class="form-control" id="name">--%>
                        <%--            </div>--%>
                        <%--            <div class="form-group">--%>
                        <%--                <label for="email">Email *</label>--%>
                        <%--                <input type="email" class="form-control" id="email">--%>
                        <%--            </div>--%>
                        <%--            <div class="form-group">--%>
                        <%--                <label for="website">Email</label>--%>
                        <%--                <input type="url" class="form-control" id="website">--%>
                        <%--            </div>--%>

                    <div class="form-group">
                            <%--                <label for="message">Đánh giá</label>--%>
                        <textarea name="mess" id="message" cols="30" rows="10" class="form-control"
                                  style="height: 80px"></textarea>
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Đăng" class="btn py-3 px-4 btn-black "/>
                    </div>
                </div>
            </form>
        </c:if>
    </div>
    <h3 class="mb-5">${reviews.size()} đánh giá</h3>
    <ul class="comment-list">
        <c:set var="count" value="0"/>
        <c:forEach items="${reviews}" begin="0" end="1" var="r">
            <li class="comment">
                <div class="vcard bio">
                    <i class="fa-solid fa-user"></i>
                </div>
                <div class="comment-body">
                    <h3>${r.getName()}</h3>
                    <div class="rating d-flex">
                        <div class="stars text-left mr-4">
                            <form style="margin: 0" action="">
                                <c:forEach var="i" begin="1" end="${r.stars}">
                                    <c:if test="${i == 1}">
                                        <input class="star star-${r.stars + 1 - i} check" type="radio" name="star"/>
                                        <label class="star star-${r.stars + 1 - i}"
                                               for="star-${r.stars + 1 - i}"></label>
                                    </c:if>
                                    <c:if test="${i != 1}">
                                        <input class="star star-${r.stars + 1- i}" type="radio" name="star"/>
                                        <label class="star star-${r.stars + 1 - i}"
                                               for="star-${r.stars + 1 - i}"></label>
                                    </c:if>
                                </c:forEach>
                            </form>
                        </div>
                    </div>
                    <div class="meta"><i>${r.getDate()}</i></div>
                    <p>${r.content}</p>
                </div>
            </li>
            <c:set var="count" value="${count + 1}"/>
        </c:forEach>
        <c:if test="${count != reviews.size()}">
            <button id="load" style="padding-left: 100%; padding-right: 100%;" class="btn btn-light"
                    onclick="loadMore('${idP}')"><i
                    class="arrow down"></i></button>
        </c:if>
    </ul>

    <!-- END comment-list -->

    <div class="comment-form-wrap pt-5">
        <%--        <h3 class="mb-5">Để lại đánh giá của bạn</h3>--%>
    </div>
</div>
<%@include file="Include/footer.jsp" %>


<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/productdetail.js"></script>
<script src="js/main.js"></script>

</body>
</html>
