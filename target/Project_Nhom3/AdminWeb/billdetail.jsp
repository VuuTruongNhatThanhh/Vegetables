<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <div class="title">Danh Sách sản phẩm trong đơn hàng</div>
      <div class="row">
        <div class="col-md-12">
          <div class="row element-button">
            <%--                        <div class="col-sm-2">--%>
            <%--                            <a class="btn btn-add btn-sm" href="/AddUserAdmin" title="Thêm">--%>
            <%--                                <i class="fas fa-plus"></i>--%>
            <%--                                Thêm tài khoản</a>--%>
            <%--                        </div>--%>
          </div>
          <table id="table1" class="table table-hover table-bordered">
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
        <p style="padding-left: 1000px; color: red">Tổng tiền: ${tp} VND</p>
          <p style="padding-left: 1000px; color: red">Phí ship: 15000 VND</p>
        </div>

      </div>
    </div>
  </div>
</section>
<script src="../bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminWeb/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="../bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script>
  $(document).ready(function () {
    $('#table1').DataTable();
  });

  function remove(id) {
    $.ajax({
              url: "/RemoveUserAdmin",
              type: "get",
              data: {
                idU: id
              },
              success: function () {
                $("tr").remove("#" + id)
              }
            }
    )
  }


</script>
</body>
</html>
