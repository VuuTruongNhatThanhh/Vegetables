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
                            <a href="Login?url=<%=url%>" class="showlogin">Ấn vào đây để đăng nhập</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <form method="post" action="CheckOut" name="checkout" class="checkout woocommerce-checkout"
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
                                            <%--                                            <label for="city" class="">Tỉnh/Thành phố--%>
                                            <%--                                                <abbr class="required" title="bắt buộc">*</abbr>--%>
                                            <%--                                            </label>--%>
                                            <label for="city" class="">Tỉnh/Thành phố
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <%--                                            <span class="woocommerce-input-wrapper">--%>
                                            <%--                                                 <input type="text" class="input-text " name="province"--%>
                                            <%--                                                        id="billing_province" placeholder="ví dụ: Hồ Chí Minh"--%>
                                            <%--                                                        value="${shipment == null?"":shipment.province}">--%>
                                            <%--                                            </span>--%>


                                            <%--                                            <select id="city" required="" class="form-control here"--%>
                                            <%--                                                    style="width: 300px" onchange="update()">--%>
                                            <%--                                                <option value="" >${shipment == null?"Tỉnh/Thành phố":shipment.province}</option>--%>
                                            <%--                                            </select>--%>

                                            <select id="city" name="province" required="" class="form-control here"
                                                    style="width: 300px" onchange="update()">
                                                <%--                                                <option value="">${shipment == null?"Tỉnh/Thành phố":shipment.province}</option>--%>
                                                <c:forEach items="${listProvinces}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </p>
                                        <p class="form-row-last address-field update_totals_on_change validate-required validate-required"
                                           id="billing_city_field" data-priority="40">
                                            <%--                                            <label for="dist" class="">Quận/Huyện--%>
                                            <%--                                                <abbr class="required" title="bắt buộc">*</abbr>--%>
                                            <%--                                            </label>--%>

                                            <label for="dist" class="">Quận/Huyện
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <%--                                            <input type="text" class="input-text " name="district"--%>
                                            <%--                                                   id="billing_district" placeholder="ví dụ: Quận 9"--%>
                                            <%--                                                   value="${shipment == null?"":shipment.district}">--%>
                                            <select id="dist" name="district" required="" class="form-control here"
                                                    style="width: 300px" onchange="update()">
                                                <%--                                                <option value="">${shipment == null?"Quận/Huyện":shipment.district}</option>--%>
                                                <option value="">Chọn quận/huyện</option>
                                            </select>
                                        </p>
                                        <p class="form-row-first address-field update_totals_on_change validate-required validate-required"
                                           id="billing_address_2_field" data-priority="50">
                                            <label for="ward" class="">Xã/Phường
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <%--                                            <input type="text" class="input-text " name="ward"--%>
                                            <%--                                                   id="billing_ward" placeholder="ví dụ: Tăng Nhơn Phú A"--%>
                                            <%--                                                   value="${shipment == null?"":shipment.ward}">--%>
                                            <select id="ward" name="ward" required="" class="form-control here"
                                                    style="width: 300px" onchange="update()">
                                                <%--                                                <option value="">${shipment == null?"Xã/Phường":shipment.ward}</option>--%>
                                                <option value="">Chọn xã/phường</option>
                                            </select>
                                        </p>
                                        <p class="form-row-last validate-required" id="billing_address_1_field"
                                           data-priority="60">
                                            <label for="billing_address_1" class="">Địa chỉ
                                                <abbr class="required" title="bắt buộc">*</abbr>
                                            </label>
                                            <span class="woocommerce-input-wrapper">
                                                <input type="text" class="input-text " name="address"
                                                       id="billing_address_1" placeholder="Ví dụ: 7/56 Đường 182"
                                                       value=""
                                                       autocomplete="address-line1">
                                            </span>
                                        </p>






<%--                                        name address--%>
                                        <input class="p" name="province-value" type="hidden" value="${shipment.province}"
                                               id="province-value">
                                        <input class="d" name="district-value" type="hidden" value="${shipment.district}"
                                               id="district-value">
                                        <input class="w" name="ward-value" type="hidden" value="${shipment.ward}" id="ward-value">

