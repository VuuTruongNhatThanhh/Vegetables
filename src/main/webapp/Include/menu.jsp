<%@ page import="vn.edu.hcmuaf.fit.model.TypeProduct" %>
<%@ page import="vn.edu.hcmuaf.fit.Dao.TypeProductDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="menu">
    <div class="menu-title">
        <i class="fa fa-bars"></i>
        <span>Danh mục sản phẩm</span>
    </div>
    <ul class="menu-drop">
        <li class="menu-item">
            <a href="/ListProduct">Tất cả</a>
        </li>
        <%for (TypeProduct parent : TypeProductDao.getInstance().getParentType()) { %>
        <li class="menu-item">
            <a class="menu-arrow" href="ListProduct?type=<%=parent.getId()%>"><%=parent.getName()%>
            </a>
            <ul class="sub-menu">
                <%for (TypeProduct child : TypeProductDao.getInstance().getChildType(parent.getId())) {%>
                <li class="menu-item"><a href="ListProduct?type=<%=child.getId()%>"><%=child.getName()%>
                </a></li>
                <%}%>
            </ul>
        </li>
        <%}%>
    </ul>
</div>
