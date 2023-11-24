
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="vn.edu.hcmuaf.fit.Dao.KeyDao" %>
<%@ page import="vn.edu.hcmuaf.fit.model.User" %>
<html>
<head>
    <title>Khóa ký đơn hàng</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/userprofile.css" type="text/css">
</head>
<body>

<%@include file="Include/header.jsp" %>
<br>
<div class="tabtab">
    <div class="tab">
        <a href="UserProfile">
            <button class="button">Thông tin cá nhân</button>
        </a>
        <a href="OderStatus">
            <button class="button">Tình trạng đơn hàng</button>
        </a>
        <a href="changepassword.jsp">
            <button class="button">Đổi mật khẩu</button>
        </a>
        <a href="key.jsp">
            <button class="button">Khóa ký đơn hàng</button>
        </a>
    </div>
    <div class="tabcontent">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <h4>Khóa ký đơn hàng</h4>
                        <hr>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <form method="post" action="AddKey" accept-charset="UTF-8">
                            <%--                            <div class="form-group row">--%>
                            <%--                                <label class="col-4 col-form-label">Mật khẩu hiện tại</label>--%>
                            <%--                                <div class="col-8">--%>
                            <%--                                    <input name="current_pass"--%>
                            <%--                                           class="form-control here" required="required" type="password">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                            <div class="form-group row">--%>
                            <%--                                <label class="col-4 col-form-label">Mật khẩu mới</label>--%>
                            <%--                                <div class="col-8">--%>
                            <%--                                    <input name="new_pass" class="form-control here"--%>
                            <%--                                           type="password">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <div class="form-group row">
                                <%
                                    // Giả sử uu là một đối tượng User đã được khai báo và khởi tạo trước đó
                                    // Ví dụ: User uu = ...;
                                    User uu = (User) request.getSession().getAttribute("auth");

                                    // Kiểm tra điều kiện từ servlet
                                    if (KeyDao.getInstance().CheckCreateKey(uu.getId()) == 0) {
                                %>
                                <label class="col-4 col-form-label">Nhập public key của bạn</label>
                                <%
                                } else {
                                %>
                                <label  class="col-4 col-form-label"> Public key của bạn</label>

                                <%
                                    }
                                %>
                                <div class="col-8">
                                    <%
                                        // Giả sử uu là một đối tượng User đã được khai báo và khởi tạo trước đó
                                        // Ví dụ: User uu = ...;

                                        String publicKey = KeyDao.getInstance().getpublickey(uu.getId());
                                        // Kiểm tra điều kiện từ servlet
                                        if (KeyDao.getInstance().CheckCreateKey(uu.getId()) == 0) {
                                    %>
<%--                                    <input name="publickey"--%>
<%--                                           class="form-control here" type="text">--%>
                                    <textarea name="publickey" id="publickey" class="form-control here"></textarea>
                                    <%
                                    } else {
                                    %>

<%--                                    <input name="conf_pass"--%>
<%--                                           class="form-control here" type="text" value="<%= publicKey %>" readonly>--%>
                                    <textarea name="conf_pass"  class="form-control here"   readonly><%= publicKey %></textarea>

                                    <%
                                        }
                                    %>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class=" col-8">
                                    <%



                                        if (KeyDao.getInstance().CheckCreateKey(uu.getId()) == 0) {
                                    %>
                                    <button name="submit" type="submit" class="btn btn-black"
                                            style="background-color: var(--main-color); color: white; width: 137px; height: 39px; margin-left: 510px">
                                        <a style="color: white; text-decoration: none; " >Lưu</a>
                                    </button>
                                    <%
                                    } else {
                                    %>
                                    <button  class="btn btn-black"
                                            style="background-color: var(--main-color); color: white; width: 137px; height: 39px; margin-left: 510px">

                                        <a style="color: white; text-decoration: none; " href="<%= request.getContextPath() %>/LostKey">Báo mất khóa</a>
                                    </button>
                                    <%
                                        }

                                    %>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-8">
                                    <%

                                        if (KeyDao.getInstance().CheckCreateKey(uu.getId()) == 0) {
                                    %>
                                    <button class="btn btn-black"
                                            style="background-color: var(--main-color); color: white; width: 225px; height: 39px; margin-left: 477px"
                                            >
                                                       <a style="color: white; text-decoration: none; " href="<%= request.getContextPath() %>/GenerateKey">Chưa có khóa? Tạo ngay</a>

                                    </button>
                                    <%
                                        }

                                    %>

                                </div>
                            </div>
                        </form>

<%--                        <p  style="word-wrap: break-word;padding-left: 30px; color: red; font-weight: bold"><%= request.getAttribute("noti") %></p>--%>
                        <%
                            Object notiAttribute = request.getAttribute("noti");
                            if (notiAttribute != null && !notiAttribute.toString().isEmpty()) {
                        %>
                        <p style="word-wrap: break-word; padding-left: 30px; color: red; font-weight: bold">
                            <%= notiAttribute %>
                        </p>
                        <%
                            }
                        %>



                        <%
                            Object notiAttribute2 = request.getAttribute("err");
                            if (notiAttribute2 != null && !notiAttribute2.toString().isEmpty()) {
                        %>
                        <p style="word-wrap: break-word; padding-left: 30px; color: red; font-weight: bold">
                            <%= notiAttribute2 %>
                        </p>
                        <%
                            }
                        %>


                        <%
                            Object notiAttribute3 = request.getAttribute("noti2");
                            if (notiAttribute3 != null && !notiAttribute3.toString().isEmpty()) {
                        %>
                        <p style="word-wrap: break-word; padding-left: 30px; color: red; font-weight: bold">
                            <%= notiAttribute3 %>
                        </p>
                        <%
                            }
                        %>
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
</body>
</html>