<%--                                        id adress--%>
                                        <input class="p" name="province" type="hidden" value="${shipment.province}"
                                               id="province-value">
                                        <input class="d" name="district" type="hidden" value="${shipment.district}"
                                               id="district-value">
                                        <input class="w" name="ward" type="hidden" value="${shipment.ward}" id="ward-value">


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



                                                <!-- Duyệt qua danh sách trong đối tượng i -->






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

                                                                        <span class="woocommerce-Price-amount amount">
                                                                            <bdi> <div style="font-size: 20px" class="cart-summary-line" id="cart-subtotal-shipping">


                                                    <span class="value"></span>

                                                    <input style="font-size: 100px" type="hidden" name="shippingFee" id="shipping-fee-input">

                                                </div>

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
                                                        <bdi> <div class="cart-summary-line cart-total">

                                                    <span class="value" style="font-size: 20px">${sessionScope.cart.total} VND</span>
                                                </div>

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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
    <script>
        var citis = document.getElementById("city");
        var districts = document.getElementById("dist");
        var wards = document.getElementById("ward");


        var Parameter = {
            url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
            method: "GET",
            responseType: "application/json",
        };
        var promise = axios(Parameter);
        promise.then(function (result) {
            renderCity(result.data);
        });

        function update() {
            var citis = document.getElementById("city");
            var districts = document.getElementById("dist");
            var wards = document.getElementById("ward");

            var option1 = citis.options[citis.selectedIndex];
            var option2 = districts.options[districts.selectedIndex];
            var option3 = wards.options[wards.selectedIndex];

            document.getElementById('text1').value = option1.text;
            document.getElementById('text2').value = option2.text;
            document.getElementById('text3').value = option3.text;
        }

        update();

        function renderCity(data) {
            for (const x of data) {
                citis.options[citis.options.length] = new Option(x.Name, x.Id);
            }
            citis.onchange = function () {
                dist.length = 1;
                ward.length = 1;
                if (this.value != "") {
                    const result = data.filter(n => n.Id === this.value);

                    for (const k of result[0].Districts) {
                        dist.options[dist.options.length] = new Option(k.Name, k.Id);
                    }
                }
            };
            dist.onchange = function () {
                ward.length = 1;
                const dataCity = data.filter((n) => n.Id === citis.value);
                if (this.value != "") {
                    const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;

                    for (const w of dataWards) {
                        wards.options[wards.options.length] = new Option(w.Name, w.Id);
                    }
                }
            };
        }
    </script>




    <script>
        $(document).ready(function () {
            $('#province').change(function () {
                var provinceId = $(this).val();
                $.ajax({
                    url: 'DistrictServlet',
                    type: 'POST',
                    data: {
                        province: provinceId
                    },
                    success: function (response) {
                        var districtSelect = $('#district');
                        districtSelect.empty(); // Xóa tất cả các option hiện có trong select box
                        districtSelect.append('<option value="">Chọn huyện</option>'); // Thêm option mặc định
                        response.forEach(function (district) {
                            districtSelect.append('<option value="' + district.DistrictID + '">' + district.DistrictName + '</option>');
                        });
                    },
                    error: function () {
                        console.log('Lỗi khi tải danh sách huyện');
                    }
                });
            });
        });
    </script>

    <script>
        $(document).ready(function () {
            $('#district').change(function () {
                // Lấy giá trị đã chọn của select box
                var districtId = $(this).val();
                // Gửi yêu cầu Ajax đến Servlet
                $.ajax({
                    url: 'WardServlet',
                    type: 'POST',
                    data: {
                        districtId: districtId
                    },
                    success: function (response) {
                        var wardSelect = $('#ward');
                        wardSelect.empty(); // Xóa tất cả các option hiện có trong select box
                        wardSelect.append('<option value="">Chọn xã</option>'); // Thêm option mặc định
                        response.forEach(function (ward) {
                            wardSelect.append('<option value="' + ward.WardCode + '">' + ward.WardName + '</option>');
                        });
                    },
                    error: function () {
                        console.log('Lỗi khi tải danh sách huyện');
                    }
                });
            });
        });
    </script>

    <script>
        $(document).ready(function() {
            $('#city').on('change', function() {
                var provinceId = $(this).val();
                $('#province').val(provinceId);
                $('#province-value').val($(this).find("option:selected").text());
            });

            $('#dist').on('change', function() {
                var districtId = $(this).val();
                $('#district').val(districtId);
                $('#district-value').val($(this).find("option:selected").text());
            });

            $('#ward').on('change', function() {
                var wardCode = $(this).val();
                $('#ward').val(wardCode);
                $('#ward-value').val($(this).find("option:selected").text());
            });
        });

    </script>

<%--    <script>--%>
<%--        $(document).ready(function () {--%>
<%--            var province = '';--%>
<%--            var district = '';--%>
<%--            var ward = '';--%>
<%--            // Lấy giá trị của select box Tỉnh--%>
<%--            $('#province').change(function () {--%>
<%--                province = $(this).val();--%>
<%--                $('#district').val('');--%>
<%--                $('#ward').val('');--%>
<%--            });--%>
<%--            // Lấy giá trị của select box Huyện--%>
<%--            $('#district').change(function () {--%>
<%--                district = $(this).val();--%>
<%--                $('#ward').val('');--%>
<%--            });--%>
<%--            var initialCartTotal = parseFloat($('.cart-total .value').text().replace(/[^\d]/g, ''));--%>
<%--            // Lấy giá trị của select box Xã và gửi dữ liệu lên server--%>
<%--            $('#ward').change(function () {--%>
<%--                ward = $(this).val();--%>
<%--                if (province && district && ward && province.trim() !== '' && district.trim() !== '' && ward.trim() !== '') {--%>
<%--                    $.ajax({--%>
<%--                        type: "POST",--%>
<%--                        url: "FeeServlet",--%>
<%--                        data: {--%>
<%--                            province: province,--%>
<%--                            district: district,--%>
<%--                            ward: ward--%>
<%--                        },--%>
<%--                        success: function (result) {--%>
<%--                            var shippingFee = parseInt(result);--%>
<%--                            // var cartTotal = parseFloat($('.cart-total .value').text().replace(/[^\d]/g, ''));--%>
<%--                            var newCartTotal = initialCartTotal/10 + shippingFee;--%>
<%--                            // Set giá trị cho input--%>
<%--                            $('#shipping-fee-input').val(shippingFee);--%>
<%--                            // Hiển thị giá trị trong span--%>
<%--                            $('#cart-subtotal-shipping .value').text(shippingFee.toLocaleString('vi-VN') + ' VND');--%>
<%--                            // Hiển thị giá trị mới của tổng--%>
<%--                            $('.cart-total .value').text(newCartTotal.toLocaleString('vi-VN') + ' VND');--%>
<%--                        },--%>
<%--                        error: function () {--%>
<%--                            alert('Đã có lỗi xảy ra!');--%>
<%--                        }--%>
<%--                    });--%>
<%--                }--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>

</body>
</html>
