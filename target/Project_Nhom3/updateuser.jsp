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
        <form method="post" action="updateInfoUser">
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
                    <input id="name" name="phone" placeholder="Số điện thoại" class="form-control here"
                           type="text">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-4 col-form-label">Địa chỉ</label>
                <div class="col-8">
                    <div class="col-6">
                        <label>Tỉnh/Thành phố<b style="color: red">*</b></label>
                        <select id="city" required="" class="form-control here"
                                style="width: 300px" onchange="update()">
                            <option value="" >Tỉnh / Thành phố</option>
                        </select>
                        <label>Quận/Huyện<b style="color: red">*</b></label>
                        <select id="dist" required="" class="form-control here"
                                style="width: 300px" onchange="update()">
                            <option value="">Quận / Huyện</option>
                        </select>

                        <label>Xã/Phường<b style="color: red">*</b></label>
                        <select id="ward" required="" class="form-control here"
                                style="width: 300px" onchange="update()">
                            <option value="">Xã / Phường</option>
                        </select>

                        <input class="p" name="city" type="hidden" value="" id="text1">
                        <input class="d" name="dist" type="hidden" value="" id="text2">
                        <input class="w" name="ward" type="hidden" value="" id="text3">

                    </div>
                    <div class="col-6">
<%--                        <label for="district">Xã/Phường<b style="color: red">*</b></label>--%>
<%--                        <input id="district" name="ward" placeholder="Xã / Phường" required="required"--%>
<%--                               class="form-control here" style="width: 300px" type="text">--%>
<%--                        <label for="ward">Tên đường</label>--%>
<%--                        <input id="ward" name="ward" placeholder="Tên đường" class="form-control here"--%>
<%--                               style="width: 300px" type="text">--%>
                        <label for="address">Số nhà<b style="color: red">*</b></label>
                        <input id="address" name="address" placeholder="Số nhà" class="form-control here"
                               style="width: 300px" type="text">
                    </div>
                </div>
            </div>
<%--            <div class="form-group row">--%>
<%--                <label for="email" class="col-4 col-form-label">Email<b style="color: red">*</b></label>--%>
<%--                <div class="col-8">--%>
<%--                    <input id="email" name="email" placeholder="Email" class="form-control here"--%>
<%--                           required="required" type="text">--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="form-group row">
                <a>
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
    function update(){
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
            if(this.value != ""){
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
</body>
</html>
