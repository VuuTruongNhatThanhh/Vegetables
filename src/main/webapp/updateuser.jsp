<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 12/7/2022
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cập nhật thông tin cá nhân</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<%@include file="Include/header.jsp" %>
<br>
<div class="row" style="display: flex; padding-left: 325px; padding-right: 415px;">
    <div class="col-md-12">
        <form method="get">
            <div class="form-group row">
                <label for="username" class="col-4 col-form-label">Tên<b
                        style="color: red">*</b></label>
                <div class="col-8">
                    <input id="username" name="username" placeholder="Tên người dùng"
                           class="form-control here" required="required" type="text">
                </div>
            </div>
            <div class="form-group row">
                <label for="name" class="col-4 col-form-label">Số điện thoại<b style="color: red">*</b></label>
                <div class="col-8">
                    <input id="name" name="name" placeholder="Số điện thoại" class="form-control here"
                           type="text">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-4 col-form-label">Địa chỉ</label>
                <div class="col-8">
                    <div class="col-6">
                        <label>Tỉnh/Thành phố<b style="color: red">*</b></label>
                        <select name="calc_shipping_provinces" required="" class="form-control here"
                                style="width: 300px">
                            <option value="">Tỉnh / Thành phố</option>
                        </select>
                        <label>Quận/Huyện<b style="color: red">*</b></label>
                        <select name="calc_shipping_district" required="" class="form-control here"
                                style="width: 300px">
                            <option value="">Quận / Huyện</option>
                        </select>
                        <input class="billing_address_1" name="" type="hidden" value="">
                        <input class="billing_address_2" name="" type="hidden" value="">
                    </div>
                    <div class="col-6">
                        <label for="district">Xã/Phường<b style="color: red">*</b></label>
                        <input id="district" name="district" placeholder="Xã / Phường" required="required"
                               class="form-control here" style="width: 300px" type="text">
                        <label for="ward">Tên đường</label>
                        <input id="ward" name="ward" placeholder="Tên đường" class="form-control here"
                               style="width: 300px" type="text">
                        <label for="address">Số nhà<b style="color: red">*</b></label>
                        <input id="address" name="address" placeholder="Số nhà" class="form-control here"
                               style="width: 300px" type="text">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-4 col-form-label">Email<b style="color: red">*</b></label>
                <div class="col-8">
                    <input id="email" name="email" placeholder="Email" class="form-control here"
                           required="required" type="text">
                </div>
            </div>
            <div class="form-group row">
                <a href="updateuser.jsp">
                    <button name="submit" type="submit" class="btn btn-black"
                            style="background-color: var(--main-color); color: white">Cập nhật
                    </button>
                </a>
            </div>
            <%--                            <div class="form-group row">--%>
            <%--                                <label for="text" class="col-4 col-form-label">Biệt danh*</label>--%>
            <%--                                <div class="col-8">--%>
            <%--                                    <input id="text" name="text" placeholder="Nick Name" class="form-control here"--%>
            <%--                                           required="required" type="text">--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
            <%--                            <div class="form-group row">--%>
            <%--                                <label for="select" class="col-4 col-form-label">Tên hiển thị</label>--%>
            <%--                                <div class="col-8">--%>
            <%--                                    <select id="select" name="select" class="custom-select">--%>
            <%--                                        <option value="admin">Người dùng</option>--%>
            <%--                                    </select>--%>
            <%--                                </div>--%>
            <%--                            </div>--%>
        </form>
    </div>
</div>
<%@include file="Include/footer.jsp" %>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>
<script src='https://cdn.jsdelivr.net/gh/vietblogdao/js/districts.min.js'></script>
<script>//<![CDATA[
if (address_2 = localStorage.getItem('address_2_saved')) {
    $('select[name="calc_shipping_district"] option').each(function () {
        if ($(this).text() == address_2) {
            $(this).attr('selected', '')
        }
    })
    $('input.billing_address_2').attr('value', address_2)
}
if (district = localStorage.getItem('district')) {
    $('select[name="calc_shipping_district"]').html(district)
    $('select[name="calc_shipping_district"]').on('change', function () {
        var target = $(this).children('option:selected')
        target.attr('selected', '')
        $('select[name="calc_shipping_district"] option').not(target).removeAttr('selected')
        address_2 = target.text()
        $('input.billing_address_2').attr('value', address_2)
        district = $('select[name="calc_shipping_district"]').html()
        localStorage.setItem('district', district)
        localStorage.setItem('address_2_saved', address_2)
    })
}
$('select[name="calc_shipping_provinces"]').each(function () {
    var $this = $(this),
        stc = ''
    c.forEach(function (i, e) {
        e += +1
        stc += '<option value=' + e + '>' + i + '</option>'
        $this.html('<option value="">Tỉnh / Thành phố</option>' + stc)
        if (address_1 = localStorage.getItem('address_1_saved')) {
            $('select[name="calc_shipping_provinces"] option').each(function () {
                if ($(this).text() == address_1) {
                    $(this).attr('selected', '')
                }
            })
            $('input.billing_address_1').attr('value', address_1)
        }
        $this.on('change', function (i) {
            i = $this.children('option:selected').index() - 1
            var str = '',
                r = $this.val()
            if (r != '') {
                arr[i].forEach(function (el) {
                    str += '<option value="' + el + '">' + el + '</option>'
                    $('select[name="calc_shipping_district"]').html('<option value="">Quận / Huyện</option>' + str)
                })
                var address_1 = $this.children('option:selected').text()
                var district = $('select[name="calc_shipping_district"]').html()
                localStorage.setItem('address_1_saved', address_1)
                localStorage.setItem('district', district)
                $('select[name="calc_shipping_district"]').on('change', function () {
                    var target = $(this).children('option:selected')
                    target.attr('selected', '')
                    $('select[name="calc_shipping_district"] option').not(target).removeAttr('selected')
                    var address_2 = target.text()
                    $('input.billing_address_2').attr('value', address_2)
                    district = $('select[name="calc_shipping_district"]').html()
                    localStorage.setItem('district', district)
                    localStorage.setItem('address_2_saved', address_2)
                })
            } else {
                $('select[name="calc_shipping_district"]').html('<option value="">Quận / Huyện</option>')
                district = $('select[name="calc_shipping_district"]').html()
                localStorage.setItem('district', district)
                localStorage.removeItem('address_1_saved', address_1)
            }
        })
    })
})
//]]></script>
</body>
</html>
