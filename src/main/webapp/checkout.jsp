<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Thanh Toán</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/checkout.css" type="text/css">
</head>
<body>
<%@include file="Include/header.jsp" %>
<div id="checkout-main">
    <div class="container page-wrapper">
        <div class="woocommerce">
            <c:if test="${auth == null}">
                <div class="form-login-toggle">
                    <div class="woocommerce-info message-wrapper">
                        <div class="message-container container medium-text-center"> Bạn đã có tài khoản?
                            <a href="/Login?url=<%=url%>" class="showlogin">Ấn vào đây để đăng nhập</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <form method="post" action="/CheckOut" name="checkout" class="checkout woocommerce-checkout"
                  novalidate="novalidate">
                <div class="row pt-0">
                    <div class="col-xl-7">
                        <div id="customer_details">
                            <div class="clear">
                                <div class="woocommerce-billing-fields">
                                    <h3>Thông tin thanh toán</h3>
                                    <div class="woocommerce-billing-fields__field-wrapper">
                                        <p class="form-row-wide validate-required" id="billing_last_name_field"
                                           data-priority="10">
                                            <label for="billing_last_name" class="">Họ và tên
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <span class="woocommerce-input-wrapper">
                                                <input type="text" class="input-text " name="user_mame"
                                                       id="billing_last_name" placeholder="Họ tên của bạn"
                                                       value="${shipment == null?"":shipment.name}">
                                            </span>
                                        </p>
                                        <p class="form-row-first validate-required validate-phone"
                                           id="billing_phone_field" data-priority="20">
                                            <label for="billing_phone" class="">Số điện thoại
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <span class="woocommerce-input-wrapper">
                                            <input type="tel" class="input-text " name="phone"
                                                   id="billing_phone" placeholder="Số điện thoại của bạn"
                                                   value="${shipment == null?"":shipment.phoneNumber}"
                                                   autocomplete="tel">
                                            </span>
                                        </p>
                                        <p class="form-row-last validate-required validate-email"
                                           id="billing_email_field" data-priority="21">
                                            <label for="billing_email" class="">Địa chỉ email
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <span class="woocommerce-input-wrapper">
                                                <c:if test="${auth == null}">
                                                <input type="email" class="input-text " name="email"
                                                       id="billing_email" placeholder="Email của bạn"
                                                       value=""
                                                       autocomplete="email username">
                                                </c:if>
                                                   <c:if test="${auth != null}">
                                                <input style="background: #ddd;cursor: not-allowed;" type="email"
                                                       class="input-text " name="email"
                                                       id="billing_email" placeholder="Email của bạn"
                                                       value="${auth.email}"
                                                       autocomplete="email username" readonly>
                                                   </c:if>
                                            </span>
                                        </p>
                                        <p class="form-row-first address-field update_totals_on_change validate-required"
                                           id="billing_state_field" data-priority="30">
                                            <label for="billing_province" class="">Tỉnh/Thành phố
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <span class="woocommerce-input-wrapper">
                                                 <input type="text" class="input-text " name="province"
                                                        id="billing_province" placeholder="ví dụ: Hồ Chí Minh"
                                                        value="${shipment == null?"":shipment.province}">
                                            </span>
                                        </p>
                                        <p class="form-row-last address-field update_totals_on_change validate-required validate-required"
                                           id="billing_city_field" data-priority="40">
                                            <label for="billing_district" class="">Quận/Huyện
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <input type="text" class="input-text " name="district"
                                                   id="billing_district" placeholder="ví dụ: Quận 9"
                                                   value="${shipment == null?"":shipment.district}">
                                        </p>
                                        <p class="form-row-first address-field update_totals_on_change validate-required validate-required"
                                           id="billing_address_2_field" data-priority="50">
                                            <label for="billing_ward" class="">Xã/Phường
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <input type="text" class="input-text " name="ward"
                                                   id="billing_ward" placeholder="ví dụ: Tăng Nhơn Phú A"
                                                   value="${shipment == null?"":shipment.ward}">
                                        </p>
                                        <p class="form-row-last validate-required" id="billing_address_1_field"
                                           data-priority="60">
                                            <label for="billing_address_1" class="">Địa chỉ
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <span class="woocommerce-input-wrapper">
                                                <input type="text" class="input-text " name="address"
                                                       id="billing_address_1" placeholder="Ví dụ: 7/56 Đường 182"
                                                       value="${shipment == null?"":shipment.address}"
                                                       autocomplete="address-line1">
                                            </span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-5">
                        <div class="col-inner has-border">
                            <div class="checkout-sidebar sm-touch-scroll">
                                <h3 id="order_review_heading">Đơn hàng của bạn</h3>
                                <div id="order_review" class="woocommerce-checkout-review-order">
                                    <table class="shop_table woocommerce-checkout-review-order-table">
                                        <thead>
                                        <tr>
                                            <th class="product-name">Sản phẩm</th>
                                            <th class="product-total">Tạm tính</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${sessionScope.items}" var="i">
                                            <tr class="cart-item">
                                                <td class="product-name">
                                                        ${i.product.name} - Loại ${i.weight.weight}G
                                                    <strong class="product-quantity">×${i.quanity}</strong>
                                                </td>
                                                <td class="product-total">
                                                <span class="woocommerce-Price-amount amount">
                                                    <bdi>
                                                        ${i.price}<span
                                                            class="woocommerce-Price-currencySymbol"> VND</span>
                                                    </bdi>
                                                </span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <tr class="cart-item">
                                            <td class="product-name">
                                                Ưu đãi
                                            </td>
                                            <td class="product-total">
                                                <span class="woocommerce-Price-amount amount">
                                                    <bdi>
                                                       ${sessionScope.cart.sale}<span
                                                            class="woocommerce-Price-currencySymbol"> VND</span>
                                                    </bdi>
                                                </span>
                                            </td>
                                        </tr>
                                        </tbody>
                                        <tfoot>
                                        <tr class="cart-subtotal">
                                            <th>Tạm tính</th>
                                            <td><span class="woocommerce-Price-amount amount"><bdi>${sessionScope.cart.total}<span
                                                    class="woocommerce-Price-currencySymbol"> VND</span></bdi></span>
                                            </td>
                                        </tr>
                                        <tr class="woocommerce-shipping-totals shipping ">
                                            <td class="shipping__inner" colspan="2">
                                                <table class="shipping__table ">
                                                    <tbody>
                                                    <tr>
                                                        <th style="border: none">Giao hàng</th>
                                                        <td style="border: none" data-title="Giao hàng">
                                                            <ul id="shipping_method"
                                                                class="shipping__list woocommerce-shipping-methods">
                                                                <li class="shipping__list_item">
                                                                    <input type="hidden" name="shipping_method[0]"
                                                                           data-index="0"
                                                                           id="shipping_method_0_local_pickup7"
                                                                           value="local_pickup:7"
                                                                           class="shipping_method">
                                                                    <label class="shipping__list_label"
                                                                           for="shipping_method_0_local_pickup7">
                                                                        Hiện tại chỉ giao trên TP.HCM:
                                                                        <span class="woocommerce-Price-amount amount">
                                                                            <bdi>15000.0
                                                                                <span class="woocommerce-Price-currencySymbol"> VND</span>
                                                                            </bdi>
                                                                        </span>
                                                                    </label>
                                                                </li>
                                                            </ul>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr class="order-total">
                                            <th>Tổng</th>
                                            <td>
                                                <strong>
                                                    <span class="woocommerce-Price-amount amount">
                                                        <bdi>${sessionScope.cart.total + 15000}
                                                            <span class="woocommerce-Price-currencySymbol"> VND</span>
                                                        </bdi>
                                                    </span>
                                                </strong>
                                                <br>
                                                <p style="font-size: 12px; margin-top: 10px;">Đã bao gồm vat (nếu
                                                    có)</p>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                    <div id="payment" class="woocommerce-checkout-payment">
                                        <ul class="wc_payment_methods payment_methods methods p-0">
                                            <li class="wc_payment_method payment_method_cod">
                                                <input id="payment_method_cod" type="radio" class="input-radio"
                                                       name="payment_method" value="cod" checked="checked"
                                                       data-order_button_text="">
                                                <label style="display: inline-block" for="payment_method_cod">
                                                    Trả tiền mặt khi nhận hàng </label>
                                                <div class="payment_box payment_method_cod">
                                                    <p>Trả tiền mặt khi giao hàng</p>
                                                </div>
                                            </li>
                                            <li class="wc_payment_method payment_method_vnpay">
                                                <input id="payment_method_vnpay" type="radio" class="input-radio"
                                                       name="payment_method" value="vnpay"
                                                       data-order_button_text="">
                                                <label style="display: inline-block" for="payment_method_vnpay">
                                                    Thanh toán qua VNPAY </label>
                                                <div class="payment_box payment_method_vnpay">
                                                    <p>Thanh toán trực tuyến qua VNPAY</p>
                                                </div>
                                            </li>
                                        </ul>
                                        <button type="submit" class="button alt"
                                                name="woocommerce_checkout_place_order"
                                                id="place_order" value="Đặt hàng" data-value="Đặt hàng">Đặt hàng
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <%@include file="Include/footer.jsp" %>
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
