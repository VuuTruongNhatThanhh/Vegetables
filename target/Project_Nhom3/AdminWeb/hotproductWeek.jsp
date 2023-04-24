<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý</title>
  <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../AdminWeb/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="../fontawesome-free-6.2.0-web/css/all.min.css">
  <link rel="stylesheet" href="../AdminWeb/css/style.css">
  <link rel="stylesheet" href="../AdminWeb/css/product.css">
</head>
<body>
<%@include file="include/menu.jsp" %>
<section class="home-section">
  <div class="home-content">
    <div class="manager-product">
      <div class="title">Sản Phẩm Bán Chạy Trong Tuần</div>
      <div class="row">
        <div class="col-md-12">

          <table id="table-id" class="table table-hover table-bordered">
            <thead>
            <tr>
              <th scope="col">Tên sản phẩm</th>
              <th scope="col">Số lượng bán trong tuần</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hotW}" begin="0" end="10" var="hotW">
              <tr id="${hotW.name}">
                <th scope="row">${hotW.name}</th>
                <td>${hotW.getAmmount()}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminWeb/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
  $("#table-id").DataTable();

  function removeP(id) {
    $.ajax({
              url: "/RemoveTypeAdmin",
              type: "get",
              data: {
                idP: id
              },
              success: function (data) {
                if (data === "") {
                  $("tr").remove("#" + id)
                } else {
                  $("#" + id).html(data)
                }
              }
            }
    )
  }
</script>
</body>
</html>
