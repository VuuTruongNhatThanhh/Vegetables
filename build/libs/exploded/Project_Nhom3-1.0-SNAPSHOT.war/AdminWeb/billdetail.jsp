<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="AdminWeb/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
  <link rel="stylesheet" href="AdminWeb/css/style.css">
  <link rel="stylesheet" href="AdminWeb/css/product.css">
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
              <c:choose>

                  <c:when test="${not empty hash and hash ne '' and verify}">
                      <p style="color: #82cd47"><strong>Hóa đơn hợp lệ, hóa đơn không bị thay đổi thông tin</strong></p>
                  </c:when>
                  <c:when test="${empty hash}">
                      <p style="color: blue"><strong>X:  Hóa đơn chưa được xác thực</strong></p>
                  </c:when>







                  <c:otherwise>
                      <p style="color: red"><strong>X:  Hóa đơn không hợp lệ, hóa đơn bị thay đổi thông tin</strong></p>

                  </c:otherwise>
              </c:choose>

              <p><strong>Tên người nhận:</strong> ${name}</p>
            <p><strong>Số điện thoại:</strong> ${phone}</p>
            <p><strong>Địa chỉ:</strong> ${address}, ${ward}, ${district}, ${province}</p>

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
<%--              <c:choose>--%>
<%--                <c:when test="${not empty bd.gethash(bd.idBill) and bd.verify(bd.idBill) and orderValidMessageDisplayed eq 'false'}">--%>
<%--                  <c:set var="orderValidMessageDisplayed" value="true" />--%>
<%--                  <p style="color: #82cd47"><strong>Đơn hàng hợp lệ, không bị thay đổi thông tin</strong></p>--%>
<%--                </c:when>--%>
<%--                <c:when test="${empty bd.gethash(bd.idBill) || bd.gethash(bd.idBill) eq '' and orderNotVerifiedMessageDisplayed eq 'false'}">--%>
<%--                  <c:set var="orderNotVerifiedMessageDisplayed" value="true" />--%>
<%--                  <p><strong>X: Đơn hàng chưa được xác thực</strong></p>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                  <c:choose>--%>
<%--                    <c:when test="${orderInvalidMessageDisplayed eq 'false'}">--%>
<%--                      <c:set var="orderInvalidMessageDisplayed" value="true" />--%>
<%--                      <p style="color: red"><strong>X: Đơn hàng không hợp lệ, đơn hàng bị thay đổi thông tin</strong></p>--%>
<%--                    </c:when>--%>
<%--                  </c:choose>--%>
<%--                </c:otherwise>--%>
<%--              </c:choose>--%>


              <tr  id="${bd.idP}">
                <th scope="row">${bd.getNameProduct()}</th>
                <td>${bd.getNameWeight()}g</td>
                <td>${bd.amount}</td>
                <td>${bd.total}</td>

              </tr>







            </c:forEach>
            </tbody>


          </table>
          <p><strong>Tổng tiền:</strong> ${tp} VND</p>
          <p><strong>Vận chuyển:</strong> ${fee} VND</p>
            <%
                // Lấy giá trị của tham số "id" từ URL
                String id = request.getParameter("id");

                // Bạn có thể sử dụng giá trị billId trong logic xử lý của trang
            %>
<c:if test="${status eq 0}">
            <c:choose>
                <c:when test="${not empty hash and hash ne '' and verify}">
                    <button onclick="confirm('<%= id %>')" class="btn btn-primary btn-sm tick"
                            type="button" title="check">
                        <i class="fa-solid fa-check"></i>
                    </button>

                    <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                            type="button" title="Xóa">
                        <i class="fas fa-trash-alt"></i>
                    </button>
                </c:when>
                <c:when test="${empty hash}">
                    <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                            type="button" title="Xóa">
                        <i class="fas fa-trash-alt"></i>
                    </button>
                </c:when>
                <c:otherwise>
                    <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                            type="button" title="Xóa">
                        <i class="fas fa-trash-alt"></i>
                    </button>
                </c:otherwise>
            </c:choose>
</c:if>

<c:if test="${status eq 1}">
    <c:choose>
        <c:when test="${not empty hash and hash ne '' and verify}">
            <button onclick="movetoship('<%= id %>')" class="btn btn-primary btn-sm tick"
                    type="button" title="Gửi cho bên giao hàng">
                <i class="fa-solid fa-truck"></i>
            </button>

            <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                    type="button" title="Xóa">
                <i class="fas fa-trash-alt"></i>
            </button>
        </c:when>
        <c:when test="${empty hash}">
            <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                    type="button" title="Xóa">
                <i class="fas fa-trash-alt"></i>
            </button>
        </c:when>
        <c:otherwise>
            <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                    type="button" title="Xóa">
                <i class="fas fa-trash-alt"></i>
            </button>
        </c:otherwise>
    </c:choose>
</c:if>


            <c:if test="${status ne 1 and status ne 0}">
                <c:choose>
                    <c:when test="${not empty hash and hash ne '' and verify}">

                        <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                                type="button" title="Xóa">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </c:when>
                    <c:when test="${empty hash}">
                        <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                                type="button" title="Xóa">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="remove('<%= id %>')" class="btn btn-primary btn-sm trash"
                                type="button" title="Xóa">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </c:otherwise>
                </c:choose>
            </c:if>

<%--          <p style="padding-left: 15px;">- Mã vận chuyển: ${idTrans}</p>--%>
<%--          <p style="padding-left: 15px;">- Ngày gửi cho bưu cục: ${createAt}</p>--%>
<%--          <p style="padding-left: 15px;">- Ngày giao dự kiến: ${leadtime}</p>--%>


        </div>

      </div>
    </div>
  </div>
</section>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminWeb/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script>
  $(document).ready(function () {
    $('#table1').DataTable();
  });

  function remove(id) {
      $.ajax({
              url: "RemoveBill",
              type: "get",
              data: {
                  id: id
              },
              success: function () {
                  $("tr").remove("#" + id)
                  window.location.href = "BillAdmin";
              }
          }
      )
  }

  function confirm(id) {
      $.ajax({
              url: "ConfirmBill",
              type: "get",
              data: {
                  id: id
              },
              success: function (data) {
                  $("tr").remove("#" + id)
                  $("#confirm").html(data);
                  window.location.href = "BillAdmin";
              }
          }
      )
  }

  function movetoship(id) {
      $.ajax({
              url: "ShipBill",
              type: "get",
              data: {
                  id: id
              },
              success: function (data) {
                  $("tr").remove("#" + id)
                  $("#movetoship").html(data);
                  window.location.href = "BillAdmin#tab2";
              }
          }
      )
  }


</script>
</body>
</html>
