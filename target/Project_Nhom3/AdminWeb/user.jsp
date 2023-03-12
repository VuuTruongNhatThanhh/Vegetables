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
            <div class="title">Danh Sách Tài Khoản</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-add btn-sm" href="/AddUserAdmin" title="Thêm">
                                <i class="fas fa-plus"></i>
                                Thêm tài khoản</a>
                        </div>
                    </div>
                    <table id="table1" class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Mã tài khoản</th>
                            <th scope="col">Tên người dùng</th>
                            <th scope="col">Email</th>
                            <th scope="col">Kích hoạt</th>
                            <th scope="col">Quyền</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${us}" var="u">
                        <tr id="${u.id}">
                            <th scope="row">${u.id}</th>
                            <td>${u.name}</td>
                            <td>${u.email}</td>
                            <td>${u.getNameActivate()}</td>
                            <td>${u.getNameRole()}</td>
                            <td>
                                <button onclick="remove('${u.id}')" class="btn btn-primary btn-sm trash" type="button"
                                        title="Xóa">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>

                    </table>
<%--                    <table id="table1">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th>Mã tài khoản</th>--%>
<%--                            <th>Tên người dùng</th>--%>
<%--                            <th>Email</th>--%>
<%--                            <th>Mật khẩu</th>--%>
<%--                                                        <th scope="col">Quyền</th>--%>
<%--                                                        <th scope="col"></th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                        <tr>--%>
<%--                            <td>Ram</td>--%>
<%--                            <td>21</td>--%>
<%--                            <td>Male</td>--%>
<%--                            <td>Doctor</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Mohan</td>--%>
<%--                            <td>32</td>--%>
<%--                            <td>Male</td>--%>
<%--                            <td>Teacher</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Rani</td>--%>
<%--                            <td>42</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Nurse</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Johan</td>--%>
<%--                            <td>23</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Software Engineer</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Shajia</td>--%>
<%--                            <td>39</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Farmer</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Jack</td>--%>
<%--                            <td>19</td>--%>
<%--                            <td>Male</td>--%>
<%--                            <td>Student</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Hina</td>--%>
<%--                            <td>30</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Artist</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Gauhar</td>--%>
<%--                            <td>36</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Artist</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Jacky</td>--%>
<%--                            <td>55</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Bank Manager</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Pintu</td>--%>
<%--                            <td>36</td>--%>
<%--                            <td>Male</td>--%>
<%--                            <td>Clerk</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Sumit</td>--%>
<%--                            <td>33</td>--%>
<%--                            <td>Male</td>--%>
<%--                            <td>Footballer</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Radhu</td>--%>
<%--                            <td>40</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Coder</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Mamta</td>--%>
<%--                            <td>49</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Student</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Priya</td>--%>
<%--                            <td>36</td>--%>
<%--                            <td>Female</td>--%>
<%--                            <td>Worker</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>Johnny</td>--%>
<%--                            <td>41</td>--%>
<%--                            <td>Male</td>--%>
<%--                            <td>Forest Officer</td>--%>
<%--                        </tr>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>
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
