<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar">
    <div class="logo-details">
        <a><img src="../../images/logo.png"></a>
    </div>
    <ul class="nav-links">
        <li>
            <a href="/StatisticalAdmin">
                <i class="fa-solid fa-border-all"></i>
                <span class="links_name">Trang chủ</span>
            </a>
        </li>
        <li>
            <a href="/LoadControl">
                <i class="fa-solid fa-shopping-basket"></i>
                <span class="links_name">Đến trang khách hàng</span>
            </a>
        </li>
        <li>
            <a href="/ProductAdmin">
                <i class="fa-solid fa-carrot"></i>
                <span class="links_name">Quản Lý Sản Phẩm</span>
            </a>
        </li>
        <li>
            <a href="/TypeAdmin">
                <i class="fa-solid fa-box-archive"></i>
                <span class="links_name">Quản Lý Loại Sản Phẩm</span>
            </a>
        </li>
        <li>
            <a href="/BillAdmin">
                <i class="fa-solid fa-note-sticky"></i>
                <span class="links_name">Quản Lý Đơn Hàng</span>
            </a>
        </li>
        <li>
            <a href="/UserAdmin">
                <i class="fa-solid fa-user"></i>
                <span class="links_name">Quản Lý Tài Khoản</span>
            </a>
        </li>
        <li>
            <a href="/LevelUpUser">
                <i class="fa-solid fa-level-up"></i>
                <span class="links_name">Thăng/ hạ quyền tài khoản</span>
            </a>
        </li>

        <li>

            <a href="/LogAdmin">
                <i class="fa-solid fa-clock-rotate-left"></i>
                <span class="links_name">Quản Lý Log</span>
            </a>
        </li>

        <li>
            <a href="/ReviewAdmin">
                <i class="fa-solid fa-comments"></i>
                <span class="links_name">Quản Lý Bình Luận</span>
            </a>
        </li>
        <li>
            <a href="/PreferentialAdmin">
                <i class="fa-solid fa-gift"></i>
                <span class="links_name">Quản Lý Ưu Đãi</span>
            </a>
        </li>
<%--        <li>--%>
<%--            <a href="/LogAdmin">--%>
<%--                <i class="fa-solid fa-newspaper"></i>--%>
<%--                <span class="links_name">Quản Lý Tin Tức</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="/LogAdmin">--%>
<%--                <i class="fa-solid fa-phone"></i>--%>
<%--                <span class="links_name">Quản Lý Liên Hệ</span>--%>
<%--            </a>--%>
<%--        </li>--%>
        <li class="${pageContext.request.contextPath}/AdminWeb/log_out">
            <a href="/Logout">
                <i class="fa-solid fa-right-from-bracket"></i>
                <span class="links_name">Đăng xuất</span>
            </a>
        </li>
    </ul>
</div>


