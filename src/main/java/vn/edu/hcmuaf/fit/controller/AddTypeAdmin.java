package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.model.TypeProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddTypeAdmin", value = "/AddTypeAdmin")
public class AddTypeAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = TypeProductDao.getInstance().getNewId();
        request.setAttribute("id", id);
//        request.setAttribute("types", types);
        request.setAttribute("action", "AddTypeAdmin");
        request.setAttribute("title", "Thêm sản phẩm");

        request.getRequestDispatcher("AdminWeb/addType.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_u = TypeProductDao.getInstance().getNewId();
        String name = request.getParameter("name");
        String typeFather = request.getParameter("typeFather");

        TypeProductDao.getInstance().addDB( name, typeFather);
        response.sendRedirect("/TypeAdmin");
    }
}
