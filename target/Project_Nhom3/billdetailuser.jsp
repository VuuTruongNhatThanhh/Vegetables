<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 12/7/2022
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Tình trạng đơn hàng</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
  <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
  <link rel="stylesheet" href="css/style.css" type="text/css">
  <link rel="stylesheet" href="css/userprofile.css" type="text/css">
</head>
<body>
<%@include file="Include/header.jsp" %>
<br>
<div class="row">
  <div class="col-4 tab">
    <a href="/UserProfile">
      <button class="button">Thông tin cá nhân</button>
    </a>
    <a href="#">
      <button class="button">Tình trạng đơn hàng</button>
    </a>
    <a href="changepassword.jsp">
      <button class="button">Đổi mật khẩu</button>
    </a>
  </div>
  <div class="row col-8">
    <div style="height: 20%;" class="tab row col-12">
      <button style="height: 10%" class="tablinks col-3" onclick="openCity(event, 'tab1')" id="defaultOpen">Sản phẩm trong đơn hàng
      </button>
<%--      <button style="height: 10%" class="tablinks col-3" onclick="openCity(event, 'tab2')">Đã xác nhận</button>--%>
<%--      <button style="height: 10%" class="tablinks col-3" onclick="openCity(event, 'tab3')">Đã giao</button>--%>
<%--      <button style="height: 10%" class="tablinks col-3" onclick="openCity(event, 'tab4')">Đã hủy</button>--%>

    </div>
    <div id="tab1" class="tabcontent">
      <div class="card">
        <div class="card-body">
          <table class="table">
            <thead>
            <tr>
              <th scope="col">Sản phẩm</th>
              <th scope="col">Khối lượng</th>
              <th scope="col">Số lượng</th>
              <th scope="col">Thành tiền</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bd}" var="bd">
              <tr  id="${bd.idP}">
                <th scope="row">${bd.getNameProduct()}</th>
                <td>${bd.getNameWeight()}g</td>
                <td>${bd.amount}</td>
                <td>${bd.total}</td>

              </tr>
            </c:forEach>
            </tbody>
          </table>
          <p style="padding-left: 450px; color: red">Tổng tiền: ${tp} VND</p>
          <p style="padding-left: 450px; color: red">Phí ship: 15000 VND</p>
        </div>
      </div>
    </div>



  </div>
</div>
</div>
<%@include file="Include/footer.jsp" %>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/main.js"></script>
<script src="js/userprofile.js"></script>
<script>
  function delivered(id, idU) {
    $.ajax({
              url: "/DeliveredBill",
              type: "get",
              data: {
                id: id,
                idU: idU
              },
              success: function (data) {
                $("tr").remove("#" + id)
                $("#delivered").html(data);
              }
            }
    )
  }

  function remove(id, idU) {
    $.ajax({
              url: "/RemoveBillUser",
              type: "get",
              data: {
                id: id,
                idU: idU
              },
              success: function (data) {
                $("tr").remove("#" + id)
                $("#cancel").html(data);
              }
            }
    )
  }
</script>
</body>
</html>
