<%@ page import="vn.edu.hcmuaf.fit.model.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<header class="header">
    <%
        String uri = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        String prmt = (String) request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
        prmt = (prmt == null) ? "" : prmt.replaceAll("&", "-");
        String url = uri + "?" + prmt;
        if (request.getSession().getAttribute("cart") == null) {
            request.getSession().setAttribute("cart", new Cart());
        }
    String user_name=(String)request.getParameter("user_name");
        String user_email=(String)request.getParameter("user_email");
    %>

    <div id="header-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 mt-2">
                    <div class="header-logo">
                        <a href="/LoadControl"><img src="../images/logo.png" alt style="margin-left: 50px"></a>
                    </div>
                </div>
                <div class="col-lg-4 mt-2">
                    <div class="wrap">
                        <form action="search" class="search" method="post">
                            <input name="txt" type="text" class="searchTerm" placeholder="Bạn muốn tìm gì?">
                            <button type="submit" class="searchButton">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-5 mt-2">
                    <div class="header-right">
                        <c:set var="auth" value="${sessionScope.auth}"/>
                        <c:if test="${auth==null}">
                            <div class="sign">
                                <ul>
                                    <li><a href="login.jsp">Đăng nhập</a></li>
                                    <li><a href="#">/</a></li>
                                    <li><a href="signup.jsp">Đăng ký</a></li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${auth !=null}">
                            <div class="head-avt">
                                <ul>

                                    <li><a href="/UserProfile">Xin chào ${auth.name}</a></li>



                                    <li><a href="Logout">Đăng xuất</a></li>
                                </ul>
                            </div>
                        </c:if>
                        <a href="/ShoppingCart" class="cart">
                            <ul style="">
                                <li><i class="fa-solid fa-cart-shopping"></i></li>
                                <li>GIỎ HÀNG</li>
                                <li>${sessionScope.cart.quanity}</li>
                            </ul>
                            <c:if test="${sessionScope.cart.quanity == 0}">
                                <div class="drop-cart">
                                    <div class="dropcart-item">Không có sản phẩm nào.</div>
                                </div>
                            </c:if>
                        </a>
                        <c:if test="${sessionScope.cart.quanity != 0}">
                            <div class="top-cart-content">
                                <ul id="cart-sidebar" class="mini-products-list">
                                    <c:forEach items="${sessionScope.item}" var="i">
                                        <li class="item"><a href="detail?pid=${i.product.id}&idW=${i.weight.id}"
                                                            class="product-image"><img src="${i.getUrlPic()}"
                                                                                       width="80"></a>
                                            <div class="detail-item">
                                                <div class="product-details"><a href="javascript:void(0);"
                                                                                title="Xóa"
                                                                                onclick="Bizweb.removeItem(19391963)"
                                                                                class="fa fa-remove">&nbsp;</a>
                                                    <p class="product-name">
                                                        <a href="detail?pid=${i.product.id}&idW=${i.weight.id}">
                                                                ${i.product.name}
                                                        </a>
                                                    </p>
                                                </div>
                                                <div class="product-details-bottom"><span
                                                        class="price">${i.price}</span>
                                                    <span class="title-desc">Số lượng:</span>
                                                    <strong>${i.quanity}</strong>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <div class="top-subtotal">Tổng cộng: <span
                                                class="price">${sessionScope.cart.total}</span>
                                        </div>
                                    </li>
                                    <li style="margin-left:-15px;margin-right:-15px;">
                                        <div class="actions">
                                            <button class="btn-checkout" type="button"
                                                    onclick="window.location.href='/CheckOut'">
                                                <span>Thanh toán</span></button>
                                            <button class="view-cart" type="button"
                                                    onclick="window.location.href='/ShoppingCart'"><span>Giỏ hàng</span>
                                            </button>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="nav-main">
        <div class="container">
            <div class="row justify-content-center">

                <div class="col-9">

                    <div class="nav-header navbar navbar-expand-lg navbar-light">
                        <div class="collapse navbar-collapse " id="navbarNav">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="/LoadControl">Trang chủ <span
                                            class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="aboutus.jsp">Giới thiệu</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/ListProduct">Cửa hàng</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="news.jsp">Tin tức thực phẩm</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="contact.jsp">Liên hệ</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
